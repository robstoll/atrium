package ch.tutteli.atrium.assertions.charsequence

import ch.tutteli.atrium.assertions.charsequence.CharSequenceContainsAssertionCreator.ISearcher

class CharSequenceContainsIgnoringCaseIndexSearcher : ISearcher<CharSequenceContainsIgnoringCaseDecorator> {
    val searcher = CharSequenceContainsIndexSearcher()
    override fun search(searchIn: CharSequence, searchFor: Any): Int {
        return searcher.search(searchIn.toString().toUpperCase(), searchFor.toString().toUpperCase())
    }
}
