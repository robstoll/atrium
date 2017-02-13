package ch.tutteli.assertk

interface IReporter {
    fun format(sb: StringBuilder, assertion: IAssertion)
}
