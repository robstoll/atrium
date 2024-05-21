package ch.tutteli.atrium.reporting.erroradjusters

import ch.tutteli.atrium.reporting.AtriumErrorAdjuster

/**
 * Represents an [AtriumErrorAdjuster] which incorporates several [AtriumErrorAdjuster] and executes one after another
 * during adjustment in the given order.
 *
 * @firstAdjuster The first adjuster which shall be used.
 * @secondAdjuster The second adjuster which shall be used.
 * @otherAdjusters The other adjusters which shall be used.
 */
expect class MultiAtriumErrorAdjuster(
    firstAdjuster: AtriumErrorAdjuster,
    secondAdjuster: AtriumErrorAdjuster,
    otherAdjusters: List<AtriumErrorAdjuster>
) : AtriumErrorAdjuster{
    override fun adjust(throwable: Throwable)
    override fun adjustOtherThanStacks(throwable: Throwable)
}
