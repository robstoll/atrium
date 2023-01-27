@file:Suppress(
    "JAVA_MODULE_DOES_NOT_READ_UNNAMED_MODULE"
)
package ch.tutteli.atrium.reporting.erroradjusters.impl
// TODO remove once https://youtrack.jetbrains.com/issue/KT-35343 is fixed

import ch.tutteli.atrium.reporting.erroradjusters.FilterAtriumErrorAdjuster
import ch.tutteli.atrium.reporting.erroradjusters.RemoveAtriumFromAtriumError

actual class RemoveAtriumFromAtriumErrorImpl : FilterAtriumErrorAdjuster(),
    RemoveAtriumFromAtriumError {

    override fun adjustStackTrace(stackTrace: Sequence<StackTraceElement>): Sequence<StackTraceElement> =
        stackTrace.filter { !it.className.startsWith("ch.tutteli.atrium") }
}
