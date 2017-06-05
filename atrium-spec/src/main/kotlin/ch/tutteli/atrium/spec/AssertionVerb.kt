package ch.tutteli.atrium.spec

import ch.tutteli.atrium.reporting.translating.IEnTranslatable

internal enum class AssertionVerb(override val value: String) : IEnTranslatable {
    VERB("verb"),
    ASSERT("assert"),
    EXPECT_THROWN("expect the thrown exception"),
}
