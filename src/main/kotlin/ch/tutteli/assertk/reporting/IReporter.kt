package ch.tutteli.assertk.reporting

import ch.tutteli.assertk.assertions.IAssertion

/**
 * Reports an assertion.
 */
interface IReporter {
    fun format(sb: StringBuilder, assertion: IAssertion)
}
