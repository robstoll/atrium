package ch.tutteli.atrium.reporting

/**
 * Refers to the bug tracker of Atrium.
 */
const val BUG_REPORT_URL = "https://github.com/robstoll/atrium/issues/new"

/**
 * Can be used in places where an argument for reporting is expected which should never be shown to the user.
 */
const val SHOULD_NOT_BE_SHOWN_TO_THE_USER_BUG = "Should not be shown to the user; if you see this, please file a bug report at $BUG_REPORT_URL"
