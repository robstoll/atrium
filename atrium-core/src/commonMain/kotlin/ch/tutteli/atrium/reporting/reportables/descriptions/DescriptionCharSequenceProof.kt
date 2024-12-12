package ch.tutteli.atrium.reporting.reportables.descriptions

import ch.tutteli.atrium.reporting.reportables.Description
import ch.tutteli.atrium.reporting.reportables.impl.SuffixedDescription

/**
 * Contains [Description]s that are related to expectation functions which are applicable to [CharSequence].
 */
enum class DescriptionCharSequenceProof(override val string: String) : Description {
    //TOD 1.3.0 consider to change to `to be at least/most`, `to be exactly` etc.

    /** @since 1.3.0 (but was since 0.18.0 in atrium-translations DescriptionCharSequenceExpectation) */
    AT_LEAST("is at least"),

    /** @since 1.3.0 (but was since 0.18.0 in atrium-translations DescriptionCharSequenceExpectation) */
    AT_MOST("is at most"),

    /** @since 1.3.0 (but was since 0.18.0 in atrium-translations DescriptionCharSequenceExpectation) */
    BLANK("blank"),

    /** @since 1.3.0 (but was since 0.18.0 in atrium-translations DescriptionCharSequenceExpectation) */
    EMPTY("empty"),

    /** @since 1.3.0 (but was since 0.18.0 in atrium-translations DescriptionCharSequenceExpectation) */
    EXACTLY("is exactly"),

    /** @since 1.3.0 (but was since 0.18.0 in atrium-translations DescriptionCharSequenceExpectation) */
    NOT_FOUND("but no match was found"),

    /** @since 1.3.0 (but was since 0.18.0 in atrium-translations DescriptionCharSequenceExpectation) */
    NUMBER_OF_MATCHES("number of matches"),

    /** @since 1.3.0 (but was since 0.18.0 in atrium-translations DescriptionCharSequenceExpectation) */
    TO_CONTAIN("to contain"),

    /** @since 1.3.0 (but was since 0.18.0 in atrium-translations DescriptionCharSequenceExpectation) */
    NOT_TO_CONTAIN("not to contain"),

    /** @since 1.3.0 (but was since 0.18.0 in atrium-translations DescriptionCharSequenceExpectation) */
    TO_END_WITH("to end with"),

    /** @since 1.3.0 (but was since 0.18.0 in atrium-translations DescriptionCharSequenceExpectation) */
    NOT_TO_END_WITH("not to end with"),

    /** @since 1.3.0 (but was since 0.18.0 in atrium-translations DescriptionCharSequenceExpectation) */
    TO_MATCH("to match entirely"),

    /** @since 1.3.0 (but was since 0.18.0 in atrium-translations DescriptionCharSequenceExpectation) */
    NOT_TO_MATCH("not to match (entirely)"),

    /** @since 1.3.0 (but was since 0.18.0 in atrium-translations DescriptionCharSequenceExpectation) */
    TO_START_WITH("to start with"),

    /** @since 1.3.0 (but was since 0.18. in atrium-translations DescriptionCharSequenceExpectation)0 */
    NOT_TO_START_WITH("not to start with"),

    /** @since 1.3.0 (but was since 0.18.0 in atrium-translations DescriptionCharSequenceExpectation) */
    STRING_MATCHING_REGEX("string matching regex"),

    /** @since 1.3.0 (but was since 0.18.0 in atrium-translations DescriptionCharSequenceExpectation) */
    VALUE("value"),
    ;

    @Suppress("FunctionName")
    companion object {
        //TODO 1.3.0 think about if you want to support placeholders like that
        /** @since 1.3.0 (but was since 0.18.0 in atrium-translations DescriptionCharSequenceExpectation) */
        val Description.IGNORING_CASE get(): Description = SuffixedDescription(this, ", ignoring case")
    }
}
