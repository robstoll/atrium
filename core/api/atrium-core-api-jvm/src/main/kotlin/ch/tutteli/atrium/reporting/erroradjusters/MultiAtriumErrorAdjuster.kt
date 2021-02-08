package ch.tutteli.atrium.reporting.erroradjusters

import ch.tutteli.atrium.reporting.AtriumErrorAdjuster
import ch.tutteli.kbox.joinToString

actual class MultiAtriumErrorAdjuster actual constructor(
    private val firstAdjuster: AtriumErrorAdjuster,
    private val secondAdjuster: AtriumErrorAdjuster,
    private val otherAdjusters: List<AtriumErrorAdjuster>
) : FilterAtriumErrorAdjuster(), AtriumErrorAdjuster {

    override fun adjustStackTrace(stackTrace: Sequence<StackTraceElement>): Sequence<StackTraceElement> =
        firstAdjuster.adjustStackTrace(stackTrace)
            .let { secondAdjuster.adjustStackTrace(it) }
            .let {
                otherAdjusters.fold(it) { filteredStack, adjuster ->
                    adjuster.adjustStackTrace(filteredStack)
                }
            }

    override fun toString(): String =
        "MultiAtriumErrorAdjuster[$firstAdjuster, $secondAdjuster, ${
            otherAdjusters.joinToString(", ", " and ") { it, sb -> sb.append(it) }
        }"
}
