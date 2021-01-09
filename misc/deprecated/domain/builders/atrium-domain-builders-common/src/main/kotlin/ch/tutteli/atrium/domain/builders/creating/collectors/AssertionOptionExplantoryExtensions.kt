//TODO remove file with 0.17.0
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.domain.builders.creating.collectors

import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.assertions.ExplanatoryAssertionGroupType
import ch.tutteli.atrium.assertions.builders.AssertionsOption
import ch.tutteli.atrium.core.Option
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.creating.collectors.assertionCollector

/**
 * Collects the assertions [assertionCreator] creates and uses them as [AssertionGroup.assertions].
 */
@Deprecated(
    "Use collectAssertions from atrium-logic which requires to pass an instance of an AssertionContainer; will be removed with 0.17.0",
    ReplaceWith(
        "collectAssertions(container, maybeSubject, assertionCreator)",
        "ch.tutteli.atrium.logic.creating.collectors.collectAssertions"
    )
)
fun <T, G : ExplanatoryAssertionGroupType, R> AssertionsOption<G, R>.collectAssertions(
    maybeSubject: Option<T>,
    assertionCreator: Expect<T>.() -> Unit
): R = withAssertions(assertionCollector.collectForComposition(maybeSubject, assertionCreator))
