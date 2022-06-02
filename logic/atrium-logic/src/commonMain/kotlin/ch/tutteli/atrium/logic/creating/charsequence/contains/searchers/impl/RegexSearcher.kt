package ch.tutteli.atrium.logic.creating.charsequence.contains.searchers.impl

import ch.tutteli.atrium.logic.creating.charsequence.contains.CharSequenceContains.Searcher
import ch.tutteli.atrium.logic.creating.charsequence.contains.searchbehaviours.NoOpSearchBehaviour

/**
 * Represents a [Searcher] which evaluates the matches of a given regular expression on the input of the search.
 *
 * It performs a non-disjoint search, meaning searching 'aa?' in 'aaaa' has 4 hits.
 */
class RegexSearcher : Searcher<NoOpSearchBehaviour, Regex> {
    override fun search(searchIn: CharSequence, searchFor: Regex): Int {
        var counter = 0
        var matchResult = searchFor.find(searchIn)
        while (matchResult != null) {
            matchResult = searchFor.find(searchIn, matchResult.range.first + 1)
            ++counter
        }
        return counter
    }
}
