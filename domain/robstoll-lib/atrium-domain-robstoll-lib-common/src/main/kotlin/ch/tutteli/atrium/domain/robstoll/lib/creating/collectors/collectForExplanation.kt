package ch.tutteli.atrium.domain.robstoll.lib.creating.collectors

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.assertions.builders.withExplanatoryAssertion
import ch.tutteli.atrium.core.Option
import ch.tutteli.atrium.core.coreFactory
import ch.tutteli.atrium.creating.CollectingAssertionContainer
import ch.tutteli.atrium.creating.PlantHasNoSubjectException
import ch.tutteli.atrium.domain.builders.AssertImpl
import ch.tutteli.atrium.reporting.BUG_REPORT_URL
import ch.tutteli.atrium.reporting.RawString
import ch.tutteli.atrium.reporting.translating.Untranslatable
import ch.tutteli.atrium.translations.DescriptionBasic

fun <T> _collectAndThrowIfNothingCollected(
    throwIfNoAssertionIsCollected: Boolean,
    maybeSubject: Option<T>,
    assertionCreator: (CollectingAssertionContainer<T>.() -> Unit)?
): List<Assertion> {
    return try {
        val collectedAssertions = collectAssertions(maybeSubject, assertionCreator)

        check(!(throwIfNoAssertionIsCollected && collectedAssertions.isEmpty())) {
            "There was not any assertion created. Specify at least one assertion"
        }

        //TODO #88 once subject is removed we should no longer get a PlantHasNoSubjectException
        // then there is also no need to evaluate lazy assertions

        // since assertions can be lazily computed we have to provoke their creation here,
        // so that a potential PlantHasNoSubjectException is thrown. It's fine to provoke the computation
        // because we require the assertions for the explanation anyway.
        expandAssertionGroups(collectedAssertions)

        collectedAssertions
    } catch (e: PlantHasNoSubjectException) {
        listOf(
            AssertImpl.builder.explanatoryGroup
                .withWarningType
                .withExplanatoryAssertion(Untranslatable("Could not evaluate sub-assertions, subject was accessed too early, please report a bug at $BUG_REPORT_URL"))
                .build()
        )
    }
}

private fun <T> collectAssertions(
    maybeSubject: Option<T>,
    assertionCreator: (CollectingAssertionContainer<T>.() -> Unit)?
): List<Assertion> {
    //TODO almost same as in _containsKeyWithNullableValueAssertions
    return if (assertionCreator != null) {
        val collectingAssertionPlant = coreFactory.newCollectingAssertionContainer(maybeSubject)
        collectingAssertionPlant.assertionCreator()
        collectingAssertionPlant.getAssertions()
    } else {
        listOf(AssertImpl.builder.createDescriptive(DescriptionBasic.IS, RawString.NULL) {
            maybeSubject.isDefined()
        })
    }
}

/**
 * Calls recursively [AssertionGroup.assertions] on every assertion group contained in [assertions].
 */
internal tailrec fun expandAssertionGroups(assertions: List<Assertion>) {
    if (assertions.isEmpty()) return

    expandAssertionGroups(
        assertions
            .asSequence()
            .filterIsInstance<AssertionGroup>()
            .flatMap { it.assertions.asSequence() }
            .toList()
    )
}
