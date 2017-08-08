package ch.tutteli.atrium.assertions.charsequence.contains.searcher

import ch.tutteli.atrium.assertions.charsequence.contains.CharSequenceContainsAssertionCreator.ISearcher
import ch.tutteli.atrium.assertions.charsequence.contains.decorators.CharSequenceContainsIgnoringCaseDecorator

class CharSequenceContainsIgnoringCaseIndexSearcher : ISearcher<CharSequenceContainsIgnoringCaseDecorator> {
    val searcher = CharSequenceContainsIndexSearcher()

    override fun search(searchIn: CharSequence, searchFor: Any): Int
        = searcher.search(searchIn.toString().toUpperCase(), searchFor.toString().toUpperCase())
}
