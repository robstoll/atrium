package ch.tutteli.atrium.spec

import ch.tutteli.atrium.reporting.translating.ISimpleTranslatable

internal enum class AssertionVerb(override val value: String) : ISimpleTranslatable {
    VERB("verb"),
    ASSERT("assert"),
    EXPECT_THROWN("expect the thrown exception"),
}
