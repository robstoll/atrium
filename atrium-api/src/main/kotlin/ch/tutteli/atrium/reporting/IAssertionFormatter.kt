package ch.tutteli.atrium.reporting

import ch.tutteli.atrium.IAtriumFactory
import ch.tutteli.atrium.assertions.IAssertion
import ch.tutteli.atrium.assertions.Message

/**
 * Represents a formatter for an [IAssertion] and its [Message]s.
 */
interface IAssertionFormatter {
    /**
     * Formats the given [assertion] and appends the result to the given [sb].
     *
     * One can define an [assertionFilter] and a [messageFilter] to filter out [IAssertion]s, [Message]s respectively
     * (for instance, filter out assertions which hold --> see [IAtriumFactory.newOnlyFailureReporter]).
     *
     * @param sb The [StringBuilder] to which the formatted [assertion] will be appended.
     * @param assertion The assertion which should be formatted
     * @param assertionFilter Can be used used to filter out [IAssertion]s which should not be formatted.
     * @param messageFilter Can be used to filter out [Message]s which should not be formatted.
     */
    fun format(sb: StringBuilder,
               assertion: IAssertion,
               assertionFilter: (IAssertion) -> Boolean,
               messageFilter: (Message) -> Boolean)
}
