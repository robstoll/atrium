package ch.tutteli.atrium.checking

import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.assertions.IAssertion
import ch.tutteli.atrium.reporting.IReporter

/**
 * An [IAssertionChecker] which throws [AssertionError]s in case an assertion fails and uses the given [reporter] for reporting.
 */
class ThrowingAssertionChecker(private val reporter: IReporter) : IAssertionCheckerDelegateFail, IAssertionChecker {

    /**
     * Checks the given [assertions] and uses [reporter] for reporting.
     * @throws AssertionError in case one of the given [assertions] does not hold
     */
    override fun check(assertionVerb: String, subject: Any, assertions: List<IAssertion>) {
        val sb = StringBuilder()
        val assertionGroup = AssertionGroup(assertionVerb, subject, assertions)
        reporter.format(sb, assertionGroup)
        if (!assertionGroup.holds()) {
            throw AssertionError(sb.toString())
        }
    }

}
