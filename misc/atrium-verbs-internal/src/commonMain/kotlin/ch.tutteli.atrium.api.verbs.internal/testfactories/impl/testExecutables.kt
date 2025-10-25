package ch.tutteli.atrium.api.verbs.internal.testfactories.impl

import ch.tutteli.atrium.api.verbs.internal.testfactories.ExpectTestExecutableForTests
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.creating.ExpectGrouping
import ch.tutteli.atrium.creating.ExpectationVerbs
import ch.tutteli.atrium.testfactories.expect.grouped.ExpectGroupedBasedExpectTestExecutable
import ch.tutteli.atrium.testfactories.expect.grouped.ExpectGroupingBasedExpectTestExecutable
import ch.tutteli.atrium.testfactories.expect.root.RootExpectTestExecutable

/**
 * We need this because when we write tests for Atrium like the following (a normal user will not nest expect like this):
 * ```
 * testFactory(...) {
 *     expect {
 *         expect(1).toEqual(2)
 *     }.toThrow<...>
 * }
 * ```
 *
 * And we need to turn the testFactory into a ExpectGrouped (because we have no way to define hierarchical tests or
 * at least multiple test nodes dynamically), then the above is the same as:
 * ```
 * expectGrouped {
 *     expect {
 *         expect(1).toEqual(2)
 *     }.toThrow<...>()
 * }
 * ```
 * Where both `expect` refer to `ExpectGrouping.expect` which means, they don't nest any more but are two independent
 * definitions which are evaluated at the end of the block. And of course, then the first fails because 1 != 2 and
 * the second because nothing was thrown. Not really what we want. Thus, we use Block to ensure, that the inner block
 * refers to a top-leve `expect` (e.g ch.tutteli.atrium.api.verbs.expect)
 */
class Block(private val expectationVerbs: ExpectationVerbs) {
    fun <T> expect(subject: T): Expect<T> = expectationVerbs.expect(subject)
}

class RootExpectTestExecutableForTestsImpl(expectationVerbs: ExpectationVerbs) :
    RootExpectTestExecutable<ExpectTestExecutableForTests>(expectationVerbs),
    ExpectTestExecutableForTests

class ExpectGroupedBasedExpectTestExecutableForTestsImpl(expectationVerbs: ExpectationVerbs) :
    ExpectGroupedBasedExpectTestExecutable<ExpectTestExecutableForTests>(expectationVerbs),
    ExpectTestExecutableForTests {

    override fun testFactory(expectGrouping: ExpectGrouping): ExpectTestExecutableForTests =
        ExpectGroupingBasedExpectTestExecutableForTestsImpl(expectGrouping, expectationVerbs)
}

class ExpectGroupingBasedExpectTestExecutableForTestsImpl(
    expectGrouping: ExpectGrouping,
    expectationVerbs: ExpectationVerbs
) : ExpectGroupingBasedExpectTestExecutable(expectGrouping, expectationVerbs),
    ExpectTestExecutableForTests
