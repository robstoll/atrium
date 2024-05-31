package ch.tutteli.atrium.domain.creating.transformers

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.creating.proofs.Proof

/**
 * Defines the minimal contract for the execution step of a subject transformation process -
 * i.e. the step after choosing all options.
 */
interface TransformationExecutionStep<SubjectT, FeatureT, ExpectForFeatureT : Expect<FeatureT>> {

    /**
     * Finishes the transformation process by appending the [Proof]
     * which is returned when calling [collect] with the given [expectationCreator].
     *
     * See [collect] for more information.
     *
     * @return an [Expect] for the subject of this expectation.
     */
    fun collectAndAppend(expectationCreator: Expect<FeatureT>.() -> Unit): Expect<SubjectT>

    /**
     * Finishes the transformation process by collecting the proofs the given [expectationCreator] creates
     * for the subject of type [FeatureT] resulting from the transformation
     * and returns the proofs as a single [Proof].
     *
     * @returns A [Proof] consisting of all proofs the given [expectationCreator] creates
     *   for the resulting subject of type [FeatureT].
     */
    fun collect(expectationCreator: Expect<FeatureT>.() -> Unit): Proof

    /**
     * Finishes the transformation process by simply performing the transformation as such.
     *
     * @return An [Expect] for the subject of type [FeatureT] resulting from the transformation.
     */
    fun transform(): ExpectForFeatureT

    /**
     * Finishes the transformation process by carrying the transformation out and appending
     * the proofs the given [expectationCreator] creates for the subject of type [FeatureT] resulting from the
     * transformation as single [Proof].
     *
     * The proofs the given [expectationCreator] creates are appended as hint in case the transformation fails.
     * This is also the difference between calling [transform] and state sub-expectations in a second call
     * as the sub-expectations the [expectationCreator] creates would not be available for reporting in case the
     * transformation cannot be carried out.
     *
     * @return The newly created [Expect] for the subject of type [FeatureT] resulting from the transformation.
     */
    fun transformAndAppend(expectationCreator: Expect<FeatureT>.() -> Unit): Expect<FeatureT>
}
