package ch.tutteli.assertk

class ThrowingAssertionChecker(private val reporter: IReporter) : IAssertionChecker {

    override fun check(assertionVerb: String, subject: Any, assertions: List<IAssertion>) {
        val sb = StringBuilder()
        var allHold = true
        assertions.forEach {
            allHold = allHold && it.holds()
            reporter.format(sb, it)
        }
        if (!allHold) {
            //FIXME wrong place, should happen in the reporter
            sb.insert(0, assertionVerb + ": " + AssertionFactory.objectFormatter.format(subject) + "\n")
            throw AssertionError(sb.toString())
        }
    }

    override fun fail(assertionVerb: String, subject: Any, assertion: IAssertion) {
        if (assertion.holds()) throw IllegalArgumentException("the given assertion should fail: $assertion")
        check(assertionVerb, subject, listOf(assertion))
    }

    override fun failWithCustomSubject(assertionVerb: String, subject: String, assertion: IAssertion) {
        if (assertion.holds()) throw IllegalArgumentException("the given assertion should fail: $assertion")
        check(assertionVerb, subject, listOf(assertion))
    }

}
