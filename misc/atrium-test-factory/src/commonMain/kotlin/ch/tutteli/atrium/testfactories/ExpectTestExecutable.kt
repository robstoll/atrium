package ch.tutteli.atrium.testfactories

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.creating.ExpectGrouping
import ch.tutteli.atrium.creating.ExpectationVerbs

/**
 * A [TestExecutable] which provides `expect` as expectation verb.
 *
 * @since 1.3.0
 */
interface ExpectTestExecutable : TestExecutable, TestExecutableImplContract {
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


    /**
     * Creates an [ExpectGrouping] which can be used to group multiple unrelated subjects.
     *
     * @param description Description of the root group.
     * @param groupingActions Some action which defines what happens within the group (typically, creating some
     *   expectations via [ch.tutteli.atrium.api.verbs.expect] or nesting the grouping further).
     *
     * @since 1.3.0
     */
    fun expectGrouped(
        description: String?,
        groupingActions: ExpectGrouping.() -> Unit
    ): ExpectGrouping
}

expect fun expectTestExecutableFactory(expectationVerbs: ExpectationVerbs): () -> ExpectTestExecutable
