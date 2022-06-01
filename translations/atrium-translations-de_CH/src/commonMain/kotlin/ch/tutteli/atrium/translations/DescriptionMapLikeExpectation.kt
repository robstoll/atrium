package ch.tutteli.atrium.translations

import ch.tutteli.atrium.assertions.DescriptiveAssertion
import ch.tutteli.atrium.reporting.translating.StringBasedTranslatable

/**
 * Contains the [DescriptiveAssertion.description]s of the assertion functions which are applicable to [Map].
 */
enum class DescriptionMapLikeExpectation(override val value: String) : StringBasedTranslatable {
    /** @since 0.18.0 */
    TO_CONTAIN("enthält"),

    /** @since 0.18.0 */
    TO_CONTAIN_KEY("enthält Key"),

    /** @since 0.18.0 */
    NOT_TO_CONTAIN_KEY("enthält nicht den Key"),

    /** @since 0.18.0 */
    ENTRY_WITH_KEY("Eintrag %s"),

    /** @since 0.18.0 */
    IN_ANY_ORDER("%s, in beliebiger Reihenfolge"),

    /** @since 0.18.0 */
    IN_ANY_ORDER_ONLY("%s ausschliesslich, in beliebiger Reihenfolge"),

    /** @since 0.18.0 */
    IN_ORDER("%, in gegebener Reihenfolge"),

    /** @since 0.18.0 */
    IN_ORDER_ONLY("%s ausschliesslich, in gegebener Reihenfolge"),

    /** @since 0.18.0 */
    KEY_DOES_NOT_EXIST("❗❗ Key existiert nicht"),

    /** @since 0.18.0 */
    WARNING_ADDITIONAL_ENTRIES("zusätzliche Einträge entdeckt"),
}
