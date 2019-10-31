package ch.tutteli.atrium.domain.robstoll.lib.creating.charsequence.contains.searchers

import ch.tutteli.atrium.domain.creating.charsequence.contains.CharSequenceContains.Searcher
import ch.tutteli.atrium.domain.creating.charsequence.contains.searchbehaviours.NoOpSearchBehaviour

/**
 * Represents a [Searcher] which uses [CharSequence.indexOf] to find expected objects.
 */
class IndexSearcher : Searcher<NoOpSearchBehaviour, Any> {
    override fun search(searchIn: CharSequence, searchFor: Any): Int {
        val expected = searchFor.toString()
        var index = searchIn.indexOf(expected)
        var counter = 0
        while (index >= 0) {
            index = searchIn.indexOf(searchFor.toString(), index + 1)
            ++counter
        }
        return counter
    }
}
