package ch.tutteli.assertk.reporting

import ch.tutteli.assertk.assertions.IAssertion
import ch.tutteli.assertk.assertions.Message

interface IAssertionMessageFormatter {
    fun format(sb: StringBuilder,
               assertion: IAssertion,
               assertionFilter: (IAssertion) -> Boolean,
               messageFilter: (Message) -> Boolean)
}
