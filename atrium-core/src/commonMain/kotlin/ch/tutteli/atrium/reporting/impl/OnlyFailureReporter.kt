//TODO remove file with 2.0.0 at the latest
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.reporting.impl

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.reporting.AssertionFormatterFacade
import ch.tutteli.atrium.reporting.Reporter

/**
 * A [Reporter] which reports only failing assertions.
 *
 * @property assertionFormatterFacade The formatter used to format [Assertion]s.
 *
 * @constructor A [Reporter] which reports only failing assertions.
 * @param assertionFormatterFacade The formatter used to format [Assertion]s.
 */
@Deprecated("Switch to DefaultTextReporter, will be removed with 2.0.0 at the latest")
class OnlyFailureReporter(
    private val assertionFormatterFacade: AssertionFormatterFacade
) : Reporter {

    /**
     * Formats the given [assertion] with the help of the defined [assertionFormatterFacade]
     * and appends the result to the given [sb] but only in case the given [assertion] [holds][Assertion.holds].
     */
    @Deprecated(
        "switch from Assertion to Proof based reporting, will be removed with 2.0.0 at the latest",
        replaceWith = ReplaceWith("sb.append(this.createReport(assertion))")
    )
    override fun format(assertion: Assertion, sb: StringBuilder): Unit =
        assertionFormatterFacade.format(assertion, sb, this::assertionFilter)

    private fun assertionFilter(assertion: Assertion) = !assertion.holds()
}
