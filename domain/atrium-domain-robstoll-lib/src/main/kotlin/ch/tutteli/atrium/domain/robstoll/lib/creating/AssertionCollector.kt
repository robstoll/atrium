package ch.tutteli.atrium.domain.robstoll.lib.creating

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.assertions.ExplanatoryAssertionGroupType
import ch.tutteli.atrium.core.coreFactory
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.creating.CollectingAssertionPlant
import ch.tutteli.atrium.creating.PlantHasNoSubjectException
import ch.tutteli.atrium.domain.builders.creating.AssertImpl
import ch.tutteli.atrium.reporting.RawString
import ch.tutteli.atrium.reporting.translating.Translatable
import ch.tutteli.atrium.translations.DescriptionBasic

object AssertionCollector {

    /**
     * Choosing this option causes the [AssertionCollector] to throw an [IllegalArgumentException] in case not a single
     * [Assertion] was collected.
     *
     * Use [doNotThrowIfNoAssertionIsCollected] if such use cases should be ignored (no exception should be thrown).
     */
    val throwIfNoAssertionIsCollected: Collector = Collector(true)
    /**
     * Choosing this option will ignore use cases where not a single [Assertion] was collected.
     *
     * Use [throwIfNoAssertionIsCollected] if you want that [AssertionCollector] throws an
     * [IllegalArgumentException] in such cases.
     */
    val doNotThrowIfNoAssertionIsCollected: Collector = Collector(false)


    class Collector(private val throwIfNoAssertionIsCollected: Boolean) {

        /**
         * Collects the [Assertion] created by [assertionCreator] and uses the given [subject] as
         * [CollectingAssertionPlant.subject] if not null.
         *
         * In case [subject] is null then a [PlantHasNoSubjectException] is thrown in case the
         * subject is accessed (which does not need to be the case all the time). In such a case a single
         * [AssertionGroup] with an [ExplanatoryAssertionGroupType] is returned containing a warning.
         *
         * @param warning The translatable used to explain why the assertions could not be evaluated.
         * @param assertionCreator The function which should at least create one assertion.
         * @param subject The subject which will be used for the [AssertionPlant].
         *
         * @return A list with the collected assertion or an [AssertionGroup] with an [ExplanatoryAssertionGroupType]
         *   containing a warning if [subject] is `null` and an assertion function tries to access it.
         *
         * @throws IllegalArgumentException Might throw an [IllegalArgumentException] in case the [assertionCreator]
         *   function does not even create one [Assertion] -- depending on the previously chosen option (see
         *   [throwIfNoAssertionIsCollected] and [doNotThrowIfNoAssertionIsCollected]).
         */
        fun <E : Any> collectAssertionsForExplanation(warning: Translatable, assertionCreator: (AssertionPlant<E>.() -> Unit)?, subject: E?): List<Assertion> {
            return try {
                val collectedAssertions = collect(assertionCreator, subject)

                require(!(throwIfNoAssertionIsCollected && collectedAssertions.isEmpty())) {
                    "There was not any assertion created which could identify an entry. Specify at least one assertion"
                }

                // since assertions can be lazily computed we have to provoke their creation here,
                // so that a potential PlantHasNoSubjectException is thrown. It's fine to provoke the computation
                // because we require the assertions for the explanation anyway.
                expandAssertionGroups(collectedAssertions)

                collectedAssertions
            } catch (e: PlantHasNoSubjectException) {
                listOf(AssertImpl.builder.explanatoryGroup.withWarning.createWithExplanatoryAssertion(warning))
            }
        }

        private fun <E : Any> collect(assertionCreator: (AssertionPlant<E>.() -> Unit)?, subject: E?): List<Assertion> {
            val collectingAssertionPlant = createPlant(subject)
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
            if(assertions.isEmpty()) return

            expandAssertionGroups(assertions
                .asSequence()
                .filterIsInstance<AssertionGroup>()
                .flatMap { it.assertions.asSequence() }
                .toList()
            )
        }
    }
}
