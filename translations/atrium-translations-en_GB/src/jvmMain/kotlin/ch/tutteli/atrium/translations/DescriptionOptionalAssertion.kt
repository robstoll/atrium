package ch.tutteli.atrium.translations

import ch.tutteli.atrium.reporting.translating.StringBasedTranslatable

enum class DescriptionOptionalAssertion(override val value: String) : StringBasedTranslatable {
    EMPTY("empty"),
    GET("get()"),
    IS_NOT_PRESENT("!! is not present")
}
