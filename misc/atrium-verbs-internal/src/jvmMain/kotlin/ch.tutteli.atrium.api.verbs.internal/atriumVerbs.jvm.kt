package ch.tutteli.atrium.api.verbs.internal

import ch.tutteli.atrium.reporting.erroradjusters.FilterAtriumErrorAdjuster
import ch.tutteli.atrium.reporting.erroradjusters.RemoveAtriumFromAtriumError

actual class RemoveAtriumButNotAtriumSpecsFromAtriumErrorImpl : FilterAtriumErrorAdjuster(), RemoveAtriumFromAtriumError {

    override fun adjustStackTrace(stackTrace: Sequence<StackTraceElement>): Sequence<StackTraceElement> =
        stackTrace.filter {
            it.className.startsWith("ch.tutteli.atrium").not() ||
                it.className.startsWith("ch.tutteli.atrium.specs")
        }
}
