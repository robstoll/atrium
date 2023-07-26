package ch.tutteli.atrium.translations

import ch.tutteli.atrium.assertions.DescriptiveAssertion
import ch.tutteli.atrium.reporting.translating.StringBasedTranslatable

/**
 * Contains the [DescriptiveAssertion.description]s of the assertion functions which are applicable to [Throwable].
 */
@Deprecated("We drop translation-support with Atrium v1.2.0")
enum class DescriptionThrowableExpectation(override val value: String) : StringBasedTranslatable {

    @Deprecated("We drop translation-support with Atrium v1.2.0")
    /** @since 0.18.0 */
    CAUSE("cause"),

    @Deprecated("We drop translation-support with Atrium v1.2.0")
    /** @since 0.18.0 */
    HAS_NO_CAUSE("❗❗ nicht durch eine andere Exception verursacht"),

    @Deprecated("We drop translation-support with Atrium v1.2.0")
    /** @since 0.18.0 */
    OCCURRED_EXCEPTION_CAUSE("cause"),

    @Deprecated("We drop translation-support with Atrium v1.2.0")
    /** @since 0.18.0 */
    OCCURRED_EXCEPTION_PROPERTIES("Eigenschaften der unerwarteten %s"),

    @Deprecated("We drop translation-support with Atrium v1.2.0")
    /** @since 0.18.0 */
    OCCURRED_EXCEPTION_MESSAGE("message"),

    @Deprecated("We drop translation-support with Atrium v1.2.0")
    /** @since 0.18.0 */
    OCCURRED_EXCEPTION_STACKTRACE("stacktrace"),

    @Deprecated("We drop translation-support with Atrium v1.2.0")
    /** @since 0.18.0 */
    OCCURRED_EXCEPTION_SUPPRESSED("suppressed"),
}
