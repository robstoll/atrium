@file:Suppress(
    // TODO remove once https://youtrack.jetbrains.com/issue/KT-35343 is fixed
    "JAVA_MODULE_DOES_NOT_READ_UNNAMED_MODULE"
)

package ch.tutteli.atrium.core.polyfills.impl

import ch.tutteli.atrium.core.polyfills.ConcurrentMap
import ch.tutteli.atrium.core.polyfills.MutableConcurrentMap
import java.util.concurrent.ConcurrentHashMap


internal actual class ConcurrentMapImpl<K, out V : Any> actual constructor(source: Map<K, V>) : ConcurrentMap<K, V> {
    private val map = ConcurrentHashMap(source)
    override operator fun get(key: K): V? = map[key]
}


internal actual class MutableConcurrentMapImpl<K, V : Any> actual constructor() : MutableConcurrentMap<K, V> {
    private val map = ConcurrentHashMap<K, V>()
    override operator fun get(key: K): V? = map[key]
    override fun putIfAbsent(key: K, value: V): V = map.putIfAbsent(key, value) ?: value
}
