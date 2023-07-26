package ch.tutteli.atrium.translations

import ch.tutteli.atrium.reporting.translating.StringBasedTranslatable

/**
 * Contains translations which are used in error like messages.
 */
@Deprecated("We drop translation-support with Atrium v1.2.0")
enum class ErrorMessages(override val value: String) : StringBasedTranslatable {

    @Deprecated("We drop translation-support with Atrium v1.2.0")
    DESCRIPTION_BASED_ON_SUBJECT("Kann die Beschreibung NICHT anzeigen, da sie auf dem Subjekt der Behauptung beruht, welches nicht definiert ist"),

    @Deprecated("We drop translation-support with Atrium v1.2.0")
    REPRESENTATION_BASED_ON_SUBJECT_NOT_DEFINED("kann die Representation NICHT evaluieren, da sie auf dem Subjekt der Behauptung beruht, welches nicht definiert ist."),
}
