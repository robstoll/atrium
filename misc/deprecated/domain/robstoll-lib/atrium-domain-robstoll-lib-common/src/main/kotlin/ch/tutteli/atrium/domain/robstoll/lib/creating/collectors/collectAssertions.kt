package ch.tutteli.atrium.domain.robstoll.lib.creating.collectors

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.assertions.builders.assertionBuilder
import ch.tutteli.atrium.assertions.builders.invisibleGroup
import ch.tutteli.atrium.assertions.builders.withExplanatoryAssertion
import ch.tutteli.atrium.core.Option
import ch.tutteli.atrium.creating.CollectingExpect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.reporting.Text
import ch.tutteli.atrium.translations.DescriptionBasic
import ch.tutteli.atrium.translations.ErrorMessages

fun <T> _collect(
    maybeSubject: Option<T>,
    assertionCreator: Expect<T>.() -> Unit
): Assertion {
    val collectedAssertions = CollectingExpect(maybeSubject)
        .addAssertionsCreatedBy(assertionCreator)
        .getAssertions()
    return if (collectedAssertions.size > 1) {
        assertionBuilder.invisibleGroup.withAssertions(collectedAssertions).build()
    } else {
        collectedAssertions[0]
    }
}

fun <T> _collectForComposition(
    maybeSubject: Option<T>,
    assertionCreatorOrNull: (Expect<T>.() -> Unit)?
): List<Assertion>  = collectAssertions(maybeSubject, assertionCreatorOrNull)

//TODO 0.16.0: better replace by silentToBeNullIfNullGiven?
private fun <T> collectAssertions(
    maybeSubject: Option<T>,
    assertionCreatorOrNull: (Expect<T>.() -> Unit)?
): List<Assertion> {
    //TODO 0.16.0: almost same as in _containsKeyWithNullableValueAssertions
    return if (assertionCreatorOrNull != null) {
        CollectingExpect(maybeSubject)
            .addAssertionsCreatedBy(assertionCreatorOrNull)
            .getAssertions()
    } else {
        listOf(assertionBuilder.createDescriptive(DescriptionBasic.IS, Text.NULL) {
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
