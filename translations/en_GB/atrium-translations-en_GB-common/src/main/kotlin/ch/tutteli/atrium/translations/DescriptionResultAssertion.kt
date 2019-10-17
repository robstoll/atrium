package ch.tutteli.atrium.translations

import ch.tutteli.atrium.assertions.DescriptiveAssertion
import ch.tutteli.atrium.reporting.translating.StringBasedTranslatable

/**
 * Contains the [DescriptiveAssertion.description]s of the assertion functions which are applicable to [Map].
 */
enum class DescriptionResultAssertion(override val value: String) : StringBasedTranslatable {
    COULD_NOT_INTERPRET_INPUT("$COULD_NOT_EVALUATE_DEFINED_ASSERTIONS unable to interpret/understand input \n$VISIT_COULD_NOT_EVALUATE_ASSERTIONS")
}
