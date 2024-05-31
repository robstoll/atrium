package ch.tutteli.atrium.domain.creating.transformers.impl

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.ProofContainer
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain._domain
import ch.tutteli.atrium.domain.collect
import ch.tutteli.atrium.domain.creating.transformers.TransformationExecutionStep

/**
 * Step which has all necessary information to perform a subject transformation (subject change/feature extraction etc.)
 * and now allows to decide how it should be done, especially regarding potential sub-assertions which should be applied
 * to the new resulting subject.
 *
 * @param SubjectT The parameter type of the current [Expect], of its subject respectively.
 * @param SubjectAfterTransformationT The parameter type of the new [Expect], of its subject respectively.
 *
 * @property container [Expect] which was involved in the building process
 *   and holds assertion for the initial subject.
 * @property action An action such as transform, extract etc. which creates and returns a new [Expect] of type [SubjectAfterTransformationT].
 * @property actionAndApply An action such as transform, extract etc. which not only creates and
 *   returns a new [Expect] of type [SubjectAfterTransformationT] but also applies a given assertionCreator lambda.
 */
abstract class BaseTransformationExecutionStep<SubjectT, SubjectAfterTransformationT, ExpectForNewSubjectT : Expect<SubjectAfterTransformationT>>(
    protected val container: ProofContainer<SubjectT>,
    protected val action: ProofContainer<SubjectT>.() -> ExpectForNewSubjectT,
    protected val actionAndApply: ProofContainer<SubjectT>.(Expect<SubjectAfterTransformationT>.() -> Unit) -> Expect<SubjectAfterTransformationT>
) : TransformationExecutionStep<SubjectT, SubjectAfterTransformationT, ExpectForNewSubjectT> {

    /**
     * Finishes the transformation process by appending the [Assertion]
     * which is returned when calling [collect] with the given [expectationCreator].
     *
     * See [collect] for more information.
     *
     * @return an [Expect] for the subject of this expectation.
     */
    final override fun collectAndAppend(expectationCreator: Expect<SubjectAfterTransformationT>.() -> Unit): Expect<SubjectT> =
        container.append(collect(expectationCreator))

    /**
     * Finishes the transformation process by collecting the assertions the given [expectationCreator] creates
     * for the subject of type [SubjectAfterTransformationT] resulting from the transformation
     * and returns the assertions as a single [Assertion].
     *
     * @returns An assertion consisting of all assertions the given [expectationCreator] creates
     *   for the resulting subject of type [SubjectAfterTransformationT].
     */
    final override fun collect(expectationCreator: Expect<SubjectAfterTransformationT>.() -> Unit): Assertion =
        container.collect { actionAndApply(this._domain, expectationCreator) }

    /**
     * Finishes the transformation process by simply performing the transformation as such.
     *
     * @return An [Expect] for the subject of type [SubjectAfterTransformationT] resulting from the transformation.
     */
    final override fun transform(): ExpectForNewSubjectT = action(container)

    /**
     * Finishes the transformation process by carrying the transformation out and appending
     * the assertions the given [expectationCreator] creates for the subject of type [SubjectAfterTransformationT] resulting from the transformation
     * as single assertion.
     *
     * I.e. the assertions the given [expectationCreator] creates are first collected and then appended which has the
     * effect that we append a failing assertion in case [expectationCreator] does not create any assertion.
     *
     * @return The newly created [Expect] for the subject of type [SubjectAfterTransformationT] resulting from the transformation.
     */
    final override fun transformAndAppend(expectationCreator: Expect<SubjectAfterTransformationT>.() -> Unit): Expect<SubjectAfterTransformationT> =
        actionAndApply(container, expectationCreator)

}
