package ch.tutteli.atrium.domain.robstoll.lib.creating.charsequence.contains.searchers

import ch.tutteli.atrium.domain.creating.charsequence.contains.CharSequenceContains.Searcher
import ch.tutteli.atrium.domain.creating.charsequence.contains.searchbehaviours.NoOpSearchBehaviour

/**
 * Represents a [Searcher] which evaluates the matches of a given regular expression on the input of the search.
 *
 * It performs a non-disjoint search, meaning searching 'aa?' in 'aaaa' has 4 hits.
 */
class RegexSearcher : Searcher<NoOpSearchBehaviour, Regex> {
    override fun search(searchIn: CharSequence, searchFor: Any): Int = search(searchIn, searchFor)

    fun search(searchIn: CharSequence, regex: Regex): Int {
        var counter = 0
        var matchResult = regex.find(searchIn)
        while (matchResult != null) {
            matchResult = regex.find(searchIn, matchResult.range.start + 1)
            ++counter
        }
        return counter
    }
}
