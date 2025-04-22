package ch.tutteli.atrium.testfactories.expect.root.impl

import ch.tutteli.atrium.creating.ExpectationVerbs
import ch.tutteli.atrium.testfactories.expect.ExpectTestExecutable
import ch.tutteli.atrium.testfactories.expect.root.RootExpectTestExecutable

/**
 * @since 1.3.0
 */
class RootExpectTestExecutableImpl(
    expectationVerbs: ExpectationVerbs
) : RootExpectTestExecutable<ExpectTestExecutable>(expectationVerbs)
