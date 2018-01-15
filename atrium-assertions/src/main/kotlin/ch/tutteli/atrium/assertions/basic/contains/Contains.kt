package ch.tutteli.atrium.assertions.basic.contains

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.assertions.basic.contains.Contains.*
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.reporting.translating.Translatable

/**
 * Defines the basic contract for sophisticated `contains` assertion builders.
 *
 * A builder typically allows a user to choose a desired [SearchBehaviour], one or more [Checker]s and uses an
 * [Creator] to finish the building process.
 */
interface Contains {

    /**
     * Represents a search behaviour but leaves it up to the [Creator] how this behaviour is implemented -- yet, it
     * provides a method to decorate a description (a [Translatable]) in order that it reflects the search behaviour.
     */
    interface SearchBehaviour {
        /**
         * Decorates the given [description] so that it represents the search behaviour and returns the result.
         *
         * @return The decorated [description].
         */
        fun decorateDescription(description: Translatable): Translatable
    }

    /**
     * Represents a check for the search result such as: the object is contained exactly once in the input of the search.
     *
     * It provides the method [createAssertion] which creates an [Assertion] representing this check.
     */
    interface Checker {
        /**
         * Creates an [Assertion] representing this check based on the given [foundNumberOfTimes] which is the result
         * of the search.
         *
         * @return The newly created [Assertion].
         */
        fun createAssertion(foundNumberOfTimes: Int): Assertion
    }

    /**
     * Represents the final step of a sophisticated `contains` assertion builder which creates the [AssertionGroup]
     * as such.
     *
     * @param T The type of the [AssertionPlant.subject].
     * @param S The type of the search criteria.
     */
    interface Creator<in T : Any, in S> {
        /**
         * Creates an [AssertionGroup] representing the sophisticated `contains` assertion for the given [plant] based
         * on the given [searchCriterion] and possibly [otherSearchCriteria] (might be empty).
         *
         * The search process as such is usually influenced by a [SearchBehaviour] which defines the search behaviour
         * and [Checker]s are used to create [Assertion]s based on a determined search result which are grouped
         * together into an [AssertionGroup].
         * This resulting [AssertionGroup] represents the sophisticated `contains` assertion as a whole.
         *
         * @param plant The plant -- or rather its [subject][AssertionPlant.subject] -- for which the [AssertionGroup]
         *   is created.
         * @param searchCriterion A search criterion.
         * @param otherSearchCriteria Other search criteria (might also be empty).
         *
         * @return The newly created [AssertionGroup].
         */
        fun createAssertionGroup(plant: AssertionPlant<T>, searchCriterion: S, otherSearchCriteria: Array<out S>): AssertionGroup
    }
}
