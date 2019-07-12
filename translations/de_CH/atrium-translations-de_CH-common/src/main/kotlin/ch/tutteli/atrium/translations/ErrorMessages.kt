package ch.tutteli.atrium.translations

import ch.tutteli.atrium.reporting.translating.StringBasedTranslatable

/**
 * Contains translations which are used in error like messages.
 */
enum class ErrorMessages(override val value: String) : StringBasedTranslatable {
    REPRESENTATION_BASED_ON_SUBJECT_NOT_DEFINED("kann Representation NICHT evaluieren, da sie auf dem Subjekt der Behauptung beruht, welches nicht definiert ist."),
}
