package ch.tutteli.atrium.logic.creating.transformers

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.creating.Expect

/**
 * Defines the minimal contract for the execution step of a subject transformation process -
 * i.e. the step after choosing all options.
 */
interface TransformationExecutionStep<T, R, E : Expect<R>> {

    /**
     * Finishes the transformation process by appending the [Assertion]
     * which is returned when calling [collect] with the given [assertionCreator].
     *
     * See [collect] for more information.
     *
     * @return An [Expect] for the current subject of the assertion.
     */
    fun collectAndAppend(assertionCreator: Expect<R>.() -> Unit): Expect<T>

    /**
     * Finishes the transformation process by collecting the assertions the given [assertionCreator] creates
     * for the subject of type [R] resulting from the transformation
     * and returns the assertions as a single [Assertion].
     *
     * @returns An assertion consisting of all assertions the given [assertionCreator] creates
     *   for the resulting subject of type [R].
     */
    fun collect(assertionCreator: Expect<R>.() -> Unit): Assertion

    /**
     * Finishes the transformation process by simply performing the transformation as such.
     *
     * @return An [Expect] for the subject of type [R] resulting from the transformation.
     */
    fun transform(): E

    /**
     * Finishes the transformation process by carrying the transformation out and appending
     * the assertions the given [assertionCreator] creates for the subject of type [R] resulting from the transformation
     * as single assertion.
     *
     * The assertions the given [assertionCreator] creates are appended as hint in case the transformation fails.
     * This is also the difference between calling [transform] and then [Expect.addAssertionsCreatedBy] in a second call
     * as the sub-assertions [assertionCreator] creates would not be available in case the transformation cannot be
     * carried out.
     *
     * @return The newly created [Expect] for the subject of type [R] resulting from the transformation.
     */
    fun transformAndAppend(assertionCreator: Expect<R>.() -> Unit): Expect<R>
}
