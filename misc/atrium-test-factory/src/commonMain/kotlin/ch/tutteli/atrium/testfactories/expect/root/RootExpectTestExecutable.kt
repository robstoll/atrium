package ch.tutteli.atrium.testfactories.expect.root

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.creating.RootExpect
import ch.tutteli.atrium.creating.ExpectGrouping
import ch.tutteli.atrium.creating.ExpectationVerbs
import ch.tutteli.atrium.testfactories.TestExecutableImplContract
import ch.tutteli.atrium.testfactories.expect.ExpectTestExecutable

/**
 * A base class for [ExpectTestExecutable] which creates a regular [RootExpect] as top `expect`.
 *
 * @since 1.3.0
 */
abstract class RootExpectTestExecutable<ExpectTestExecutableT : ExpectTestExecutable>(
    override val expectationVerbs: ExpectationVerbs
) : ExpectTestExecutable, TestExecutableImplContract {
    override fun <T> expect(subject: T): Expect<T> =
        expectationVerbs.expect(subject)

    override fun <T> expect(
        subject: T,
        expectationCreator: Expect<T>.() -> Unit
    ): Expect<T> = expectationVerbs.expect(subject, expectationCreator)

    override fun expectGrouped(description: String?, groupingActions: ExpectGrouping.() -> Unit): ExpectGrouping =
        expectationVerbs.expectGrouped(description ?: expectationVerbs.defaultExpectGroupDescription, groupingActions)
}
