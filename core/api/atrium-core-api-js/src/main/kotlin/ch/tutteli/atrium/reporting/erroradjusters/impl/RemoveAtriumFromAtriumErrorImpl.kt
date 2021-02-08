package ch.tutteli.atrium.reporting.erroradjusters.impl

import ch.tutteli.atrium.reporting.erroradjusters.FilterAtriumErrorAdjuster
import ch.tutteli.atrium.reporting.erroradjusters.RemoveAtriumFromAtriumError

actual class RemoveAtriumFromAtriumErrorImpl : FilterAtriumErrorAdjuster(),
    RemoveAtriumFromAtriumError {
    override fun adjustStack(stackTrace: Sequence<String>): Sequence<String> =
        stackTrace.filter { !atriumRegex.containsMatchIn(it) }

    companion object {
        val atriumRegex = Regex("[\\\\|/]atrium-[a-zA-Z-]+.js")
    }
}
