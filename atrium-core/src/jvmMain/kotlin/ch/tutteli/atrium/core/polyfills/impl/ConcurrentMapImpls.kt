package ch.tutteli.atrium.core.polyfills.impl

import ch.tutteli.atrium.core.polyfills.ConcurrentMap
import ch.tutteli.atrium.core.polyfills.MutableConcurrentMap
import java.util.concurrent.ConcurrentHashMap


internal actual class ConcurrentMapImpl<K, out V : Any> actual constructor(source: Map<K, V>) : ConcurrentMap<K, V> {
    private val map = ConcurrentHashMap(source)
    actual override operator fun get(key: K): V? = map[key]
}


internal actual class MutableConcurrentMapImpl<K, V : Any> actual constructor() : MutableConcurrentMap<K, V> {
    private val map = ConcurrentHashMap<K, V>()
    actual override operator fun get(key: K): V? = map[key]
    actual override fun putIfAbsent(key: K, value: V): V = map.putIfAbsent(key, value) ?: value
}
