@file:Suppress(
    // TODO remove once https://youtrack.jetbrains.com/issue/KT-35343 is fixed
    "JAVA_MODULE_DOES_NOT_READ_UNNAMED_MODULE"
)

package ch.tutteli.atrium.translations

import ch.tutteli.atrium.reporting.translating.StringBasedTranslatable

enum class DescriptionOptionalAssertion(override val value: String) : StringBasedTranslatable {
    EMPTY("empty"),
    GET("get()"),
    IS_NOT_PRESENT("!! is not present")
}
