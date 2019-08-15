package ch.tutteli.atrium.spec

import ch.tutteli.atrium.reporting.translating.StringBasedTranslatable

enum class AssertionVerb(override val value: String) : StringBasedTranslatable {
    VERB("verb"),
    ASSERT("assert"),
    EXPECT_THROWN("expect the thrown exception"),
}
