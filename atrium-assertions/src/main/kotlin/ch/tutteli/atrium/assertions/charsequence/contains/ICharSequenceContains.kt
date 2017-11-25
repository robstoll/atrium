package ch.tutteli.atrium.assertions.charsequence.contains

import ch.tutteli.atrium.assertions.basic.contains.IContains

/**
 * Defines the contract for sophisticated [CharSequence] `contains` assertions.
 */
interface ICharSequenceContains {

    interface ISearchBehaviour : IContains.ISearchBehaviour

    interface ICreator<in T : CharSequence, in S> : IContains.ICreator<T, S>

    interface IChecker : IContains.IChecker

    /**
     * Represents a searcher which supports the search behaviour [S] for a given input [CharSequence] of the search.
     *
     * @param S The search behaviour which should be applied to the input [CharSequence] in which the [ISearcher]
     *          will look for something -- the actual implementation of the search behaviour happens in the
     *          [ISearcher]; [ISearchBehaviour] only decorates the [ITranslatable] for reporting.
     */
    interface ISearcher<S : ISearchBehaviour> {
        /**
         * Searches in the given [searchIn] for the given [searchFor], using its [toString][Any.toString]
         * implementation, and returns the number of occurrences.
         *
         * Whether searches are disjoint or non-disjoint is up to the implementation.
         *
         * @param searchIn The input [CharSequence] in which this [ISearcher] shall search
         * @param searchFor The object which shall be found
         *
         * @return The number of occurrences of [searchFor] in [searchIn].
         */
        fun search(searchIn: CharSequence, searchFor: Any): Int
    }
}
