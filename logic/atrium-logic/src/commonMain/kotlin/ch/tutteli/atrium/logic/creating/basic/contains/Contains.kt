//TODO remove file with 2.0.0 at the latest
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.logic.creating.basic.contains

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.creating.proofs.basic.contains.ToContain

//TODO 1.3.0 deprecate everything in this package and sub-packages

/**
 * Defines the basic contract for sophisticated `contains` assertion builders.
 *
 * The entry point for the contract constitutes a [Contains.EntryPointStep].
 * A builder typically allows a user to choose a desired [SearchBehaviour], one or more [Checker]s and uses an
 * [Creator] to finish the building process.
 */
@Deprecated(
    "Switch to core ToContain, will be removed with 2.0.0 at the latest",
    ReplaceWith("ch.tutteli.atrium.creating.basic.contains.ToContain")
)
interface Contains : ToContain {

    /**
     * The entry point of the contract.
     * Use `_logic` to retrieve the [EntryPointStepLogic] counterpart in case you want to extend the building process.
     */
    @Deprecated(
        "Switch to core ToContain.EntryPointStep, will be removed with 2.0.0 at the latest",
        ReplaceWith("ch.tutteli.atrium.creating.basic.contains.ToContain.EntryPointStep")
    )
    interface EntryPointStep<T : Any, out S : SearchBehaviour>

    /**
     * The entry point of the contract, containing the [container] -- i.e. the subject of this expectation
     * for which the sophisticated `contain` assertion should be created -- as well as the chosen [searchBehaviour].
     *
     * The [searchBehaviour] might me modified in which case it is recommended that a new [EntryPointStep] is created (retain
     * immutability).
     */
    @Deprecated(
        "Switch to core ToContain.EntryPointStepCore, will be removed with 2.0.0 at the latest",
        ReplaceWith("ch.tutteli.atrium.creating.basic.contains.ToContain.EntryPointStepCore")
    )
    interface EntryPointStepLogic<T : Any, out S : SearchBehaviour> {
        /**
         * The [AssertionContainer] from which this building process started and to which the resulting [Assertion]
         * should be appended.
         */
        val container: AssertionContainer<T>

        /**
         * The chosen [SearchBehaviour].
         */
        val searchBehaviour: S
    }

    /**
     * The step of choosing/defining [Checker]s.
     */
    @Deprecated(
        "Switch to core ToContain.CheckerStep, will be removed with 2.0.0 at the latest",
        ReplaceWith("ch.tutteli.atrium.creating.basic.contains.ToContain.CheckerStep")
    )
    interface CheckerStep<T : Any, out S : SearchBehaviour, out C : Checker, out B : EntryPointStep<T, S>>

    /**
     * The step of choosing/defining [Checker]s on the logic level.
     */
    @Deprecated(
        "Switch to core ToContain.CheckerStepCore, will be removed with 2.0.0 at the latest",
        ReplaceWith("ch.tutteli.atrium.creating.basic.contains.ToContain.CheckerStepCore")
    )
    interface CheckerStepLogic<T : Any, out S : SearchBehaviour, out C : Checker, out B : EntryPointStepLogic<T, S>> {
        /**
         * The previously chosen [EntryPointStep], containing inter alia the [AssertionContainer] to which the resulting
         * [Assertion] shall be appended.
         */
        val entryPointStepLogic: B

        /**
         * Contains all [Checker]s which should be applied to the search result.
         *
         * It typically contains the [Checker] this builder created and might contain other [Checker]s which builders,
         * precedent to this builder within the fluent API, created already.
         */
        val checkers: List<C>
    }

    /**
     * Represents a search behaviour but leaves it up to the [Creator] how this behaviour is implemented -- yet, it
     * provides a method to decorate a description (a [ch.tutteli.atrium.reporting.translating.Translatable]) in order that it reflects the search behaviour.
     */
    @Deprecated(
        "Switch to core ToContain.SearchBehaviour, will be removed with 2.0.0 at the latest",
        ReplaceWith("ch.tutteli.atrium.creating.basic.contains.ToContain.SearchBehaviour")
    )
    interface SearchBehaviour {
        /**
         * Decorates the given [description] so that it represents the search behaviour and returns the result.
         *
         * @return The decorated [description].
         */
        @Suppress("DEPRECATION")
        fun decorateDescription(description: ch.tutteli.atrium.reporting.translating.Translatable): ch.tutteli.atrium.reporting.translating.Translatable
    }

    /**
     * Represents a check for the search result such as: the object is contained exactly once in the input of the search.
     *
     * It provides the method [createAssertion] which creates an [Assertion] representing this check.
     */
    @Deprecated(
        "Switch to core ToContain.Checker, will be removed with 2.0.0 at the latest",
        ReplaceWith("ch.tutteli.atrium.creating.basic.contains.ToContain.Checker")
    )
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
     * @param T The type of the subject of this expectation.
     * @param SC The type of the search criteria.
     */
    @Deprecated(
        "Switch to core ToContain.Creator, will be removed with 2.0.0 at the latest",
        ReplaceWith("ch.tutteli.atrium.creating.basic.contains.ToContain.Creator")
    )
    interface Creator<T, in SC> {
        /**
         * Creates an [AssertionGroup] representing the sophisticated `contains` assertion for the
         * subject of the given [container], based on the given [searchCriteria].
         *
         * The search process as such is usually influenced by a [SearchBehaviour] which defines the search behaviour
         * and [Checker]s are used to create [Assertion]s based on a determined search result which are grouped
         * together into an [AssertionGroup].
         * This resulting [AssertionGroup] represents the sophisticated `contains` assertion as a whole.
         *
         * @param container The [AssertionContainer] from which this building process started.
         * @param searchCriteria The search criteria - typically not empty.
         *
         * @return The newly created [AssertionGroup].
         *
         * @throws IllegalArgumentException Might throw an [IllegalArgumentException] in case [searchCriteria] is empty
         *   and an empty value is not allowed.
         */
        fun createAssertionGroup(
            container: AssertionContainer<T>,
            searchCriteria: List<SC>
        ): AssertionGroup
    }
}
