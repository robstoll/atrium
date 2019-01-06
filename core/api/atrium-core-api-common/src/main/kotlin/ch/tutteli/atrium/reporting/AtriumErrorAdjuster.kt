package ch.tutteli.atrium.reporting


/**
 * Responsible to adjust a given [AtriumError] for improved error reporting.
 *
 * Typically this involves filtering the stack trace (`stackTrace` in JVM, `stack` in JS) in some way
 * or another.
 */
expect interface AtriumErrorAdjuster : AtriumErrorAdjusterCommon


interface AtriumErrorAdjusterCommon {

    /**
     * Adjusts the given [atriumError] -  typically this involves filtering the stack trace
     * (`stackTrace` in JVM, `stack` in JS) in some way or another.
     *
     * @return The adjusted [AtriumError] - typically the given [atriumError] and not a new instance since stack
     *   creation is rather expensive.
     */
    fun adjust(atriumError: AtriumError): AtriumError

    /**
     * Adjusts parts of the given [atriumError] but not its stack trace.
     *
     * This method is intended for usages where the stack trace is modified by multiple [AtriumErrorAdjuster] (part
     * of the platform specific [AtriumErrorAdjuster] interface).
     *
     * @return The adjusted [AtriumError] - typically the given [atriumError] and not a new instance since stack
     *   creation is rather expensive.
     */
    fun adjustOtherThanStack(atriumError: AtriumError): AtriumError
}
