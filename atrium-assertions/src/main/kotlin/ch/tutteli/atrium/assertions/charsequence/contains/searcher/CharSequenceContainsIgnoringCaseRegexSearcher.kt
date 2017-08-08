package ch.tutteli.atrium.assertions.charsequence.contains.searcher

import ch.tutteli.atrium.assertions.charsequence.contains.CharSequenceContainsAssertionCreator.ISearcher
import ch.tutteli.atrium.assertions.charsequence.contains.decorators.CharSequenceContainsIgnoringCaseDecorator
import java.util.regex.Pattern

class CharSequenceContainsIgnoringCaseRegexSearcher : ISearcher<CharSequenceContainsIgnoringCaseDecorator> {
    val searcher = CharSequenceContainsRegexSearcher()

    override fun search(searchIn: CharSequence, searchFor: Any): Int {
        val pattern = Pattern.compile(searchFor.toString(), Pattern.CASE_INSENSITIVE)
        return searcher.search(searchIn, pattern)
    }
}
