package ch.tutteli.atrium.reporting.reportables

import ch.tutteli.atrium.reporting.Text

/**
 * Contains [TextElement]s which are used in error like messages.
 */
enum class ErrorMessages(override val string: String) : TextElement {

    /** @since 1.3.0 (but was in ch.tutteli.atrium.creating.ErrorMessages since 0.18.0) */
    AT_LEAST_ONE_EXPECTATION_DEFINED("at least one expectation defined"),

    /** @since 1.3.0 (but was in ch.tutteli.atrium.creating.ErrorMessages since 0.18.0) */
    FORGOT_DO_DEFINE_EXPECTATION("You forgot to create expectations in the expectationCreator-lambda"),

    /** @since 1.3.0 (but was in ch.tutteli.atrium.translations.ErrorMessages before) */
    DESCRIPTION_BASED_ON_SUBJECT("CANNOT show description as it is based on subject which is not defined"),

    /** @since 1.3.0 (but was in ch.tutteli.atrium.translations.ErrorMessages before) */
    REPRESENTATION_BASED_ON_SUBJECT_NOT_DEFINED("CANNOT evaluate representation as it is based on subject which is not defined."),

    /** @since 1.3.0 */
    FEATURE_EXTRACTION_NOT_POSSIBLE("❗❗ subject extraction not possible, previous expectation failed, cannot show sub-expectations")
}

fun useAlternativeUsageHint(alternativeAsCodeExample: String): List<InlineElement> =
    listOf(Text("Use `$alternativeAsCodeExample` $defaultSuffixForUsageHintWithoutLambda"))

/** @since 1.3.0 */
const val defaultSuffixForUsageHintWithoutLambda =
    "which does not expect an expectationCreator-lambda if you don't want to create sub-expectations."

/** @since 1.3.0 */
val defaultHintsAtLeastOneExpectationDefined = listOf(
    Text("Sometimes you can use an alternative to `{ }`"),
    Text("For instance, instead of `toThrow<..> { }` you should use `toThrow<..>()`")
)
