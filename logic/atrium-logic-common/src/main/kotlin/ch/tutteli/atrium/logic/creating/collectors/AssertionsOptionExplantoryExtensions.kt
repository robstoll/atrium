package ch.tutteli.atrium.logic.creating.collectors

import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.assertions.ExplanatoryAssertionGroupType
import ch.tutteli.atrium.assertions.builders.AssertionsOption
import ch.tutteli.atrium.core.Option
import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.collectForCompositionBasedOnSubject

/**
 * Collects the assertions [assertionCreator] creates and uses them as [AssertionGroup.assertions].
 *
 * //TODO 0.18.0 in case we somehow incorporate the current container in AssertionsOptions, then remove container as parameter
 */
fun <T, G : ExplanatoryAssertionGroupType, R> AssertionsOption<G, R>.collectAssertions(
    container: AssertionContainer<*>,
    maybeSubject: Option<T>,
    assertionCreator: Expect<T>.() -> Unit
): R = withAssertions(container.collectForCompositionBasedOnSubject(maybeSubject, assertionCreator))
