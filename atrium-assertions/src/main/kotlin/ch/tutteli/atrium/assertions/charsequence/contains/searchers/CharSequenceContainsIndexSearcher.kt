package ch.tutteli.atrium.assertions.charsequence.contains.searchers

import ch.tutteli.atrium.assertions.charsequence.contains.creators.CharSequenceContainsAssertionCreator.ISearcher
import ch.tutteli.atrium.assertions.charsequence.contains.decorators.CharSequenceContainsNoOpDecorator

/**
 * Represents an [ISearcher] which uses [kotlin.text.indexOf] to find expected objects.
 */
class CharSequenceContainsIndexSearcher : ISearcher<CharSequenceContainsNoOpDecorator> {
    override fun search(searchIn: CharSequence, searchFor: Any): Int {
        val expected = searchFor.toString()
        var index = searchIn.indexOf(expected)
        var counter = 0
        while (index >= 0) {
            index = searchIn.indexOf(expected, index + 1)
            ++counter
        }
        return counter
    }
}
