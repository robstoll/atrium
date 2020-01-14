package ch.tutteli.atrium.translations

import ch.tutteli.atrium.assertions.DescriptiveAssertion
import ch.tutteli.atrium.creating.SubjectProvider
import ch.tutteli.atrium.reporting.translating.StringBasedTranslatable

/**
 * Contains the [DescriptiveAssertion.description]s of the assertion functions which postulate that a
 * [AssertionPlant.subject][SubjectProvider.subject] of type `T` can be transformed (usually down-casting or unboxing) to `TSub`.
 */
@Suppress("DEPRECATION")
@Deprecated("Use DescriptionAnyAssertion instead; will be removed with 0.10.0")
enum class DescriptionTypeTransformationAssertion(override val value: String) : StringBasedTranslatable {
    @Deprecated(
        "Use DescriptionAnyAssertion",
        ReplaceWith(
            "DescriptionAnyAssertion.IS_INSTANCE_OF_TYPE",
            "ch.tutteli.atrium.translations.DescriptionAnyAssertion"
        )
    )
    IS_A("ist vom Typ oder ein Subtyp von"),
    WARNING_DOWN_CAST_FAILED("$COULD_NOT_EVALUATE_DEFINED_ASSERTIONS -- down-cast zu %s schlug fehl.\n$VISIT_COULD_NOT_EVALUATE_ASSERTIONS"),
}
