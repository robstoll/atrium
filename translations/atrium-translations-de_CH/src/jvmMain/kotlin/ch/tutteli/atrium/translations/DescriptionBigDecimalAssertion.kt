package ch.tutteli.atrium.translations

import ch.tutteli.atrium.assertions.DescriptiveAssertion
import ch.tutteli.atrium.reporting.translating.StringBasedTranslatable
import java.math.BigDecimal

/**
 * Contains the [DescriptiveAssertion.description]s of the assertion functions which are applicable to [BigDecimal].
 */
@Deprecated("We drop translation-support with Atrium v1.2.0")
enum class DescriptionBigDecimalAssertion(override val value: String) : StringBasedTranslatable {
    @Deprecated("We drop translation-support with Atrium v1.2.0")
    FAILURE_IS_EQUAL_INCLUDING_SCALE_BUT_NUMERICALLY_EQUAL("beachte, wäre %s verwendet worden, würde die Behauptung stimmen."),

    @Deprecated("We drop translation-support with Atrium v1.2.0")
    IS_EQUAL_INCLUDING_SCALE("ist (inklusive Scale)"),

    @Deprecated("We drop translation-support with Atrium v1.2.0")
    IS_NOT_EQUAL_INCLUDING_SCALE("ist nicht (inklusive Scale)"),

    @Deprecated("We drop translation-support with Atrium v1.2.0")
    IS_NUMERICALLY_EQUAL_TO("ist numerisch gleich wie"),

    @Deprecated("We drop translation-support with Atrium v1.2.0")
    IS_NOT_NUMERICALLY_EQUAL_TO("is nicht numerisch gleich wie"),
}
