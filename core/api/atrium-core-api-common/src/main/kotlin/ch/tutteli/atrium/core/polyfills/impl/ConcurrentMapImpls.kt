package ch.tutteli.atrium.core.polyfills.impl

import ch.tutteli.atrium.core.polyfills.ConcurrentMap
import ch.tutteli.atrium.core.polyfills.MutableConcurrentMap

internal expect class ConcurrentMapImpl<K, out V: Any>(source: Map<K, V>) : ConcurrentMap<K, V>

internal expect class MutableConcurrentMapImpl<K, V: Any>() : MutableConcurrentMap<K, V>
