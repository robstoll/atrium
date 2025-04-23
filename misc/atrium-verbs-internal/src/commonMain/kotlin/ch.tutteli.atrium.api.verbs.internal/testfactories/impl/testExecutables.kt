package ch.tutteli.atrium.api.verbs.internal.testfactories.impl

import ch.tutteli.atrium.api.verbs.internal.testfactories.ExpectTestExecutableForTests
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.creating.ExpectGrouping
import ch.tutteli.atrium.creating.ExpectationVerbs
import ch.tutteli.atrium.testfactories.expect.grouped.ExpectGroupedBasedExpectTestExecutable
import ch.tutteli.atrium.testfactories.expect.grouped.ExpectGroupingBasedExpectTestExecutable
import ch.tutteli.atrium.testfactories.expect.root.RootExpectTestExecutable

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
