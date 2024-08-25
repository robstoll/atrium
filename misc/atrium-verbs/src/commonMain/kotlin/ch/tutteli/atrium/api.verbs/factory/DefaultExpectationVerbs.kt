package ch.tutteli.atrium.api.verbs.factory

import ch.tutteli.atrium.api.verbs.AssertionVerb
import ch.tutteli.atrium.creating.ExpectationVerbs
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.creating.ExpectGrouping
import ch.tutteli.atrium.api.verbs.expect as defaultExpect

/**
 * Represents a facade to all the default expectation verb functions.
 *
 * @since 1.3.0
 */
object DefaultExpectationVerbs : ExpectationVerbs {
    override fun <T> expect(subject: T): Expect<T> =
        defaultExpect(subject)

    override fun <T> expect(subject: T, expectationCreator: Expect<T>.() -> Unit): Expect<T> =
        defaultExpect(subject)

    override fun expectGrouped(description: String?, groupingActions: ExpectGrouping.() -> Unit): ExpectGrouping =
        ch.tutteli.atrium.api.verbs.expectGrouped(
            description = AssertionVerb.EXPECT_GROUPED.getDefault(),
            groupingActions = groupingActions
        )

    override fun <T> expectInExpectGrouped(expectGrouping: ExpectGrouping, subject: T): Expect<T> =
        expectGrouping.defaultExpect(subject)

    override fun <T> expectInExpectGrouped(
        expectGrouping: ExpectGrouping,
        subject: T,
        expectationCreator: Expect<T>.() -> Unit
    ): Expect<T> = expectGrouping.defaultExpect(subject, expectationCreator)
}
