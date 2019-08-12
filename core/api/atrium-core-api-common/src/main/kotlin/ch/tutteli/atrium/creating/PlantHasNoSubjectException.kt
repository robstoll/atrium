package ch.tutteli.atrium.creating

import ch.tutteli.atrium.reporting.BUG_REPORT_URL

/**
 * Represents the [Exception] that an [AssertionPlant.subject] was not defined but one tried to access it.
 */
@Deprecated("No longer required with Expect; will be removed with 1.0.0")
class PlantHasNoSubjectException
    @Deprecated("Use the constructor without argument; will be removed with 1.0.0", ReplaceWith("PlantHasNoSubjectException()"))
    constructor(message: String) : RuntimeException(message){

    @Suppress("DEPRECATION" /* TODO remove with 1.0.0 */)
    constructor(): this(
        "subject is not available, you as user should not see this message.\n" +
            "Please file a bug report (including stacktrace if possible): $BUG_REPORT_URL"
    )
}
