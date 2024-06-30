package ch.tutteli.atrium.api.verbs

import ch.tutteli.atrium.reporting.reportables.Description

/**
 * The [Description] for the expectation verb used in [expect].
 */
enum class ExpectationVerb(override val string: String) : Description {
    EXPECT("I expected subject"),
    EXPECT_GROUPED("my expectations"),
}
