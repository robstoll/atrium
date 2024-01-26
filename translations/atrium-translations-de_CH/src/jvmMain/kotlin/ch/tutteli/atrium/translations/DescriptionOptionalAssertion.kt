@file:Suppress("DEPRECATION")
package ch.tutteli.atrium.translations

import ch.tutteli.atrium.assertions.DescriptiveAssertion
import ch.tutteli.atrium.reporting.translating.StringBasedTranslatable
import java.util.Optional

/**
 * Contains the [DescriptiveAssertion.description]s of the assertion functions which are applicable to [Optional].
 */
@Deprecated("Switch to atrium-translations-en_GB, this module will be removed with Atrium 1.3")
enum class DescriptionOptionalAssertion(override val value: String) : StringBasedTranslatable {
    @Deprecated("Switch to atrium-translations-en_GB, this module will be removed with Atrium 1.3")
    EMPTY("leer"),
    @Deprecated("Switch to atrium-translations-en_GB, this module will be removed with Atrium 1.3")
    GET("get()"),
    @Deprecated("Switch to atrium-translations-en_GB, this module will be removed with Atrium 1.3")
    IS_NOT_PRESENT("!! ist nicht definiert")

}
