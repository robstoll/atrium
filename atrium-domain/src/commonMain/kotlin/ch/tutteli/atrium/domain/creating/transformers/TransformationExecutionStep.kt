package ch.tutteli.atrium.domain.creating.transformers

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.creating.ExpectationCreatorWithUsageHints
import ch.tutteli.atrium.creating.proofs.Proof
import ch.tutteli.atrium.reporting.reportables.Reportable

/**
 * Defines the minimal contract for the execution step of a subject transformation process -
 * i.e. the step after choosing all options.
 *
 * @param SubjectT The parameter type of the current [Expect], of its subject respectively.
 * @param SubjectAfterTransformationT The parameter type of the new [Expect], of its subject respectively.
 * @param ExpectForNewSubjectT The resulting [Expect] type for the new subject
 *
 * @since 1.3.0
 */
interface TransformationExecutionStep<SubjectT, SubjectAfterTransformationT, ExpectForNewSubjectT : Expect<SubjectAfterTransformationT>> {

    /**
     * Finishes the transformation process by appending the [Proof]
     * which is returned when calling [collect] with the given [expectationCreatorWithUsageHints].
     *
     * See [collect] for more information.
     *
     * @return an [Expect] for the subject of this expectation.
     */
    fun collectAndAppend(expectationCreatorWithUsageHints: ExpectationCreatorWithUsageHints<SubjectAfterTransformationT>): Expect<SubjectT>

    /**
     * Finishes the transformation process by collecting the proofs the given [expectationCreatorWithUsageHints] creates
     * for the subject of type [SubjectAfterTransformationT] resulting from the transformation
     * and returns the proofs as a single [Proof].
     *
     * @returns A [Proof] consisting of all proofs the given [expectationCreatorWithUsageHints] creates
     *   for the resulting subject of type [SubjectAfterTransformationT].
     */
    fun collect(expectationCreatorWithUsageHints: ExpectationCreatorWithUsageHints<SubjectAfterTransformationT>): Proof

    /**
     * Finishes the transformation process by simply performing the transformation as such.
     *
     * @return An [Expect] of type [ExpectForNewSubjectT] for the subject of type [SubjectAfterTransformationT]
     *   resulting from the transformation.
     */
    fun transform(): ExpectForNewSubjectT

    /**
     * Finishes the transformation process by carrying the transformation out and appending
     * the proofs the given [expectationCreatorWithUsageHints] creates for the subject of type
     * [SubjectAfterTransformationT] resulting from the transformation as single [Proof].
     *
     * The proofs the given [expectationCreatorWithUsageHints] creates are appended as hint in case the transformation
     * fails together with the provided [ExpectationCreatorWithUsageHints.usageHintsOverloadWithoutExpectationCreator].
     * This is also the difference between calling [transform] and state sub-expectations in a second call
     * as the sub-expectations the [expectationCreatorWithUsageHints] creates would not be available for reporting in case the
     * transformation cannot be carried out.
     *
     * @return The newly created [Expect] for the subject of type [SubjectAfterTransformationT] resulting from the
     *   transformation.
     */
    fun transformAndAppend(
        expectationCreatorWithUsageHints: ExpectationCreatorWithUsageHints<SubjectAfterTransformationT>
    ): Expect<SubjectAfterTransformationT>
}
