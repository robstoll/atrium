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
    AT_LEAST("ist zumindest"),

    @Deprecated(
        "Use DescriptionCharSequenceExpectation instead; will be removed with 1.0.0 at the latest",
        ReplaceWith("DescriptionCharSequenceExpectation.AT_MOST")
    )
    AT_MOST("ist höchstens"),

    @Deprecated(
        "Use DescriptionCharSequenceExpectation instead; will be removed with 1.0.0 at the latest",
        ReplaceWith("DescriptionCharSequenceExpectation.BLANK")
    )
    BLANK("blank"),

    @Deprecated(
        "Use DescriptionCharSequenceExpectation instead; will be removed with 1.0.0 at the latest",
        ReplaceWith("DescriptionCharSequenceExpectation.TO_CONTAIN")
    )
    CONTAINS("enthält"),

    @Deprecated(
        "Use DescriptionCharSequenceExpectation instead; will be removed with 1.0.0 at the latest",
        ReplaceWith("DescriptionCharSequenceExpectation.NOT_TO_CONTAIN")
    )
    CONTAINS_NOT("enthält nicht"),

    @Deprecated(
        "Use DescriptionCharSequenceExpectation instead; will be removed with 1.0.0 at the latest",
        ReplaceWith("DescriptionCharSequenceExpectation.EMPTY")
    )
    EMPTY("empty"),

    @Deprecated(
        "Use DescriptionCharSequenceExpectation instead; will be removed with 1.0.0 at the latest",
        ReplaceWith("DescriptionCharSequenceExpectation.TO_END_WITH")
    )
    ENDS_WITH("endet mit"),

    @Deprecated(
        "Use DescriptionCharSequenceExpectation instead; will be removed with 1.0.0 at the latest",
        ReplaceWith("DescriptionCharSequenceExpectation.NOT_TO_END_WITH")
    )
    ENDS_NOT_WITH("endet nicht mit"),

    @Deprecated(
        "Use DescriptionCharSequenceExpectation instead; will be removed with 1.0.0 at the latest",
        ReplaceWith("DescriptionCharSequenceExpectation.EXACTLY")
    )
    EXACTLY("ist genau"),

    @Deprecated(
        "Use DescriptionCharSequenceExpectation instead; will be removed with 1.0.0 at the latest",
        ReplaceWith("DescriptionCharSequenceExpectation.IGNORING_CASE")
    )
    IGNORING_CASE("%s, Gross-/Kleinschreibung ignorierend"),

    @Deprecated(
        "Use DescriptionCharSequenceExpectation instead; will be removed with 1.0.0 at the latest",
        ReplaceWith("DescriptionCharSequenceExpectation.TO_MATCH")
    )
    MATCHES("stimmt vollständig überein mit"),

    @Deprecated(
        "Use DescriptionCharSequenceExpectation instead; will be removed with 1.0.0 at the latest",
        ReplaceWith("DescriptionCharSequenceExpectation.NOT_TO_MATCH")
    )
    MISMATCHES("stimmt nicht vollständig überein mit"),

    @Deprecated(
        "Use DescriptionCharSequenceExpectation instead; will be removed with 1.0.0 at the latest",
        ReplaceWith("DescriptionCharSequenceExpectation.NOT_FOUND")
    )
    NOT_FOUND("aber es wurde kein Treffer gefunden"),

    @Deprecated(
        "Use DescriptionCharSequenceExpectation instead; will be removed with 1.0.0 at the latest",
        ReplaceWith("DescriptionCharSequenceExpectation.NUMBER_OF_MATCHES_FOUND")
    )
    NUMBER_OF_MATCHES_FOUND("und %s Treffer wurden gefunden"),

    @Deprecated(
        "Use DescriptionCharSequenceExpectation instead; will be removed with 1.0.0 at the latest",
        ReplaceWith("DescriptionCharSequenceExpectation.NUMBER_OF_MATCHES")
    )
    NUMBER_OF_OCCURRENCES("Anzahl Treffer"),

    @Deprecated(
        "Use DescriptionCharSequenceExpectation instead; will be removed with 1.0.0 at the latest",
        ReplaceWith("DescriptionCharSequenceExpectation.TO_START_WITH")
    )
    STARTS_WITH("beginnt mit"),

    @Deprecated(
        "Use DescriptionCharSequenceExpectation instead; will be removed with 1.0.0 at the latest",
        ReplaceWith("DescriptionCharSequenceExpectation.NOT_TO_START_WITH")
    )
    STARTS_NOT_WITH("beginnt nicht mit"),

    @Deprecated(
        "Use DescriptionCharSequenceExpectation instead; will be removed with 1.0.0 at the latest",
        ReplaceWith("DescriptionCharSequenceExpectation.STRING_MATCHING_REGEX")
    )
    STRING_MATCHING_REGEX("Zeichenkette übereinstimmend mit Regex"),

    @Deprecated(
        "Use DescriptionCharSequenceExpectation instead; will be removed with 1.0.0 at the latest",
        ReplaceWith("DescriptionCharSequenceExpectation.VALUE")
    )
    VALUE("Wert"),
}
