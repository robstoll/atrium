package ch.tutteli.atrium.core.robstoll.lib.reporting

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.reporting.AssertionFormatterFacade
import ch.tutteli.atrium.reporting.AtriumError
import ch.tutteli.atrium.reporting.AtriumErrorAdjuster
import ch.tutteli.atrium.reporting.Reporter

/**
 * A [Reporter] which reports only failing assertions.
 *
 * @property assertionFormatterFacade The formatter used to format [Assertion]s.
 *
 * @constructor A [Reporter] which reports only failing assertions.
 * @param assertionFormatterFacade The formatter used to format [Assertion]s.
 * @param atriumErrorAdjuster The adjuster which should be used to adjust the resulting [AtriumError].
 */
class OnlyFailureReporter(
    private val assertionFormatterFacade: AssertionFormatterFacade,
    override val atriumErrorAdjuster: AtriumErrorAdjuster
) : Reporter {

    /**
     * Formats the given [assertion] with the help of the defined [assertionFormatterFacade]
     * and appends the result to the given [sb] but only in case the given [assertion] [holds][Assertion.holds].
     */
    override fun format(assertion: Assertion, sb: StringBuilder) =
        assertionFormatterFacade.format(assertion, sb, this::assertionFilter)

    private fun assertionFilter(assertion: Assertion) = !assertion.holds()
}
