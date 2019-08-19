package ch.tutteli.atrium.specs

import ch.tutteli.atrium.reporting.translating.StringBasedTranslatable

internal enum class AssertionVerb(override val value: String) : StringBasedTranslatable {
    VERB("verb"),
    ASSERT("assert"),
    EXPECT_THROWN("expect the thrown exception"),
}
