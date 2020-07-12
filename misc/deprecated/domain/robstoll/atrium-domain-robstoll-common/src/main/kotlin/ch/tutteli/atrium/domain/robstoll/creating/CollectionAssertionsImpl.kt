package ch.tutteli.atrium.domain.robstoll.creating

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.creating.SubjectProvider
import ch.tutteli.atrium.domain.creating.CollectionAssertions
import ch.tutteli.atrium.domain.robstoll.lib.creating._isEmpty
import ch.tutteli.atrium.domain.robstoll.lib.creating._isNotEmpty
import ch.tutteli.atrium.domain.robstoll.lib.creating._size

class CollectionAssertionsImpl : CollectionAssertions, CollectionAssertionsDeprecatedImpl() {

    override fun isEmpty(subjectProvider: SubjectProvider<Collection<*>>) = _isEmpty(subjectProvider)

    override fun isNotEmpty(subjectProvider: SubjectProvider<Collection<*>>) = _isNotEmpty(subjectProvider)

    override fun <T : Collection<*>> size(expect: Expect<T>) = _size(expect)
}
