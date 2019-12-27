@file:Suppress("JAVA_MODULE_DOES_NOT_READ_UNNAMED_MODULE" /* TODO remove once https://youtrack.jetbrains.com/issue/KT-35343 is fixed */)

package ch.tutteli.atrium.translations

import ch.tutteli.atrium.assertions.DescriptiveAssertion
import ch.tutteli.atrium.reporting.translating.StringBasedTranslatable
import java.math.BigDecimal

/**
 * Contains the [DescriptiveAssertion.description]s of the assertion functions which are applicable to [BigDecimal].
 */
enum class DescriptionBigDecimalAssertion(override val value: String) : StringBasedTranslatable {
    FAILURE_IS_EQUAL_INCLUDING_SCALE_BUT_NUMERICALLY_EQUAL("notice, if you used %s then the assertion would have hold."),
    IS_EQUAL_INCLUDING_SCALE("is equal (including scale)"),
    IS_NOT_EQUAL_INCLUDING_SCALE("is not equal (including scale)"),
    IS_NUMERICALLY_EQUAL_TO("is numerically equal to"),
    IS_NOT_NUMERICALLY_EQUAL_TO("is not numerically equal to"),
}
