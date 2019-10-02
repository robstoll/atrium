@file:Suppress("DEPRECATION" /* TODO remove with 1.0.0 */)
package ch.tutteli.atrium.assertions.charsequence.contains.searchers

import ch.tutteli.atrium.assertions.charsequence.contains.CharSequenceContains.Searcher
import ch.tutteli.atrium.assertions.charsequence.contains.searchbehaviours.CharSequenceContainsNoOpSearchBehaviour
import java.util.regex.Pattern

/**
 * Represents a [Searcher] which evaluates the matches of a given regular expression on the input of the search.
 */
@Deprecated("Please open an issue if you used this class; will be removed with 1.0.0")
class CharSequenceContainsRegexSearcher : Searcher<CharSequenceContainsNoOpSearchBehaviour, Regex> {
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
