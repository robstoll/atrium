
package ch.tutteli.atrium.reporting.reportables.descriptions

import ch.tutteli.atrium.reporting.reportables.Description
import java.math.BigDecimal

/**
 * Contains [Description]s that are related to expectation functions which are applicable to [BigDecimal].
 */
enum class DescriptionBigDecimalProof(override val string: String) : Description {
    /** @since 1.3.0 (but was in atrium-translations DescriptionBigDecimalExpectation) */
    FAILURE_IS_EQUAL_INCLUDING_SCALE_BUT_NUMERICALLY_EQUAL("notice, if you used %s then the expectation would have been met."),

    /** @since 1.3.0 (but was in atrium-translations DescriptionBigDecimalExpectation) */
    IS_EQUAL_INCLUDING_SCALE("is equal (including scale)"),

    /** @since 1.3.0 (but was in atrium-translations DescriptionBigDecimalExpectation) */
    IS_NOT_EQUAL_INCLUDING_SCALE("is not equal (including scale)"),

    /** @since 1.3.0 (but was in atrium-translations DescriptionBigDecimalExpectation) */
    IS_NUMERICALLY_EQUAL_TO("is numerically equal to"),

    /** @since 1.3.0 (but was in atrium-translations DescriptionBigDecimalExpectation) */
    IS_NOT_NUMERICALLY_EQUAL_TO("is not numerically equal to"),
}
