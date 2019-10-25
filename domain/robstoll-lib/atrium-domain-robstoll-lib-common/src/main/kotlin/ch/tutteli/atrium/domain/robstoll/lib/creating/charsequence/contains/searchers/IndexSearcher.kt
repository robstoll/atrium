package ch.tutteli.atrium.domain.robstoll.lib.creating.charsequence.contains.searchers

import ch.tutteli.atrium.domain.creating.charsequence.contains.CharSequenceContains.Searcher
import ch.tutteli.atrium.domain.creating.charsequence.contains.searchbehaviours.NoOpSearchBehaviour

/**
 * Represents a [Searcher] which uses [CharSequence.indexOf] to find expected objects.
 */
class IndexSearcher : Searcher<NoOpSearchBehaviour, String> {
    override fun search(searchIn: CharSequence, searchFor: String): Int {
        var index = searchIn.indexOf(searchFor)
        var counter = 0
        while (index >= 0) {
            index = searchIn.indexOf(searchFor, index + 1)
            ++counter
        }
        return counter
    }
}
