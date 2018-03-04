package ch.tutteli.atrium.creating.charsequence.contains.searchers

import ch.tutteli.atrium.creating.charsequence.contains.CharSequenceContains.Searcher
import ch.tutteli.atrium.creating.charsequence.contains.searchbehaviours.IgnoringCaseSearchBehaviour
import java.util.regex.Pattern

/**
 * Represents a [Searcher] which implements the [IgnoringCaseSearchBehaviour] behaviour and evaluates
 * the matches of a given regular expression on the input of the search.
 */
class IgnoringCaseRegexSearcher : Searcher<IgnoringCaseSearchBehaviour> {
    private val searcher = RegexSearcher()

    override fun search(searchIn: CharSequence, searchFor: Any): Int {
        val pattern = Pattern.compile(searchFor.toString(), Pattern.CASE_INSENSITIVE)
        return searcher.search(searchIn, pattern)
    }
}
