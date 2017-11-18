package ch.tutteli.atrium.assertions.charsequence.contains.searchers

import ch.tutteli.atrium.assertions.charsequence.contains.ICharSequenceContains.ISearcher
import ch.tutteli.atrium.assertions.charsequence.contains.decorators.CharSequenceContainsIgnoringCaseDecorator
import java.util.regex.Pattern

/**
 * Represents an [ISearcher] which implements the [CharSequenceContainsIgnoringCaseDecorator] behaviour and evaluates
 * the matches of a given regular expression on the input of the search.
 */
class CharSequenceContainsIgnoringCaseRegexSearcher : ISearcher<CharSequenceContainsIgnoringCaseDecorator> {
    private val searcher = CharSequenceContainsRegexSearcher()

    override fun search(searchIn: CharSequence, searchFor: Any): Int {
        val pattern = Pattern.compile(searchFor.toString(), Pattern.CASE_INSENSITIVE)
        return searcher.search(searchIn, pattern)
    }
}
