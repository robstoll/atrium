package ch.tutteli.atrium.domain.creating.transformers.impl

import ch.tutteli.atrium.creating.ProofContainer
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain._domain
import ch.tutteli.atrium.domain.collect
import ch.tutteli.atrium.domain.creating.transformers.TransformationExecutionStep
import ch.tutteli.atrium.creating.ExpectationCreator
import ch.tutteli.atrium.creating.ExpectationCreatorWithUsageHints
import ch.tutteli.atrium.creating.proofs.Proof
import ch.tutteli.atrium.domain.collectBasedOnGivenSubject
import ch.tutteli.atrium.reporting.reportables.Reportable

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
            actionAndApply(this._domain, expectationCreatorWithUsageHints)
        })

    final override fun transform(): ExpectForNewSubjectT = action(container)

    final override fun transformAndAppend(
        expectationCreatorWithUsageHints: ExpectationCreatorWithUsageHints<SubjectAfterTransformationT>
    ): Expect<SubjectAfterTransformationT> =
        actionAndApply(container, expectationCreatorWithUsageHints)
}
