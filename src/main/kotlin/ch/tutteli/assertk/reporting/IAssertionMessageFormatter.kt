package ch.tutteli.assertk.reporting

import ch.tutteli.assertk.assertions.IAssertion
import ch.tutteli.assertk.assertions.Message

/**
 * Represents a formatter for an [IAssertion] and its [Message](s).
 */
interface IAssertionMessageFormatter {
    /**
     * Formats the given [assertion] and appends the result to the given [sb]
     * where one can define an [assertionFilter] and a [messageFilter] to filter out assertions or messages
     * (for instance, filter out assertions which hold --> see [OnlyFailureReporter])
     */
    fun format(sb: StringBuilder,
               assertion: IAssertion,
               assertionFilter: (IAssertion) -> Boolean,
               messageFilter: (Message) -> Boolean)
}
