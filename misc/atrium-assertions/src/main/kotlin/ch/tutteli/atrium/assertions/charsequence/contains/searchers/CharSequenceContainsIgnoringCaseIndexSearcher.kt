package ch.tutteli.atrium.assertions.charsequence.contains.searchers

import ch.tutteli.atrium.assertions.charsequence.contains.CharSequenceContains.Searcher
import ch.tutteli.atrium.assertions.charsequence.contains.searchbehaviours.CharSequenceContainsIgnoringCaseSearchBehaviour

/**
 * Represents a [Searcher] which implements the [CharSequenceContainsIgnoringCaseSearchBehaviour] behaviour and uses
 * [CharSequence.indexOf] to find expected objects.
 */
@Deprecated("Please open an issue if you used this class, will be removed with 1.0.0")
class CharSequenceContainsIgnoringCaseIndexSearcher : Searcher<CharSequenceContainsIgnoringCaseSearchBehaviour> {
    private val searcher = CharSequenceContainsIndexSearcher()

    override fun search(searchIn: CharSequence, searchFor: Any): Int
        = searcher.search(searchIn.toString().toUpperCase(), searchFor.toString().toUpperCase())
}
