package ch.tutteli.atrium.creating.charsequence.contains.creators

import ch.tutteli.atrium.creating.charsequence.contains.CharSequenceContains
import ch.tutteli.atrium.creating.throwUnsupportedOperationException

/**
 * A dummy implementation of [ICreatorFactory] which should be replaced by an actual implementation.
 */
object CreatorFactory : ICreatorFactory {

    override fun <T : CharSequence, SC: Any, S : CharSequenceContains.SearchBehaviour> newSearcherBased(
        searchBehaviour: S,
        searcher: CharSequenceContains.Searcher<S>,
        checkers: List<CharSequenceContains.Checker>
    ): CharSequenceContains.Creator<T, SC> = throwUnsupportedOperationException()
}
