package ch.tutteli.atrium.assertions.charsequence.contains

import ch.tutteli.atrium.assertions.IAssertion
import ch.tutteli.atrium.assertions.base.contains.IContains
import ch.tutteli.atrium.assertions.charsequence.contains.creators.CharSequenceContainsAssertionCreator
import ch.tutteli.atrium.reporting.translating.ITranslatable

interface ICharSequenceContains {
    /**
     * Represents a decoration behaviour of the input [CharSequence] of the search but leaves it up to the [ISearcher]
     * to implement the behaviour -- yet, it provides a method to decorate the description (an [ITranslatable]) of the
     * resulting [IAssertion] produced by [CharSequenceContainsAssertionCreator].
     */
    interface IDecorator : IContains.IDecorator

    interface IChecker : IContains.IChecker

    /**
     * Represents a searcher which supports the decoration behaviour [D] for a given input [CharSequence] of the search.
     *
     * @param D The decoration behaviour which should be applied to the input [CharSequence] in which the [ISearcher]
     *          will look for something -- the actual decorator implementation happens in the [ISearcher]; [IDecorator]
     *          only decorates [ITranslatable] for reporting.
     */
    interface ISearcher<D : IDecorator> {
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
