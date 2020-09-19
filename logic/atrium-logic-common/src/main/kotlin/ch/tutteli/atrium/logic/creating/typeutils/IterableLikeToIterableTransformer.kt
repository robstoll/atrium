package ch.tutteli.atrium.logic.creating.typeutils

interface IterableLikeToIterableTransformer {
    /**
     * Transforms the given [IterableLike] to an Iterable of element types [T].
     * Note that an unsafe cast applies, i.e. the caller has to know that the elements of [iterableLike] are all of type
     * [T] and specify it correctly. Alternatively, the caller can call `map { it as T }` after the transform.
     */
    fun <T> transform(iterableLike: IterableLike): Iterable<T>

    fun supportedTypes(): List<String>
}
