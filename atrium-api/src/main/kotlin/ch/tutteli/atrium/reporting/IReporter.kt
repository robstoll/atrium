package ch.tutteli.atrium.reporting

import ch.tutteli.atrium.assertions.IAssertion

/**
 * Reports an assertion.
 */
interface IReporter {
    fun format(sb: StringBuilder, assertion: IAssertion)
}
