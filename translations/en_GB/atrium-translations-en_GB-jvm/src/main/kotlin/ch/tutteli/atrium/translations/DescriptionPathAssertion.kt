package ch.tutteli.atrium.translations

import ch.tutteli.atrium.reporting.translating.StringBasedTranslatable

enum class DescriptionPathAssertion(override val value: String) : StringBasedTranslatable {
    DOES_NOT_HAVE_PARENT("does not have a parent"),
    ENDS_NOT_WITH("does not end with"),
    ENDS_WITH("ends with"),
    EXIST("exist"),
    EXTENSION("extension"),
    FILE_NAME("file name"),
    FILE_NAME_WITHOUT_EXTENSION("file name without extension"),
    PARENT("parent"),
    STARTS_WITH("starts with"),
    STARTS_NOT_WITH("does not start with")
}
