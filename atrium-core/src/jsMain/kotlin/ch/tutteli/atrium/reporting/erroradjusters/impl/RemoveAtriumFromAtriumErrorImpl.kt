package ch.tutteli.atrium.reporting.erroradjusters.impl

import ch.tutteli.atrium.reporting.erroradjusters.FilterAtriumErrorAdjuster
import ch.tutteli.atrium.reporting.erroradjusters.RemoveAtriumFromAtriumError

actual class RemoveAtriumFromAtriumErrorImpl : FilterAtriumErrorAdjuster(),
    RemoveAtriumFromAtriumError {
    override fun adjustStack(stackTrace: Sequence<String>): Sequence<String> =
        stackTrace.filterNot { atriumRegex.containsMatchIn(it) }

    companion object {
        val atriumRegex = Regex(
            // kotlin 1.4
            """([\\|/]atrium[\\|/]atrium-[a-zA-Z0-9-]+[\\|/]src[\\|/])|"""+
            //kotlin 1.3
                """([\\|/](atrium-)?atrium-[a-zA-Z0-9-]+.js)"""

        )
    }
}
