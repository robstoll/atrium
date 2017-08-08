package ch.tutteli.atrium.assertions.charsequence

import ch.tutteli.atrium.assertions.charsequence.CharSequenceContainsAssertionCreator.ISearcher

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
