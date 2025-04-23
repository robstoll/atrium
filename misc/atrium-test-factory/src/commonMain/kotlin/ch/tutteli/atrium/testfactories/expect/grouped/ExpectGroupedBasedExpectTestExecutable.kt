package ch.tutteli.atrium.testfactories.expect.grouped

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.creating.ExpectGrouping
import ch.tutteli.atrium.creating.ExpectationVerbs
import ch.tutteli.atrium.testfactories.TestExecutable
import ch.tutteli.atrium.testfactories.expect.ExpectTestExecutable

/**
 * Defines a [TestExecutable] which will create an [ExpectGrouping] for the top branch node and provides `expect`
 * as expectation verb, i.e. works on [ExpectTestExecutable].
 *
 * @since 1.3.0
 */
abstract class ExpectGroupedBasedExpectTestExecutable<ExpectTestExecutableT : ExpectTestExecutable>(
    override val expectationVerbs: ExpectationVerbs,
) : GroupingBasedTestExecutable<ExpectTestExecutableT>, ExpectTestExecutable {

    override fun <T> expect(subject: T): Expect<T> =
        error("should not be called on an ExpectGroupedBasedExpectTestExecutable, use testFactory first")

    override fun <T> expect(subject: T, expectationCreator: Expect<T>.() -> Unit): Expect<T> =
        error("should not be called on ExpectGroupedBasedExpectTestExecutable, use testFactory first")

    override fun expectGrouped(description: String?, groupingActions: ExpectGrouping.() -> Unit): ExpectGrouping =
        error("should not be called on ExpectGroupedBasedExpectTestExecutable, use testFactory first")
}

