package ch.tutteli.atrium.assertions.basic.contains

import ch.tutteli.atrium.assertions.IAssertion
import ch.tutteli.atrium.assertions.IAssertionGroup
import ch.tutteli.atrium.assertions.basic.contains.Contains.*
import ch.tutteli.atrium.creating.IAssertionPlant
import ch.tutteli.atrium.reporting.translating.ITranslatable

/**
 * Defines the basic contract for sophisticated `contains` assertion builders.
 *
 * A builder typically allows a user to choose a desired [SearchBehaviour], one or more [Checker]s and uses an
 * [Creator] to finish the building process.
 */
interface Contains {

    /**
     * Represents a search behaviour but leaves it up to the [Creator] how this behaviour is implemented -- yet, it
     * provides a method to decorate a description (an [ITranslatable]) in order that it reflects the search behaviour.
     */
    interface SearchBehaviour {
        /**
         * Decorates the given [description] so that it represents the search behaviour and returns the result.
         *
         * @return The decorated [description].
         */
        fun decorateDescription(description: ITranslatable): ITranslatable
    }

    /**
     * Represents a check for the search result such as: the object is contained exactly once in the input of the search.
     *
     * It provides the method [createAssertion] which creates an [IAssertion] representing this check.
     */
    interface Checker {
        /**
         * Creates an [IAssertion] representing this check based on the given [foundNumberOfTimes] which is the result
         * of the search.
         *
         * @return The newly created [IAssertion].
         */
        fun createAssertion(foundNumberOfTimes: Int): IAssertion
    }

    /**
     * Represents the final step of a sophisticated `contains` assertion builder which creates the [IAssertionGroup]
     * as such.
     *
     * @param T The type of the [IAssertionPlant.subject].
     * @param S The type of the search criteria.
     */
    interface Creator<in T : Any, in S> {
        /**
         * Creates an [IAssertionGroup] representing the sophisticated `contains` assertion for the given [plant] based
         * on the given [searchCriterion] and possibly [otherSearchCriteria] (might be empty).
         *
         * The search process as such is usually influenced by an [SearchBehaviour] which defines the search behaviour
         * and [Checker]s are used to create [IAssertion]s based on a determined search result which are grouped
         * together into an [IAssertionGroup].
         * This resulting [IAssertionGroup] represents the sophisticated `contains` assertion as a whole.
         *
         * @param plant The plant -- or rather its [subject][IAssertionPlant.subject] -- for which the [IAssertionGroup]
         *        is created.
         * @param searchCriterion A search criterion.
         * @param otherSearchCriteria Other search criteria (might also be empty).
         *
         * @return The newly created [IAssertionGroup].
         */
        fun createAssertionGroup(plant: IAssertionPlant<T>, searchCriterion: S, otherSearchCriteria: Array<out S>): IAssertionGroup
    }
}
