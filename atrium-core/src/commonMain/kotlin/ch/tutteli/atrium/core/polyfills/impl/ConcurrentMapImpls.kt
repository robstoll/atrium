package ch.tutteli.atrium.core.polyfills.impl

import ch.tutteli.atrium.core.polyfills.ConcurrentMap
import ch.tutteli.atrium.core.polyfills.MutableConcurrentMap

internal expect class ConcurrentMapImpl<K, out V : Any>(source: Map<K, V>) : ConcurrentMap<K, V> {
    override fun get(key: K): V?
}

internal expect class MutableConcurrentMapImpl<K, V : Any>() : MutableConcurrentMap<K, V> {
    override fun get(key: K): V?
    override fun putIfAbsentReturnNew(key: K, value: V): V

    @Deprecated(
        "Use putIfAbsentReturnNew since we return the new value and not the old",
        replaceWith = ReplaceWith("computeIfAbsent(key) { value }")
    )
    override fun putIfAbsent(key: K, value: V): V
}
