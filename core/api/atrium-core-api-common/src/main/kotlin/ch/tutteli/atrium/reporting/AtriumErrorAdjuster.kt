package ch.tutteli.atrium.reporting

/**
 * Responsible to adjust a given [AtriumError] for improved error reporting.
 *
 * Typically this involves filtering the stack trace (`stackTrace` in JVM, `stack` in JS) in some way or another.
 */
interface AtriumErrorAdjuster {

    /**
     * Adjusts the given [atriumError] -  typically this involves filtering the stack trace
     * (`stackTrace` in JVM, `stack` in JS) in some way or another.
     *
     * @return The adjusted [AtriumError] - typically the given [atriumError] since stack creation is rather expensive.
     */
    fun adjust(atriumError: AtriumError): AtriumError
}
