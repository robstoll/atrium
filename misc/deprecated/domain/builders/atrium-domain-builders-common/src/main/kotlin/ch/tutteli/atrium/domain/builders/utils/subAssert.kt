@file:Suppress(
/* TODO remove annotation with 1.0.0 */ "DEPRECATION",
/* TODO remove annotation with 1.0.0 */ "TYPEALIAS_EXPANSION_DEPRECATION"
)

package ch.tutteli.atrium.domain.builders.utils

import ch.tutteli.atrium.creating.Expect

@Suppress("NOTHING_TO_INLINE")
@Deprecated(
    "Use expectLambda from atrium-logic; will be removed with 1.0.0",
    ReplaceWith("expectLambda(assertionCreator)", "ch.tutteli.atrium.logic.utils.expectLambda")
)
inline fun <T> subExpect(noinline assertionCreator: Expect<T>.() -> Unit) = assertionCreator
