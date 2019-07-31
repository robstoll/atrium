package ch.tutteli.atrium.domain.robstoll.creating

import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.creating.AssertionPlantNullable
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.creating.MapEntryAssertions
import ch.tutteli.atrium.domain.robstoll.lib.creating.*
import kotlin.reflect.KClass


class MapEntryAssertionsImpl : MapEntryAssertions {
    override fun <K : Any, V : Any, T : Map.Entry<K, V>> isKeyValue(assertionContainer: Expect<T>, key: K, value: V) =
        _isKeyValue(assertionContainer, key, value)

    override fun <K : Any, V : Any, T : Map.Entry<K?, V?>> isKeyValue(
        assertionContainer: Expect<T>,
        key: K?,
        value: V?,
        keyType: KClass<K>,
        valueType: KClass<V>
    ) = _isKeyValue(assertionContainer, key, value, keyType, valueType)

    override fun <K, T : Map.Entry<K, *>> key(assertionContainer: Expect<T>) = _key(assertionContainer)
    override fun <V, T : Map.Entry<*, V>> value(assertionContainer: Expect<T>) = _value(assertionContainer)


    override fun <K : Any, V : Any> isKeyValue(
        plant: AssertionPlant<Map.Entry<K, V>>,
        key: K,
        value: V
    ) = _keyValue(plant, key, value)

    override fun <K : Any> key(
        plant: AssertionPlant<Map.Entry<K, *>>,
        assertionCreator: AssertionPlant<K>.() -> Unit
    ) = _key(plant, assertionCreator)

    override fun <V : Any> value(
        plant: AssertionPlant<Map.Entry<*, V>>,
        assertionCreator: AssertionPlant<V>.() -> Unit
    ) = _value(plant, assertionCreator)

    override fun <K> nullableKey(
        plant: AssertionPlant<Map.Entry<K, *>>,
        assertionCreator: AssertionPlantNullable<K>.() -> Unit
    ) = _nullableKey(plant, assertionCreator)

    override fun <V> nullableValue(
        plant: AssertionPlant<Map.Entry<*, V>>,
        assertionCreator: AssertionPlantNullable<V>.() -> Unit
    ) = _nullableValue(plant, assertionCreator)
}
