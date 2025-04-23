package ch.tutteli.atrium.testfactories.expect.grouped.impl

import ch.tutteli.atrium.creating.ExpectGrouping
import ch.tutteli.atrium.creating.ExpectationVerbs
import ch.tutteli.atrium.testfactories.expect.ExpectTestExecutable
import ch.tutteli.atrium.testfactories.expect.grouped.ExpectGroupedBasedExpectTestExecutable

/**
 * An [ExpectGroupedBasedExpectTestExecutable] which works on [ExpectTestExecutable].
 *
 * @since 1.3.0
 */
class ExpectGroupedBasedExpectTestExecutableImpl(
    expectationVerbs: ExpectationVerbs
) : ExpectGroupedBasedExpectTestExecutable<ExpectTestExecutable>(expectationVerbs) {

    override fun testFactory(expectGrouping: ExpectGrouping): ExpectTestExecutable =
        ExpectGroupingBasedExpectTestExecutableImpl(expectGrouping, expectationVerbs)
}
