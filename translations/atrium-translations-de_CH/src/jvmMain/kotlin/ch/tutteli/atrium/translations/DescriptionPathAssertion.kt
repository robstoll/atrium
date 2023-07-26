package ch.tutteli.atrium.translations

import ch.tutteli.atrium.assertions.DescriptiveAssertion
import ch.tutteli.atrium.reporting.translating.StringBasedTranslatable
import java.nio.file.Path

/**
 * Contains the [DescriptiveAssertion.description]s of the assertion functions which are applicable to [Path].
 */
@Deprecated("We drop translation-support with Atrium v1.2.0")
enum class DescriptionPathAssertion(override val value: String) : StringBasedTranslatable {
    @Deprecated("We drop translation-support with Atrium v1.2.0")
    ABSOLUTE_PATH("ein absoluter Pfad"),

    @Deprecated("We drop translation-support with Atrium v1.2.0")
    DOES_NOT_HAVE_PARENT("!! hat keinen Elternpfad"),

    @Deprecated("We drop translation-support with Atrium v1.2.0")
    ENDS_NOT_WITH("endet nicht mit"),

    @Deprecated("We drop translation-support with Atrium v1.2.0")
    ENDS_WITH("endet mit"),

    @Deprecated("We drop translation-support with Atrium v1.2.0")
    EXIST("existieren"),

    @Deprecated("We drop translation-support with Atrium v1.2.0")
    PARENT("Elternpfad"),

    @Deprecated("We drop translation-support with Atrium v1.2.0")
    EXTENSION("Dateiendung"),

    @Deprecated("We drop translation-support with Atrium v1.2.0")
    FILE_NAME("Dateiname"),

    @Deprecated("We drop translation-support with Atrium v1.2.0")
    FILE_NAME_WITHOUT_EXTENSION("Dateiname ohne Endung"),

    @Deprecated("We drop translation-support with Atrium v1.2.0")
    STARTS_WITH("beginnt mit"),

    @Deprecated("We drop translation-support with Atrium v1.2.0")
    STARTS_NOT_WITH("beginnt nicht mit"),

    @Deprecated("We drop translation-support with Atrium v1.2.0")
    READABLE("lesbar"),

    @Deprecated("We drop translation-support with Atrium v1.2.0")
    WRITABLE("schreibbar"),

    @Deprecated("We drop translation-support with Atrium v1.2.0")
    EXECUTABLE("ausführbar"),

    @Deprecated("We drop translation-support with Atrium v1.2.0")
    A_FILE("eine Datei"),

    @Deprecated("We drop translation-support with Atrium v1.2.0")
    A_DIRECTORY("ein Verzeichnis"),

    @Deprecated("We drop translation-support with Atrium v1.2.0")
    AN_EMPTY_DIRECTORY("ein leeres Verzeichnis"),

    @Deprecated("We drop translation-support with Atrium v1.2.0")
    DIRECTORY_CONTAINS("Verzeichnis enthält"),

    @Deprecated("We drop translation-support with Atrium v1.2.0")
    A_SYMBOLIC_LINK("eine symbolische Verknüpfung"),

    @Deprecated("We drop translation-support with Atrium v1.2.0")
    A_UNKNOWN_FILE_TYPE("ein unbekannter Dateityp"),

    @Deprecated("We drop translation-support with Atrium v1.2.0")
    FAILURE_DUE_TO_NO_SUCH_FILE("es exisitiert kein Dateisystemeintrag an diesem Ort"),

    @Deprecated("We drop translation-support with Atrium v1.2.0")
    FAILURE_DUE_TO_PERMISSION_FILE_TYPE_HINT("%s exisitiert an diesem Ort, ist aber nicht %s"),

    @Deprecated("We drop translation-support with Atrium v1.2.0")
    HINT_OWNER("der Besitzer ist %s"),

    @Deprecated("We drop translation-support with Atrium v1.2.0")
    HINT_OWNER_AND_GROUP("der Besitzer ist %s, die Gruppe ist %s"),

    @Deprecated("We drop translation-support with Atrium v1.2.0")
    HINT_ACTUAL_POSIX_PERMISSIONS("die POSIX-Berechtigungen lauten %s"),

    @Deprecated("We drop translation-support with Atrium v1.2.0")
    HINT_ACTUAL_ACL_PERMISSIONS("die Access Control List lautet:"),

    @Deprecated("We drop translation-support with Atrium v1.2.0")
    FAILURE_DUE_TO_PARENT("Problem am Elternpfad"),

    @Deprecated("We drop translation-support with Atrium v1.2.0")
    FAILURE_DUE_TO_ACCESS_DENIED("der Zugriff wurde verweigert"),

    @Deprecated("We drop translation-support with Atrium v1.2.0")
    FAILURE_DUE_TO_ACCESS_EXCEPTION("beim Zugriff wurde eine %s geworfen:"),

    @Deprecated("We drop translation-support with Atrium v1.2.0")
    FAILURE_DUE_TO_WRONG_FILE_TYPE("war %s anstatt %s"),

    @Deprecated("We drop translation-support with Atrium v1.2.0")
    FAILURE_DUE_TO_LINK_LOOP("Zykel von symbolischen Verknüpfungen gefunden: %s"),

    @Deprecated("We drop translation-support with Atrium v1.2.0")
    HINT_CLOSEST_EXISTING_PARENT_DIRECTORY("das nächste, existierende Elternverzeichnis ist %s"),

    @Deprecated("We drop translation-support with Atrium v1.2.0")
    HINT_FOLLOWED_SYMBOLIC_LINK("folgte der symbolischen Verknüpfung %s nach %s"),

    @Deprecated("We drop translation-support with Atrium v1.2.0")
    HAS_SAME_TEXTUAL_CONTENT("hat denselben textlichen Inhalt mit Kodierung %s, %s"),

    @Deprecated("We drop translation-support with Atrium v1.2.0")
    HAS_SAME_BINARY_CONTENT("hat denselben binären Inhalt"),

    @Deprecated("We drop translation-support with Atrium v1.2.0")
    RELATIVE_PATH("ein relativer Pfad")
}
