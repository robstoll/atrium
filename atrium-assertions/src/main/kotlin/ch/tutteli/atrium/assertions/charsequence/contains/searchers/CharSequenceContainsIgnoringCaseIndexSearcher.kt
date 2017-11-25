package ch.tutteli.atrium.assertions.charsequence.contains.searchers

import ch.tutteli.atrium.assertions.charsequence.contains.ICharSequenceContains.ISearcher
import ch.tutteli.atrium.assertions.charsequence.contains.searchbehaviours.CharSequenceContainsIgnoringCaseSearchBehaviour

/**
 * Represents an [ISearcher] which implements the [CharSequenceContainsIgnoringCaseSearchBehaviour] behaviour and uses
 * [CharSequence.indexOf][kotlin.text.indexOf] to find expected objects.
 */
class CharSequenceContainsIgnoringCaseIndexSearcher : ISearcher<CharSequenceContainsIgnoringCaseSearchBehaviour> {
    private val searcher = CharSequenceContainsIndexSearcher()

    override fun search(searchIn: CharSequence, searchFor: Any): Int
        = searcher.search(searchIn.toString().toUpperCase(), searchFor.toString().toUpperCase())
}
