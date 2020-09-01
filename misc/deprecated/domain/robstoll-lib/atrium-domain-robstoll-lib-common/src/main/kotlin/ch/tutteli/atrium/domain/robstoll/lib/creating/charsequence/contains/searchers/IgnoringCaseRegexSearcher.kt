//TODO remove with 1.0.0
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.domain.robstoll.lib.creating.charsequence.contains.searchers

import ch.tutteli.atrium.domain.creating.charsequence.contains.CharSequenceContains.Searcher
import ch.tutteli.atrium.domain.creating.charsequence.contains.searchbehaviours.IgnoringCaseSearchBehaviour

/**
 * Represents a [Searcher] which implements the [IgnoringCaseSearchBehaviour] behaviour and evaluates
 * the matches of a given regular expression on the input of the search.
 */
@Deprecated("Use class from atrium-logic; will be removed with 1.0.0")
class IgnoringCaseRegexSearcher : Searcher<IgnoringCaseSearchBehaviour, String> {
    private val searcher = RegexSearcher()

    override fun search(searchIn: CharSequence, searchFor: String): Int =
        searcher.search(searchIn, Regex(searchFor, RegexOption.IGNORE_CASE))
}
