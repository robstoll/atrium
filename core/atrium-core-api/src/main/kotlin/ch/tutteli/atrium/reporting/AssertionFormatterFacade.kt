package ch.tutteli.atrium.reporting

import ch.tutteli.atrium.IAtriumFactory
import ch.tutteli.atrium.assertions.IAssertion

/**
 * Responsible to call an appropriate [AssertionFormatter] which supports [format]ing a given [IAssertion].
 */
interface AssertionFormatterFacade {
    /**
     * Formats the given [assertion] and appends the result to the given [sb].
     *
     * One can define an [assertionFilter] to filter out [IAssertion]s
     * (for instance, filter out assertions which hold &rarr; see [IAtriumFactory.newOnlyFailureReporter]).
     *
     * @param sb The [StringBuilder] to which the formatted [assertion] will be appended.
     * @param assertion The assertion which should be formatted
     * @param assertionFilter Can be used used to filter out [IAssertion]s which should not be formatted.
     */
    fun format(assertion: IAssertion, sb: StringBuilder, assertionFilter: (IAssertion) -> Boolean)

    /**
     * Uses the given [assertionFormatterFactory] to create and register an [AssertionFormatter] -- which means
     * the created [AssertionFormatter] will be considered when an [IAssertion] shall be [format]ted.
     *
     * @param assertionFormatterFactory The factory method to create an [AssertionFormatter] which shall be registered.
     */
    fun register(assertionFormatterFactory: (AssertionFormatterController) -> AssertionFormatter)
}
