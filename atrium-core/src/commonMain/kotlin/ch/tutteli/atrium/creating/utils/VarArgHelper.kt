package ch.tutteli.atrium.creating.utils

import ch.tutteli.kbox.glue
import ch.tutteli.kbox.mapVararg

/**
 * Represents a parameter object used to express the arguments `T, vararg T`
 * and provides utility functions to transform them.
 *
 * @since 1.3.0
 */
interface VarArgHelper<out T> {
    /**
     * The first argument in the argument list `T, vararg T`
     *
     * @since 1.3.0
     */
    val expected: T

    /**
     * The second argument in the argument list `T, vararg T`
     *
     * @since 1.3.0
     */
    val otherExpected: Array<out T>

    /**
     * Returns the arguments as [List].
     *
     * @since 1.3.0
     */
    fun toList(): List<T> = expected glue otherExpected
}

/**
 * Maps [VarArgHelper.expected] and all elements in [VarArgHelper.otherExpected] via the given [transform] function.
 *
 * @since 1.3.0
 */
inline fun <T, reified R> VarArgHelper<T>.mapArguments(transform: (T) -> R): Pair<R, Array<out R>> =
    mapVararg(expected, otherExpected, transform)
