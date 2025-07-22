package ch.tutteli.atrium.core.polyfills.impl

import ch.tutteli.atrium.core.polyfills.ConcurrentMap
import ch.tutteli.atrium.core.polyfills.MutableConcurrentMap

internal expect class ConcurrentMapImpl<K, out V : Any>(source: Map<K, V>) : ConcurrentMap<K, V> {
    override fun get(key: K): V?
}

internal expect class MutableConcurrentMapImpl<K, V : Any>() : MutableConcurrentMap<K, V> {
    override fun get(key: K): V?
    override fun computeIfAbsent(key: K, value: (K) -> V): V
    override fun putIfAbsent(key: K, value: V): V
}
