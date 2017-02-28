package ch.tutteli.assertk

interface IAssertionMessageFormatter {
    fun format(sb: StringBuilder,
               assertion: IAssertion,
               assertionFilter: (IAssertion) -> Boolean,
               messageFilter: (Message) -> Boolean)
}
