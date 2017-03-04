package ch.tutteli.assertk

import ch.tutteli.assertk.assertions.IAssertion

interface IReporter {
    fun format(sb: StringBuilder, assertion: IAssertion)
}
