package ch.tutteli.atrium.core.polyfills.impl

import ch.tutteli.atrium.core.polyfills.MutableConcurrentMap

internal actual class MutableConcurrentMapImpl<K, V : Any> actual constructor() : MutableConcurrentMap<K, V> {
    private val map = HashMap<K, V>()

    actual override operator fun get(key: K): V? = map[key]

    actual override fun putIfAbsentReturnNew(key: K, value: V): V =
        map[key] ?: value.also { map[key] = it }

    @Deprecated(
        "Use putIfAbsentReturnNew since we return the new value and not the old",
        replaceWith = ReplaceWith("putIfAbsentReturnNew(key, value)")
    )
    actual override fun putIfAbsent(key: K, value: V): V = putIfAbsentReturnNew(key, value)
}
