package ch.tutteli.atrium.testfactories.impl

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.creating.ExpectGrouping
import ch.tutteli.atrium.creating.ExpectationVerbs
import ch.tutteli.atrium.logic._logic
import ch.tutteli.atrium.logic._logicAppend
import ch.tutteli.atrium.logic.creating.transformers.propertiesOfThrowable
import ch.tutteli.atrium.logic.grouping
import ch.tutteli.atrium.reporting.AtriumError
import ch.tutteli.atrium.reporting.Text
import ch.tutteli.atrium.testfactories.ExpectTestExecutable
import ch.tutteli.atrium.testfactories.TestExecutable
import ch.tutteli.atrium.testfactories.TestExecutableImplContract

interface GroupingBasedTestExecutable<TestExecutableT : TestExecutable> : TestExecutable, TestExecutableImplContract {
    fun testFactory(expectGrouping: ExpectGrouping): TestExecutableT
}

internal class ExpectGroupedBasedExpectTestExecutable(
    override val expectationVerbs: ExpectationVerbs,
) : GroupingBasedTestExecutable<ExpectTestExecutable>, ExpectTestExecutable {
    override fun testFactory(expectGrouping: ExpectGrouping): ExpectTestExecutable =
        ExpectGroupingBasedExpectTestExecutable(expectGrouping, expectationVerbs)

    override fun <T> expect(subject: T): Expect<T> =
        error("should not be called on ExpectGroupedBasedExpectTestExecutable, use testFactory first")

    override fun <T> expect(subject: T, expectationCreator: Expect<T>.() -> Unit): Expect<T> =
        error("should not be called on ExpectGroupedBasedExpectTestExecutable, use testFactory first")

    override fun expectGrouped(description: String?, groupingActions: ExpectGrouping.() -> Unit): ExpectGrouping =
        error("should not be called on ExpectGroupedBasedExpectTestExecutable, use testFactory first")
}

internal class ExpectGroupingBasedExpectTestExecutable(
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

//TODO 1.3.0 rename once we append Proofs
internal fun ExpectGrouping.executeAndAppendExceptionAsAssertion(executable: () -> Unit) =
    try {
        executable()
        // does not collect the assertions created by the inner group but that's a different story
        // a feature which is not used currently
        _logic.createAndAppend("executing test group succeeds", Text.EMPTY) { true }
    } catch (e: Throwable) {
        val rootAssertion = (e as? AtriumError)?.rootAssertion
        if (rootAssertion != null) {
            // TODO 1.4.0 we could add a usage hint that one should define an own expectation verbs
            _logic.append(rootAssertion)
        } else {
            _logic.append(propertiesOfThrowable(e, _logic))
        }
    }


