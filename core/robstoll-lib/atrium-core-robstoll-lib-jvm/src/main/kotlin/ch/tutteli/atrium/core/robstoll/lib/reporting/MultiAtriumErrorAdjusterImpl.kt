package ch.tutteli.atrium.core.robstoll.lib.reporting

import ch.tutteli.atrium.reporting.AtriumErrorAdjuster

actual class MultiAtriumErrorAdjusterImpl actual constructor(
    private val firstAdjuster: AtriumErrorAdjuster,
    private val secondAdjuster: AtriumErrorAdjuster,
    private val otherAdjusters: List<AtriumErrorAdjuster>
) : FilterAtriumErrorAdjuster(), AtriumErrorAdjuster {

    override fun Sequence<StackTraceElement>.filterUndesiredStackFrames(): Sequence<StackTraceElement> =
        firstAdjuster.adjustStackTrace(this)
            .let { secondAdjuster.adjustStackTrace(it) }
            .let {
                otherAdjusters.fold(it) { filteredStack, adjuster ->
                    adjuster.adjustStackTrace(filteredStack)
                }
            }
}
