package ch.tutteli.atrium.testfactories.expect.grouped

import ch.tutteli.atrium.creating.ExpectGrouping
import ch.tutteli.atrium.testfactories.TestExecutable
import ch.tutteli.atrium.testfactories.TestExecutableImplContract

/**
 * Defines a [TestExecutable] which will create an [ExpectGrouping] for the top branch node.
 *
 * @since 1.3.0
 */
interface GroupingBasedTestExecutable<TestExecutableT : TestExecutable> : TestExecutable, TestExecutableImplContract {
    fun testFactory(expectGrouping: ExpectGrouping): TestExecutableT
}
