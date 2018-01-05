package ch.tutteli.atrium.assertions

import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.reporting.translating.StringBasedTranslatable

/**
 * Contains the [DescriptiveAssertion.description]s of the assertion functions which postulate that a
 * [AssertionPlant.subject] of type `T` can be narrowed to `TSub` where `TSub <: T`.
 */
enum class DescriptionNarrowingAssertion(override val value: String) : StringBasedTranslatable {
    IS_A("is type or sub-type of"),
    WARNING_DOWN_CAST_FAILED("Could not evaluate the defined assertion(s) -- the down-cast to %s failed.\n$VISIT_COULD_NOT_EVALUATE_ASSERTIONS"),
}
