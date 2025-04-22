package ch.tutteli.atrium.testfactories.expect.grouped

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.creating.ExpectGrouping
import ch.tutteli.atrium.creating.ExpectationVerbs
import ch.tutteli.atrium.logic._logicAppend
import ch.tutteli.atrium.logic.grouping
import ch.tutteli.atrium.reporting.Text
import ch.tutteli.atrium.testfactories.TestExecutableImplContract
import ch.tutteli.atrium.testfactories.expect.ExpectTestExecutable

/**
 * A base class for [ExpectTestExecutable] which hook into a given [expectGrouping].
 *
 * @since 1.3.0
 */
abstract class ExpectGroupingBasedExpectTestExecutable(
    private val expectGrouping: ExpectGrouping,
    override val expectationVerbs: ExpectationVerbs
) : ExpectTestExecutable, TestExecutableImplContract {
    override fun <T> expect(subject: T): Expect<T> =
        expectationVerbs.expectInExpectGrouped(expectGrouping, subject)

    override fun <T> expect(subject: T, expectationCreator: Expect<T>.() -> Unit): Expect<T> =
        expectationVerbs.expectInExpectGrouped(expectGrouping, subject, expectationCreator)

    override fun expectGrouped(description: String?, groupingActions: ExpectGrouping.() -> Unit): ExpectGrouping =
        expectGrouping._logicAppend {
            grouping(
                description ?: expectationVerbs.defaultExpectGroupDescription,
                Text.EMPTY_PROVIDER,
                groupingActions
            )
        }
}
