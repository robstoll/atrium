package ch.tutteli.atrium.translations

import ch.tutteli.atrium.assertions.DescriptiveAssertion
import ch.tutteli.atrium.reporting.translating.StringBasedTranslatable

/**
 * Contains the [DescriptiveAssertion.description]s of the assertion functions which are applicable to [CharSequence].
 */
enum class DescriptionCharSequenceAssertion(override val value: String) : StringBasedTranslatable {
    AT_LEAST("is at least"),
    AT_MOST("is at most"),
    BLANK("blank"),
    CONTAINS("contains"),
    CONTAINS_NOT("does not contain"),
    EMPTY("empty"),
    ENDS_WITH("ends with"),
    ENDS_NOT_WITH("does not end with"),
    EXACTLY("is exactly"),
    IGNORING_CASE("%s, ignoring case"),
    MATCHES("matches entirely"),
    MISMATCHES("does not match entirely"),
    NOT_FOUND("but no match was found"),
    NUMBER_OF_MATCHES_FOUND("and %s matches were found"),
    NUMBER_OF_OCCURRENCES("number of matches"),
    STARTS_WITH("starts with"),
    STARTS_NOT_WITH("does not start with"),
    STRING_MATCHING_REGEX("string matching regex"),
    VALUE("value"),
}
