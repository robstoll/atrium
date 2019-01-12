package ch.tutteli.atrium.spec.integration

internal data class SimpleMapEntry<K, V>(override val key: K, override val value: V) : Map.Entry<K, V> {
    override fun toString(): String = "Map.Entry($key, $value)"
}

fun <K, V> mapEntry(key: K, value: V): Map.Entry<K, V> = SimpleMapEntry(key, value)
