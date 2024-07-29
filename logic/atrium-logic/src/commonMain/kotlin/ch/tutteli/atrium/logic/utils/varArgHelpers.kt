//TODO remove file with 2.0.0 at the latest
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.logic.utils

import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.logic.creating.charsequence.contains.CharSequenceContains
import ch.tutteli.atrium.logic.creating.iterable.contains.IterableLikeContains
import ch.tutteli.atrium.logic.creating.maplike.contains.MapLikeContains
import ch.tutteli.atrium.logic.creating.typeutils.IterableLike
import ch.tutteli.atrium.logic.creating.typeutils.MapLike
import ch.tutteli.atrium.logic.creating.typeutils.iterableLikeToIterableTransformer
import ch.tutteli.atrium.logic.creating.typeutils.mapLikeToMapTransformer

//TODO 1.3.0 deprecate everything

/**
 * Transforms the given [iterableLike] to `Pair<T, Array<out T>>` with the intention that it can be easily
 * used for a function requiring `T, vararg T`.
 *
 * @throws IllegalArgumentException in case the [iterableLike] is empty.
 *
 * @since 0.14.0
 */
inline fun <reified T> CharSequenceContains.CheckerStepLogic<*, *>.toVarArg(
    iterableLike: IterableLike
): Pair<T, Array<out T>> = entryPointStepLogic.toVarArg(iterableLike)

/**
 * Transforms the given [iterableLike] to `Pair<T, Array<out T>>` with the intention that it can be easily
 * used for a function requiring `T, vararg T`.
 *
 * @throws IllegalArgumentException in case the [iterableLike] is empty.
 *
 * @since 0.14.0
 */
inline fun <reified T> CharSequenceContains.EntryPointStepLogic<*, *>.toVarArg(
    iterableLike: IterableLike
): Pair<T, Array<out T>> = container.iterableLikeToVarArg(iterableLike)

/**
 * Transforms the given [iterableLike] to `Pair<T, Array<out T>>` with the intention that it can be easily
 * used for a function requiring `T, vararg T`.
 *
 * @throws IllegalArgumentException in case the [iterableLike] is empty.
 *
 * @since 0.14.0
 */
inline fun <reified T> IterableLikeContains.CheckerStepLogic<*, *, *>.toVarArg(
    iterableLike: IterableLike
): Pair<T, Array<out T>> = entryPointStepLogic.toVarArg(iterableLike)

/**
 * Transforms the given [iterableLike] to `Pair<T, Array<out T>>` with the intention that it can be easily used
 * for a function requiring `T, vararg T`.
 *
 * @throws IllegalArgumentException in case the [iterableLike] is empty.
 *
 * @since 0.14.0
 */
// part of the API level because we inline, something to be aware about
inline fun <reified T> IterableLikeContains.EntryPointStepLogic<*, *, *>.toVarArg(
    iterableLike: IterableLike
): Pair<T, Array<out T>> = container.iterableLikeToVarArg(iterableLike)

/**
 * Transforms the given [iterableLike] to `Pair<T, Array<out T>>` with the intention that it can be easily used
 * for a function requiring `T, vararg T`.
 *
 * @throws IllegalArgumentException in case the [iterableLike] is empty.
 *
 * @since 0.14.0
 */
@PublishedApi
internal inline fun <reified T> AssertionContainer<*>.iterableLikeToVarArg(
    iterableLike: IterableLike
): Pair<T, Array<out T>> = toVarArg(iterableLikeToIterable(iterableLike))

/**
 * Transforms the given [iterableLike] to an [Iterable] with an element type [T].
 *
 * Note that an unsafe cast applies, i.e. you need to know that the element type of the given [iterableLike] is
 * actually [T]. Use `.map { it as T }` afterwards if you don't know what you are doing.
 *
 * @returns the resulting [Iterable].
 * @throws IllegalArgumentException in case the [iterableLike] is empty (has not next element).
 *
 * @since 0.14.0
 */
fun <T> AssertionContainer<*>.iterableLikeToIterable(iterableLike: IterableLike): Iterable<T> =
    iterableLikeToIterableWithoutCheckForElements<T>(iterableLike)
        .requireHasNext { "IterableLike without elements are not allowed for this function." }

/**
 * Transforms the given [iterableLike] to an [Iterable] with an element type [T].
 *
 * It does not check if the resulting [Iterable] has a next element. Use [iterableLikeToIterable] which
 * incorporates this check. Use this function only, if you want to support [IterableLike] types in conjunction with
 * another ...Like type.
 *
 * @returns the resulting [Iterable] of type
 * @since 0.15.0
 */
fun <T> AssertionContainer<*>.iterableLikeToIterableWithoutCheckForElements(
    iterableLike: IterableLike
) : Iterable<T> = iterableLikeToIterableTransformer
    .unsafeTransform(iterableLike)

private fun <E, T : Iterable<E>> T.requireHasNext(errorMessage: () -> String): T {
    require(iterator().hasNext(), errorMessage)
    return this
}

/**
 * Transforms the given [MapLike] to `Pair<Pair<K, V>, Array<out Pair<K, V>>>` with the intention that
 * it can be easily used for a function requiring `Pair<K, V>, vararg Pair<K, V>`.
 *
 * @throws IllegalArgumentException in case the [mapLike] is empty.
 *
 * @since 0.15.0
 */
fun <K, V> MapLikeContains.EntryPointStepLogic<*, *, *, *>.toVarArgPairs(
    mapLike: MapLike
): Pair<Pair<K, V>, Array<out Pair<K, V>>> = container.mapLikeToVarArgPairs(mapLike)

/**
 * Transforms the given [MapLike] to an [Iterable] with an element type [Pair]`<K, V>`.
 *
 * @throws IllegalArgumentException in case the [mapLike] is empty.
 *
 * @since 0.15.0
 */
fun <K, V> AssertionContainer<*>.mapLikeToVarArgPairs(mapLike: MapLike): Pair<Pair<K, V>, Array<out Pair<K, V>>> =
    toVarArg(mapLikeToIterablePair(mapLike))


/**
 * Transforms the given [MapLike] to `Pair<Pair<K, V>, Array<out Pair<K, V>>>` with the intention that
 * it can be easily used for a function requiring `Pair<K, V>, vararg Pair<K, V>`.
 *
 * Note that an unsafe cast applies, i.e. you need to know that the key and value type of the given [mapLike] is
 * actually [K] and [V] for all entries. Use `.map { (it.key as K) to (it.value as V) }` afterwards
 * if you don't know what you are doing.
 *
 * @since 0.15.0
 */
fun <K, V> AssertionContainer<*>.mapLikeToIterablePair(mapLike: MapLike): List<Pair<K, V>> =
    mapLikeToMapTransformer
        .unsafeTransform<K, V>(mapLike)
        .requireHasNext { "MapLike without entries are not allowed for this function." }


/**
 * Transforms the given [Iterable] to `Pair<T, Array<out T>>` with the intention that it can be easily used
 * for a function requiring `T, vararg T`
 *
 * @since 0.14.0
 */
@PublishedApi
internal inline fun <reified T> toVarArg(iterable: Iterable<T>): Pair<T, Array<out T>> {
    return iterable.first() to iterable.drop(1).toTypedArray()
}
