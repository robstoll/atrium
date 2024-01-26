@file:Suppress("DEPRECATION")
package ch.tutteli.atrium.translations

import ch.tutteli.atrium.assertions.DescriptiveAssertion
import ch.tutteli.atrium.reporting.translating.StringBasedTranslatable

/**
 * Contains the [DescriptiveAssertion.description]s of the assertion functions which are applicable to [Map].
 */
@Deprecated("Switch to atrium-translations-en_GB, this module will be removed with Atrium 1.3")
enum class DescriptionListLikeExpectation(override val value: String) : StringBasedTranslatable {
    /** @since 0.18.0 */
    @Deprecated("Switch to atrium-translations-en_GB, this module will be removed with Atrium 1.3")
    INDEX_OUT_OF_BOUNDS("❗❗ Index ausserhalb der Grenzen (index out of bounds)"),

    /** @since 1.2.0 */
    @Deprecated("Switch to atrium-translations-en_GB, this module will be removed with Atrium 1.3")
    IS_EMPTY("❗❗ Liste ist leer (list is empty)"),
}
