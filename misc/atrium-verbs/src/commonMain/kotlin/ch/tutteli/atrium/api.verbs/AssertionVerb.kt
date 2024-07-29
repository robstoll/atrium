//TODO remove file with 2.0.0
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.api.verbs

import ch.tutteli.atrium.reporting.translating.StringBasedTranslatable

//TODO 1.3.0 replace by TextConstants or the like?
/**
 * The [ch.tutteli.atrium.reporting.translating.Translatable] for the assertion [expect].
 */
@Deprecated(
    "switch to ExpectationVerb which is Description based, will be removed with 2.0.0 at the latest",
    ReplaceWith("ExpectationVerb")
)
enum class AssertionVerb(override val value: String) : StringBasedTranslatable {
    @Deprecated(
        "switch to ExpectationVerb which is Description based, will be removed with 2.0.0 at the latest",
        ReplaceWith("ExpectationVerb.EXPECT")
    )
    EXPECT("I expected subject"),
    @Deprecated(
        "switch to ExpectationVerb which is Description based, will be removed with 2.0.0 at the latest",
        ReplaceWith("ExpectationVerb.EXPECT_GROUPED")
    )
    EXPECT_GROUPED("my expectations"),
}
