package ch.tutteli.atrium.translations

import ch.tutteli.atrium.assertions.DescriptiveAssertion
import ch.tutteli.atrium.reporting.translating.StringBasedTranslatable

/**
 * Contains the [DescriptiveAssertion.description]s of the expectation functions which are applicable to [CharSequence].
 */
enum class DescriptionCharSequenceExpectation(override val value: String) : StringBasedTranslatable {
    /** @since 0.18.0 */
    AT_LEAST("ist zumindest"),

    /** @since 0.18.0 */
    AT_MOST("ist höchstens"),

    /** @since 0.18.0 */
    BLANK("blank"),

    /** @since 0.18.0 */
    EMPTY("empty"),

    /** @since 0.18.0 */
    EXACTLY("ist genau"),

    /** @since 0.18.0 */
    IGNORING_CASE("%s, Gross-/Kleinschreibung ignorierend"),

    /** @since 0.18.0 */
    NOT_FOUND("aber es wurde kein Treffer gefunden"),

    /** @since 0.18.0 */
    NUMBER_OF_MATCHES_FOUND("und %s Treffer wurden gefunden"),

    /** @since 0.18.0 */
    NUMBER_OF_MATCHES("Anzahl Treffer"),


    /** @since 0.18.0 */
    TO_CONTAIN("enthält"),

    /** @since 0.18.0 */
    NOT_TO_CONTAIN("enthält nicht"),

    /** @since 0.18.0 */
    TO_END_WITH("endet mit"),

    /** @since 0.18.0 */
    NOT_TO_END_WITH("endet nicht mit"),

    /** @since 0.18.0 */
    TO_MATCH("stimmt vollständig überein mit"),

    /** @since 0.18.0 */
    NOT_TO_MATCH("stimmt nicht vollständig überein mit"),

    /** @since 0.18.0 */
    TO_START_WITH("beginnt mit"),

    /** @since 0.18.0 */
    NOT_TO_START_WITH("beginnt nicht mit"),

    /** @since 0.18.0 */
    STRING_MATCHING_REGEX("Zeichenkette übereinstimmend mit Regex"),

    /** @since 0.18.0 */
    VALUE("Wert"),
}
