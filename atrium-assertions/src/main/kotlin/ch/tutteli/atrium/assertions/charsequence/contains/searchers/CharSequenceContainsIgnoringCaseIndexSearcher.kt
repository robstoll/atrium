package ch.tutteli.atrium.assertions.charsequence.contains.searchers

import ch.tutteli.atrium.assertions.charsequence.contains.CharSequenceContainsAssertionCreator.ISearcher
import ch.tutteli.atrium.assertions.charsequence.contains.decorators.CharSequenceContainsIgnoringCaseDecorator

/**
 * Represents an [ISearcher] which implements the [CharSequenceContainsIgnoringCaseDecorator] behaviour and uses
 * [CharSequence.indexOf][kotlin.text.indexOf] to find expected objects.
 */
class CharSequenceContainsIgnoringCaseIndexSearcher : ISearcher<CharSequenceContainsIgnoringCaseDecorator> {
    private val searcher = CharSequenceContainsIndexSearcher()

    override fun search(searchIn: CharSequence, searchFor: Any): Int
        = searcher.search(searchIn.toString().toUpperCase(), searchFor.toString().toUpperCase())
}
