package ch.tutteli.atrium.domain.robstoll.lib.creating.charsequence.contains.searchers

import ch.tutteli.atrium.domain.creating.charsequence.contains.CharSequenceContains.Searcher
import ch.tutteli.atrium.domain.creating.charsequence.contains.searchbehaviours.IgnoringCaseSearchBehaviour

/**
 * Represents a [Searcher] which implements the [IgnoringCaseSearchBehaviour] behaviour and uses
 * [CharSequence.indexOf] to find expected objects.
 */
class IgnoringCaseIndexSearcher : Searcher<IgnoringCaseSearchBehaviour, Any> {
    private val searcher = IndexSearcher()

    override fun search(searchIn: CharSequence, searchFor: Any): Int =
        searcher.search(searchIn.toString().toUpperCase(), (searchFor as String).toUpperCase())
}
