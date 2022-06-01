//TODO remove file with 1.0.0 at the latest
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.translations

import ch.tutteli.atrium.assertions.DescriptiveAssertion
import ch.tutteli.atrium.reporting.translating.StringBasedTranslatable

/**
 * Contains the [DescriptiveAssertion.description]s of the assertion functions which are applicable to [Map].
 */
@Deprecated("Will be removed with 1.0.0 at the latest")
enum class DescriptionMapLikeAssertion(override val value: String) : StringBasedTranslatable {
    @Deprecated(
        "Use DescriptionMapLikeExpectation.TO_CONTAIN; will be removed with 1.0.0 at the latest",
        ReplaceWith("DescriptionMapLikeExpectation.TO_CONTAIN")
    )
    CONTAINS("enthält"),

    @Deprecated(
        "Use DescriptionMapLikeExpectation.TO_CONTAIN_KEY; will be removed with 1.0.0 at the latest",
        ReplaceWith("DescriptionMapLikeExpectation.TO_CONTAIN_KEY")
    )
    CONTAINS_KEY("enthält Key"),

    @Deprecated(
        "Use DescriptionMapLikeExpectation.NOT_TO_CONTAIN_KEY; will be removed with 1.0.0 at the latest",
        ReplaceWith("DescriptionMapLikeExpectation.NOT_TO_CONTAIN_KEY")
    )
    CONTAINS_NOT_KEY("enthält nicht den Key"),

    @Deprecated(
        "Use DescriptionMapLikeExpectation.ENTRY_WITH_KEY; will be removed with 1.0.0 at the latest",
        ReplaceWith("DescriptionMapLikeExpectation.ENTRY_WITH_KEY")
    )
    ENTRY_WITH_KEY("Eintrag %s"),

    @Deprecated(
        "Use DescriptionMapLikeExpectation.IN_ANY_ORDER; will be removed with 1.0.0 at the latest",
        ReplaceWith("DescriptionMapLikeExpectation.IN_ANY_ORDER")
    )
    IN_ANY_ORDER("%s, in beliebiger Reihenfolge"),

    @Deprecated(
        "Use DescriptionMapLikeExpectation.IN_ANY_ORDER_ONLY; will be removed with 1.0.0 at the latest",
        ReplaceWith("DescriptionMapLikeExpectation.IN_ANY_ORDER_ONLY")
    )
    IN_ANY_ORDER_ONLY("%s ausschliesslich, in beliebiger Reihenfolge"),

    @Deprecated(
        "Use DescriptionMapLikeExpectation.IN_ORDER; will be removed with 1.0.0 at the latest",
        ReplaceWith("DescriptionMapLikeExpectation.IN_ORDER")
    )
    IN_ORDER("%, in gegebener Reihenfolge"),

    @Deprecated(
        "Use DescriptionMapLikeExpectation.IN_ORDER_ONLY; will be removed with 1.0.0 at the latest",
        ReplaceWith("DescriptionMapLikeExpectation.IN_ORDER_ONLY")
    )
    IN_ORDER_ONLY("%s ausschliesslich, in gegebener Reihenfolge"),

    @Deprecated(
        "Use DescriptionMapLikeExpectation.KEY_DOES_NOT_EXIST; will be removed with 1.0.0 at the latest",
        ReplaceWith("DescriptionMapLikeExpectation.KEY_DOES_NOT_EXIST")
    )
    KEY_DOES_NOT_EXIST("❗❗ Key existiert nicht"),

    @Deprecated(
        "Use DescriptionMapLikeExpectation.WARNING_ADDITIONAL_ENTRIES; will be removed with 1.0.0 at the latest",
        ReplaceWith("DescriptionMapLikeExpectation.WARNING_ADDITIONAL_ENTRIES")
    )
    WARNING_ADDITIONAL_ENTRIES("zusätzliche Einträge entdeckt"),
}
