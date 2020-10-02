package ch.tutteli.atrium.domain.creating.changers

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.creating.collectors.assertionCollector

/**
 * Step which kind of holds the state of a previous final step related to a subject change/feature extraction etc.
 * and now allows to decide what should happen with it.
 *
 * @param T The type of the current [Expect], the current subject of the assertion respectively.
 * @param R The type of the new [Expect], the new subject of the assertion respectively.
 *
 * @property expect [Expect] which was involved in the building process
 *   and holds assertion for the initial subject.
 * @property action An action such as transform, extract etc. which creates and returns a new [Expect] of type [R].
 * @property actionAndApply An action such as transform, extract etc. which not only creates and
 *   returns a new [Expect] of type [R] but also applies a given assertionCreator lambda.
 */
@Deprecated("Use TransformationExecutionStep from atrium-logic; will be removed with 1.0.0")
abstract class PostFinalStep<T, R, E : Expect<R>>(
    protected val expect: Expect<T>,
    protected val action: Expect<T>.() -> E,
    protected val actionAndApply: Expect<T>.(Expect<R>.() -> Unit) -> Expect<R>
) {

    /**
     * Returns the newly created [Expect] for the feature.
     */
    fun getExpectOfFeature(): E = action(expect)

    /**
     * Collects the assertions the given [assertionCreator] might create for the new [Expect] of the feature
     * and returns them as a single [Assertion]
     *
     * @returns An assertion consisting of all assertions the given [assertionCreator] might create
     *   for the new [Expect] of the feature.
     */
    fun collect(assertionCreator: Expect<R>.() -> Unit): Assertion = assertionCollector.collect(expect) {
        actionAndApply(this, assertionCreator)
    }


    /**
     * Creates a new [Expect] for the feature, adds all assertions the given [assertionCreator] creates for it
     * and returns the new [Expect].
     * @returns An assertion consisting of all assertions the given [assertionCreator] might create
     *   for the new [Expect] of the feature.
     */
    fun addToFeature(assertionCreator: Expect<R>.() -> Unit): Expect<R> {
        return actionAndApply(expect) {
            // collect also checks that the user specified at least one assertion in the assertionCreator lambda
            addAssertion(assertionCollector.collect(this, assertionCreator))
        }
    }

    /**
     * Creates a new [Expect] for the feature, adds all assertions the given [assertionCreator] creates for it
     * and returns the initial [Expect].
     *
     * @returns An assertion consisting of all assertions the given [assertionCreator] might create
     *   for the new [Expect] of the feature.
     */
    fun addToInitial(assertionCreator: Expect<R>.() -> Unit): Expect<T> {
        // collect also checks that the user specified at least one assertion in the assertionCreator lambda
        return expect.addAssertion(collect(assertionCreator))
    }
}
