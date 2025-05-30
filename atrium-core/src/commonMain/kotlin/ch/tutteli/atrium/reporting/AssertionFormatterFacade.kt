// TODO remove with 2.0.0 at the latest
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.reporting

import ch.tutteli.atrium.assertions.Assertion

/**
 * Responsible to call an appropriate [AssertionFormatter] which supports [format]ing a given [Assertion].
 */
@Deprecated("Switch to Proof based reporting, will be removed with 2.0.0 at the latest")
interface AssertionFormatterFacade {

    /**
     * Formats the given [assertion] and appends the result to the given [sb].
     *
     * One can define an [assertionFilter] to filter out [Assertion]s.
     *
     * @param sb The [StringBuilder] to which the formatted [assertion] will be appended.
     * @param assertion The assertion which should be formatted
     * @param assertionFilter Can be used used to filter out [Assertion]s which should not be formatted.
     */
    fun format(assertion: Assertion, sb: StringBuilder, assertionFilter: (Assertion) -> Boolean)

    /**
     * Uses the given [assertionFormatterFactory] to create and register an [AssertionFormatter] -- which means
     * the created [AssertionFormatter] will be considered when an [Assertion] shall be [format]ted.
     *
     * @param assertionFormatterFactory The factory method to create an [AssertionFormatter] which shall be registered.
     */
    fun register(assertionFormatterFactory: (AssertionFormatterController) -> AssertionFormatter)
}
