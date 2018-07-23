package ch.tutteli.atrium.verbs

import ch.tutteli.atrium.reporting.translating.StringBasedTranslatable
import ch.tutteli.atrium.reporting.translating.Translatable

/**
 * The [Translatable]s for the assertion functions [assert], [assertThat] and [expect].
 */
enum class AssertionVerb(override val value: String) : StringBasedTranslatable {
    ASSERT("assert"),
    ASSERT_THROWN("assert the thrown exception"),
    ASSERT_THAT("assert that"),
    ASSERT_THAT_THROWN("assert that the thrown exception"),
    EXPECT("expect"),
    EXPECT_THROWN("expect the thrown exception"),
}
