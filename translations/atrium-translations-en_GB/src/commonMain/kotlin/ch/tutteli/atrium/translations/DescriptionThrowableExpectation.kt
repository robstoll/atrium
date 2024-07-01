// TODO remove with 2.0.0 at the latest
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.translations

import ch.tutteli.atrium.assertions.DescriptiveAssertion
import ch.tutteli.atrium.reporting.translating.StringBasedTranslatable

/**
 * Contains the [DescriptiveAssertion.description]s of the assertion functions which are applicable to [Throwable].
 */
enum class DescriptionThrowableExpectation(override val value: String) : StringBasedTranslatable {

    /** @since 0.18.0 */
    CAUSE("cause"),

    /** @since 0.18.0 */
    HAS_NO_CAUSE("❗❗ not caused by another exception"),

    /** @since 0.18.0 */
    OCCURRED_EXCEPTION_CAUSE("cause"),

    /** @since 0.18.0 */
    OCCURRED_EXCEPTION_PROPERTIES("Properties of the unexpected %s"),

    /** @since 0.18.0 */
    OCCURRED_EXCEPTION_MESSAGE("message"),

    /** @since 0.18.0 */
    OCCURRED_EXCEPTION_STACKTRACE("stacktrace"),

    /** @since 0.18.0 */
    OCCURRED_EXCEPTION_SUPPRESSED("suppressed"),
}
