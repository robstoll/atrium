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
