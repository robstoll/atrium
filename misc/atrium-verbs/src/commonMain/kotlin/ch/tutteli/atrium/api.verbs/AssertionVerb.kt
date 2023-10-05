package ch.tutteli.atrium.api.verbs

import ch.tutteli.atrium.reporting.translating.StringBasedTranslatable
import ch.tutteli.atrium.reporting.translating.Translatable

/**
 * The [Translatable] for the assertion [expect].
 */
enum class AssertionVerb(override val value: String) : StringBasedTranslatable {
    EXPECT("I expected subject"),
    EXPECT_GROUPED("my expectations"),
}
