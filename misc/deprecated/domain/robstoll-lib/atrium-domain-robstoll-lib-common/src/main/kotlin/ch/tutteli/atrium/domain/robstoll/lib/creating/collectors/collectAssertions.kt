package ch.tutteli.atrium.domain.robstoll.lib.creating.collectors

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.assertions.builders.assertionBuilder
import ch.tutteli.atrium.assertions.builders.invisibleGroup
import ch.tutteli.atrium.core.ExperimentalNewExpectTypes
import ch.tutteli.atrium.core.Option
import ch.tutteli.atrium.creating.CollectingExpect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.creating.ExperimentalComponentFactoryContainer
import ch.tutteli.atrium.creating.impl.DefaultComponentFactoryContainer
import ch.tutteli.atrium.reporting.Text
import ch.tutteli.atrium.translations.DescriptionBasic

fun <T> _collect(
    maybeSubject: Option<T>,
    assertionCreator: Expect<T>.() -> Unit
): Assertion {
    @Suppress("DEPRECATION" /* OptIn is only available since 1.3.70 which we cannot use if we want to support 1.2 */)
    @UseExperimental(ExperimentalNewExpectTypes::class, ExperimentalComponentFactoryContainer::class)
    //TODO 0.16.0 don't use DefaultComponentFactoryContainer but the one from the container
    val collectedAssertions = CollectingExpect(maybeSubject, DefaultComponentFactoryContainer)
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
): List<Assertion> = collectAssertions(maybeSubject, assertionCreatorOrNull)

private fun <T> collectAssertions(
    maybeSubject: Option<T>,
    assertionCreatorOrNull: (Expect<T>.() -> Unit)?
): List<Assertion> {
    return if (assertionCreatorOrNull != null) {
        @Suppress("DEPRECATION" /* OptIn is only available since 1.3.70 which we cannot use if we want to support 1.2 */)
        @UseExperimental(ExperimentalNewExpectTypes::class, ExperimentalComponentFactoryContainer::class)
        CollectingExpect(maybeSubject, DefaultComponentFactoryContainer)
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
