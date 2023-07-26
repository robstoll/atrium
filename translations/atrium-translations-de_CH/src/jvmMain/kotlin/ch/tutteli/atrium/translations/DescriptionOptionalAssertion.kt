package ch.tutteli.atrium.translations

import ch.tutteli.atrium.assertions.DescriptiveAssertion
import ch.tutteli.atrium.reporting.translating.StringBasedTranslatable
import java.util.Optional

/**
 * Contains the [DescriptiveAssertion.description]s of the assertion functions which are applicable to [Optional].
 */
@Deprecated("We drop translation-support with Atrium v1.2.0")
enum class DescriptionOptionalAssertion(override val value: String) : StringBasedTranslatable {

    @Deprecated("We drop translation-support with Atrium v1.2.0")
    EMPTY("leer"),

    @Deprecated("We drop translation-support with Atrium v1.2.0")
    GET("get()"),

    @Deprecated("We drop translation-support with Atrium v1.2.0")
    IS_NOT_PRESENT("!! ist nicht definiert")

}
