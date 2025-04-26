package ch.tutteli.atrium.creating.typeutils

/**
 * @since 1.3.0
 */
interface MapLikeToIterablePairTransformer {
    /**
     * Transforms the given [mapLike] to an [Iterable]`<`[Pair]`<K, V>>` where [K] represents the key type
     * and [V] the value type.
     *
     * Note that an unsafe cast applies, i.e. the caller has to know that the given types [K] and [V] are correct for
     * all keys and values of the given [mapLike].
     *
     * @since 1.3.0
     */
    fun <K, V> unsafeTransform(mapLike: MapLike): List<Pair<K, V>>

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
