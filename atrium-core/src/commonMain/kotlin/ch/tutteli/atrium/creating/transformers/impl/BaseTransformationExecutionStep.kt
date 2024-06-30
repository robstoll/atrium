package ch.tutteli.atrium.creating.transformers.impl

import ch.tutteli.atrium._core
import ch.tutteli.atrium.creating.*
import ch.tutteli.atrium.creating.transformers.TransformationExecutionStep
import ch.tutteli.atrium.creating.proofs.Proof

/**
 * Step which has all necessary information to perform a subject transformation (subject change/feature extraction etc.)
 * and now allows to decide how it should be done, especially regarding potential sub-expectations which should be
 * applied to the new resulting subject.
 *
 * @param SubjectT The parameter type of the current [Expect], of its subject respectively.
 * @param SubjectAfterTransformationT The parameter type of the new [Expect], of its subject respectively.
 * @param ExpectForNewSubjectT The resulting [Expect] type for the new subject
 *
 * @property container [ProofContainer] which was involved in the building process and holds expectations for the
 *   initial subject.
 * @property action An action such as transform, extract etc. which creates and returns a new [Expect] of
 *   type [ExpectForNewSubjectT].
 * @property actionAndApply An action such as transform, extract etc. which not only creates and
 *   returns a new [Expect] of type [ExpectForNewSubjectT] but also applies a given [ExpectationCreator].
 */
abstract class BaseTransformationExecutionStep<SubjectT, SubjectAfterTransformationT, ExpectForNewSubjectT : Expect<SubjectAfterTransformationT>>(
    protected val container: ProofContainer<SubjectT>,
    protected val action: ProofContainer<SubjectT>.() -> ExpectForNewSubjectT,
    protected val actionAndApply: ProofContainer<SubjectT>.(ExpectationCreatorWithUsageHints<SubjectAfterTransformationT>) -> Expect<SubjectAfterTransformationT>
) : TransformationExecutionStep<SubjectT, SubjectAfterTransformationT, ExpectForNewSubjectT> {


    final override fun collectAndAppend(
        expectationCreatorWithUsageHints: ExpectationCreatorWithUsageHints<SubjectAfterTransformationT>
    ): Expect<SubjectT> =
        container.append(collect(expectationCreatorWithUsageHints))

    final override fun collect(
        expectationCreatorWithUsageHints: ExpectationCreatorWithUsageHints<SubjectAfterTransformationT>
    ): Proof =
        container.collect(ExpectationCreatorWithUsageHints(expectationCreatorWithUsageHints.usageHintsOverloadWithoutExpectationCreator) {
            actionAndApply(this._core, expectationCreatorWithUsageHints)
        })

    final override fun transform(): ExpectForNewSubjectT = action(container)

    final override fun transformAndAppend(
        expectationCreatorWithUsageHints: ExpectationCreatorWithUsageHints<SubjectAfterTransformationT>
    ): Expect<SubjectAfterTransformationT> =
        actionAndApply(container, expectationCreatorWithUsageHints)
}
