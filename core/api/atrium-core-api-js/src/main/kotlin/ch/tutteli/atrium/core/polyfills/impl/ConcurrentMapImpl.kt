package ch.tutteli.atrium.core.polyfills.impl

import ch.tutteli.atrium.core.polyfills.ConcurrentMap

internal actual class ConcurrentMapImpl<K, out V: Any> actual constructor(
    source: Map<K, V>
) : ConcurrentMap<K, V> {
    private val map = source.toMap()

    override operator fun get(key: K): V? = map[key]
}
