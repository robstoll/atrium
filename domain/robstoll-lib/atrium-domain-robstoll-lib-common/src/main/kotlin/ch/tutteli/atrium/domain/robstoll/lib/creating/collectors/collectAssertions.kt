package ch.tutteli.atrium.domain.robstoll.lib.creating.collectors

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.assertions.builders.invisibleGroup
import ch.tutteli.atrium.assertions.builders.withExplanatoryAssertion
import ch.tutteli.atrium.core.Option
import ch.tutteli.atrium.core.coreFactory
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.builders.AssertImpl
import ch.tutteli.atrium.reporting.RawString
import ch.tutteli.atrium.translations.DescriptionBasic
import ch.tutteli.atrium.translations.ErrorMessages

fun <T> _collect(
    maybeSubject: Option<T>,
    assertionCreator: Expect<T>.() -> Unit
): Assertion {
    val collectedAssertions = AssertImpl.coreFactory.newCollectingAssertionContainer(maybeSubject)
        .addAssertionsCreatedBy(assertionCreator)
        .getAssertions()
    return if (collectedAssertions.size > 1) {
        AssertImpl.builder.invisibleGroup.withAssertions(collectedAssertions).build()
    } else {
        collectedAssertions[0]
    }
}


fun <T> _collectForComposition(
    maybeSubject: Option<T>,
    assertionCreatorOrNull: (Expect<T>.() -> Unit)?
): List<Assertion> {
    //TODO remove try-catch with 1.0.0 should no longer be needed once PlantHasNoSubjectException is removed
    return try {
        val collectedAssertions = collectAssertions(maybeSubject, assertionCreatorOrNull)

        //TODO remove with 1.0.0
        // Required as we support mixing Expect with Assert.
        // And since assertions can be lazily computed we have to provoke their creation here,
        // so that a potential PlantHasNoSubjectException is thrown. It's fine to provoke the computation
        // because we require the assertions for the explanation anyway.
        expandAssertionGroups(collectedAssertions)

        collectedAssertions
    } catch (@Suppress("DEPRECATION") e: ch.tutteli.atrium.creating.PlantHasNoSubjectException) {
        @Suppress("DEPRECATION")
        listOf(
            AssertImpl.builder.explanatoryGroup
                .withWarningType
                .withExplanatoryAssertion(ErrorMessages.SUBJECT_ACCESSED_TOO_EARLY)
                .build()
        )
    }
}

private fun <T> collectAssertions(
    maybeSubject: Option<T>,
    assertionCreatorOrNull: (Expect<T>.() -> Unit)?
): List<Assertion> {
    //TODO almost same as in _containsKeyWithNullableValueAssertions
    return if (assertionCreatorOrNull != null) {
        coreFactory.newCollectingAssertionContainer(maybeSubject)
            .addAssertionsCreatedBy(assertionCreatorOrNull)
            .getAssertions()
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
