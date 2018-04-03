package ch.tutteli.atrium.domain.robstoll.lib.creating.collectors

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.core.coreFactory
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.creating.CollectingAssertionPlant
import ch.tutteli.atrium.creating.PlantHasNoSubjectException
import ch.tutteli.atrium.domain.builders.AssertImpl
import ch.tutteli.atrium.reporting.RawString
import ch.tutteli.atrium.reporting.translating.Translatable
import ch.tutteli.atrium.translations.DescriptionBasic

class AssertionCollectorForExplanation(private val throwIfNoAssertionIsCollected: Boolean) {

    fun <E : Any> collect(
        warning: Translatable,
        assertionCreator: (AssertionPlant<E>.() -> Unit)?,
        subject: E?
    ): List<Assertion> {
        return try {
            val collectedAssertions = collect(assertionCreator, subject)

            require(!(throwIfNoAssertionIsCollected && collectedAssertions.isEmpty())) {
                "There was not any assertion created which could identify an entry. Specify at least one assertion"
            }

            // since assertions can be lazily computed we have to provoke their creation here,
            // so that a potential PlantHasNoSubjectException is thrown. It's fine to provoke the computation
            // because we require the assertions for the explanation anyway.
            expandAssertionGroups(
                collectedAssertions
            )

            collectedAssertions
        } catch (e: PlantHasNoSubjectException) {
            listOf(AssertImpl.builder.explanatoryGroup.withWarning.createWithExplanatoryAssertion(warning))
        }
    }

    private fun <E : Any> collect(assertionCreator: (AssertionPlant<E>.() -> Unit)?, subject: E?): List<Assertion> {
        val collectingAssertionPlant =
            createPlant(
                subject
            )
        if (assertionCreator != null) {
            collectingAssertionPlant.addAssertionsCreatedBy(assertionCreator)
        } else {
            collectingAssertionPlant.createAndAddAssertion(DescriptionBasic.IS, RawString.NULL, { subject == null })
        }
        return collectingAssertionPlant.getAssertions()
    }

    private fun <E : Any> createPlant(subject: E?): CollectingAssertionPlant<E> {
        return coreFactory.newCollectingPlant {
            subject ?: throw PlantHasNoSubjectException()
        }
    }

    /**
     * Calls recursively [AssertionGroup.assertions] on every assertion group contained in [assertions].
     */
    private tailrec fun expandAssertionGroups(assertions: List<Assertion>) {
        if (assertions.isEmpty()) return

        expandAssertionGroups(
            assertions
                .asSequence()
                .filterIsInstance<AssertionGroup>()
                .flatMap { it.assertions.asSequence() }
                .toList()
        )
    }
}
