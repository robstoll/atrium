//TODO remove file with 1.0.0 at the latest
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.translations

import ch.tutteli.atrium.assertions.DescriptiveAssertion
import ch.tutteli.atrium.reporting.translating.StringBasedTranslatable

/**
 * Contains the [DescriptiveAssertion.description]s of the assertion functions which are applicable to [Map].
 */
@Deprecated("Will be removed with 1.0.0 at the latest")
enum class DescriptionMapAssertion(override val value: String) : StringBasedTranslatable {
    @Deprecated("Will be removed with 1.0.0 at the latest (maybe earlier)")
    CANNOT_EVALUATE_KEY_DOES_NOT_EXIST("$COULD_NOT_EVALUATE_DEFINED_ASSERTIONS -- der gegebene Key existiert nicht.\n$VISIT_COULD_NOT_EVALUATE_ASSERTIONS"),

    @Deprecated("Will be removed with 1.0.0 at the latest")
    CONTAINS_IN_ANY_ORDER("enthält, in beliebiger Reihenfolge"),

    @Deprecated(
        "Use DescriptionMapLikeAssertion.CONTAINS_KEY; will be removed with 1.0.0 at the latest",
        ReplaceWith("DescriptionMapLikeAssertion.CONTAINS_KEY")
    )
    CONTAINS_KEY("enthält Key"),

    @Deprecated(
        "Use DescriptionMapLikeAssertion.CONTAINS_NOT_KEY; will be removed with 1.0.0 at the latest",
        ReplaceWith("DescriptionMapLikeAssertion.CONTAINS_NOT_KEY")
    )
    CONTAINS_NOT_KEY("enthält nicht den Key"),

    @Deprecated(
        "Use DescriptionMapLikeAssertion.ENTRY_WITH_KEY; will be removed with 1.0.0 at the latest",
        ReplaceWith("DescriptionMapLikeAssertion.ENTRY_WITH_KEY")
    )
    ENTRY_WITH_KEY("Eintrag %s"),

    @Deprecated(
        "Use DescriptionMapLikeAssertion.KEY_DOES_NOT_EXIST; will be removed with 1.0.0 at the latest",
        ReplaceWith("DescriptionMapLikeAssertion.KEY_DOES_NOT_EXIST")
    )
    KEY_DOES_NOT_EXIST("❗❗ Key existiert nicht"),

    @Deprecated("Will be removed with 1.0.0 at the latest")
    SIZE("Grösse"),
}
