package ch.tutteli.atrium.reporting

import ch.tutteli.atrium.assertions.Assertion

/**
 * A [Reporter] which reports only failing assertions.
 *
 * @property assertionFormatterFacade The formatter used to format [Assertion]s.
 *
 * @constructor A [Reporter] which reports only failing assertions.
 * @param assertionFormatterFacade The formatter used to format [Assertion]s.
 */
class OnlyFailureReporter(private val assertionFormatterFacade: AssertionFormatterFacade) : Reporter {

    /**
     * Formats the given [assertion] with the help of the defined [assertionFormatterFacade]
     * and appends the result to the given [sb] but only in case the given [assertion] [holds][Assertion.holds].
     */
    override fun format(assertion: Assertion, sb: StringBuilder)
        = assertionFormatterFacade.format(assertion, sb, this::assertionFilter)

    private fun assertionFilter(assertion: Assertion) = !assertion.holds()
}
