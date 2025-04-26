package ch.tutteli.atrium.creating.proofs.charsequence.contains.searchers.impl

import ch.tutteli.atrium.creating.proofs.charsequence.contains.CharSequenceToContain.Searcher
import ch.tutteli.atrium.creating.proofs.charsequence.contains.searchbehaviours.IgnoringCaseSearchBehaviour

/**
 * Represents a [Searcher] which implements the [IgnoringCaseSearchBehaviour] behaviour and uses
 * [CharSequence.indexOf] to find expected objects.
 */
class IgnoringCaseIndexSearcher : Searcher<IgnoringCaseSearchBehaviour, Any> {
    private val searcher = IndexSearcher()

    override fun search(searchIn: CharSequence, searchFor: Any): Int =
        searcher.search(searchIn.toString().toUpperCase(), searchFor.toString().toUpperCase())
}
