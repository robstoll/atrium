package ch.tutteli.assertk

class OnlyFailureReporter(private val assertionMessageFormatter: IAssertionMessageFormatter) : IReporter {

    override fun format(sb: StringBuilder, assertion: IAssertion) {
        assertion.messages().forEach {
            if (!it.holds) {
                sb.append(assertionMessageFormatter.format(
                    listOf(it.description to AssertionFactory.objectFormatter.format(it.representation))))
            }
        }
    }
}
