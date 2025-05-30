package ch.tutteli.atrium.reporting

import ch.tutteli.kbox.failIf

/**
 * Refers to the bug tracker of Atrium.
 */
const val BUG_REPORT_URL = "https://github.com/robstoll/atrium/issues/new"

/**
 * Can be used in places where an argument for reporting is expected which should never be shown to the user.
 */
const val SHOULD_NOT_BE_SHOWN_TO_THE_USER_BUG =
    //TODO 2.0.0 change to Text
    "Should not be shown to the user; if you see this, please file a bug report at $BUG_REPORT_URL"

val forgotToAppendProofPseudoUsageHint = listOf(
    Text("bug detected, looks like we forgot to append a Proof, please open a bug at $BUG_REPORT_URL")
)

inline fun failWithBugErrorIf(predicate: Boolean, crossinline errorMessage: () -> String) {
    failIf(predicate) {
        bugErrorMessage(errorMessage())
    }
}

@Suppress("NOTHING_TO_INLINE")
inline fun errorDueToBug(errorMessage: String): Nothing =
    error(bugErrorMessage(errorMessage))

@Suppress("NOTHING_TO_INLINE")
inline fun bugErrorMessage(errorMessage: String) = "bug detected: $errorMessage, please report a bug at $BUG_REPORT_URL"
