package ch.tutteli.atrium.api.verbs.internal.factory

import ch.tutteli.atrium.creating.ExpectationVerbs
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.creating.ExpectGrouping
import ch.tutteli.atrium.api.verbs.internal.expect as internalExpect

/**
 * Represents a facade to all the Atrium internal expectation verb functions.
 *
 * Use with caution, we might change the implementation without prior notice.
 *
 * @since 1.3.0
 */
object InternalExpectationVerbs : ExpectationVerbs {
    override fun <T> expect(subject: T): Expect<T> =
        internalExpect(subject)

    override fun <T> expect(subject: T, expectationCreator: Expect<T>.() -> Unit): Expect<T> =
        internalExpect(subject)

    override fun expectGrouped(description: String?, groupingActions: ExpectGrouping.() -> Unit): ExpectGrouping =
        ch.tutteli.atrium.api.verbs.internal.expectGrouped(
            description = "my expectations",
            groupingActions = groupingActions
        )

    override fun <T> expectInExpectGrouped(expectGrouping: ExpectGrouping, subject: T): Expect<T> =
        expectGrouping.internalExpect(subject)

    override fun <T> expectInExpectGrouped(
        expectGrouping: ExpectGrouping,
        subject: T,
        expectationCreator: Expect<T>.() -> Unit
    ): Expect<T> = expectGrouping.internalExpect(subject, expectationCreator)
}
