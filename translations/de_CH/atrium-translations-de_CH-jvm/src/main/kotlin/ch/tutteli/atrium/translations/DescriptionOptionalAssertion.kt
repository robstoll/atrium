@file:Suppress("JAVA_MODULE_DOES_NOT_READ_UNNAMED_MODULE" /* TODO remove once https://youtrack.jetbrains.com/issue/KT-35343 is fixed */)

package ch.tutteli.atrium.translations

import ch.tutteli.atrium.assertions.DescriptiveAssertion
import ch.tutteli.atrium.reporting.translating.StringBasedTranslatable
import java.util.Optional

/**
 * Contains the [DescriptiveAssertion.description]s of the assertion functions which are applicable to [Optional].
 */
enum class DescriptionOptionalAssertion(override val value: String) : StringBasedTranslatable {
    EMPTY("leer"),
    GET("get()"),
    IS_NOT_PRESENT("!! ist nicht definiert")
}
