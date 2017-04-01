package ch.tutteli.assertk.reporting

import ch.tutteli.assertk.assertions.IAssertion
import ch.tutteli.assertk.assertions.Message

/**
 * A [IReporter] which reports only failing assertions.
 */
class OnlyFailureReporter(private val assertionMessageFormatter: IAssertionMessageFormatter) : IReporter {

    /**
     * Formats the given [assertion] with the help of the defined [assertionMessageFormatter]
     * and appends the result to the given [sb] but only in case the given [assertion] [IAssertion.holds].
     */
    override fun format(sb: StringBuilder, assertion: IAssertion)
        = assertionMessageFormatter.format(sb, assertion, this::assertionFilter, this::messageFilter)

    private fun assertionFilter(assertion: IAssertion) = !assertion.holds()
    private fun messageFilter(message: Message) = !message.holds
}
