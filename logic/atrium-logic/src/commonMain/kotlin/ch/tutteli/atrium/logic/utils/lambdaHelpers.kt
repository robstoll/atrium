package ch.tutteli.atrium.logic.utils

import ch.tutteli.atrium.creating.Expect

/**
 * Can be used in places where a
 * [lambda with receiver](https://kotlinlang.org/docs/reference/lambdas.html#function-literals-with-receiver)
 * is required where the receiver is of type [Expect]`<T>`.
 */
@Suppress("NOTHING_TO_INLINE")
@Deprecated(
    "Replace with expectationCreator from atrium-core, atrium-logic will be removed with 2.0.0 at the latest",
    ReplaceWith("expectationCreator(assertionCreator)", "ch.tutteli.atrium.creating.expectationCreator")
)
inline fun <T> expectLambda(noinline assertionCreator: Expect<T>.() -> Unit) = assertionCreator
