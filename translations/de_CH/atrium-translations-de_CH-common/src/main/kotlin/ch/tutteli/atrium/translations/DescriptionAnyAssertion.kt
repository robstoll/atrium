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
    IS_A("ist eine Instanz vom Typ"),
    IS_SAME("ist dieselbe Instanz wie"),
    IS_NOT_SAME("ist nicht dieselbe Instanz wie"),
}
