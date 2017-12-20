package ch.tutteli.atrium.assertions.charsequence.contains

import ch.tutteli.atrium.assertions.IAssertion
import ch.tutteli.atrium.assertions.IAssertionGroup
import ch.tutteli.atrium.assertions.basic.contains.Contains
import ch.tutteli.atrium.creating.IAssertionPlant
import ch.tutteli.atrium.reporting.translating.Translatable

/**
 * Defines the contract for sophisticated [CharSequence] `contains` assertions.
 */
interface CharSequenceContains {

    /**
     * Represents a search behaviour but leaves it up to the [ISearcher] how this behaviour is implemented -- yet, it
     * provides a method to decorate a description (an [Translatable]) in order that it reflects the search behaviour.
     */
    interface SearchBehaviour : Contains.SearchBehaviour

    /**
     * Represents the final step of a sophisticated `contains` assertion builder which creates the [IAssertionGroup]
     * as such.
     *
     * @param T The type of the [IAssertionPlant.subject].
     * @param S The type of the search criteria.
     */
    interface Creator<in T : CharSequence, in S> : Contains.Creator<T, S>

    /**
     * Represents a check for the search result such as: the object is contained exactly once in the input of the
     * search.
     *
     * It provides the method [createAssertion] which creates an [IAssertion] representing this check.
     */
    interface Checker : Contains.Checker

    /**
     * Represents a searcher which supports the search behaviour [S] for a given input [CharSequence] of the search.
     *
     * @param S The search behaviour which should be applied to the input [CharSequence] in which the [ISearcher]
     *          will look for something -- the actual implementation of the search behaviour happens in the
     *          [ISearcher]; [ISearchBehaviour] only decorates the [ITranslatable] for reporting.
     */
    interface ISearcher<S : SearchBehaviour> {
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
