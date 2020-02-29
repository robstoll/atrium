package ch.tutteli.atrium.domain.builders.utils

import ch.tutteli.kbox.glue

/**
 * Represents a parameter object used to express the arguments `T, vararg T`
 * and provides utility functions to transform them.
 */
interface VarArgHelper<out T> {
    /**
     * The first argument in the argument list `T, vararg T`
     */
    val expected: T
    /**
     * The second argument in the argument list `T, vararg T`
     */
    val otherExpected: Array<out T>

    /**
     * Creates an [ArgumentMapperBuilder] which allows to map [expected] and [otherExpected].
     */
    val mapArguments: ArgumentMapperBuilder<T> get() = ArgumentMapperBuilder(expected, otherExpected)

    /**
     * Returns the arguments as [List].
     */
    fun toList(): List<T> = expected glue otherExpected
}
