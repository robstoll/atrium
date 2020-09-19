package ch.tutteli.atrium.logic.creating.iterable.contains

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.logic.creating.basic.contains.Contains
import ch.tutteli.atrium.logic.creating.iterable.contains.IterableLikeContains.*
import ch.tutteli.atrium.logic.creating.iterable.contains.creators.IterableLikeContainsAssertions
import ch.tutteli.atrium.logic.creating.iterable.contains.steps.impl.EntryPointStepImpl
import ch.tutteli.atrium.logic.creating.typeutils.IterableLike
import ch.tutteli.atrium.reporting.translating.Translatable

/**
 * Defines the contract for sophisticated [Iterable] contains assertion builders.
 *
 * The building process is typically started by the creation of a [EntryPointStep],
 * goes on by specifying a desired [SearchBehaviour],
 * defines which [Checker]s should be applied and
 * is finalized by one of the [IterableLikeContainsAssertions] which usually use a [Creator].
 */
interface IterableLikeContains {

    /**
     * The entry point of the contract.
     *
     * Use `_logic` to retrieve the [EntryPointStepLogic] counterpart in case you want to extend the building process.
     */
    interface EntryPointStep<E, T : IterableLike, out S : SearchBehaviour> : Contains.EntryPointStep<T, S>

    /**
     * The entry point of the contract on the logic level, containing the [container] -- i.e. the subject of the assertion
     * for which the sophisticated `contain` assertion should be created -- as well as the chosen [searchBehaviour].
     *
     * The [searchBehaviour] might me modified in which case it is recommended that a new [EntryPointStep] is created (retain
     * immutability).
     */
    interface EntryPointStepLogic<E, T : IterableLike, out S : SearchBehaviour> : Contains.EntryPointStepLogic<T, S> {
        /**
         * The converter which shall be used to turn the subject of the assertion into an [Iterable] of type [E].
         */
        val converter: (T) -> Iterable<E>

        fun <S2: SearchBehaviour> withSearchBehaviour(searchBehaviour: S2) : EntryPointStep<E, T, S2> =
            EntryPointStepImpl(container, converter, searchBehaviour)
    }

    /**
     * Sole purpose of this interface is to hide [EntryPointStepLogic] from newcomers which
     * usually do not have to deal with this type and to keep the API clean.
     *
     * Moreover, this keeps the API clean and does not pollute it with things like `times`, `containsBuilder` etc.
     *
     * See https://github.com/robstoll/atrium-roadmap/wiki/Requirements#personas for more information about the personas.
     */
    interface EntryPointStepInternal<E, T : IterableLike, out S : SearchBehaviour> :
        EntryPointStep<E, T, S>,
        EntryPointStepLogic<E, T, S>


    /**
     * The step of choosing/defining [Checker]s.
     */
    interface CheckerStep<E, T : IterableLike, out S : SearchBehaviour>
        : Contains.CheckerStep<T, S, Checker, EntryPointStep<E, T, S>>

    /**
     * The step of choosing/defining [Checker]s on the logic level.
     */
    interface CheckerStepLogic<E, T : IterableLike, out S : SearchBehaviour>
        : Contains.CheckerStepLogic<T, S, Checker, EntryPointStepLogic<E, T, S>>

    /**
     * Sole purpose of this interface is to hide [CheckerStepLogic] from newcomers which
     * usually don't have to deal with this type and to keep the API clean.
     *
     * Moreover, this keeps the API clean and does not pollute it with things like `times`, `containsBuilder` etc.
     *
     * See https://github.com/robstoll/atrium-roadmap/wiki/Requirements#personas for more information about the personas.
     */
    interface CheckerStepInternal<E, T : IterableLike, out S : SearchBehaviour> :
        CheckerStep<E, T, S>,
        CheckerStepLogic<E, T, S>

    /**
     * Represents a search behaviour but leaves it up to the [Creator] how this behaviour is implemented -- yet, it
     * provides a method to decorate a description (a [Translatable]) in order that it reflects the search behaviour.
     */
    interface SearchBehaviour : Contains.SearchBehaviour

    /**
     * Represents the final step of a sophisticated `contains` assertion builder which creates the [AssertionGroup]
     * as such.
     *
     * @param T The type of the subject of the assertion.
     * @param SC The type of the search criteria.
     */
    interface Creator<T : IterableLike, in SC> : Contains.Creator<T, SC>

    /**
     * Represents a check for the search result such as:
     * the object is contained exactly once in the input of the search.
     *
     * It provides the method [createAssertion] which creates an [Assertion] representing this check.
     */
    interface Checker : Contains.Checker
}
