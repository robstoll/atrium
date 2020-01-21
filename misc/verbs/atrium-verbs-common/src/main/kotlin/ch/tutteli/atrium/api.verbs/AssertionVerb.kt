package ch.tutteli.atrium.api.verbs

import ch.tutteli.atrium.reporting.translating.StringBasedTranslatable
import ch.tutteli.atrium.reporting.translating.Translatable

/**
 * The [Translatable]s for the assertion functions [assert], [assertThat] and [expect].
 */
enum class AssertionVerb(override val value: String) : StringBasedTranslatable {
    ASSERT("asserted that subject"),
    ASSERT_THAT("asserted that subject"),
    EXPECT("expected that subject"),
}
