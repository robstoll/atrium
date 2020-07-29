package ch.tutteli.atrium.domain.builders.utils

import ch.tutteli.atrium.domain.creating.typeutils.IterableLike
import ch.tutteli.kbox.glue

/**
 * Represents a parameter object used to express the arguments `T, vararg T`
 * and provides utility functions to transform them.
 */
//TODO copy/move to atrium-logic with 0.14.0
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

/**
 * Transforms the given [IterableLike] to `Pair<T, Array<out T>>` with the intend that it can be easily used for a function
 * requiring `T, vararg T`
 *
 * @throws IllegalArgumentException in case the iterable is empty.
 */
inline fun <reified T> toVarArg(iterableLike: IterableLike): Pair<T, Array<out T>> =
    iterableToPair(iterableLikeToIterable(iterableLike))

inline fun <reified T> iterableLikeToIterable(iterableLike: IterableLike): Iterable<T> =
    when (iterableLike) {
        is Sequence<*> -> iterableLike.map { it as T }.asIterable()
        is Iterable<*> -> iterableLike.map { it as T }
        is Array<*> -> iterableLike.map { it as T }
        is CharArray -> iterableLike.map { it as T }
        is ByteArray -> iterableLike.map { it as T }
        is ShortArray -> iterableLike.map { it as T }
        is IntArray -> iterableLike.map { it as T }
        is LongArray -> iterableLike.map { it as T }
        is FloatArray -> iterableLike.map { it as T }
        is DoubleArray -> iterableLike.map { it as T }
        is BooleanArray -> iterableLike.map { it as T }
        else -> throw IllegalArgumentException("toVarArg accepts arguments of types Iterable, Sequence, Array")
    }

inline fun <reified T> iterableToPair(iterable: Iterable<T>): Pair<T, Array<out T>> {
    require(iterable.iterator().hasNext()) { "Iterable without elements are not allowed for this function." }
    return iterable.first() to iterable.drop(1).toTypedArray()
}
