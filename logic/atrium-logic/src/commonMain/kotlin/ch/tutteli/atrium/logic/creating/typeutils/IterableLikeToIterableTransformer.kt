//TODO remove file with 2.0.0 at the latest
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.logic.creating.typeutils

@Deprecated(
    "Use the import from atrium-core, atrium-logic will be removed with 2.0.0 at the latest",
    ReplaceWith("ch.tutteli.atrium.creating.typeutils.IterableLikeToIterableTransformer")
)
interface IterableLikeToIterableTransformer {
    /**
     * Transforms the given [IterableLike] to an [Iterable] with an element type [T].
     *
     * Note that an unsafe cast applies, i.e. the caller has to know that the elements of the given [iterableLike]
     * are all of type [T] and specify it correctly. Alternatively, the caller can call `map { it as T }`
     * after the [unsafeTransform] call.
     */
    fun <T> unsafeTransform(iterableLike: IterableLike): Iterable<T>

    fun supportedTypes(): List<String>
}
