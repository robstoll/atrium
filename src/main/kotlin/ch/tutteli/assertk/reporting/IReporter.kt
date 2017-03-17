package ch.tutteli.assertk.reporting

import ch.tutteli.assertk.assertions.IAssertion

interface IReporter {
    fun format(sb: StringBuilder, assertion: IAssertion)
}
