// TODO remove with 2.0.0 at the latest
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.reporting

import ch.tutteli.atrium.assertions.Assertion

/**
 * Represents a reporter which reports about [Assertion]s.
 */
//TODO 1.3.0 deprecate
interface Reporter {

    /**
     * Reports about the given [assertion], using the given [sb] where the actual
     * implementation will decide whether the given [assertion] is noteworthy to be reported.
     *
     * @param sb The [StringBuilder] which can be used for reporting.
     * @param assertion The assertion which should be considered for reporting.
     */
    fun format(assertion: Assertion, sb: StringBuilder)
}
