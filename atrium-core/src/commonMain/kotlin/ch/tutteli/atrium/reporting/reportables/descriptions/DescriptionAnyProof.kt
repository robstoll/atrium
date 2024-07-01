package ch.tutteli.atrium.reporting.reportables.descriptions

import ch.tutteli.atrium.reporting.reportables.Description

/**
 * Contains [Description]s that are related to expectation functions which are applicable to [Any].
 */
enum class DescriptionAnyProof(override val string: String) : Description {
    /** @since 1.3.0 (but was since 0.18.0 in atrium-translations DescriptionAnyExpectation) */
    TO_EQUAL("to equal"),

    /** @since 1.3.0 (but was since 0.18.0 in atrium-translations DescriptionAnyExpectation) */
    NOT_TO_EQUAL("not to equal"),

    /** @since 1.3.0 (but was since 0.18.0 in atrium-translations DescriptionAnyExpectation) */
    TO_BE_AN_INSTANCE_OF("to be an instance of type"),

    /** @since 1.3.0 (but was since 0.18.0 in atrium-translations DescriptionAnyExpectation) */
    TO_BE_THE_INSTANCE("to be the instance"),

    /** @since 1.3.0 (but was since 1.1.0 in atrium-translations DescriptionAnyExpectation) */
    NOT_TO_BE_AN_INSTANCE_OF("not to be an instance of"),

    /** @since 1.3.0 (but was since 0.18.0 in atrium-translations DescriptionAnyExpectation) */
    NOT_TO_BE_THE_INSTANCE("not to be the instance"),

    /** @since 1.3.0 (but was since 0.18.0 in atrium-translations DescriptionAnyExpectation) */
    NOT_TO_EQUAL_ONE_OF("not to equal one of"),

}

