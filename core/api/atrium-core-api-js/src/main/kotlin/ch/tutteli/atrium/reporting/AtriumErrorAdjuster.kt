package ch.tutteli.atrium.reporting


/**
 * Responsible to adjust a given [AtriumError] for improved error reporting.
 *
 * Typically this involves filtering `stack` in some way or another.
 */
actual interface AtriumErrorAdjuster : AtriumErrorAdjusterCommon {

    /**
     * Adjusts the `stack` in some way or another.
     *
     * As side notice, `stack` is a property of Error which is currently not visible in Kotlin.
     *
     * @return The adjusted `stack`.
     */
    fun adjustStack(stackTrace: Sequence<String>): Sequence<String>
}
