package ch.tutteli.atrium.assertions.charsequence.contains.searchers

import ch.tutteli.atrium.assertions.charsequence.contains.CharSequenceContains.Searcher
import ch.tutteli.atrium.assertions.charsequence.contains.searchbehaviours.CharSequenceContainsIgnoringCaseSearchBehaviour
import java.util.regex.Pattern

/**
 * Represents a [Searcher] which implements the [CharSequenceContainsIgnoringCaseSearchBehaviour] behaviour and evaluates
 * the matches of a given regular expression on the input of the search.
 */
class CharSequenceContainsIgnoringCaseRegexSearcher : Searcher<CharSequenceContainsIgnoringCaseSearchBehaviour> {
    private val searcher = CharSequenceContainsRegexSearcher()

    override fun search(searchIn: CharSequence, searchFor: Any): Int {
        val pattern = Pattern.compile(searchFor.toString(), Pattern.CASE_INSENSITIVE)
        return searcher.search(searchIn, pattern)
    }
}
