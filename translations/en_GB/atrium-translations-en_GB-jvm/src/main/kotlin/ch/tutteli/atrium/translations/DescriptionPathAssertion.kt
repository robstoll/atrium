package ch.tutteli.atrium.translations

import ch.tutteli.atrium.reporting.translating.StringBasedTranslatable

enum class DescriptionPathAssertion(override val value: String) : StringBasedTranslatable {
    EXIST("exist"),
    FILE_NAME("file name")
    DOES_NOT_HAVE_PARENT("does not have a parent"),
    EXIST("exist"),
    ENDS_WITH("ends with"),
    PARENT("parent"),
    FILE_NAME_WITHOUT_EXTENSION("file name without extension")
}
