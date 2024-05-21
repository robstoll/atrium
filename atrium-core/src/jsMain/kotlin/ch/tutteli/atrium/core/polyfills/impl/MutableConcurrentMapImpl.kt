package ch.tutteli.atrium.core.polyfills.impl

import ch.tutteli.atrium.core.polyfills.MutableConcurrentMap

internal actual class MutableConcurrentMapImpl<K, V : Any> actual constructor() : MutableConcurrentMap<K, V> {
    private val map = HashMap<K, V>()

    actual override operator fun get(key: K): V? = map[key]
    actual override fun putIfAbsent(key: K, value: V): V =
        map[key] ?: value.apply { map[key] = this }
}
