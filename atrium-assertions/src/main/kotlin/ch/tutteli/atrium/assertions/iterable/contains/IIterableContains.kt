package ch.tutteli.atrium.assertions.iterable.contains

import ch.tutteli.atrium.assertions.IAssertion
import ch.tutteli.atrium.assertions.IAssertionGroup
import ch.tutteli.atrium.assertions.basic.contains.IContains
import ch.tutteli.atrium.creating.IAssertionPlant
import ch.tutteli.atrium.reporting.translating.ITranslatable

/**
 * Defines the contract for sophisticated [Iterable] `contains` assertions.
 */
interface IIterableContains {

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
    interface ICreator<in T : Iterable<*>, in S> : IContains.ICreator<T, S>

    /**
     * Represents a check for the search result such as: the object is contained exactly once in the input of the search.
     *
     * It provides the method [createAssertion] which creates an [IAssertion] representing this check.
     */
    interface IChecker : IContains.IChecker
}
