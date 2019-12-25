package ch.tutteli.atrium.domain.robstoll.creating

import ch.tutteli.atrium.creating.SubjectProvider
import ch.tutteli.atrium.domain.creating.MapAssertions
import ch.tutteli.atrium.domain.robstoll.lib.creating._containsKey
import ch.tutteli.atrium.domain.robstoll.lib.creating._containsNotKey
import ch.tutteli.atrium.domain.robstoll.lib.creating._isEmpty
import ch.tutteli.atrium.domain.robstoll.lib.creating._isNotEmpty

class MapAssertionsImpl : MapAssertions, MapAssertionsDeprecatedImpl() {

    override fun <K> containsKey(subjectProvider: SubjectProvider<Map<out K, *>>, key: K) =
        _containsKey(subjectProvider, key)

    override fun <K> containsNotKey(subjectProvider: SubjectProvider<Map<out K, *>>, key: K) =
        _containsNotKey(subjectProvider, key)

    override fun isEmpty(subjectProvider: SubjectProvider<Map<*, *>>) = _isEmpty(subjectProvider)

    override fun isNotEmpty(subjectProvider: SubjectProvider<Map<*, *>>) = _isNotEmpty(subjectProvider)

}
