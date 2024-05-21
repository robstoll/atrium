package ch.tutteli.atrium.reporting.erroradjusters.impl

import ch.tutteli.atrium.reporting.erroradjusters.RemoveRunnerFromAtriumError

expect class RemoveRunnerFromAtriumErrorImpl() : RemoveRunnerFromAtriumError {
    override fun adjust(throwable: Throwable)
    override fun adjustOtherThanStacks(throwable: Throwable)
}
