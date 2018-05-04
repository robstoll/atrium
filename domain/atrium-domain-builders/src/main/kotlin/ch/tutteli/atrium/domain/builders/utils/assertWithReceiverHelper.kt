package ch.tutteli.atrium.domain.builders.utils

import ch.tutteli.atrium.creating.Assert

/**
 * Helper function to circumvent Kotlin inference issues involving lambdas such as:
 * - https://youtrack.jetbrains.com/issue/KT-24230
 * - https://youtrack.jetbrains.com/issue/KT-23883
 * - and probably more
 *
 * @param assertionCreator Your assertion creator lambda
 * @return your passed lambda.
 */
fun <T : Any> subAssert(assertionCreator: Assert<T>.() -> Unit) = assertionCreator
