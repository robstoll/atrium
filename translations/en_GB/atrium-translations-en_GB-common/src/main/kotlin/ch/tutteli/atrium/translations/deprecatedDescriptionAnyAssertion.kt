//TODO remove file with latest with 1.0.0
@file:Suppress("DEPRECATION")
package ch.tutteli.atrium.translations

import ch.tutteli.atrium.assertions.DescriptiveAssertion
import ch.tutteli.atrium.reporting.translating.StringBasedTranslatable

/**
 * Contains the [DescriptiveAssertion.description]s of the assertion functions which are applicable to [Any].
 */
@Deprecated("Will be removed with latest with 1.0.0")
enum class DescriptionAnyAssertion(override val value: String) : StringBasedTranslatable {
    @Deprecated("Use DescriptionAnyExpectation instead", ReplaceWith("DescriptionAnyExpectation.TO_EQUAL"))
    TO_BE("equals"),

    @Deprecated("Use DescriptionAnyExpectation instead", ReplaceWith("DescriptionAnyExpectation.NOT_TO_EQUAL"))
    NOT_TO_BE("does not equal"),

    @Deprecated("Use DescriptionAnyExpectation instead", ReplaceWith("DescriptionAnyExpectation.TO_BE_AN_INSTANCE_OF"))
    IS_A("is instance of type"),

    @Deprecated("Use DescriptionAnyExpectation instead", ReplaceWith("DescriptionAnyExpectation.TO_BE_THE_INSTANCE"))
    IS_SAME("is the same instance as"),

    @Deprecated("Use DescriptionAnyExpectation instead", ReplaceWith("DescriptionAnyExpectation.NOT_TO_BE_THE_INSTANCE"))
    IS_NOT_SAME("is not the same instance as"),

    @Deprecated("Use DescriptionAnyExpectation instead", ReplaceWith("DescriptionAnyExpectation.NOT_TO_EQUAL_ONE_IN"))
    IS_NONE_OF("is none of"),

    @Deprecated("Use DescriptionAnyExpectation instead", ReplaceWith("DescriptionAnyExpectation.BECAUSE"))
    BECAUSE("because")
}
