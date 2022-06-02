//TODO remove file with 1.0.0 at the latest
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.translations

import ch.tutteli.atrium.assertions.DescriptiveAssertion
import ch.tutteli.atrium.reporting.translating.StringBasedTranslatable

/**
 * Contains the [DescriptiveAssertion.description]s of the assertion functions which are applicable to [CharSequence].
 */
@Deprecated("Will be removed with 1.0.0 at the latest")
enum class DescriptionCharSequenceAssertion(override val value: String) : StringBasedTranslatable {
    @Deprecated(
        "Use DescriptionCharSequenceExpectation instead; will be removed with 1.0.0 at the latest",
        ReplaceWith("DescriptionCharSequenceExpectation.AT_LEAST")
    )
    AT_LEAST("is at least"),

    @Deprecated(
        "Use DescriptionCharSequenceExpectation instead; will be removed with 1.0.0 at the latest",
        ReplaceWith("DescriptionCharSequenceExpectation.AT_MOST")
    )
    AT_MOST("is at most"),

    @Deprecated(
        "Use DescriptionCharSequenceExpectation instead; will be removed with 1.0.0 at the latest",
        ReplaceWith("DescriptionCharSequenceExpectation.BLANK")
    )
    BLANK("blank"),

    @Deprecated(
        "Use DescriptionCharSequenceExpectation instead; will be removed with 1.0.0 at the latest",
        ReplaceWith("DescriptionCharSequenceExpectation.TO_CONTAIN")
    )
    CONTAINS("contains"),

    @Deprecated(
        "Use DescriptionCharSequenceExpectation instead; will be removed with 1.0.0 at the latest",
        ReplaceWith("DescriptionCharSequenceExpectation.NOT_TO_CONTAIN")
    )
    CONTAINS_NOT("does not contain"),

    @Deprecated(
        "Use DescriptionCharSequenceExpectation instead; will be removed with 1.0.0 at the latest",
        ReplaceWith("DescriptionCharSequenceExpectation.EMPTY")
    )
    EMPTY("empty"),

    @Deprecated(
        "Use DescriptionCharSequenceExpectation instead; will be removed with 1.0.0 at the latest",
        ReplaceWith("DescriptionCharSequenceExpectation.TO_END_WITH")
    )
    ENDS_WITH("ends with"),

    @Deprecated(
        "Use DescriptionCharSequenceExpectation instead; will be removed with 1.0.0 at the latest",
        ReplaceWith("DescriptionCharSequenceExpectation.NOT_TO_END_WITH")
    )
    ENDS_NOT_WITH("does not end with"),

    @Deprecated(
        "Use DescriptionCharSequenceExpectation instead; will be removed with 1.0.0 at the latest",
        ReplaceWith("DescriptionCharSequenceExpectation.EXACTLY")
    )
    EXACTLY("is exactly"),

    @Deprecated(
        "Use DescriptionCharSequenceExpectation instead; will be removed with 1.0.0 at the latest",
        ReplaceWith("DescriptionCharSequenceExpectation.IGNORING_CASE")
    )
    IGNORING_CASE("%s, ignoring case"),

    @Deprecated(
        "Use DescriptionCharSequenceExpectation instead; will be removed with 1.0.0 at the latest",
        ReplaceWith("DescriptionCharSequenceExpectation.TO_MATCH")
    )
    MATCHES("matches entirely"),

    @Deprecated(
        "Use DescriptionCharSequenceExpectation instead; will be removed with 1.0.0 at the latest",
        ReplaceWith("DescriptionCharSequenceExpectation.NOT_TO_MATCH")
    )
    MISMATCHES("does not match entirely"),

    @Deprecated(
        "Use DescriptionCharSequenceExpectation instead; will be removed with 1.0.0 at the latest",
        ReplaceWith("DescriptionCharSequenceExpectation.NOT_FOUND")
    )
    NOT_FOUND("but no match was found"),

    @Deprecated(
        "Use DescriptionCharSequenceExpectation instead; will be removed with 1.0.0 at the latest",
        ReplaceWith("DescriptionCharSequenceExpectation.NUMBER_OF_MATCHES_FOUND")
    )
    NUMBER_OF_MATCHES_FOUND("and %s matches were found"),

    @Deprecated(
        "Use DescriptionCharSequenceExpectation instead; will be removed with 1.0.0 at the latest",
        ReplaceWith("DescriptionCharSequenceExpectation.NUMBER_OF_MATCHES")
    )
    NUMBER_OF_OCCURRENCES("number of matches"),

    @Deprecated(
        "Use DescriptionCharSequenceExpectation instead; will be removed with 1.0.0 at the latest",
        ReplaceWith("DescriptionCharSequenceExpectation.TO_START_WITH")
    )
    STARTS_WITH("starts with"),

    @Deprecated(
        "Use DescriptionCharSequenceExpectation instead; will be removed with 1.0.0 at the latest",
        ReplaceWith("DescriptionCharSequenceExpectation.NOT_TO_START_WITH")
    )
    STARTS_NOT_WITH("does not start with"),

    @Deprecated(
        "Use DescriptionCharSequenceExpectation instead; will be removed with 1.0.0 at the latest",
        ReplaceWith("DescriptionCharSequenceExpectation.STRING_MATCHING_REGEX")
    )
    STRING_MATCHING_REGEX("string matching regex"),

    @Deprecated(
        "Use DescriptionCharSequenceExpectation instead; will be removed with 1.0.0 at the latest",
        ReplaceWith("DescriptionCharSequenceExpectation.VALUE")
    )
    VALUE("value"),
}
