@file:Suppress("DEPRECATION" /* will be removed with 1.0.0 */)
package ch.tutteli.atrium.spec.integration

@Deprecated("Switch from Assert to Expect and use data class from atrium-specs-common; will be removed with 1.0.0")
internal data class SimpleMapEntry<K, V>(override val key: K, override val value: V) : Map.Entry<K, V> {
    override fun toString(): String = "Map.Entry($key, $value)"
}

@Suppress("DeprecatedCallableAddReplaceWith")
@Deprecated("Switch from Assert to Expect and use mapEntry from atrium-specs-common; will be removed with 1.0.0")
fun <K, V> mapEntry(key: K, value: V): Map.Entry<K, V> = SimpleMapEntry(key, value)
