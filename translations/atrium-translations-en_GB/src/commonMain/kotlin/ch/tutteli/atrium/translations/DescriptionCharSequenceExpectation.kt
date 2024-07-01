// TODO remove with 2.0.0 at the latest
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.translations

import ch.tutteli.atrium.assertions.DescriptiveAssertion
import ch.tutteli.atrium.reporting.translating.StringBasedTranslatable

/**
 * Contains the [DescriptiveAssertion.description]s of the expectation functions which are applicable to [CharSequence].
 */
enum class DescriptionCharSequenceExpectation(override val value: String) : StringBasedTranslatable {
    /** @since 0.18.0 */
    AT_LEAST("is at least"),

    /** @since 0.18.0 */
    AT_MOST("is at most"),

    /** @since 0.18.0 */
    BLANK("blank"),

    /** @since 0.18.0 */
    EMPTY("empty"),

    /** @since 0.18.0 */
    EXACTLY("is exactly"),

    /** @since 0.18.0 */
    IGNORING_CASE("%s, ignoring case"),

    /** @since 0.18.0 */
    NOT_FOUND("but no match was found"),

    /** @since 0.18.0 */
    NUMBER_OF_MATCHES_FOUND("and %s matches were found"),

    /** @since 0.18.0 */
    NUMBER_OF_MATCHES("number of matches"),


    /** @since 0.18.0 */
    TO_CONTAIN("to contain"),

    /** @since 0.18.0 */
    NOT_TO_CONTAIN("not to contain"),

    /** @since 0.18.0 */
    TO_END_WITH("to end with"),

    /** @since 0.18.0 */
    NOT_TO_END_WITH("not to end with"),

    /** @since 0.18.0 */
    TO_MATCH("to match entirely"),

    /** @since 0.18.0 */
    NOT_TO_MATCH("not to match (entirely)"),

    /** @since 0.18.0 */
    TO_START_WITH("to start with"),

    /** @since 0.18.0 */
    NOT_TO_START_WITH("not to start with"),

    /** @since 0.18.0 */
    STRING_MATCHING_REGEX("string matching regex"),

    /** @since 0.18.0 */
    VALUE("value"),
}
