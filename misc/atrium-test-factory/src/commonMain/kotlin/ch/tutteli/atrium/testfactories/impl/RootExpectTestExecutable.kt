package ch.tutteli.atrium.testfactories.impl

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.creating.ExpectGrouping
import ch.tutteli.atrium.creating.ExpectationVerbs
import ch.tutteli.atrium.testfactories.ExpectTestExecutable
import ch.tutteli.atrium.testfactories.TestExecutableImplContract

internal class RootExpectTestExecutable(
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
