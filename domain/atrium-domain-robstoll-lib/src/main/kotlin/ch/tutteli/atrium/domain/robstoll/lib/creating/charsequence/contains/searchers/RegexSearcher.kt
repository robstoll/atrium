package ch.tutteli.atrium.domain.robstoll.lib.creating.charsequence.contains.searchers

import ch.tutteli.atrium.domain.creating.charsequence.contains.CharSequenceContains.Searcher
import ch.tutteli.atrium.domain.creating.charsequence.contains.searchbehaviours.NoOpSearchBehaviour
import java.util.regex.Pattern

/**
 * Represents a [Searcher] which evaluates the matches of a given regular expression on the input of the search.
 */
class RegexSearcher : Searcher<NoOpSearchBehaviour> {
    override fun search(searchIn: CharSequence, searchFor: Any): Int
        = search(searchIn, Pattern.compile(searchFor.toString()))

    fun search(searchIn: CharSequence, pattern: Pattern): Int {
        var index = 0
        var counter = 0
        val matcher = pattern.matcher(searchIn)
        while (matcher.find(index)) {
            index = matcher.start() + 1
            ++counter
        }
        return counter
    }
}
