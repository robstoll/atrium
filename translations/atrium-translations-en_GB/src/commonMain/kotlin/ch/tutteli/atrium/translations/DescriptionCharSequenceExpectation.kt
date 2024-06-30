// TODO remove with 2.0.0 at the latest
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.translations

import ch.tutteli.atrium.assertions.DescriptiveAssertion
import ch.tutteli.atrium.reporting.translating.StringBasedTranslatable

/**
 * Contains the [DescriptiveAssertion.description]s of the expectation functions which are applicable to [CharSequence].
 */
@Deprecated(
    "Switch to DescriptionCharSequenceProof from core, will be removed with 2.0.0 at the latest",
    ReplaceWith("ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionCharSequenceProof")
)
enum class DescriptionCharSequenceExpectation(override val value: String) : StringBasedTranslatable {
    @Deprecated(
        "will be removed with 2.0.0 at the latest",
        ReplaceWith("ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionCharSequenceProof.AT_LEAST")
    )
    /** @since 0.18.0 */
    AT_LEAST("is at least"),

    @Deprecated(
        "will be removed with 2.0.0 at the latest",
        ReplaceWith("ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionCharSequenceProof.AT_MOST")
    )
    /** @since 0.18.0 */
    AT_MOST("is at most"),

    @Deprecated(
        "will be removed with 2.0.0 at the latest",
        ReplaceWith("ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionCharSequenceProof.BLANK")
    )
    /** @since 0.18.0 */
    BLANK("blank"),

    @Deprecated(
        "will be removed with 2.0.0 at the latest",
        ReplaceWith("ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionCharSequenceProof.EMPTY")
    )
    /** @since 0.18.0 */
    EMPTY("empty"),

    @Deprecated(
        "will be removed with 2.0.0 at the latest",
        ReplaceWith("ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionCharSequenceProof.EXACTLY")
    )
    /** @since 0.18.0 */
    EXACTLY("is exactly"),

    @Deprecated(
        "will be removed with 2.0.0 at the latest",
        ReplaceWith("ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionCharSequenceProof.IGNORING_CASE")
    )
    /** @since 0.18.0 */
    IGNORING_CASE("%s, ignoring case"),

    @Deprecated(
        "will be removed with 2.0.0 at the latest",
        ReplaceWith("ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionCharSequenceProof.NOT_FOUND")
    )
    /** @since 0.18.0 */
    NOT_FOUND("but no match was found"),

    @Deprecated(
        "will be removed with 2.0.0 at the latest",
        ReplaceWith("ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionCharSequenceProof.NUMBER_OF_MATCHES_FOUND")
    )
    /** @since 0.18.0 */
    NUMBER_OF_MATCHES_FOUND("and %s matches were found"),

    @Deprecated(
        "will be removed with 2.0.0 at the latest",
        ReplaceWith("ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionCharSequenceProof.NUMBER_OF_MATCHES")
    )
    /** @since 0.18.0 */
    NUMBER_OF_MATCHES("number of matches"),


    @Deprecated(
        "will be removed with 2.0.0 at the latest",
        ReplaceWith("ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionCharSequenceProof.TO_CONTAIN")
    )
    /** @since 0.18.0 */
    TO_CONTAIN("to contain"),

    @Deprecated(
        "will be removed with 2.0.0 at the latest",
        ReplaceWith("ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionCharSequenceProof.NOT_TO_CONTAIN")
    )
    /** @since 0.18.0 */
    NOT_TO_CONTAIN("not to contain"),

    @Deprecated(
        "will be removed with 2.0.0 at the latest",
        ReplaceWith("ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionCharSequenceProof.TO_END_WITH")
    )
    /** @since 0.18.0 */
    TO_END_WITH("to end with"),

    @Deprecated(
        "will be removed with 2.0.0 at the latest",
        ReplaceWith("ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionCharSequenceProof.NOT_TO_END_WITH")
    )
    /** @since 0.18.0 */
    NOT_TO_END_WITH("not to end with"),

    @Deprecated(
        "will be removed with 2.0.0 at the latest",
        ReplaceWith("ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionCharSequenceProof.TO_MATCH")
    )
    /** @since 0.18.0 */
    TO_MATCH("to match entirely"),

    @Deprecated(
        "will be removed with 2.0.0 at the latest",
        ReplaceWith("ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionCharSequenceProof.NOT_TO_MATCH")
    )
    /** @since 0.18.0 */
    NOT_TO_MATCH("not to match (entirely)"),

    @Deprecated(
        "will be removed with 2.0.0 at the latest",
        ReplaceWith("ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionCharSequenceProof.TO_START_WITH")
    )
    /** @since 0.18.0 */
    TO_START_WITH("to start with"),

    @Deprecated(
        "will be removed with 2.0.0 at the latest",
        ReplaceWith("ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionCharSequenceProof.NOT_TO_START_WITH")
    )
    /** @since 0.18.0 */
    NOT_TO_START_WITH("not to start with"),

    @Deprecated(
        "will be removed with 2.0.0 at the latest",
        ReplaceWith("ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionCharSequenceProof.STRING_MATCHING_REGEX")
    )
    /** @since 0.18.0 */
    STRING_MATCHING_REGEX("string matching regex"),

    @Deprecated(
        "will be removed with 2.0.0 at the latest",
        ReplaceWith("ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionCharSequenceProof.VALUE")
    )
    /** @since 0.18.0 */
    VALUE("value"),
}
