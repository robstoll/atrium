package ch.tutteli.atrium.logic.creating.typeutils

interface MapLikeToIterablePairTransformer {
    /**
     * Transforms the given [mapLike] to an [Iterable]`<`[Pair]`<K, V>>` where [K] represents the key type
     * and [V] the value type.
     *
     * Note that an unsafe cast applies, i.e. the caller has to know that the given types [K] and [V] are correct for
     * all keys and values of the given [mapLike].
     */
    fun <K, V> unsafeTransform(mapLike: MapLike): List<Pair<K, V>>

    fun supportedTypes(): List<String>
}
