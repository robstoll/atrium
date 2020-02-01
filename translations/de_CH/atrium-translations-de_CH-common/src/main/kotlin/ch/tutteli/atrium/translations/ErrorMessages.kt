package ch.tutteli.atrium.translations

import ch.tutteli.atrium.reporting.BUG_REPORT_URL
import ch.tutteli.atrium.reporting.translating.StringBasedTranslatable

/**
 * Contains translations which are used in error like messages.
 */
enum class ErrorMessages(override val value: String) : StringBasedTranslatable {
    AT_LEAST_ONE_ASSERTION_DEFINED("zumindest eine Behauptung definiert"),
    FORGOT_DO_DEFINE_ASSERTION("Sie vergassen eine Behauptung innerhlab der assertionCreator-Lambda zu definieren"),
    HINT_AT_LEAST_ONE_ASSERTION_DEFINED("Manchmal kann man eine Alternative zu `{ }` verwenden. Zum Beispiel, anstelle von `wirft<..> { }` sollten Sie `wirft<..>()` verwenden."),
    DEDSCRIPTION_BASED_ON_SUBJECT("Kann die Beschreibung NICHT anzeigen, da sie auf dem Subjekt der Behauptung beruht, welches nicht definiert ist"),
    REPRESENTATION_BASED_ON_SUBJECT_NOT_DEFINED("kann die Representation NICHT evaluieren, da sie auf dem Subjekt der Behauptung beruht, welches nicht definiert ist."),
    @Deprecated("Will be removed with 1.0.0")
    SUBJECT_ACCESSED_TOO_EARLY("Konnte die zusätzlichen Aussagen (Assertions) nicht auswerten; das Subjekt (subject) wurde zu früh verwendet. Bitte erfassen Sie einen Bug-Report unter $BUG_REPORT_URL inklusive Stacktrace wenn möglich -- vielen Dank."),
}
