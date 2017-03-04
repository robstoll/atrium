package ch.tutteli.assertk.reporting

import ch.tutteli.assertk.Message
import ch.tutteli.assertk.assertions.IAssertion

interface IAssertionMessageFormatter {
    fun format(sb: StringBuilder,
               assertion: IAssertion,
               assertionFilter: (IAssertion) -> Boolean,
               messageFilter: (Message) -> Boolean)
}
