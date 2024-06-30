package ch.tutteli.atrium.reporting.reportables.descriptions

import ch.tutteli.atrium.reporting.reportables.Description

/**
 * Contains [Description]s that are related to expectation functions which are applicable to [Any].
 */
enum class DescriptionDocumentationUtil(override val string: String) : Description {
    /** @since 1.3.0 (but was since 0.18.0 in atrium-translations DescriptionAnyExpectation) */
    BECAUSE("because"),
}

