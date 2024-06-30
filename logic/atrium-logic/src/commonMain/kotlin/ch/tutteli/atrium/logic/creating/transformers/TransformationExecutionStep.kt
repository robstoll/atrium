//TODO remove file with 2.0.0 at the latest
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.logic.creating.transformers

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.Expect

/**
 * Defines the minimal contract for the execution step of a subject transformation process -
 * i.e. the step after choosing all options.
 */
interface TransformationExecutionStep<SubjectT, FeatureT, ExpectForFeatureT : Expect<FeatureT>> {

    /**
     * Finishes the transformation process by appending the [Assertion]
     * which is returned when calling [collect] with the given [assertionCreator].
     *
     * See [collect] for more information.
     *
     * @return an [Expect] for the subject of this expectation.
     */
    fun collectAndAppend(assertionCreator: Expect<FeatureT>.() -> Unit): Expect<SubjectT>

    /**
     * Finishes the transformation process by collecting the assertions the given [assertionCreator] creates
     * for the subject of type [FeatureT] resulting from the transformation
     * and returns the assertions as a single [Assertion].
     *
     * @returns An assertion consisting of all assertions the given [assertionCreator] creates
     *   for the resulting subject of type [FeatureT].
     */
    fun collect(assertionCreator: Expect<FeatureT>.() -> Unit): Assertion

    /**
     * Finishes the transformation process by simply performing the transformation as such.
     *
     * @return An [Expect] for the subject of type [FeatureT] resulting from the transformation.
     */
    fun transform(): ExpectForFeatureT

    /**
     * Finishes the transformation process by carrying the transformation out and appending
     * the assertions the given [assertionCreator] creates for the subject of type [FeatureT] resulting from the transformation
     * as single assertion.
     *
     * The assertions the given [assertionCreator] creates are appended as hint in case the transformation fails.
     * This is also the difference between calling [transform] and then append the sub-assertions in a second call
     * as the sub-assertions the [assertionCreator] creates would not be available for reporting in case the
     * transformation cannot be carried out.
     *
     * @return The newly created [Expect] for the subject of type [FeatureT] resulting from the transformation.
     */
    fun transformAndAppend(assertionCreator: Expect<FeatureT>.() -> Unit): Expect<FeatureT>
}
