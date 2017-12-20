package ch.tutteli.atrium.reporting

import ch.tutteli.atrium.IAtriumFactory
import ch.tutteli.atrium.assertions.IAssertion

/**
 * Represents a reporter which reports about [IAssertion]s.
 */
interface Reporter {

    /**
     * Reports about the given [assertion], using the given [sb] where the actual
     * implementation will decide whether the given [assertion] is noteworthy to be reported.
     *
     * For instance, [IAtriumFactory.newOnlyFailureReporter] will only report failing [IAssertion]s.
     *
     * @param sb The [StringBuilder] which can be used for reporting.
     * @param assertion The assertion which should be considered for reporting.
     */
    fun format(assertion: IAssertion, sb: StringBuilder)
}
