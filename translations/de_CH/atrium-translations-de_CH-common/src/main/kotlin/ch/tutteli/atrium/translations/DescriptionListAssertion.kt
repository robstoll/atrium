package ch.tutteli.atrium.translations

import ch.tutteli.atrium.assertions.DescriptiveAssertion
import ch.tutteli.atrium.reporting.translating.StringBasedTranslatable

/**
 * Contains the [DescriptiveAssertion.description]s of the assertion functions which are applicable to [Map].
 */
enum class DescriptionListAssertion(override val value: String) : StringBasedTranslatable {
    @Deprecated("Will be removed with 1.0.0 at the latest (maybe earlier)")
    CANNOT_EVALUATE_INDEX_OUT_OF_BOUNDS("$COULD_NOT_EVALUATE_DEFINED_ASSERTIONS -- Index ausserhalb der Grenzen (index out of bounds).\n$VISIT_COULD_NOT_EVALUATE_ASSERTIONS"),
    INDEX_OUT_OF_BOUNDS("❗❗ Index ausserhalb der Grenzen (index out of bounds)")
}
