package ch.tutteli.atrium.assertions.basic.contains

import ch.tutteli.atrium.assertions.IAssertion
import ch.tutteli.atrium.assertions.IAssertionGroup
import ch.tutteli.atrium.assertions.charsequence.contains.ICharSequenceContains.ISearcher
import ch.tutteli.atrium.creating.IAssertionPlant
import ch.tutteli.atrium.reporting.translating.ITranslatable

interface IContains {

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

    /**
     * Represents the final step of a sophisticated `contains` assertion builder.
     */
    interface ICreator<in T1 : Any, in T2> {
        /**
         * Creates an [IAssertionGroup] representing the sophisticated `contains` assertion for the given [plant] based
         * on the given [expected] and [otherExpected].
         *
         * @return The newly created [IAssertionGroup].
         */
        fun createAssertionGroup(plant: IAssertionPlant<T1>, expected: T2, otherExpected: Array<out T2>): IAssertionGroup
    }
}
