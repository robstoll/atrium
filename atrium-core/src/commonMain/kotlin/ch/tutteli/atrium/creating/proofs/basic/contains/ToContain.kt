package ch.tutteli.atrium.creating.proofs.basic.contains

import ch.tutteli.atrium._core
import ch.tutteli.atrium.creating.ProofContainer
import ch.tutteli.atrium.creating.proofs.Proof
import ch.tutteli.atrium.creating.proofs.ProofGroup
import ch.tutteli.atrium.reporting.reportables.Description
import ch.tutteli.atrium.reporting.reportables.TextElement

/**
 * Defines the basic contract for sophisticated `toContain` expectation builders.
 *
 * The entry point for the contract constitutes a [ToContain.EntryPointStep].
 * A builder typically allows a user to choose a desired [SearchBehaviour], one or more [Checker]s and uses a
 * [Creator] to finish the building process.
 *
 * @since 1.3.0
 */
interface ToContain {

    /**
     * The entry point of the contract.
     * Use [_core] to retrieve the [EntryPointStepCore] counterpart in case you want to extend the building process.
     *
     * @since 1.3.0
     */
    interface EntryPointStep<SubjectT : Any, out SearchBehaviourT : SearchBehaviour>

    /**
     * The entry point of the contract, containing the [container] -- i.e. the subject of this expectation
     * for which the sophisticated `toContain` expectation should be created -- as well as the chosen [searchBehaviour].
     *
     * The [searchBehaviour] might me modified in which case it is recommended that a new [EntryPointStep] is created (retain
     * immutability).
     *
     * @since 1.3.0
     */
    interface EntryPointStepCore<SubjectT : Any, out SearchBehaviourT : SearchBehaviour> {
        /**
         * The [ProofContainer] from which this building process started and to which the resulting [Proof]
         * should be appended.
         *
         * @since 1.3.0
         */
        val container: ProofContainer<SubjectT>

        /**
         * The chosen [SearchBehaviour].
         *
         * @since 1.3.0
         */
        val searchBehaviour: SearchBehaviourT
    }

    /**
     * The step of choosing/defining [Checker]s.
     *
     * @since 1.3.0
     */
    interface CheckerStep<SubjectT : Any, out SearchBehaviourT : SearchBehaviour, out CheckerT : Checker, out EntryPointStepT : EntryPointStep<SubjectT, SearchBehaviourT>>

    /**
     * The step of choosing/defining [Checker]s on the logic level.
     *
     * @since 1.3.0
     */
    interface CheckerStepCore<SubjectT : Any, out SearchBehaviourT : SearchBehaviour, out CheckerT : Checker, out EntryPointStepT : EntryPointStepCore<SubjectT, SearchBehaviourT>> {
        /**
         * The previously chosen [EntryPointStep], containing inter alia the [ProofContainer] to which the resulting
         * [Proof] shall be appended.
         *
         * @since 1.3.0
         */
        val entryPointStepCore: EntryPointStepT

        /**
         * Contains all [Checker]s which should be applied to the search result.
         *
         * It typically contains the [Checker] this builder created and might contain other [Checker]s which builders,
         * precedent to this builder within the fluent API, created already.
         *
         * @since 1.3.0
         */
        val checkers: List<CheckerT>
    }

    /**
     * Represents a search behaviour but leaves it up to the [Creator] how this behaviour is implemented -- yet, it
     * provides a method to decorate a [Description] in order that it reflects the search behaviour.
     *
     * @since 1.3.0
     */
    interface SearchBehaviour {
        /**
         * Decorates the given [description] so that it represents the search behaviour and returns the result.
         *
         * @return The decorated [description].
         *
         * @since 1.3.0
         */
        fun decorateDescription(description: Description): TextElement
    }

    /**
     * Represents a check for the search result such as: the object is contained exactly once in the input of the search.
     *
     * It provides the method [createProof] which creates an [Proof] representing this check.
     *
     * @since 1.3.0
     */
    interface Checker {
        /**
         * Creates an [Proof] representing this check based on the given [foundNumberOfTimes] which is the result
         * of the search.
         *
         * @return The newly created [Proof].
         *
         * @since 1.3.0
         */
        fun createProof(foundNumberOfTimes: Int): Proof
    }

    /**
     * Represents the final step of a sophisticated `toContain` expectation builder which creates the [ProofGroup]
     * as such.
     *
     * @param SubjectT The type of the subject of this expectation.
     * @param SearchCriteriaT The type of the search criteria.
     *
     * @since 1.3.0
     */
    interface Creator<SubjectT, in SearchCriteriaT> {
        /**
         * Creates an [ProofGroup] representing the sophisticated `toContain` expectation for the
         * subject of the given [container], based on the given [searchCriteria].
         *
         * The search process as such is usually influenced by a [SearchBehaviour] which defines the search behaviour
         * and [Checker]s are used to create [Proof]s based on a determined search result which are grouped
         * together into an [ProofGroup].
         * This resulting [ProofGroup] represents the sophisticated `toContain` expectation as a whole.
         *
         * @param container The [ProofContainer] from which this building process started.
         * @param searchCriteria The search criteria - typically not empty.
         *
         * @return The newly created [ProofGroup].
         *
         * @throws IllegalArgumentException Might throw an [IllegalArgumentException] in case [searchCriteria] is empty
         *   and an empty value is not allowed.
         *
         * @since 1.3.0
         */
        fun createProofGroup(
            container: ProofContainer<SubjectT>,
            searchCriteria: List<SearchCriteriaT>
        ): ProofGroup
    }
}
