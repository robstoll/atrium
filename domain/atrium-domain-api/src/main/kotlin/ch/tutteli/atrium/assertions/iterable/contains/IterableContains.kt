package ch.tutteli.atrium.assertions.iterable.contains

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.assertions.basic.contains.Contains
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.reporting.translating.Translatable

/**
 * Defines the *deprecated* contract for sophisticated [Iterable] `contains` assertions.
 */
@Deprecated("use the interface from package creating, will be removed with 1.0.0", ReplaceWith("ch.tutteli.atrium.creating.iterable.contains.IterableContains"))
interface IterableContains {

    /**
     * Represents a search behaviour but leaves it up to the [Creator] how this behaviour is implemented -- yet, it
     * provides a method to decorate a description (a [Translatable]) in order that it reflects the search behaviour.
     */
    @Deprecated("use the interface from package creating, will be removed with 1.0.0", ReplaceWith("ch.tutteli.atrium.creating.iterable.contains.IterableContains.SearchBehaviour"))
    interface SearchBehaviour : Contains.SearchBehaviour, ch.tutteli.atrium.creating.iterable.contains.IterableContains.SearchBehaviour

    /**
     * Represents the final step of a sophisticated `contains` assertion builder which creates the [AssertionGroup]
     * as such.
     *
     * @param T The type of the [AssertionPlant.subject].
     * @param S The type of the search criteria.
     */
    @Deprecated("use the interface from package creating, will be removed with 1.0.0", ReplaceWith("ch.tutteli.atrium.creating.iterable.contains.IterableContains.Creator"))
    interface Creator<in T : Iterable<*>, in S> : Contains.Creator<T, S>, ch.tutteli.atrium.creating.iterable.contains.IterableContains.Creator<T, S>

    /**
     * Represents a check for the search result such as: the object is contained exactly once in the input of the search.
     *
     * It provides the method [createAssertion] which creates an [Assertion] representing this check.
     */
    @Deprecated("use the interface from package creating, will be removed with 1.0.0", ReplaceWith("ch.tutteli.atrium.creating.iterable.contains.IterableContains.Checker"))
    interface Checker : Contains.Checker, ch.tutteli.atrium.creating.iterable.contains.IterableContains.Checker
}
