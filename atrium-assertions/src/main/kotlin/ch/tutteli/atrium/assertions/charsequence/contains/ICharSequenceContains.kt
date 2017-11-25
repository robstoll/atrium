package ch.tutteli.atrium.assertions.charsequence.contains

import ch.tutteli.atrium.assertions.IAssertion
import ch.tutteli.atrium.assertions.IAssertionGroup
import ch.tutteli.atrium.assertions.basic.contains.IContains
import ch.tutteli.atrium.creating.IAssertionPlant
import ch.tutteli.atrium.reporting.translating.ITranslatable

/**
 * Defines the contract for sophisticated [CharSequence] `contains` assertions.
 */
interface ICharSequenceContains {

    /**
     * Represents a search behaviour but leaves it up to the [ICreator] how this behaviour is implemented -- yet, it
     * provides a method to decorate a description (an [ITranslatable]) in order that it reflects the search behaviour.
     */
    interface ISearchBehaviour : IContains.ISearchBehaviour

    /**
     * Represents the final step of a sophisticated `contains` assertion builder which creates the [IAssertionGroup]
     * as such.
     *
     * @param T The type of the [IAssertionPlant.subject].
     * @param S The type of the search criteria.
     */
    interface ICreator<in T : CharSequence, in S> : IContains.ICreator<T, S>

    /**
     * Represents a check for the search result such as: the object is contained exactly once in the input of the
     * search.
     *
     * It provides the method [createAssertion] which creates an [IAssertion] representing this check.
     */
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
