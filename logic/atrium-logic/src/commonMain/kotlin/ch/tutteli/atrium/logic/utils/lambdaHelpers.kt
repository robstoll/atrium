package ch.tutteli.atrium.logic.utils

import ch.tutteli.atrium.creating.Expect

/**
 * Can be used in places where a
 * [lambda with receiver](https://kotlinlang.org/docs/reference/lambdas.html#function-literals-with-receiver)
 * is required where the receiver is of type [Expect]`<T>`.
 */
@Suppress("NOTHING_TO_INLINE")
inline fun <T> expectLambda(noinline assertionCreator: Expect<T>.() -> Unit) = assertionCreator
