package ch.tutteli.atrium.translations

import ch.tutteli.atrium.reporting.translating.StringBasedTranslatable

enum class DescriptionPathAssertion(override val value: String) : StringBasedTranslatable {
    DOES_NOT_HAVE_PARENT("hat kein übergeordneter Pfad"),
    ENDS_NOT_WITH("endet nicht mit"),
    ENDS_WITH("endet mit"),
    EXIST("existieren"),
    EXTENSION("Dateiendung"),
    FILE_NAME("Dateiname"),
    FILE_NAME_WITHOUT_EXTENSION("Dateiname ohne Endung"),
    PARENT("übergeordneter Pfad"),
    STARTS_WITH("beginnt mit"),
    STARTS_NOT_WITH("beginnt nicht mit"),
}
