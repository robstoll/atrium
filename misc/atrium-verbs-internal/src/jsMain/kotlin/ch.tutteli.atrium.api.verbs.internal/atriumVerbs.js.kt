package ch.tutteli.atrium.api.verbs.internal

import ch.tutteli.atrium.reporting.erroradjusters.FilterAtriumErrorAdjuster
import ch.tutteli.atrium.reporting.erroradjusters.RemoveAtriumFromAtriumError

actual class RemoveAtriumButNotAtriumSpecsFromAtriumErrorImpl : FilterAtriumErrorAdjuster(),
    RemoveAtriumFromAtriumError {
    override fun adjustStack(stackTrace: Sequence<String>): Sequence<String> =
        stackTrace.filterNot { atriumRegex.containsMatchIn(it) }

    companion object {
        val atriumRegex = Regex(
            // since Kotlin 1.4 -- writes the src/ path as if the atrium file was in the same project
            // (see also https://youtrack.jetbrains.com/issue/KT-64220/KJS-IR-Stacktrace-should-not-contain-project-path-when-the-library-was-built)
            """([\\|/]src[\\|/](generated[\\|/])?(common|js)Main[\\|/](kotlin[\\|/])?ch[\\|/]tutteli[\\|/]atrium[\\|/](?!specs[\\|/]))"""
        )
    }
}
