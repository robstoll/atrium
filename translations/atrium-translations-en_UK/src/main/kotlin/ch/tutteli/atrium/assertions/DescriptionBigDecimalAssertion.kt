package ch.tutteli.atrium.assertions

import ch.tutteli.atrium.reporting.translating.StringBasedTranslatable
import java.math.BigDecimal

/**
 * Contains the [DescriptiveAssertion.description]s of the assertion functions which are applicable to [BigDecimal].
 */
enum class DescriptionBigDecimalAssertion(override val value: String) : StringBasedTranslatable {
    FAILURE_TO_BE_BUT_NUMERICALLY_EQUAL("notice, if you used %s then the assertion would have hold."),
    IS_EQUAL_INCLUDING_SCALE("is equal (including scale)"),
    IS_NOT_EQUAL_INCLUDING_SCALE("is not equal (including scale)"),
    IS_NUMERICALLY_EQUAL_TO("is numerically equal to"),
    IS_NOT_NUMERICALLY_EQUAL_TO("is not numerically equal to"),
}
