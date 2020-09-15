package ch.tutteli.atrium.logic.utils

import ch.tutteli.atrium.core.ExperimentalNewExpectTypes
import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.logic.creating.charsequence.contains.CharSequenceContains
import ch.tutteli.atrium.logic.creating.iterable.contains.IterableLikeContains
import ch.tutteli.atrium.logic.creating.typeutils.IterableLike
import ch.tutteli.atrium.logic.utils.impl.DefaultIterableLikeToIterableTransformer

/**
 * Transforms the given [IterableLike] to `Pair<T, Array<out T>>` with the intend that it can be easily used for a function
 * requiring `T, vararg T`
 *
 * @throws IllegalArgumentException in case the iterable is empty.
 */
inline fun <reified T> CharSequenceContains.CheckerStepLogic<*, *>.toVarArg(iterableLike: IterableLike): Pair<T, Array<out T>> =
    entryPointStepLogic.toVarArg(iterableLike)

/**
 * Transforms the given [IterableLike] to `Pair<T, Array<out T>>` with the intend that it can be easily used for a function
 * requiring `T, vararg T`
 *
 * @throws IllegalArgumentException in case the iterable is empty.
 */
inline fun <reified T> CharSequenceContains.EntryPointStepLogic<*, *>.toVarArg(iterableLike: IterableLike): Pair<T, Array<out T>> =
    container.toVarArg(iterableLike)

/**
 * Transforms the given [IterableLike] to `Pair<T, Array<out T>>` with the intend that it can be easily used for a function
 * requiring `T, vararg T`
 *
 * @throws IllegalArgumentException in case the iterable is empty.
 */
inline fun <reified T> IterableLikeContains.CheckerStepLogic<*, *, *>.toVarArg(iterableLike: IterableLike): Pair<T, Array<out T>> =
    entryPointStepLogic.toVarArg(iterableLike)

/**
 * Transforms the given [IterableLike] to `Pair<T, Array<out T>>` with the intend that it can be easily used for a function
 * requiring `T, vararg T`
 *
 * @throws IllegalArgumentException in case the iterable is empty.
 */
inline fun <reified T> IterableLikeContains.EntryPointStepLogic<*, *, *>.toVarArg(iterableLike: IterableLike): Pair<T, Array<out T>> =
    container.toVarArg(iterableLike)

/**
 * Transforms the given [IterableLike] to `Pair<T, Array<out T>>` with the intend that it can be easily used for a function
 * requiring `T, vararg T`
 *
 * @throws IllegalArgumentException in case the iterable is empty.
 */
inline fun <reified T> AssertionContainer<*>.toVarArg(iterableLike: IterableLike): Pair<T, Array<out T>> =
    ch.tutteli.atrium.logic.utils.toVarArg(iterableLikeToIterable(iterableLike))

/**
 * Transforms the given [IterableLike] to an [Iterable] with an element type [T].
 *
 * Note that if [T] has itself type parameters, then an unsafe cast applies.
 */
@Suppress("DEPRECATION" /* OptIn is only available since 1.3.70 which we cannot use if we want to support 1.2 */)
@UseExperimental(ExperimentalNewExpectTypes::class)
fun <T> AssertionContainer<*>.iterableLikeToIterable(iterableLike: IterableLike): Iterable<T> =
    getImpl(IterableLikeToIterableTransformer::class) {
        DefaultIterableLikeToIterableTransformer()
    }.transform(iterableLike)

/**
 * Transforms the given [Iterable] to `Pair<T, Array<out T>>` with the intend that it can be easily used for a function
 * requiring `T, vararg T`
 *
 * @throws IllegalArgumentException in case the iterable is empty.
 */
inline fun <reified T> toVarArg(iterable: Iterable<T>): Pair<T, Array<out T>> {
    require(iterable.iterator().hasNext()) { "Iterable without elements are not allowed for this function." }
    return iterable.first() to iterable.drop(1).toTypedArray()
}
