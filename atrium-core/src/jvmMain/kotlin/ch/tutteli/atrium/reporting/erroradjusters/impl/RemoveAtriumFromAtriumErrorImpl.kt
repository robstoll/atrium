package ch.tutteli.atrium.reporting.erroradjusters.impl

import ch.tutteli.atrium.reporting.erroradjusters.FilterAtriumErrorAdjuster
import ch.tutteli.atrium.reporting.erroradjusters.RemoveAtriumFromAtriumError

actual class RemoveAtriumFromAtriumErrorImpl : FilterAtriumErrorAdjuster(),
    RemoveAtriumFromAtriumError {

    override fun adjustStackTrace(stackTrace: Sequence<StackTraceElement>): Sequence<StackTraceElement> =
        stackTrace.filter { !it.className.startsWith("ch.tutteli.atrium") }
}
