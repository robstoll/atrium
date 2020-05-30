package ch.tutteli.atrium.domain.creating.iterable.contains

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.creating.SubjectProvider
import ch.tutteli.atrium.domain.creating.basic.contains.Contains
import ch.tutteli.atrium.domain.creating.iterable.contains.IterableContains.*
import ch.tutteli.atrium.domain.creating.iterable.contains.creators.IterableContainsAssertions
import ch.tutteli.atrium.reporting.translating.Translatable

/**
 * Defines the contract for sophisticated [Iterable] `contains` [Assertion] builders.
 *
 * The building process is typically started by the creation of a [Builder],
 * goes on by specifying a desired [SearchBehaviour],
 * defines which [Checker]s should be applied and
 * is finalized by one of the [IterableContainsAssertions] which usually use a [Creator].
 */
interface IterableContains {

    /**
     * The entry point of the contract, containing the [subjectProvider] -- i.e. the subject of the assertion
     * for which the sophisticated `contain` assertion should be created -- as well as the chosen [searchBehaviour].
     *
     * The [searchBehaviour] might me modified in which case it is recommended that a new [Builder] is created (retain
     * immutability).
     */
    interface Builder<out E, out T : Iterable<E>, out S : SearchBehaviour> : Contains.Builder<T, S>

    /**
     * The step of choosing/defining [Checker]s.
     */
    interface CheckerOption<out E, out T : Iterable<E>, out S : SearchBehaviour>
        : Contains.CheckerOption<T, S, Checker, Builder<E, T, S>>

    /**
     * Represents a search behaviour but leaves it up to the [Creator] how this behaviour is implemented -- yet, it
     * provides a method to decorate a description (a [Translatable]) in order that it reflects the search behaviour.
     */
    interface SearchBehaviour : Contains.SearchBehaviour

    /**
     * Represents the final step of a sophisticated `contains` assertion builder which creates the [AssertionGroup]
     * as such.
     *
     * @param T The type of the [AssertionPlant.subject][SubjectProvider.subject].
     * @param SC The type of the search criteria.
     */
    interface Creator<in T : Iterable<*>, in SC> : Contains.Creator<T, SC>

    /**
     * Represents a check for the search result such as:
     * the object is contained exactly once in the input of the search.
     *
     * It provides the method [createAssertion] which creates an [Assertion] representing this check.
     */
    interface Checker : Contains.Checker
}
