package ch.tutteli.atrium.testfactories.expect.grouped.impl

import ch.tutteli.atrium.creating.ExpectGrouping
import ch.tutteli.atrium.creating.ExpectationVerbs
import ch.tutteli.atrium.testfactories.expect.grouped.ExpectGroupingBasedExpectTestExecutable

/**
 * @since 1.3.0
 */
class ExpectGroupingBasedExpectTestExecutableImpl(
    expectGrouping: ExpectGrouping,
    expectationVerbs: ExpectationVerbs
) : ExpectGroupingBasedExpectTestExecutable(expectGrouping, expectationVerbs)
