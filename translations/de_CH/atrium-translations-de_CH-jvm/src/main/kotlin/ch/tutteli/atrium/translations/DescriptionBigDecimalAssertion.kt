@file:Suppress("JAVA_MODULE_DOES_NOT_READ_UNNAMED_MODULE" /* remove once https://youtrack.jetbrains.com/issue/KT-35343 is fixed */)

package ch.tutteli.atrium.translations

import ch.tutteli.atrium.assertions.DescriptiveAssertion
import ch.tutteli.atrium.reporting.translating.StringBasedTranslatable
import java.math.BigDecimal

/**
 * Contains the [DescriptiveAssertion.description]s of the assertion functions which are applicable to [BigDecimal].
 */
enum class DescriptionBigDecimalAssertion(override val value: String) : StringBasedTranslatable {
    FAILURE_IS_EQUAL_INCLUDING_SCALE_BUT_NUMERICALLY_EQUAL("beachte, wäre %s verwendet worden, würde die Behauptung stimmen."),
    IS_EQUAL_INCLUDING_SCALE("ist (inklusive Scale)"),
    IS_NOT_EQUAL_INCLUDING_SCALE("ist nicht (inklusive Scale)"),
    IS_NUMERICALLY_EQUAL_TO("ist numerisch gleich wie"),
    IS_NOT_NUMERICALLY_EQUAL_TO("is nicht numerisch gleich wie"),
}
