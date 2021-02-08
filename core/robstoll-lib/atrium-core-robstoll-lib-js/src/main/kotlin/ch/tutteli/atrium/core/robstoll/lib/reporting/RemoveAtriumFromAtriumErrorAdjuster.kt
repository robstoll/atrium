//TODO remove file with 0.17.0
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.core.robstoll.lib.reporting

import ch.tutteli.atrium.reporting.AtriumErrorAdjuster

@Deprecated(
    "Use RemoveAtriumFromAtriumErrorAdjuster from atrium-core, will be removed with 0.17.0",
    ReplaceWith("ch.tutteli.atrium.reporting.erroradjusters.impl.RemoveAtriumFromAtriumErrorAdjuster")
)
actual class RemoveAtriumFromAtriumErrorAdjuster : FilterAtriumErrorAdjuster(), AtriumErrorAdjuster {
    override fun adjustStack(stackTrace: Sequence<String>): Sequence<String> = stackTrace.filter {
        !atriumRegex.containsMatchIn(it)
    }

    companion object {
        val atriumRegex = Regex("[\\\\|/]atrium-[a-zA-Z-]+.js")
    }
}
