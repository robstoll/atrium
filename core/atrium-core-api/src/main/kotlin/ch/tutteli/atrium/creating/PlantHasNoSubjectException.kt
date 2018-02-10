package ch.tutteli.atrium.creating

import ch.tutteli.atrium.reporting.BUG_REPORT_URL

/**
 * Represents the [Exception] that an [AssertionPlant.subject] was not defined but one tried to access it.
 */
class PlantHasNoSubjectException : RuntimeException(
    "subject is not available, you as user should not see this message, please fill in a bug including the stacktrace if you do: " + BUG_REPORT_URL
)
