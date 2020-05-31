@file:Suppress("DEPRECATION" /* TODO remove with 1.0.0 */)
package ch.tutteli.atrium.spec

import ch.tutteli.atrium.reporting.translating.StringBasedTranslatable

@Deprecated(
    "Switch to atrium-specs-common; will be removed with 1.0.0",
    ReplaceWith("ch.tutteli.atrium.specs.AssertionVerb")
)
enum class AssertionVerb(override val value: String) : StringBasedTranslatable {
    @Deprecated(
        "Switch to atrium-specs-common; will be removed with 1.0.0",
        ReplaceWith("ch.tutteli.atrium.specs.AssertionVerb.VERB")
    )
    VERB("verb"),
    @Deprecated(
        "Switch to atrium-specs-common; will be removed with 1.0.0",
        ReplaceWith("ch.tutteli.atrium.specs.AssertionVerb.ASSERT")
    )
    ASSERT("assert"),
    @Deprecated(
        "Switch to atrium-specs-common; will be removed with 1.0.0",
        ReplaceWith("ch.tutteli.atrium.specs.AssertionVerb.EXPECT_THROWN")
    )
    EXPECT_THROWN("expect the thrown exception"),
}
