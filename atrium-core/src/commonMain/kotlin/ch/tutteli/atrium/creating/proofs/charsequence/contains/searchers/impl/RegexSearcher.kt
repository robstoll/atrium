package ch.tutteli.atrium.creating.proofs.charsequence.contains.searchers.impl

import ch.tutteli.atrium.creating.proofs.charsequence.contains.CharSequenceToContain.Searcher
import ch.tutteli.atrium.creating.proofs.charsequence.contains.searchbehaviours.NoOpSearchBehaviour

/**
 * Represents a [Searcher] which evaluates the matches of a given regular expression on the input of the search.
 *
 * It performs a non-disjoint search, meaning searching 'aa?' in 'aaaa' has 4 hits.
 */
class RegexSearcher : Searcher<NoOpSearchBehaviour, Regex> {
    override fun search(searchIn: CharSequence, searchFor: Regex): Int {
        var counter = 0
        val searchInString = searchIn.toString()
        var matchResult = searchFor.find(searchInString)
        while (matchResult != null) {
            ++counter
            val startIndex = matchResult.range.first.let { startIndex ->
                startIndex + if (searchInString[startIndex].isHighSurrogate()) 2 else 1
            }
            matchResult = searchFor.find(searchIn, startIndex)
        }
        return counter
    }
}
