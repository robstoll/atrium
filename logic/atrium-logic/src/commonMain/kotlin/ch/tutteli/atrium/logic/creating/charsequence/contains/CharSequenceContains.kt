package ch.tutteli.atrium.logic.creating.charsequence.contains

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.logic.creating.basic.contains.Contains
import ch.tutteli.atrium.logic.creating.charsequence.contains.CharSequenceContains.*
import ch.tutteli.atrium.logic.creating.charsequence.contains.creators.CharSequenceContainsAssertions

/**
 * Defines the contract for sophisticated [CharSequence] contains assertion builders.
 *
 * The building process is typically started by the creation of a [EntryPointStep],
 * goes on by specifying a desired [SearchBehaviour],
 * defines which [Checker]s should be applied and
 * is finalized by one of the [CharSequenceContainsAssertions]
 * which usually use a [Creator] which in turn uses a [Searcher].
 */
interface CharSequenceContains {

    /**
     * The entry point of the contract.
     * Use `_logic` to retrieve the [EntryPointStepLogic] counterpart in case you want to extend the building process.
     */
    interface EntryPointStep<T : CharSequence, out S : SearchBehaviour> : Contains.EntryPointStep<T, S>

    /**
     * The entry point of the contract on the logic level, containing the [container] -- i.e. the subject of this expectation
     * for which the sophisticated `contain` assertion should be created -- as well as the chosen [searchBehaviour].
     *
     * The [searchBehaviour] might me modified in which case it is recommended that a new [EntryPointStep] is created (retain
     * immutability).
     */
    interface EntryPointStepLogic<T : CharSequence, out S : SearchBehaviour> : Contains.EntryPointStepLogic<T, S>

    /**
     * Sole purpose of this interface is to hide [EntryPointStepLogic] from newcomers which
     * usually do not have to deal with this type and to keep the API clean.
     *
     * Moreover, this keeps the API clean and does not pollute it with things like `times`, `containsBuilder` etc.
     *
     * See https://github.com/robstoll/atrium-roadmap/wiki/Requirements#personas for more information about the personas.
     */
    interface EntryPointStepInternal<T : CharSequence, out S : SearchBehaviour> : EntryPointStep<T, S>, EntryPointStepLogic<T, S>

    /**
     * The step of choosing/defining [Checker]s.
     */
    interface CheckerStep<T : CharSequence, out S : SearchBehaviour>
        : Contains.CheckerStep<T, S, Checker, EntryPointStep<T, S>>

    /**
     * The step of choosing/defining [Checker]s on the logic level.
     */
    interface CheckerStepLogic<T : CharSequence, out S : SearchBehaviour>
        : Contains.CheckerStepLogic<T, S, Checker, EntryPointStepLogic<T, S>>

    /**
     * Sole purpose of this interface is to hide [CheckerStepLogic] from newcomers which
     * usually don't have to deal with this type and to keep the API clean.
     *
     * Moreover, this keeps the API clean and does not pollute it with things like `times`, `containsBuilder` etc.
     *
     * See https://github.com/robstoll/atrium-roadmap/wiki/Requirements#personas for more information about the personas.
     */
    interface CheckerStepInternal<T : CharSequence, out S : SearchBehaviour> :
        CheckerStep<T, S>,
        CheckerStepLogic<T, S>

    /**
     * Represents a search behaviour but leaves it up to the [Searcher] how this behaviour is implemented -- yet, it
     * provides a method to decorate a description (a [ch.tutteli.atrium.reporting.translating.Translatable]) in order that it reflects the search behaviour.
     */
    interface SearchBehaviour : Contains.SearchBehaviour

    /**
     * Represents the final step of a sophisticated `contains` assertion builder which creates the [AssertionGroup]
     * as such.
     *
     * @param T The type of the subject of this expectation.
     * @param SC The type of the search criteria.
     */
    interface Creator<T : CharSequence, in SC> : Contains.Creator<T, SC>

    /**
     * Represents a check for the search result such as: the object is contained exactly once in the input of the
     * search.
     *
     * It provides the method [createAssertion] which creates an [Assertion] representing this check.
     */
    interface Checker : Contains.Checker

    /**
     * Represents a searcher which supports the search behaviour [S] for a given input [CharSequence] of the search.
     *
     * @param S The search behaviour which should be applied to the input [CharSequence] in which the [Searcher]
     *   will look for something -- the actual implementation of the search behaviour happens in the
     *   [Searcher]; [SearchBehaviour] only decorates the [ch.tutteli.atrium.reporting.translating.Translatable] for reporting.
     * @param SC The search criterion
     */
    interface Searcher<S : SearchBehaviour, SC> {
        /**
         * Searches in the given [searchIn] for the given [searchFor], using its [toString][Any.toString]
         * implementation, and returns the number of occurrences.
         *
         * Whether searches are disjoint or non-disjoint is up to the implementation.
         *
         * @param searchIn The input [CharSequence] in which this [Searcher] shall search
         * @param searchFor The object which shall be found
         *
         * @return The number of occurrences of [searchFor] in [searchIn].
         */
        fun search(searchIn: CharSequence, searchFor: SC): Int
    }
}

