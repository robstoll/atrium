package ch.tutteli.atrium.domain.robstoll.creating

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.creating.MapEntryAssertions
import ch.tutteli.atrium.domain.robstoll.lib.creating._isKeyValue
import ch.tutteli.atrium.domain.robstoll.lib.creating._key
import ch.tutteli.atrium.domain.robstoll.lib.creating._value
import kotlin.reflect.KClass


class MapEntryAssertionsImpl : MapEntryAssertions, MapEntryAssertionsDeprecatedImpl() {
    override fun <K : Any, V : Any, T : Map.Entry<K, V>> isKeyValue(expect: Expect<T>, key: K, value: V) =
        _isKeyValue(expect, key, value)

    override fun <K : Any, V : Any, T : Map.Entry<K?, V?>> isKeyValue(
        expect: Expect<T>,
        key: K?,
        value: V?,
        keyType: KClass<K>,
        valueType: KClass<V>
    ) = _isKeyValue(expect, key, value, keyType, valueType)

    override fun <K, T : Map.Entry<K, *>> key(expect: Expect<T>) = _key(expect)
    override fun <V, T : Map.Entry<*, V>> value(expect: Expect<T>) = _value(expect)
}
