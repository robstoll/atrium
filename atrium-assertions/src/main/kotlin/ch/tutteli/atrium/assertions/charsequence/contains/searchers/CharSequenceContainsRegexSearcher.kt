package ch.tutteli.atrium.assertions.charsequence.contains.searchers

import ch.tutteli.atrium.assertions.charsequence.contains.CharSequenceContainsAssertionCreator.ISearcher
import ch.tutteli.atrium.assertions.charsequence.contains.decorators.CharSequenceContainsNoOpDecorator
import java.util.regex.Pattern

/**
 * Represents an [ISearcher] which evaluates the matches of a given regular expression on the input of the search.
 */
class CharSequenceContainsRegexSearcher : ISearcher<CharSequenceContainsNoOpDecorator> {
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
