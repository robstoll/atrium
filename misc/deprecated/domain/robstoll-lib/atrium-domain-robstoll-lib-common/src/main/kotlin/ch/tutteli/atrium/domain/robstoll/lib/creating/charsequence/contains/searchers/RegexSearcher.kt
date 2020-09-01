//TODO remove with 1.0.0
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.domain.robstoll.lib.creating.charsequence.contains.searchers

import ch.tutteli.atrium.domain.creating.charsequence.contains.CharSequenceContains.Searcher
import ch.tutteli.atrium.domain.creating.charsequence.contains.searchbehaviours.NoOpSearchBehaviour

/**
 * Represents a [Searcher] which evaluates the matches of a given regular expression on the input of the search.
 *
 * It performs a non-disjoint search, meaning searching 'aa?' in 'aaaa' has 4 hits.
 */
@Deprecated("Use class from atrium-logic; will be removed with 1.0.0")
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
