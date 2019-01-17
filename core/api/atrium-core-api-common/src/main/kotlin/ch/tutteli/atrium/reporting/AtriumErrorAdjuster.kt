package ch.tutteli.atrium.reporting


/**
 * Responsible to adjust a given [Throwable] (usually an [AtriumError]) for improved error reporting.
 *
 * Typically this involves filtering the stack traces (`stackTrace` in JVM, `stack` in JS) in some way
 * or another (also the stack trace of the cause or suppressed [Throwable]s).
 */
expect interface AtriumErrorAdjuster : AtriumErrorAdjusterCommon


/**
 * Defines the general contract for [AtriumError] adjusters which all platforms have to fulfil.
 */
interface AtriumErrorAdjusterCommon {

    /**
     * Adjusts the given [throwable] -  typically this involves filtering the stack trace
     * (`stackTrace` in JVM, `stack` in JS) in some way or another as well as the stack traces of a [Throwable.cause]
     * and other stack traces (e.g. stack traces of suppressed throwable in JVM).
     *
     * Usually the given [throwable] is an [AtriumError] but an arbitrary Throwable can be passed
     */
    fun <T: Throwable> adjust(throwable: T)

    /**
     * Adjusts parts of the given [throwable] but not its stack trace.
     *
     * This method is intended for usages where the stack trace is modified by multiple [AtriumErrorAdjuster]s (part
     * of the platform specific [AtriumErrorAdjuster] interface).
     */
    fun <T: Throwable> adjustOtherThanStacks(throwable: T)
}
