package ch.tutteli.atrium.creating.charsequence.contains.searchers

import ch.tutteli.atrium.creating.charsequence.contains.CharSequenceContains.Searcher
import ch.tutteli.atrium.creating.charsequence.contains.searchbehaviours.IgnoringCaseSearchBehaviour

/**
 * Represents a [Searcher] which implements the [IgnoringCaseSearchBehaviour] behaviour and uses
 * [CharSequence.indexOf] to find expected objects.
 */
class CharSequenceContainsIgnoringCaseIndexSearcher : Searcher<IgnoringCaseSearchBehaviour> {
    private val searcher = CharSequenceContainsIndexSearcher()

    override fun search(searchIn: CharSequence, searchFor: Any): Int
        = searcher.search(searchIn.toString().toUpperCase(), searchFor.toString().toUpperCase())
}
