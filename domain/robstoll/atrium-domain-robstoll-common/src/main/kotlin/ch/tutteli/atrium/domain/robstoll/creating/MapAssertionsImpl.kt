package ch.tutteli.atrium.domain.robstoll.creating

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.creating.SubjectProvider
import ch.tutteli.atrium.domain.creating.ExtractedFeatureOption
import ch.tutteli.atrium.domain.creating.MapAssertions
import ch.tutteli.atrium.domain.robstoll.lib.creating.*
import kotlin.reflect.KClass

class MapAssertionsImpl : MapAssertions, MapAssertionsDeprecatedImpl() {

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

    override fun <K, V, T : Map<out K, V>> getExisting(
        assertionContainer: Expect<T>,
        key: K
    ): ExtractedFeatureOption<T, V> = _getExisting(assertionContainer, key)

    override fun <T : Map<*, *>> size(assertionContainer: Expect<T>) = _size(assertionContainer)
}
