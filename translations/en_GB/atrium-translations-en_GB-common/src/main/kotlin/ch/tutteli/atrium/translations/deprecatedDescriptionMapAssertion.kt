//TODO remove file with latest with 1.0.0
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.translations

import ch.tutteli.atrium.assertions.DescriptiveAssertion
import ch.tutteli.atrium.reporting.translating.StringBasedTranslatable

/**
 * Contains the [DescriptiveAssertion.description]s of the assertion functions which are applicable to [Map].
 */
@Deprecated("Will be removed with latest with 1.0.0")
enum class DescriptionMapAssertion(override val value: String) : StringBasedTranslatable {
    @Deprecated("Will be removed with latest with 1.0.0")
    CANNOT_EVALUATE_KEY_DOES_NOT_EXIST("$COULD_NOT_EVALUATE_DEFINED_ASSERTIONS -- given key does not exist.\n$VISIT_COULD_NOT_EVALUATE_ASSERTIONS"),

    @Deprecated("Will be removed with latest with 1.0.0")
    CONTAINS_IN_ANY_ORDER("contains, in any order"),

    @Deprecated(
        "Use DescriptionMapLikeAssertion.CONTAINS_KEY; will be removed with latest with 1.0.0",
        ReplaceWith("DescriptionMapLikeAssertion.CONTAINS_KEY")
    )
    CONTAINS_KEY("contains key"),

    @Deprecated(
        "Use DescriptionMapLikeAssertion.CONTAINS_NOT_KEY; will be removed with latest with 1.0.0",
        ReplaceWith("DescriptionMapLikeAssertion.CONTAINS_NOT_KEY")
    )
    CONTAINS_NOT_KEY("does not contain key"),

    @Deprecated(
        "Use DescriptionMapLikeAssertion.ENTRY_WITH_KEY; will be removed with latest with 1.0.0",
        ReplaceWith("DescriptionMapLikeAssertion.ENTRY_WITH_KEY")
    )
    ENTRY_WITH_KEY("entry %s"),

    @Deprecated(
        "Use DescriptionMapLikeAssertion.KEY_DOES_NOT_EXIST; will be removed with latest with 1.0.0",
        ReplaceWith("DescriptionMapLikeAssertion.KEY_DOES_NOT_EXIST")
    )
    KEY_DOES_NOT_EXIST("❗❗ key does not exist"),

    @Deprecated("Will be removed with latest with 1.0.0")
    SIZE("size"),
}
