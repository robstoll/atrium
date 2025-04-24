package ch.tutteli.atrium.creating.typeutils

/**
 * @since 1.3.0
 */
interface IterableLikeToIterableTransformer {
    /**
     * Transforms the given [IterableLike] to an [Iterable] with an element type [T].
     *
     * Note that an unsafe cast applies, i.e. the caller has to know that the elements of the given [iterableLike]
     * are all of type [T] and specify it correctly. Alternatively, the caller can call `map { it as T }`
     * after the [unsafeTransform] call.
     *
     * @since 1.3.0
     */
    fun <T> unsafeTransform(iterableLike: IterableLike): Iterable<T>

    /**
     * Returns a list of type representations which this [IterableLikeToIterableTransformer]
     * can transform to an [Iterable].
     *
     * Intended to be used in error messages.
     *
     * @since 1.3.0
     */
    fun supportedTypes(): List<String>
}
