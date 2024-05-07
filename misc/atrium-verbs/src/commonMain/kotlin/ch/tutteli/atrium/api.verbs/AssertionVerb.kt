package ch.tutteli.atrium.api.verbs

import ch.tutteli.atrium.reporting.translating.StringBasedTranslatable

//TODO 1.3.0 replace by TextConstants or the like?
/**
 * The [ch.tutteli.atrium.reporting.translating.Translatable] for the assertion [expect].
 */
enum class AssertionVerb(override val value: String) : StringBasedTranslatable {
    EXPECT("I expected subject"),
    EXPECT_GROUPED("my expectations"),
}
