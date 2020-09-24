package ch.tutteli.atrium.logic.creating.transformers.impl

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.creating.collectors.assertionCollector
import ch.tutteli.atrium.logic._logic
import ch.tutteli.atrium.logic.collect
import ch.tutteli.atrium.logic.creating.transformers.TransformationExecutionStep
import ch.tutteli.atrium.logic.toExpect

/**
 * Step which has all necessary information to perform a subject transformation (subject change/feature extraction etc.)
 * and now allows to decide how it should be done, especially regarding potential sub-assertions which should be applied
 * to the new resulting subject.
 *
 * @param T The type of the current [Expect], the current subject of the assertion respectively.
 * @param R The type of the new [Expect], the new subject of the assertion respectively.
 *
 * @property container [Expect] which was involved in the building process
 *   and holds assertion for the initial subject.
 * @property action An action such as transform, extract etc. which creates and returns a new [Expect] of type [R].
 * @property actionAndApply An action such as transform, extract etc. which not only creates and
 *   returns a new [Expect] of type [R] but also applies a given assertionCreator lambda.
 */
abstract class BaseTransformationExecutionStep<T, R, E : Expect<R>>(
    protected val container: AssertionContainer<T>,
    protected val action: AssertionContainer<T>.() -> E,
    protected val actionAndApply: AssertionContainer<T>.(Expect<R>.() -> Unit) -> Expect<R>
) : TransformationExecutionStep<T, R, E> {

    /**
     * Finishes the transformation process by appending the [Assertion]
     * which is returned when calling [collect] with the given [assertionCreator].
     *
     * See [collect] for more information.
     *
     * @return An [Expect] for the current subject of the assertion.
     */
    final override fun collectAndAppend(assertionCreator: Expect<R>.() -> Unit): Expect<T> =
        container.toExpect().addAssertion(collect(assertionCreator))

    /**
     * Finishes the transformation process by collecting the assertions the given [assertionCreator] creates
     * for the subject of type [R] resulting from the transformation
     * and returns the assertions as a single [Assertion].
     *
     * @returns An assertion consisting of all assertions the given [assertionCreator] creates
     *   for the resulting subject of type [R].
     */
    final override fun collect(assertionCreator: Expect<R>.() -> Unit): Assertion =
        container.collect { actionAndApply(this._logic, assertionCreator) }

    /**
     * Finishes the transformation process by simply performing the transformation as such.
     *
     * @return An [Expect] for the subject of type [R] resulting from the transformation.
     */
    final override fun transform(): E = action(container)

    /**
     * Finishes the transformation process by carrying the transformation out and appending
     * the assertions the given [assertionCreator] creates for the subject of type [R] resulting from the transformation
     * as single assertion.
     *
     * I.e. the assertions the given [assertionCreator] creates are first collected and then appended which has the
     * effect that we append a failing assertion in case [assertionCreator] does not create any assertion.
     *
     * @return The newly created [Expect] for the subject of type [R] resulting from the transformation.
     */
    final override fun transformAndAppend(assertionCreator: Expect<R>.() -> Unit): Expect<R> =
        actionAndApply(container, assertionCreator)

}
