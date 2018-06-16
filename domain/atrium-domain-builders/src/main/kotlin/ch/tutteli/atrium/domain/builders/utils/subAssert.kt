package ch.tutteli.atrium.domain.builders.utils

import ch.tutteli.atrium.creating.AssertionPlant

/**
 * Helper function to circumvent Kotlin inference issues involving lambdas such as:
 * - https://youtrack.jetbrains.com/issue/KT-24230
 * - https://youtrack.jetbrains.com/issue/KT-23883
 * - and probably more
 *
 * @param assertionCreator Your assertion creator lambda
 * @return your passed lambda.
 */
@Suppress("NOTHING_TO_INLINE")
inline fun <T : Any> subAssert(noinline assertionCreator: AssertionPlant<T>.() -> Unit) = assertionCreator
