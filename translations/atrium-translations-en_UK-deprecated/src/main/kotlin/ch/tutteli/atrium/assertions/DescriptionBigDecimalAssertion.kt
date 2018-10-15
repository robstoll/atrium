@file:Suppress("DEPRECATION" /* TODO remove with 1.0.0*/)
package ch.tutteli.atrium.assertions

import ch.tutteli.atrium.reporting.translating.StringBasedTranslatable
import java.math.BigDecimal

/**
 * Contains the [DescriptiveAssertion.description]s of the assertion functions which are applicable to [BigDecimal].
 */
@Deprecated(
    "use the description from package translations, will be removed with 1.0.0",
    ReplaceWith("ch.tutteli.atrium.translations.DescriptionBigDecimalAssertion")
)
enum class DescriptionBigDecimalAssertion(override val value: String) : StringBasedTranslatable {
    FAILURE_TO_BE_BUT_NUMERICALLY_EQUAL(ch.tutteli.atrium.translations.DescriptionBigDecimalAssertion.FAILURE_IS_EQUAL_INCLUDING_SCALE_BUT_NUMERICALLY_EQUAL.value),
    IS_EQUAL_INCLUDING_SCALE(ch.tutteli.atrium.translations.DescriptionBigDecimalAssertion.IS_EQUAL_INCLUDING_SCALE.value),
    IS_NOT_EQUAL_INCLUDING_SCALE(ch.tutteli.atrium.translations.DescriptionBigDecimalAssertion.IS_NOT_EQUAL_INCLUDING_SCALE.value),
    IS_NUMERICALLY_EQUAL_TO(ch.tutteli.atrium.translations.DescriptionBigDecimalAssertion.IS_NUMERICALLY_EQUAL_TO.value),
    IS_NOT_NUMERICALLY_EQUAL_TO(ch.tutteli.atrium.translations.DescriptionBigDecimalAssertion.IS_NOT_NUMERICALLY_EQUAL_TO.value),
}
