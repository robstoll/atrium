package ch.tutteli.atrium.reporting.erroradjusters.impl

import ch.tutteli.atrium.reporting.erroradjusters.RemoveAtriumFromAtriumError

expect class RemoveAtriumFromAtriumErrorImpl() : RemoveAtriumFromAtriumError {
    override fun adjustOtherThanStacks(throwable: Throwable)
    override fun adjust(throwable: Throwable)
}
