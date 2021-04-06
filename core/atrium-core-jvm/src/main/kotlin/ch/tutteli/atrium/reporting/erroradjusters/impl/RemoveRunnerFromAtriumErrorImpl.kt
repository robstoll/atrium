@file:Suppress(
    // TODO remove once https://youtrack.jetbrains.com/issue/KT-35343 is fixed
    "JAVA_MODULE_DOES_NOT_READ_UNNAMED_MODULE"
)
package ch.tutteli.atrium.reporting.erroradjusters.impl

import ch.tutteli.atrium.reporting.erroradjusters.FilterAtriumErrorAdjuster
import ch.tutteli.atrium.reporting.erroradjusters.RemoveRunnerFromAtriumError

actual class RemoveRunnerFromAtriumErrorImpl : FilterAtriumErrorAdjuster(), RemoveRunnerFromAtriumError {

    override fun adjustStackTrace(stackTrace: Sequence<StackTraceElement>): Sequence<StackTraceElement> =
        stackTrace.takeWhile {
            !it.className.startsWith("org.junit") &&
                !it.className.startsWith("org.jetbrains.spek") &&
                !it.className.startsWith("org.spekframework.spek2") &&
                !it.className.startsWith("io.kotest") &&
                !it.className.startsWith("io.kotlintest")
        }
}
