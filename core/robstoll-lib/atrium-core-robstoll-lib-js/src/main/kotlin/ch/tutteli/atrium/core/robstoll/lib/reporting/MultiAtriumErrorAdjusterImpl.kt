//TODO remove file with 0.17.0
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.core.robstoll.lib.reporting

import ch.tutteli.atrium.reporting.AtriumErrorAdjuster

@Deprecated(
    "Use MultiAtriumErrorAdjuster from atrium-core, will be removed with 0.17.0",
    ReplaceWith("ch.tutteli.atrium.reporting.erroradjusters.MultiAtriumErrorAdjuster")
)
actual class MultiAtriumErrorAdjusterImpl actual constructor(
    private val firstAdjuster: AtriumErrorAdjuster,
    private val secondAdjuster: AtriumErrorAdjuster,
    private val otherAdjusters: List<AtriumErrorAdjuster>
) : FilterAtriumErrorAdjuster(), AtriumErrorAdjuster {

    override fun adjustStack(stackTrace: Sequence<String>): Sequence<String> =
        firstAdjuster.adjustStack(stackTrace)
            .let { secondAdjuster.adjustStack(it) }
            .let {
                otherAdjusters.fold(it) { filteredStack, adjuster ->
                    adjuster.adjustStack(filteredStack)
                }
            }
}
