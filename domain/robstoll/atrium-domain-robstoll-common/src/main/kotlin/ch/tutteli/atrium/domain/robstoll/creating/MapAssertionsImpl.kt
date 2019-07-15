package ch.tutteli.atrium.domain.robstoll.creating

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.*
import ch.tutteli.atrium.domain.creating.MapAssertions
import ch.tutteli.atrium.domain.robstoll.lib.creating.*
import kotlin.reflect.KClass

/**
 * Robstoll's implementation of [MapAssertions].
 */
class MapAssertionsImpl : MapAssertions {

    override fun <K, V : Any, T : Map<out K, V?>> contains(
        assertionContainer: Expect<T>,
        valueType: KClass<V>,
        keyValuePairs: List<Pair<K, V?>>
    ) = _contains(assertionContainer, valueType, keyValuePairs)

    override fun <K, V : Any, T : Map<out K, V?>> containsKeyWithValueAssertions(
        assertionContainer: Expect<T>,
        valueType: KClass<V>,
        keyValues: List<Pair<K, (Expect<V>.() -> Unit)?>>
    ) = _containsKeyWithValueAssertion(assertionContainer, valueType, keyValues)

    override fun <K> containsKey(subjectProvider: SubjectProvider<Map<out K, *>>, key: K) =
        _containsKey(subjectProvider, key)

    override fun <K> containsNotKey(subjectProvider: SubjectProvider<Map<out K, *>>, key: K) =
        _containsNotKey(subjectProvider, key)

    override fun isEmpty(subjectProvider: SubjectProvider<Map<*, *>>) = _isEmpty(subjectProvider)

    override fun isNotEmpty(subjectProvider: SubjectProvider<Map<*, *>>) = _isNotEmpty(subjectProvider)



    override fun <K, V> contains(plant: AssertionPlant<Map<out K, V>>, keyValuePairs: List<Pair<K, V>>) =
        _contains(plant, keyValuePairs)

    override fun <K, V : Any> containsKeyWithValueAssertions(
        plant: AssertionPlant<Map<out K, V?>>,
        keyValues: List<Pair<K, (Assert<V>.() -> Unit)?>>
    ) = _containsKeyWithValueAssertion(plant, keyValues)

    override fun <K, V : Any> getExisting(plant: AssertionPlant<Map<out K, V>>, key: K) = _getExisting(plant, key)

    override fun <K, V : Any> getExisting(
        plant: AssertionPlant<Map<out K, V>>,
        key: K,
        assertionCreator: AssertionPlant<V>.() -> Unit
    ) = _getExisting(plant, key, assertionCreator)

    override fun <K, V> getExistingNullable(plant: AssertionPlant<Map<out K, V>>, key: K) =
        _getExistingNullable(plant, key)

    override fun <K, V> getExistingNullable(
        plant: AssertionPlant<Map<out K, V>>,
        key: K,
        assertionCreator: AssertionPlantNullable<V>.() -> Unit
    ) = _getExistingNullable(plant, key, assertionCreator)


    override fun hasSize(plant: AssertionPlant<Map<*, *>>, size: Int) = _hasSize(plant, size)

    override fun <K> keys(
        plant: AssertionPlant<Map<out K, *>>,
        assertionCreator: AssertionPlant<Set<K>>.() -> Unit
    ): Assertion = _keys(plant, assertionCreator)

    override fun <V> values(
        plant: AssertionPlant<Map<*, V>>,
        assertionCreator: AssertionPlant<Collection<V>>.() -> Unit
    ): Assertion = _values(plant, assertionCreator)
}
