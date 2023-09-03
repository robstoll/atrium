package ch.tutteli.atrium.translations

import ch.tutteli.atrium.assertions.DescriptiveAssertion
import ch.tutteli.atrium.reporting.translating.StringBasedTranslatable

/**
 * Contains the [DescriptiveAssertion.description]s of the expectation functions which are applicable to [Any].
 */
enum class DescriptionAnyExpectation(override val value: String) : StringBasedTranslatable {
    /** @since 0.18.0 */
    TO_EQUAL("to equal"),

    /** @since 0.18.0 */
    NOT_TO_EQUAL("not to equal"),

    /** @since todo v num */
    NOT_TO_EQUAL_NULL_BUT_BE_OF_TYPE("not to equal: null but to be an instance of"),

    /** @since 0.18.0 */
    TO_BE_AN_INSTANCE_OF("to be an instance of type"),

    /** @since 0.18.0 */
    TO_BE_THE_INSTANCE("to be the instance"),

    /** @since 0.18.0 */
    NOT_TO_BE_THE_INSTANCE("not to be the instance"),

    /** @since 0.18.0 */
    NOT_TO_EQUAL_ONE_IN("not to equal one in"),

    /** @since 0.18.0 */
    BECAUSE("because"),
}
