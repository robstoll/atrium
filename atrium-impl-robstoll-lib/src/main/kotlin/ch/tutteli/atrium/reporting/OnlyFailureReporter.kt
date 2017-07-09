package ch.tutteli.atrium.reporting

import ch.tutteli.atrium.assertions.IAssertion

/**
 * An [IReporter] which reports only failing assertions.
 *
 * @property assertionFormatterFacade The formatter used to format [IAssertion]s.
 *
 * @constructor
 * @param assertionFormatterFacade The formatter used to format [IAssertion]s.
 */
class OnlyFailureReporter(private val assertionFormatterFacade: IAssertionFormatterFacade) : IReporter {

    /**
     * Formats the given [assertion] with the help of the defined [assertionFormatterFacade]
     * and appends the result to the given [sb] but only in case the given [assertion] [holds][IAssertion.holds].
     */
    override fun format(assertion: IAssertion, sb: StringBuilder)
        = assertionFormatterFacade.format(assertion, sb, this::assertionFilter)

    private fun assertionFilter(assertion: IAssertion) = !assertion.holds()
}
