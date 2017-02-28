package ch.tutteli.assertk

class OnlyFailureReporter(private val assertionMessageFormatter: IAssertionMessageFormatter) : IReporter {

    override fun format(sb: StringBuilder, assertion: IAssertion)
        = assertionMessageFormatter.format(sb, assertion, this::assertionFilter, this::messageFilter)

    private fun assertionFilter(assertion: IAssertion) = !assertion.holds()
    private fun messageFilter(message: Message) = !message.holds
}
