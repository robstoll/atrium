package ch.tutteli.assertk

class OnlyFailureReporter(private val assertionMessageFormatter: IAssertionMessageFormatter) : IReporter {

    override fun format(sb: StringBuilder, assertion: IAssertion) {
        if (!assertion.holds()) {
            sb.append(assertionMessageFormatter.format(assertion.logMessages()))
        }
    }
}
