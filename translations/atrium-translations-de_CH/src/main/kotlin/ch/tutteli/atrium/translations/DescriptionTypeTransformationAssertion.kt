package ch.tutteli.atrium.translations

import ch.tutteli.atrium.assertions.DescriptiveAssertion
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.reporting.translating.StringBasedTranslatable

/**
 * Contains the [DescriptiveAssertion.description]s of the assertion functions which postulate that a
 * [AssertionPlant.subject] of type `T` can be transformed (usually down-casting or unboxing) to `TSub`.
 */
enum class DescriptionTypeTransformationAssertion(override val value: String) : StringBasedTranslatable {
    IS_A("ist der Typ oder ein Subtyp von"),
    WARNING_DOWN_CAST_FAILED("$COULD_NOT_EVALUATE_DEFINED_ASSERTIONS -- down-cast zu %s schlug fehl.\n$VISIT_COULD_NOT_EVALUATE_ASSERTIONS"),
}
