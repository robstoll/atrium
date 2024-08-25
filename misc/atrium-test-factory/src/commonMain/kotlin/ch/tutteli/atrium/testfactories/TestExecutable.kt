package ch.tutteli.atrium.testfactories

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.creating.ExpectGrouping

/**
 * Represents a test as such and provides an abstraction over which expectation verb shall be used.
 *
 * If the platform supports hierarchical tests, then the used verb corresponds to a regular (root) expectation verb.
 * On the other hand, if the platform does not support it, then it corresponds to an expectation verb which is based on
 * [ExpectGrouping].
 *
 * @since 1.3.0
 */
interface TestExecutable {

    /**
     * Creates an [Expect] for the given [subject].
     *
     * @param subject The subject for which we are going to postulate expectations.
     *
     * @return The newly created [Expect].
     * @throws AssertionError in case an assertion does not hold.
     *
     * @since 1.3.0
     */
    fun <T> expect(subject: T): Expect<T>

    /**
     * Creates an [Expect] for the given [subject] and appends the expectations the given
     * [expectationCreator]-lambda creates as group to it.
     *
     * @param subject The subject for which we are going to postulate expectations.
     * @param expectationCreator expectation-group with a non-fail fast behaviour.
     * @return The newly created [Expect].
     * @throws AssertionError in case an assertion does not hold.
     *
     * @since 1.3.0
     */
    fun <T> expect(subject: T, expectationCreator: Expect<T>.() -> Unit): Expect<T>
}
