package ch.tutteli.atrium.creating.proofs.charsequence.contains

import ch.tutteli.atrium._core
import ch.tutteli.atrium.creating.proofs.Proof
import ch.tutteli.atrium.creating.proofs.ProofGroup
import ch.tutteli.atrium.creating.proofs.basic.contains.ToContain
import ch.tutteli.atrium.creating.proofs.charsequence.contains.creators.CharSequenceToContainProofs

/**
 * Defines the contract for sophisticated [CharSequence] `toContain` expectation builders.
 *
 * The building process is typically started by the creation of a [EntryPointStep],
 * goes on by specifying a desired [SearchBehaviour],
 * defines which [Checker]s should be applied and
 * is finalized by one of the [CharSequenceToContainProofs]
 * which usually use a [Creator] which in turn uses a [Searcher].
 *
 * @since 1.3.0
 */
interface CharSequenceToContain {

    /**
     * The entry point of the contract.
     * Use [_core] to retrieve the [EntryPointStepCore] counterpart in case you want to extend the building process.
     *
     * @since 1.3.0
     */
    interface EntryPointStep<SubjectT : CharSequence, out SearchBehaviourT : SearchBehaviour> :
        ToContain.EntryPointStep<SubjectT, SearchBehaviourT>

    /**
     * The entry point of the contract on the core level, containing the [container] -- i.e. the subject of this
     * expectation for which the sophisticated `toContain` expectation should be created -- as well as the
     * chosen [searchBehaviour].
     *
     * The [searchBehaviour] might be modified in which case it is recommended that a new [EntryPointStep] is
     * created (retain immutability).
     *
     * @since 1.3.0
     */
    interface EntryPointStepCore<SubjectT : CharSequence, out SearchBehaviourT : SearchBehaviour> :
        ToContain.EntryPointStepCore<SubjectT, SearchBehaviourT>

    /**
     * Sole purpose of this interface is to hide [EntryPointStepCore] from newcomers which
     * usually do not have to deal with this type.
     *
     * Moreover, this keeps the API clean and does not pollute it with things like `times`, `toContainBuilder` etc.
     *
     * See https://github.com/robstoll/atrium-roadmap/wiki/Requirements#personas for more information about the personas.
     *
     * @since 1.3.0
     */
    interface EntryPointStepInternal<SubjectT : CharSequence, out SearchBehaviourT : SearchBehaviour> :
        EntryPointStep<SubjectT, SearchBehaviourT>, EntryPointStepCore<SubjectT, SearchBehaviourT>

    /**
     * The step of choosing/defining [Checker]s.
     */
    interface CheckerStep<SubjectT : CharSequence, out SearchBehaviourT : SearchBehaviour>
        : ToContain.CheckerStep<SubjectT, SearchBehaviourT, Checker, EntryPointStep<SubjectT, SearchBehaviourT>>

    /**
     * The step of choosing/defining [Checker]s on the logic level.
     */
    interface CheckerStepCore<T : CharSequence, out S : SearchBehaviour>
        : ToContain.CheckerStepCore<T, S, Checker, EntryPointStepCore<T, S>>

    /**
     * Sole purpose of this interface is to hide [CheckerStepCore] from newcomers which
     * usually don't have to deal with this type.
     *
     * Moreover, this keeps the API clean and does not pollute it with things like `times`, `containsBuilder` etc.
     *
     * See https://github.com/robstoll/atrium-roadmap/wiki/Requirements#personas for more information about the personas.
     */
    interface CheckerStepInternal<SubjectT : CharSequence, out SearchBehaviourT : SearchBehaviour> :
        CheckerStep<SubjectT, SearchBehaviourT>,
        CheckerStepCore<SubjectT, SearchBehaviourT>

    /**
     * Represents a search behaviour but leaves it up to the [Searcher] how this behaviour is implemented -- yet, it
     * provides a method to decorate a description (a [ch.tutteli.atrium.reporting.reportables.Description])
     * in order that it reflects the search behaviour.
     */
    interface SearchBehaviour : ToContain.SearchBehaviour

    /**
     * Represents the final step of a sophisticated [CharSequence] `toContain` expectation builder which creates
     * the [ProofGroup] as such.
     *
     * @param T The type of the subject of this expectation.
     * @param SC The type of the search criteria.
     */
    interface Creator<T : CharSequence, in SC> : ToContain.Creator<T, SC>

    /**
     * Represents a check for the search result such as: the object is contained exactly once in the input of the
     * search.
     *
     * It provides the method [createProof] which creates an [Proof] representing this check.
     */
    interface Checker : ToContain.Checker

    /**
     * Represents a searcher which supports the search behaviour [S] for a given input [CharSequence] of the search.
     *
     * @param S The search behaviour which should be applied to the input [CharSequence] in which the [Searcher]
     *   will look for something -- the actual implementation of the search behaviour happens in the
     *   [Searcher]; [SearchBehaviour] only decorates the [ch.tutteli.atrium.reporting.reportables.Description]
     *   for reporting.
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

