package ch.tutteli.atrium.reporting

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.core.CoreFactory

/**
 * Represents a reporter which reports about [Assertion]s.
 */
interface Reporter {

    /**
     * The [AtriumErrorAdjuster] which shall be used to modify stack traces in reporting.
     */
    @Deprecated("Do no longer use this but build the AtriumErrorAdjuster based on the configured ComponentFactoryContainer of the Expect/ProofContainer, so that you use the latest configured AtriumErrorAdjuster; will be removed with 0.17.0")
    val atriumErrorAdjuster: AtriumErrorAdjuster

    /**
     * Reports about the given [assertion], using the given [sb] where the actual
     * implementation will decide whether the given [assertion] is noteworthy to be reported.
     *
     * For instance, [CoreFactory.newOnlyFailureReporter] will only report failing [Assertion]s.
     *
     * @param sb The [StringBuilder] which can be used for reporting.
     * @param assertion The assertion which should be considered for reporting.
     */
    fun format(assertion: Assertion, sb: StringBuilder)
}
