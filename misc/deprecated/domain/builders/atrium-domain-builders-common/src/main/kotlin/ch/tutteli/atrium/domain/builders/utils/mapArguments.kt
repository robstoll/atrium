@file:Suppress(
/* TODO remove annotation with 0.17.0 */ "DEPRECATION",
/* TODO remove annotation with 0.17.0 */ "TYPEALIAS_EXPANSION_DEPRECATION"
)

package ch.tutteli.atrium.domain.builders.utils

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.creating.PleaseUseReplacementException
import kotlin.js.JsName

/**
 * Creates a [ArgumentMapperBuilder] with the given [first] and [others] as arguments;
 * specify the mapping as such using a subsequent step in the building process.
 *
 * For instance (there are more options as second step to choose from):
 * ```
 * fun <K, V> pairs(pair: Pair<K, V>, vararg otherPairs: Pair<K, V>) {
 *   mapArguments(pair, otherPairs).toExpect<Map.Entry<K, V>> { isKeyValue(it) }
 * }
 * ```
 */
@Deprecated(
    "Use mapArguments from atrium-logic; will be removed with 0.17.0",
    ReplaceWith("mapArguments(first, others)", "ch.tutteli.atrium.logic.utils.mapArguments")
)
fun <T> mapArguments(first: T, others: Array<out T>): ArgumentMapperBuilder<T> = ArgumentMapperBuilder(first, others)

/**
 * Maps the given [first] and [others] to the given type [R] with the help of the given [mapper].
 *
 * Use the overload without `mapper` in case you want to map to an [Assert][AssertionPlant] lambda with receiver
 * and use one of the available options as second step. For instance, `mapArguments(a, aX).toExpect<String> { ... }`
 */
@Deprecated(
    "Use mapArguments from atrium-logic; will be removed with 0.17.0",
    ReplaceWith("ch.tutteli.atrium.logic.utils.mapArguments(first, others, mapper)")
)
inline fun <T, reified R> mapArguments(first: T, others: Array<out T>, mapper: (T) -> R): Pair<R, Array<out R>> =
    mapArguments(first, others).to(mapper)

/**
 * Creates a [ArgumentMapperBuilder] with the given [first] and [others] as arguments;
 * specify the mapping as such using a subsequent step in the building process.
 */
@Deprecated(
    "Use mapArguments from atrium-logic; will be removed with 0.17.0",
    ReplaceWith("ch.tutteli.atrium.logic.utils.mapArguments(first, others)")
)
fun mapArguments(first: Byte, others: ByteArray): ArgumentMapperBuilder<Byte> =
    mapArguments(first, others.toTypedArray())

/**
 * Maps the given [first] and [others] to the given type [R] with the help of the given [mapper].
 *
 * Use the overload without `mapper` in case you want to map to an [Assert][AssertionPlant] lambda with receiver
 * and use one of the available options as second step. For instance, `mapArguments(a, aX).toExpect<String> { ... }`
 */
@Deprecated(
    "Use mapArguments from atrium-logic; will be removed with 0.17.0",
    ReplaceWith("ch.tutteli.atrium.logic.utils.mapArguments(first, others, mapper)")
)
inline fun <reified R> mapArguments(first: Byte, others: ByteArray, mapper: (Byte) -> R): Pair<R, Array<out R>> =
    mapArguments(first, others).to(mapper)

/**
 * Creates a [ArgumentMapperBuilder] with the given [first] and [others] as arguments;
 * specify the mapping as such using a subsequent step in the building process.
 */
@Deprecated(
    "Use mapArguments from atrium-logic; will be removed with 0.17.0",
    ReplaceWith("ch.tutteli.atrium.logic.utils.mapArguments(first, others)")
)
fun mapArguments(first: Char, others: CharArray): ArgumentMapperBuilder<Char> =
    mapArguments(first, others.toTypedArray())

/**
 * Maps the given [first] and [others] to the given type [R] with the help of the given [mapper].
 *
 * Use the overload without `mapper` in case you want to map to an [Assert][AssertionPlant] lambda with receiver
 * and use one of the available options as second step. For instance, `mapArguments(a, aX).toExpect<String> { ... }`
 */
@Deprecated(
    "Use mapArguments from atrium-logic; will be removed with 0.17.0",
    ReplaceWith("ch.tutteli.atrium.logic.utils.mapArguments(first, others, mapper)")
)
inline fun <reified R> mapArguments(first: Char, others: CharArray, mapper: (Char) -> R): Pair<R, Array<out R>> =
    mapArguments(first, others).to(mapper)

/**
 * Creates a [ArgumentMapperBuilder] with the given [first] and [others] as arguments;
 * specify the mapping as such using a subsequent step in the building process.
 */
@Deprecated(
    "Use mapArguments from atrium-logic; will be removed with 0.17.0",
    ReplaceWith("ch.tutteli.atrium.logic.utils.mapArguments(first, others)")
)
fun mapArguments(first: Short, others: ShortArray): ArgumentMapperBuilder<Short> =
    mapArguments(first, others.toTypedArray())

/**
 * Maps the given [first] and [others] to the given type [R] with the help of the given [mapper].
 *
 * Use the overload without `mapper` in case you want to map to an [Assert][AssertionPlant] lambda with receiver
 * and use one of the available options as second step. For instance, `mapArguments(a, aX).toExpect<String> { ... }`
 */
@Deprecated(
    "Use mapArguments from atrium-logic; will be removed with 0.17.0",
    ReplaceWith("ch.tutteli.atrium.logic.utils.mapArguments(first, others, mapper)")
)
inline fun <reified R> mapArguments(first: Short, others: ShortArray, mapper: (Short) -> R): Pair<R, Array<out R>> =
    mapArguments(first, others).to(mapper)

/**
 * Creates a [ArgumentMapperBuilder] with the given [first] and [others] as arguments;
 * specify the mapping as such using a subsequent step in the building process.
 */
@Deprecated(
    "Use mapArguments from atrium-logic; will be removed with 0.17.0",
    ReplaceWith("ch.tutteli.atrium.logic.utils.mapArguments(first, others)")
)
fun mapArguments(first: Int, others: IntArray): ArgumentMapperBuilder<Int> = mapArguments(first, others.toTypedArray())

/**
 * Maps the given [first] and [others] to the given type [R] with the help of the given [mapper].
 *
 * Use the overload without `mapper` in case you want to map to an [Assert][AssertionPlant] lambda with receiver
 * and use one of the available options as second step. For instance, `mapArguments(a, aX).toExpect<String> { ... }`
 */
@Deprecated(
    "Use mapArguments from atrium-logic; will be removed with 0.17.0",
    ReplaceWith("ch.tutteli.atrium.logic.utils.mapArguments(first, others, mapper)")
)
inline fun <reified R> mapArguments(first: Int, others: IntArray, mapper: (Int) -> R): Pair<R, Array<out R>> =
    mapArguments(first, others).to(mapper)

/**
 * Creates a [ArgumentMapperBuilder] with the given [first] and [others] as arguments;
 * specify the mapping as such using a subsequent step in the building process.
 */
@Deprecated(
    "Use mapArguments from atrium-logic; will be removed with 0.17.0",
    ReplaceWith("ch.tutteli.atrium.logic.utils.mapArguments(first, others)")
)
fun mapArguments(first: Long, others: LongArray): ArgumentMapperBuilder<Long> =
    mapArguments(first, others.toTypedArray())

/**
 * Maps the given [first] and [others] to the given type [R] with the help of the given [mapper].
 *
 * Use the overload without `mapper` in case you want to map to an [Assert][AssertionPlant] lambda with receiver
 * and use one of the available options as second step. For instance, `mapArguments(a, aX).toExpect<String> { ... }`
 */
@Deprecated(
    "Use mapArguments from atrium-logic; will be removed with 0.17.0",
    ReplaceWith("ch.tutteli.atrium.logic.utils.mapArguments(first, others, mapper)")
)
inline fun <reified R> mapArguments(first: Long, others: LongArray, mapper: (Long) -> R): Pair<R, Array<out R>> =
    mapArguments(first, others).to(mapper)

/**
 * Creates a [ArgumentMapperBuilder] with the given [first] and [others] as arguments;
 * specify the mapping as such using a subsequent step in the building process.
 */
@Deprecated(
    "Use mapArguments from atrium-logic; will be removed with 0.17.0",
    ReplaceWith("ch.tutteli.atrium.logic.utils.mapArguments(first, others)")
)
fun mapArguments(first: Float, others: FloatArray): ArgumentMapperBuilder<Float> =
    mapArguments(first, others.toTypedArray())

/**
 * Maps the given [first] and [others] to the given type [R] with the help of the given [mapper].
 *
 * Use the overload without `mapper` in case you want to map to an [Assert][AssertionPlant] lambda with receiver
 * and use one of the available options as second step. For instance, `mapArguments(a, aX).toExpect<String> { ... }`
 */
@Deprecated(
    "Use mapArguments from atrium-logic; will be removed with 0.17.0",
    ReplaceWith("ch.tutteli.atrium.logic.utils.mapArguments(first, others, mapper)")
)
inline fun <reified R> mapArguments(first: Float, others: FloatArray, mapper: (Float) -> R): Pair<R, Array<out R>> =
    mapArguments(first, others).to(mapper)

/**
 * Creates a [ArgumentMapperBuilder] with the given [first] and [others] as arguments;
 * specify the mapping as such using a subsequent step in the building process.
 */
@Deprecated(
    "Use mapArguments from atrium-logic; will be removed with 0.17.0",
    ReplaceWith("ch.tutteli.atrium.logic.utils.mapArguments(first, others)")
)
fun mapArguments(first: Double, others: DoubleArray): ArgumentMapperBuilder<Double> =
    mapArguments(first, others.toTypedArray())

/**
 * Maps the given [first] and [others] to the given type [R] with the help of the given [mapper].
 *
 * Use the overload without `mapper` in case you want to map to an [Assert][AssertionPlant] lambda with receiver
 * and use one of the available options as second step. For instance, `mapArguments(a, aX).toExpect<String> { ... }`
 */
@Deprecated(
    "Use mapArguments from atrium-logic; will be removed with 0.17.0",
    ReplaceWith("ch.tutteli.atrium.logic.utils.mapArguments(first, others, mapper)")
)
inline fun <reified R> mapArguments(first: Double, others: DoubleArray, mapper: (Double) -> R): Pair<R, Array<out R>> =
    mapArguments(first, others).to(mapper)

/**
 * Creates a [ArgumentMapperBuilder] with the given [first] and [others] as arguments;
 * specify the mapping as such using a subsequent step in the building process.
 */
@Deprecated(
    "Use mapArguments from atrium-logic; will be removed with 0.17.0",
    ReplaceWith("ch.tutteli.atrium.logic.utils.mapArguments(first, others)")
)
fun mapArguments(first: Boolean, others: BooleanArray): ArgumentMapperBuilder<Boolean> =
    mapArguments(first, others.toTypedArray())

/**
 * Maps the given [first] and [others] to the given type [R] with the help of the given [mapper].
 *
 * Use the overload without `mapper` in case you want to map to an [Assert][AssertionPlant] lambda with receiver
 * and use one of the available options as second step. For instance, `mapArguments(a, aX).toExpect<String> { ... }`
 */
@Deprecated(
    "Use mapArguments from atrium-logic; will be removed with 0.17.0",
    ReplaceWith("ch.tutteli.atrium.logic.utils.mapArguments(first, others, mapper)")
)
inline fun <reified R> mapArguments(
    first: Boolean,
    others: BooleanArray,
    mapper: (Boolean) -> R
): Pair<R, Array<out R>> = mapArguments(first, others).to(mapper)

/**
 * Builder to map variable length arguments formulated as ([first]: [T], `vararg` [others] : [T]) to something else.
 */
@Deprecated("Use ArgumentMapperBuilder, mapArguments respectively, from atrium-logic; will be removed with 0.17.0")
class ArgumentMapperBuilder<out T>(
    val first: T,
    val others: Array<out T>
) {

    /**
     * Maps each argument to an [Expect]&lt;[R]&gt; lambda with receiver
     * using the given [assertionCreator] (passing in the argument).
     *
     * @returns The mapped [first] and [others].
     */
    inline fun <reified R> toExpect(
        crossinline assertionCreator: Expect<R>.(T) -> Unit
    ): Pair<Expect<R>.() -> Unit, Array<out Expect<R>.() -> Unit>> = to { t -> subExpect<R> { assertionCreator(t) } }

    /**
     * Maps each argument to the given type [R] by using the given [mapper]
     *
     * @returns The mapped [first] and [others].
     */
    inline fun <reified R> to(mapper: (T) -> R): Pair<R, Array<out R>> =
        mapper(first) to others.map { mapper(it) }.toTypedArray()
}

/**
 * Maps each argument to `null` if it is already `null` and if not, then ...
 *
 * ... what happens otherwise needs to be specified in the next step.
 *
 * Notice, a future version might constrain [T] with a lower bound `Nothing?`.
 *
 * @returns The mapped [ArgumentMapperBuilder.first] and [ArgumentMapperBuilder.others].
 */
@Suppress("DeprecatedCallableAddReplaceWith")
@Deprecated("Use ArgumentMapperBuilder, mapArguments respectively, from atrium-logic; will be removed with 0.17.0")
fun <T : Any> ArgumentMapperBuilder<T?>.toNullOr(): ArgumentToNullOrMapperBuilder<T> =
    ArgumentToNullOrMapperBuilder(this)

@JsName("deprecatedToNullOr")
@Suppress("unused")
@Deprecated(
    "Since your arguments are not nullable it does not make sense to call this function - use the replacement.",
    ReplaceWith("toExpect(assertionCreator)")
)
fun <T : Any> ArgumentMapperBuilder<T>.toNullOr(): Nothing =
    throw PleaseUseReplacementException("Since your arguments are not nullable it does not make sense to call this function.")

/**
 * Builder to map variable length arguments formulated as `(first: T, vararg others : T)` to something else.
 *
 * Its usage is intended to turn the nullable argument type of the given [argumentMapperBuilder] into its non-nullable
 * type if the argument is not null in order that the non-nullable type can be used/passed to function which expect
 * a non-nullable type.
 */
@Deprecated("Use ArgumentMapperBuilder, mapArguments respectively, from atrium-logic; will be removed with 0.17.0")
class ArgumentToNullOrMapperBuilder<T : Any>(
    val argumentMapperBuilder: ArgumentMapperBuilder<T?>
) {
    /**
     * Maps each argument to `null` if it is already `null` and if not, then to an [Expect]&lt;[R]&gt;
     * lambda with receiver by using the given [assertionCreator] (passing in the argument).
     *
     * @returns The mapped arguments.
     */
    inline fun <R : Any> toExpect(
        crossinline assertionCreator: Expect<R>.(T) -> Unit
    ): Pair<(Expect<R>.() -> Unit)?, Array<out (Expect<R>.() -> Unit)?>> =
        argumentMapperBuilder.to { nullableT -> nullableT?.let { t -> subExpect<R> { assertionCreator(t) } } }
}
