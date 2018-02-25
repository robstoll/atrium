package ch.tutteli.atrium.creating.iterable.contains

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.creating.basic.contains.Contains
import ch.tutteli.atrium.creating.charsequence.contains.CharSequenceContains
import ch.tutteli.atrium.reporting.translating.Translatable

/**
 * Defines the contract for sophisticated [Iterable] `contains` assertions.
 */
interface IterableContains {

    /**
     * The entry point of the [Iterable] `contains` contract, containing the [plant] to which the sophisticated `contain` assertion
     * should be added as well as the chosen [searchBehaviour].
     *
     * The [searchBehaviour] might me modified in which case it is recommended that a new [Builder] is created (retain
     * immutability).
     */
    interface Builder<out E, out T : Iterable<E>, out S : SearchBehaviour> : Contains.Builder<T, S>

    /**
     * The step of creating [Checker]s, containing the previously chosen [containsBuilder] and a list of so-far chosen
     * [checkers].
     */
    interface CheckerBuilder<out E, out T : Iterable<E>, out S : SearchBehaviour>
        : Contains.CheckerBuilder<T, S, Checker, Builder<E, T, S>>

    /**
     * Represents a search behaviour but leaves it up to the [Creator] how this behaviour is implemented -- yet, it
     * provides a method to decorate a description (a [Translatable]) in order that it reflects the search behaviour.
     */
    interface SearchBehaviour : Contains.SearchBehaviour

    /**
     * Represents the final step of a sophisticated `contains` assertion builder which creates the [AssertionGroup]
     * as such.
     *
     * @param T The type of the [AssertionPlant.subject].
     * @param S The type of the search criteria.
     */
    interface Creator<in T : Iterable<*>, in S> : Contains.Creator<T, S>

    /**
     * Represents a check for the search result such as: the object is contained exactly once in the input of the search.
     *
     * It provides the method [createAssertion] which creates an [Assertion] representing this check.
     */
    interface Checker : Contains.Checker
}

/**
 * Helper method to simplify adding assertions to the plant which itself is stored in
 * [CharSequenceContains.CheckerBuilder.containsBuilder].
 *
 * @return The plant to support a fluent API.
 */
fun <E, T : Iterable<E>, S : IterableContains.SearchBehaviour> IterableContains.CheckerBuilder<E, T, S>.addAssertion(
    assertion: Assertion
): AssertionPlant<T> = containsBuilder.plant.addAssertion(assertion)
