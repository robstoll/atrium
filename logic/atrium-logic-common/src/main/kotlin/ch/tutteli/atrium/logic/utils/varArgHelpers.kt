package ch.tutteli.atrium.logic.utils

import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.logic.creating.charsequence.contains.CharSequenceContains
import ch.tutteli.atrium.logic.creating.iterable.contains.IterableLikeContains
import ch.tutteli.atrium.logic.creating.typeutils.*

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
 * @throws IllegalArgumentException in case the [iterableLike] is empty (has not next element).
 *
 * @since 0.14.0
 */
fun <T> AssertionContainer<*>.iterableLikeToIterable(iterableLike: IterableLike): Iterable<T> =
    iterableLikeToIterableTransformer
        .unsafeTransform<T>(iterableLike)
        .requireHasNext { "IterableLike without elements are not allowed for this function." }

private fun <E, T : Iterable<E>> T.requireHasNext(errorMessage: () -> String): T {
    require(iterator().hasNext(), errorMessage)
    return this
}

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
