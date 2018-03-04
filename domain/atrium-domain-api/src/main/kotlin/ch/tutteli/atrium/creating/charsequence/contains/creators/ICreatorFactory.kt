package ch.tutteli.atrium.creating.charsequence.contains.creators

import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.creating.charsequence.contains.CharSequenceContains

/**
 * Defines the minimum set of [CharSequenceContains.Creator]s an implementation of the domain of Atrium
 * has to provide.
 */
interface ICreatorFactory {

    /**
     * Returns a [CharSequenceContains.Searcher] based [CharSequenceContains.Creator] where the creator is only
     * responsible to create a resulting [AssertionGroup] based on the results of the given
     * [searcher] and the given [checkers].
     *
     * It typically does not implement search- or checking-behaviour itself (yet, still up to the implementation).
     *
     * @param searchBehaviour The [CharSequenceContains.SearchBehaviour] the [searcher] should take into account.
     * @param searcher The [CharSequenceContains.Searcher] which conducts the search.
     * @param checkers A non-empty list of [CharSequenceContains.Checker]s operating on the result of the [searcher].
     *
     * @return The newly created creator.
     */
    fun <T : CharSequence, SC: Any, S : CharSequenceContains.SearchBehaviour> newSearcherBased(
        searchBehaviour: S,
        searcher: CharSequenceContains.Searcher<S>,
        checkers: List<CharSequenceContains.Checker>
    ): CharSequenceContains.Creator<T, SC>
}
