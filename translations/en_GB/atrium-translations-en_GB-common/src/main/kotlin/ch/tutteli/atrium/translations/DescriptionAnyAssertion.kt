package ch.tutteli.atrium.translations

import ch.tutteli.atrium.assertions.DescriptiveAssertion
import ch.tutteli.atrium.reporting.translating.StringBasedTranslatable

/**
 * Contains the [DescriptiveAssertion.description]s of the assertion functions which are applicable to [Any].
 */
enum class DescriptionAnyAssertion(override val value: String) : StringBasedTranslatable {
    @Deprecated(
        "Will be removed in version 1.0.0.",
        ReplaceWith("DescriptionBasic.TO_BE", imports = ["ch.tutteli.atrium.translations.DescriptionBasic"])
    )
    TO_BE(DescriptionBasic.TO_BE.value),
    @Deprecated(
        "Will be removed in version 1.0.0.",
        ReplaceWith("DescriptionBasic.NOT_TO_BE", imports = ["ch.tutteli.atrium.translations.DescriptionBasic"])
    )
    NOT_TO_BE(DescriptionBasic.NOT_TO_BE.value),
    IS_A("is instance of type"),
    IS_SAME("is the same as"),
    IS_NOT_SAME("is not the same as")
}
