@file:Suppress("DEPRECATION")
package ch.tutteli.atrium.translations

import ch.tutteli.atrium.assertions.DescriptiveAssertion
import ch.tutteli.atrium.reporting.translating.StringBasedTranslatable

/**
 * Contains the [DescriptiveAssertion.description]s which are so basic that one does not want to use
 * a different wording in two different expectation functions.
 */
@Deprecated("Switch to atrium-translations-en_GB, this module will be removed with Atrium 1.3")
enum class
DescriptionBasic(override val value: String) : StringBasedTranslatable {
    @Deprecated("Switch to atrium-translations-en_GB, this module will be removed with Atrium 1.3")
    TO("zu"),

    @Deprecated("Switch to atrium-translations-en_GB, this module will be removed with Atrium 1.3")
    NOT_TO("nicht zu"),

    @Deprecated("Switch to atrium-translations-en_GB, this module will be removed with Atrium 1.3")
    TO_BE("ist"),

    @Deprecated("Switch to atrium-translations-en_GB, this module will be removed with Atrium 1.3")
    NOT_TO_BE("ist nicht"),

    /** @since 0.18.0 */
    @Deprecated("Switch to atrium-translations-en_GB, this module will be removed with Atrium 1.3")
    TO_HAVE("hat"),

    /** @since 0.18.0 */
    @Deprecated("Switch to atrium-translations-en_GB, this module will be removed with Atrium 1.3")
    NOT_TO_HAVE("hat nicht"),

    @Deprecated("Switch to atrium-translations-en_GB, this module will be removed with Atrium 1.3")
    WAS("war"),

    @Deprecated("Switch to atrium-translations-en_GB, this module will be removed with Atrium 1.3")
    NONE("kein"),
}
