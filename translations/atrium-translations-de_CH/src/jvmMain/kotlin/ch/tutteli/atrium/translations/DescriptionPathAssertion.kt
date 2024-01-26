@file:Suppress("DEPRECATION")
package ch.tutteli.atrium.translations

import ch.tutteli.atrium.assertions.DescriptiveAssertion
import ch.tutteli.atrium.reporting.translating.StringBasedTranslatable
import java.nio.file.Path

/**
 * Contains the [DescriptiveAssertion.description]s of the assertion functions which are applicable to [Path].
 */
@Deprecated("Switch to atrium-translations-en_GB, this module will be removed with Atrium 1.3")
enum class DescriptionPathAssertion(override val value: String) : StringBasedTranslatable {
    @Deprecated("Switch to atrium-translations-en_GB, this module will be removed with Atrium 1.3")
    ABSOLUTE_PATH("ein absoluter Pfad"),
    @Deprecated("Switch to atrium-translations-en_GB, this module will be removed with Atrium 1.3")
    DOES_NOT_HAVE_PARENT("!! hat keinen Elternpfad"),
    @Deprecated("Switch to atrium-translations-en_GB, this module will be removed with Atrium 1.3")
    ENDS_NOT_WITH("endet nicht mit"),
    @Deprecated("Switch to atrium-translations-en_GB, this module will be removed with Atrium 1.3")
    ENDS_WITH("endet mit"),
    @Deprecated("Switch to atrium-translations-en_GB, this module will be removed with Atrium 1.3")
    EXIST("existieren"),
    @Deprecated("Switch to atrium-translations-en_GB, this module will be removed with Atrium 1.3")
    PARENT("Elternpfad"),
    @Deprecated("Switch to atrium-translations-en_GB, this module will be removed with Atrium 1.3")
    EXTENSION("Dateiendung"),
    @Deprecated("Switch to atrium-translations-en_GB, this module will be removed with Atrium 1.3")
    FILE_NAME("Dateiname"),
    @Deprecated("Switch to atrium-translations-en_GB, this module will be removed with Atrium 1.3")
    FILE_NAME_WITHOUT_EXTENSION("Dateiname ohne Endung"),
    @Deprecated("Switch to atrium-translations-en_GB, this module will be removed with Atrium 1.3")
    STARTS_WITH("beginnt mit"),
    @Deprecated("Switch to atrium-translations-en_GB, this module will be removed with Atrium 1.3")
    STARTS_NOT_WITH("beginnt nicht mit"),
    @Deprecated("Switch to atrium-translations-en_GB, this module will be removed with Atrium 1.3")
    READABLE("lesbar"),
    @Deprecated("Switch to atrium-translations-en_GB, this module will be removed with Atrium 1.3")
    WRITABLE("schreibbar"),
    @Deprecated("Switch to atrium-translations-en_GB, this module will be removed with Atrium 1.3")
    EXECUTABLE("ausführbar"),
    @Deprecated("Switch to atrium-translations-en_GB, this module will be removed with Atrium 1.3")
    A_FILE("eine Datei"),
    @Deprecated("Switch to atrium-translations-en_GB, this module will be removed with Atrium 1.3")
    A_DIRECTORY("ein Verzeichnis"),
    @Deprecated("Switch to atrium-translations-en_GB, this module will be removed with Atrium 1.3")
    AN_EMPTY_DIRECTORY("ein leeres Verzeichnis"),
    @Deprecated("Switch to atrium-translations-en_GB, this module will be removed with Atrium 1.3")
    DIRECTORY_CONTAINS("Verzeichnis enthält"),
    @Deprecated("Switch to atrium-translations-en_GB, this module will be removed with Atrium 1.3")
    A_SYMBOLIC_LINK("eine symbolische Verknüpfung"),
    @Deprecated("Switch to atrium-translations-en_GB, this module will be removed with Atrium 1.3")
    A_UNKNOWN_FILE_TYPE("ein unbekannter Dateityp"),
    @Deprecated("Switch to atrium-translations-en_GB, this module will be removed with Atrium 1.3")
    FAILURE_DUE_TO_NO_SUCH_FILE("es exisitiert kein Dateisystemeintrag an diesem Ort"),
    @Deprecated("Switch to atrium-translations-en_GB, this module will be removed with Atrium 1.3")
    FAILURE_DUE_TO_PERMISSION_FILE_TYPE_HINT("%s exisitiert an diesem Ort, ist aber nicht %s"),
    @Deprecated("Switch to atrium-translations-en_GB, this module will be removed with Atrium 1.3")
    HINT_OWNER("der Besitzer ist %s"),
    @Deprecated("Switch to atrium-translations-en_GB, this module will be removed with Atrium 1.3")
    HINT_OWNER_AND_GROUP("der Besitzer ist %s, die Gruppe ist %s"),
    @Deprecated("Switch to atrium-translations-en_GB, this module will be removed with Atrium 1.3")
    HINT_ACTUAL_POSIX_PERMISSIONS("die POSIX-Berechtigungen lauten %s"),
    @Deprecated("Switch to atrium-translations-en_GB, this module will be removed with Atrium 1.3")
    HINT_ACTUAL_ACL_PERMISSIONS("die Access Control List lautet:"),
    @Deprecated("Switch to atrium-translations-en_GB, this module will be removed with Atrium 1.3")
    FAILURE_DUE_TO_PARENT("Problem am Elternpfad"),
    @Deprecated("Switch to atrium-translations-en_GB, this module will be removed with Atrium 1.3")
    FAILURE_DUE_TO_ACCESS_DENIED("der Zugriff wurde verweigert"),
    @Deprecated("Switch to atrium-translations-en_GB, this module will be removed with Atrium 1.3")
    FAILURE_DUE_TO_ACCESS_EXCEPTION("beim Zugriff wurde eine %s geworfen:"),
    @Deprecated("Switch to atrium-translations-en_GB, this module will be removed with Atrium 1.3")
    FAILURE_DUE_TO_WRONG_FILE_TYPE("war %s anstatt %s"),
    @Deprecated("Switch to atrium-translations-en_GB, this module will be removed with Atrium 1.3")
    FAILURE_DUE_TO_LINK_LOOP("Zykel von symbolischen Verknüpfungen gefunden: %s"),
    @Deprecated("Switch to atrium-translations-en_GB, this module will be removed with Atrium 1.3")
    HINT_CLOSEST_EXISTING_PARENT_DIRECTORY("das nächste, existierende Elternverzeichnis ist %s"),
    @Deprecated("Switch to atrium-translations-en_GB, this module will be removed with Atrium 1.3")
    HINT_FOLLOWED_SYMBOLIC_LINK("folgte der symbolischen Verknüpfung %s nach %s"),
    @Deprecated("Switch to atrium-translations-en_GB, this module will be removed with Atrium 1.3")
    HAS_SAME_TEXTUAL_CONTENT("hat denselben textlichen Inhalt mit Kodierung %s, %s"),
    @Deprecated("Switch to atrium-translations-en_GB, this module will be removed with Atrium 1.3")
    HAS_SAME_BINARY_CONTENT("hat denselben binären Inhalt"),
    @Deprecated("Switch to atrium-translations-en_GB, this module will be removed with Atrium 1.3")
    RELATIVE_PATH("ein relativer Pfad")
}
