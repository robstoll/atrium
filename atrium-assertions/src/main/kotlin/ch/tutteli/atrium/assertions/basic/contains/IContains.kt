package ch.tutteli.atrium.assertions.basic.contains

import ch.tutteli.atrium.assertions.IAssertion
import ch.tutteli.atrium.assertions.IAssertionGroup
import ch.tutteli.atrium.assertions.basic.contains.IContains.*
import ch.tutteli.atrium.creating.IAssertionPlant
import ch.tutteli.atrium.reporting.translating.ITranslatable

/**
 * Defines the basic contract for sophisticated `contains` assertion builders.
 *
 * A builder typically uses a [IDecorator] to define the search behaviour, an [IChecker] which creates [IAssertion]s
 * based on the search result and a [ICreator] which steers (or performs) the search, passes the result to the
 * [IChecker]s and creates an [IAssertionGroup] representing the sophisticated `contains` assertion as a whole.
 */
interface IContains {

    /**
     * Represents a search behaviour but leaves it up to [ICreator] how this behaviour is implemented -- yet, it
     * provides a method to decorate a description (an [ITranslatable]) in order that it reflects the search behaviour.
     */
    //TODO rename to ISearchBehaviour
    interface IDecorator {
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
    interface IChecker {
        /**
         * Creates an [IAssertion] representing this check based on the given [foundNumberOfTimes] which is the result
         * of the search.
         *
         * @return The newly created [IAssertion].
         */
        fun createAssertion(foundNumberOfTimes: Int): IAssertion
    }

    //TODO rename T1 and T2, should be T and S (for search criteria)
    /**
     * Represents the final step of a sophisticated `contains` assertion builder which creates the [IAssertionGroup]
     * as such.
     *
     * @param T1 The type of the [IAssertionPlant.subject].
     * @param T2 The type of the search criteria.
     */
    interface ICreator<in T1 : Any, in T2> {
        /**
         * Creates an [IAssertionGroup] representing the sophisticated `contains` assertion for the given [plant] based
         * on the given [expected] and [otherExpected].
         *
         * It is usually influenced by an [IDecorator] which defines the search behaviour and uses [IChecker]s which
         * create [IAssertion]s based on a determined search result. The created [IAssertionGroup] represents the
         * sophisticated `contains` assertion as a whole (including the assertions created by the [IChecker]s).
         *
         * @param plant The plant -- or rather its [subject][IAssertionPlant.subject] -- for which the [IAssertionGroup]
         *        is created.
         * @param expected A search criteria.
         * @param otherExpected Other search criteria (might be empty).
         *
         * @return The newly created [IAssertionGroup].
         */
        //TODO rename expected and otherExpected to searchCriteria and OtherSearchCriteria
        fun createAssertionGroup(plant: IAssertionPlant<T1>, expected: T2, otherExpected: Array<out T2>): IAssertionGroup
    }
}
