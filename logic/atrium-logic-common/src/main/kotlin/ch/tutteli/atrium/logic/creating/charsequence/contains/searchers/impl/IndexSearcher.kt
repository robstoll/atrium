package ch.tutteli.atrium.logic.creating.charsequence.contains.searchers.impl

import ch.tutteli.atrium.logic.creating.charsequence.contains.CharSequenceContains.Searcher
import ch.tutteli.atrium.logic.creating.charsequence.contains.searchbehaviours.NoOpSearchBehaviour

/**
 * Represents a [Searcher] which uses [CharSequence.indexOf] to find expected objects.
 */
class IndexSearcher : Searcher<NoOpSearchBehaviour, Any> {
    override fun search(searchIn: CharSequence, searchFor: Any): Int {
        val expected = searchFor.toString()
        var index = searchIn.indexOf(expected)
        var counter = 0
        while (index >= 0) {
            index = searchIn.indexOf(expected, index + 1)
            ++counter
        }
        return counter
    }
}
