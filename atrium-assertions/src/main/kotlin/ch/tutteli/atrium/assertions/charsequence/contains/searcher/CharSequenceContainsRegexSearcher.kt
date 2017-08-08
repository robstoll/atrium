package ch.tutteli.atrium.assertions.charsequence.contains.searcher

import ch.tutteli.atrium.assertions.charsequence.contains.CharSequenceContainsAssertionCreator.ISearcher
import ch.tutteli.atrium.assertions.charsequence.contains.decorators.CharSequenceContainsNoOpDecorator
import java.util.regex.Pattern

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
