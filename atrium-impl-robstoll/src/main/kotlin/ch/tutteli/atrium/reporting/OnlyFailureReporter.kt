package ch.tutteli.atrium.reporting

import ch.tutteli.atrium.assertions.IAssertion
import ch.tutteli.atrium.assertions.Message

/**
 * An [IReporter] which reports only failing assertions.
 *
 * @property assertionFormatterFacade The formatter used to format [IAssertion]s and its [Message]s.
 *
 * @constructor
 * @param assertionFormatterFacade The formatter used to format [IAssertion]s and its [Message]s.
 */
internal class OnlyFailureReporter(private val assertionFormatterFacade: IAssertionFormatterFacade) : IReporter {

    /**
     * Formats the given [assertion] with the help of the defined [assertionFormatterFacade]
     * and appends the result to the given [sb] but only in case the given [assertion] [holds][IAssertion.holds].
     */
    override fun format(assertion: IAssertion, sb: StringBuilder)
        = assertionFormatterFacade.format(assertion, sb, this::assertionFilter, this::messageFilter)

    private fun assertionFilter(assertion: IAssertion) = !assertion.holds()
    private fun messageFilter(message: Message) = !message.holds
}
