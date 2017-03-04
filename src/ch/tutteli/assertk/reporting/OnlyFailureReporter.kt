package ch.tutteli.assertk.reporting

import ch.tutteli.assertk.assertions.IAssertion
import ch.tutteli.assertk.assertions.Message

class OnlyFailureReporter(private val assertionMessageFormatter: IAssertionMessageFormatter) : IReporter {

    override fun format(sb: StringBuilder, assertion: IAssertion)
        = assertionMessageFormatter.format(sb, assertion, this::assertionFilter, this::messageFilter)

    private fun assertionFilter(assertion: IAssertion) = !assertion.holds()
    private fun messageFilter(message: Message) = !message.holds
}
