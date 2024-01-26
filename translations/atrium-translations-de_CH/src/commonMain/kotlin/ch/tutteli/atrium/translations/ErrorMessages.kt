@file:Suppress("DEPRECATION")
package ch.tutteli.atrium.translations

import ch.tutteli.atrium.reporting.translating.StringBasedTranslatable

/**
 * Contains translations which are used in error like messages.
 */
@Deprecated("Switch to atrium-translations-en_GB, this module will be removed with Atrium 1.3")
enum class ErrorMessages(override val value: String) : StringBasedTranslatable {

    @Deprecated("Switch to atrium-translations-en_GB, this module will be removed with Atrium 1.3")
    DESCRIPTION_BASED_ON_SUBJECT("Kann die Beschreibung NICHT anzeigen, da sie auf dem Subjekt der Behauptung beruht, welches nicht definiert ist"),

    @Deprecated("Switch to atrium-translations-en_GB, this module will be removed with Atrium 1.3")
    REPRESENTATION_BASED_ON_SUBJECT_NOT_DEFINED("kann die Representation NICHT evaluieren, da sie auf dem Subjekt der Behauptung beruht, welches nicht definiert ist."),
}
