package ch.tutteli.atrium.translations

import ch.tutteli.atrium.reporting.translating.StringBasedTranslatable

enum class DescriptionPathAssertion(override val value: String) : StringBasedTranslatable {
    DOES_NOT_HAVE_PARENT("hat keinen Elternpfad"),
    EXIST("existieren"),
    ENDS_WITH("endet mit"),
    PARENT("Elternpfad"),
    FILE_NAME_WITHOUT_EXTENSION("Dateiname ohne Endung"),
    READABLE("lesbar"),
    WRITABLE("schreibbar"),
    A_FILE("eine Datei"),
    A_DIRECTORY("ein Verzeichnis"),
    A_SYMBOLIC_LINK("eine symbolische Verknüpfung"),
    A_UNKNOWN_FILE_TYPE("ein unbekannter Dateityp"),
    FAILURE_DUE_TO_NO_SUCH_FILE("es exisitiert kein Dateisystemeintrag an diesem Ort"),
    FAILURE_DUE_TO_PERMISSION_FILE_TYPE_HINT("%s exisitiert an diesem Ort, ist aber nicht %s"),
    HINT_OWNER("der Besitzer ist %s"),
    HINT_OWNER_AND_GROUP("der Besitzer ist %s, die Gruppe ist %s"),
    HINT_ACTUAL_POSIX_PERMISSIONS("die POSIX-Berechtigungen lauten %s"),
    HINT_ACTUAL_ACL_PERMISSIONS("die Access Control List lautet:"),
    FAILURE_DUE_TO_PARENT("Problem am Elternpfad"),
    FAILURE_DUE_TO_ACCESS_DENIED("der Zugriff wurde verweigert"),
    FAILURE_DUE_TO_EXCEPTION("beim Zugriff wurde eine %s geworfen: %s"),
    FAILURE_DUE_TO_WRONG_FILE_TYPE("war %s anstatt %s"),
    FAILURE_DUE_TO_LINK_LOOP("Zykel von symbolischen Verknüpfungen gefunden: %s"),
    HINT_CLOSEST_EXISTING_PARENT_DIRECTORY("das nächste, existierende Elternverzeichnis ist %s"),
    HINT_FOLLOWED_SYMBOLIC_LINK("folgte der symbolischen Verknüpfung %s nach %s")
}
