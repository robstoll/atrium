package ch.tutteli.atrium.domain.robstoll.creating

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.creating.SubjectProvider
import ch.tutteli.atrium.domain.creating.MapAssertions
import ch.tutteli.atrium.domain.creating.changers.ExtractedFeaturePostStep
import ch.tutteli.atrium.domain.robstoll.lib.creating.*
import kotlin.reflect.KClass

class MapAssertionsImpl : MapAssertions, MapAssertionsDeprecatedImpl() {

    override fun <K, V : Any, T : Map<out K, V?>> contains(
        expect: Expect<T>,
        valueType: KClass<V>,
        keyValuePairs: List<Pair<K, V?>>
    ) = _contains(expect, valueType, keyValuePairs)

    override fun <K, V : Any, T : Map<out K, V?>> containsKeyWithValueAssertions(
        expect: Expect<T>,
        valueType: KClass<V>,
        keyValues: List<Pair<K, (Expect<V>.() -> Unit)?>>
    ) = _containsKeyWithValueAssertion(expect, valueType, keyValues)

    override fun <K> containsKey(subjectProvider: SubjectProvider<Map<out K, *>>, key: K) =
        _containsKey(subjectProvider, key)

    override fun <K> containsNotKey(subjectProvider: SubjectProvider<Map<out K, *>>, key: K) =
        _containsNotKey(subjectProvider, key)

    override fun isEmpty(subjectProvider: SubjectProvider<Map<*, *>>) = _isEmpty(subjectProvider)

    override fun isNotEmpty(subjectProvider: SubjectProvider<Map<*, *>>) = _isNotEmpty(subjectProvider)

    override fun <K, V, T : Map<out K, V>> getExisting(
        expect: Expect<T>,
        key: K
    ): ExtractedFeaturePostStep<T, V> = _getExisting(expect, key)

    override fun <T : Map<*, *>> size(expect: Expect<T>) = _size(expect)
}
