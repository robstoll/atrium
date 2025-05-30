package ch.tutteli.atrium.creating.proofs.charsequence.contains.searchers.impl

import ch.tutteli.atrium.creating.proofs.charsequence.contains.CharSequenceToContain.Searcher
import ch.tutteli.atrium.creating.proofs.charsequence.contains.searchbehaviours.IgnoringCaseSearchBehaviour

/**
 * Represents a [Searcher] which implements the [IgnoringCaseSearchBehaviour] behaviour and evaluates
 * the matches of a given regular expression on the input of the search.
 */
class IgnoringCaseRegexSearcher : Searcher<IgnoringCaseSearchBehaviour, String> {
    private val searcher = RegexSearcher()

    override fun search(searchIn: CharSequence, searchFor: String): Int =
        searcher.search(searchIn, Regex(searchFor, RegexOption.IGNORE_CASE))
}
