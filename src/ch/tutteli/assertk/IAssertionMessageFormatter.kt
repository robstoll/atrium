package ch.tutteli.assertk

interface IAssertionMessageFormatter {
    fun format(sb: StringBuilder, assertion: IAssertion, filter: (Message) -> Boolean)
}
