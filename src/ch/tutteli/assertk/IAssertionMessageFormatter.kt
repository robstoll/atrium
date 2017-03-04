package ch.tutteli.assertk

import ch.tutteli.assertk.assertions.IAssertion

interface IAssertionMessageFormatter {
    fun format(sb: StringBuilder,
               assertion: IAssertion,
               assertionFilter: (IAssertion) -> Boolean,
               messageFilter: (Message) -> Boolean)
}
