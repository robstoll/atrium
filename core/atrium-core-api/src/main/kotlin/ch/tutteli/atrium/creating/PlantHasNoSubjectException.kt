package ch.tutteli.atrium.creating

import ch.tutteli.atrium.reporting.BUG_REPORT_URL

/**
 * Represents the [Exception] that an [AssertionPlant.subject] was not defined but one tried to access it.
 */
class PlantHasNoSubjectException
    @Deprecated("Use the constructor without argument, will be removed with 1.0.0", ReplaceWith("PlantHasNoSubjectException()"))
    constructor(message: String) : RuntimeException(message){
    constructor(): this(
        "subject is not available, you as user should not see this message.\n" +
            "Please fill in a bug (including stacktrace would be good): $BUG_REPORT_URL"
    )
}
