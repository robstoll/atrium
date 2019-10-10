package ch.tutteli.atrium.translations

import ch.tutteli.atrium.reporting.translating.StringBasedTranslatable

enum class DescriptionPathAssertion(override val value: String) : StringBasedTranslatable {
    EXIST("existieren"),
    FILE_NAME("Dateiname")
    DOES_NOT_HAVE_PARENT("hat kein übergeordneter Pfad"),
    EXIST("existieren"),
    ENDS_WITH("endet mit"),
    PARENT("übergeordneter Pfad"),
    FILE_NAME_WITHOUT_EXTENSION("Dateiname ohne Endung"),
}
