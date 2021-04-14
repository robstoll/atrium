package ch.tutteli.atrium.api.verbs

import ch.tutteli.atrium.reporting.translating.StringBasedTranslatable
import ch.tutteli.atrium.reporting.translating.Translatable

/**
 * The [Translatable] for the assertion [expect].
 */
enum class AssertionVerb(override val value: String) : StringBasedTranslatable {
    //TODO remove with 0.18.0
    @Deprecated("will be removed with 0.18.0 without replacement")
    ASSERT("asserted that subject"),
    //TODO remove with 0.18.0
    @Deprecated("will be removed with 0.18.0 without replacement")
    ASSERT_THAT("asserted that subject"),

    EXPECT("expected that subject"),
}
