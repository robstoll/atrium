package ch.tutteli.atrium.translations

import ch.tutteli.atrium.assertions.DescriptiveAssertion
import ch.tutteli.atrium.reporting.translating.StringBasedTranslatable

/**
 * Contains the [DescriptiveAssertion.description]s of the assertion functions which are applicable to [Any].
 */
enum class DescriptionFunLikeAssertion(override val value: String) : StringBasedTranslatable {
    IS_NOT_THROWING_1("wirft"),
    IS_NOT_THROWING_2("keine Exception bei Aufruf"),
    THROWN_EXCEPTION_WHEN_CALLED("geworfene Exception bei Aufruf"),
}
