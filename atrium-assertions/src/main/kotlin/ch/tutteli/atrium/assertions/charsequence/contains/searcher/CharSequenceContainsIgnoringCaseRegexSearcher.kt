package ch.tutteli.atrium.assertions.charsequence.contains.searcher

import ch.tutteli.atrium.assertions.charsequence.contains.CharSequenceContainsAssertionCreator.ISearcher
import ch.tutteli.atrium.assertions.charsequence.contains.decorators.CharSequenceContainsIgnoringCaseDecorator
import java.util.regex.Pattern

/**
 * Represents an [ISearcher] which implements the [CharSequenceContainsIgnoringCaseDecorator] behaviour and evaluates
 * the matches of a given regular expression on the input stream.
 */
class CharSequenceContainsIgnoringCaseRegexSearcher : ISearcher<CharSequenceContainsIgnoringCaseDecorator> {
    private val searcher = CharSequenceContainsRegexSearcher()

    override fun search(searchIn: CharSequence, searchFor: Any): Int {
        val pattern = Pattern.compile(searchFor.toString(), Pattern.CASE_INSENSITIVE)
        return searcher.search(searchIn, pattern)
    }
}
