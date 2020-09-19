package ch.tutteli.atrium.logic.creating.typeutils

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
