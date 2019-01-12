package ch.tutteli.atrium.domain.builders.utils

import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.creating.AssertionPlantNullable

/**
 * Creates a [ArgumentMapperBuilder] with the given [first] and [others] as arguments; 
 * specify the mapping as such using a subsequent step in the building process.
 *
 * For instance (there are more options as second step to choose from):
 * ```
 * fun <K, V> pairs(pair: Pair<K, V>, vararg otherPairs: Pair<K, V>) {
 *   mapArguments(pair, otherPairs).toAssert<Map.Entry<K, V>> { isKeyValue(it) }
 * }
 * ```
 */
fun <T> mapArguments(first: T, others: Array<out T>): ArgumentMapperBuilder<T>
    = ArgumentMapperBuilder(first, others)

/**
 * Maps the given [first] and [others] to the given type [R] with the help of the given [mapper].
 *
 * Use the overload without `mapper` in case you want to map to an [Assert][AssertionPlant] lambda with receiver
 * and use one of the available options as second step. For instance, `mapArguments(a, aX).toAssert<String> { ... }`
 */
inline fun <T, reified R> mapArguments(first: T, others: Array<out T>, mapper: (T) -> R): Pair<R, Array<out R>>
    = mapArguments(first, others).to(mapper)

/**
 * Builder to map variable length arguments formulated as ([first]: [T], `vararg` [others] : [T]) to something else.
 */
class ArgumentMapperBuilder<out T>(
    val first: T,
    val others: Array<out T>
){

    /**
     * Maps each argument to an [Assert][AssertionPlant]&lt;[R]&gt; lambda with receiver
     * using the given [assertionCreator] (passing in the argument).
     *
     * @returns The mapped [first] and [others].
     */
    inline fun <reified R : Any> toAssert(
        crossinline assertionCreator: Assert<R>.(T) -> Unit
    ): Pair<Assert<R>.() -> Unit, Array<out Assert<R>.() -> Unit>>
        = to { t -> subAssert<R> { assertionCreator(t) } }

    /**
     * Maps each argument to `null` if it is already `null` and if not, then to an [Assert][AssertionPlant]&lt;[R]&gt;
     * lambda with receiver by using the given [assertionCreator] (passing in the argument).
     *
     * @returns The mapped [first] and [others].
     */
    inline fun <reified R : Any> toNullOrAssert(
        crossinline assertionCreator: Assert<R>.(T) -> Unit
    ): Pair<(Assert<R>.() -> Unit)?, Array<out (Assert<R>.() -> Unit)?>>
        = to { nullableT -> nullableT?.let { t -> subAssert<R> { assertionCreator(t) } } }

    /**
     * Maps each argument to an [AssertionPlantNullable]&lt;[R]&gt; lambda with receiver by
     * using the given [assertionCreator] (passing in the argument).
     *
     * @returns The mapped [first] and [others].
     */
    inline fun <reified R> toAssertionPlantNullable(
        crossinline assertionCreator: AssertionPlantNullable<R>.(T) -> Unit
    ): Pair<AssertionPlantNullable<R>.() -> Unit, Array<out AssertionPlantNullable<R>.() -> Unit>>
        = to { t -> subAssertNullable<R> { assertionCreator(t) } }

    /**
     * Maps each argument to the given type [R] by using the given [mapper]
     *
     * @returns The mapped [first] and [others].
     */
    inline fun <reified R> to(mapper: (T) -> R): Pair<R, Array<out R>>
        = mapper(first) to others.map { mapper(it) }.toTypedArray()
}
