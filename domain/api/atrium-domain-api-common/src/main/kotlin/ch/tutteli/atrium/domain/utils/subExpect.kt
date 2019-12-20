package ch.tutteli.atrium.domain.utils

import ch.tutteli.atrium.creating.Expect

/**
 * Helper function to create an [Expect] lambda with receiver;
 * helps to circumvent Kotlin type inference bugs involving lambdas.
 *
 * Following a few examples of bugs (you might want to vote if you encounter the same):
 * - https://youtrack.jetbrains.com/issue/KT-24230
 * - https://youtrack.jetbrains.com/issue/KT-23883
 * - and probably more
 *
 * @param assertionCreator Your assertion creator lambda.
 * @return your passed lambda.
 */
@Suppress("NOTHING_TO_INLINE")
inline fun <T> subExpect(noinline assertionCreator: Expect<T>.() -> Unit) = assertionCreator
