package ch.tutteli.assertk

import ch.tutteli.assertk.assertions.AssertionGroup
import ch.tutteli.assertk.assertions.IAssertion
import ch.tutteli.assertk.reporting.IReporter

class ThrowingAssertionChecker(private val reporter: IReporter) : IAssertionCheckerDelegateFail, IAssertionChecker {

    override fun check(assertionVerb: String, subject: Any, assertions: List<IAssertion>) {
        val sb = StringBuilder()
        val assertionGroup = AssertionGroup(assertionVerb, subject, assertions)
        reporter.format(sb, assertionGroup)
        if (!assertionGroup.holds()) {
            throw AssertionError(sb.toString())
        }
    }

}
