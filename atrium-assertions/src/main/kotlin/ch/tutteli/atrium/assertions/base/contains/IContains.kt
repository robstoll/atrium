package ch.tutteli.atrium.assertions.base.contains

import ch.tutteli.atrium.assertions.IAssertion
import ch.tutteli.atrium.assertions.charsequence.contains.creators.CharSequenceContainsAssertionCreator
import ch.tutteli.atrium.assertions.charsequence.contains.creators.CharSequenceContainsAssertionCreator.ISearcher
import ch.tutteli.atrium.reporting.translating.ITranslatable

interface IContains {
    /**
     * Represents a decoration behaviour of the input [CharSequence] of the search but leaves it up to the [ISearcher]
     * to implement the behaviour -- yet, it provides a method to decorate the description (an [ITranslatable]) of the
     * resulting [IAssertion] produced by [CharSequenceContainsAssertionCreator].
     */
    interface IDecorator {
        /**
         * Decorates the given [description] and returns the result.
         *
         * @return The decorated [description].
         */
        fun decorateDescription(description: ITranslatable): ITranslatable
    }

    /**
     * Represents a check for the search result such as: the object is contained exactly once in the input string.
     *
     * It provides the method [createAssertion] which creates a corresponding Assertion which represents this check.
     */
    interface IChecker {
        /**
         * Creates an [IAssertion] representing the check based on the given [foundNumberOfTimes] which is the result
         * of the search (usually produced by an [ISearcher]).
         *
         * @return The newly created [IAssertion].
         */
        fun createAssertion(foundNumberOfTimes: Int): IAssertion
    }
}
