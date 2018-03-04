package ch.tutteli.atrium.creating.charsequence.contains.creators

import ch.tutteli.atrium.creating.charsequence.contains.CharSequenceContains

/**
 * Robstoll's implementation of [ICreatorFactory].
 */
object CreatorFactory : ICreatorFactory {

    override fun <T : CharSequence, SC: Any, S : CharSequenceContains.SearchBehaviour> newSearcherBased(
        searchBehaviour: S,
        searcher: CharSequenceContains.Searcher<S>,
        checkers: List<CharSequenceContains.Checker>
    ): CharSequenceContains.Creator<T, SC> = SearcherBasedCreator(searchBehaviour, searcher, checkers)
}
