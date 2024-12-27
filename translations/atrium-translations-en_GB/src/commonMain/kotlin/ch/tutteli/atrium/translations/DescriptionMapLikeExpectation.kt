package ch.tutteli.atrium.translations

import ch.tutteli.atrium.assertions.DescriptiveAssertion
import ch.tutteli.atrium.reporting.translating.StringBasedTranslatable

/**
 * Contains the [DescriptiveAssertion.description]s of the assertion functions which are applicable to [Map].
 */
enum class DescriptionMapLikeExpectation(override val value: String) : StringBasedTranslatable {
    /** @since 0.18.0 */
    TO_CONTAIN("to contain"),

    /** @since 0.18.0 */
    TO_CONTAIN_KEY("to contain key"),

    /** @since 0.18.0 */
    NOT_TO_CONTAIN_KEY("not to contain key"),

    /** @since 0.18.0 */
    ENTRY_WITH_KEY("entry %s"),

    /** @since 0.18.0 */
    IN_ANY_ORDER("%s, in any order"),

    /** @since 0.18.0 */
    IN_ANY_ORDER_ONLY("%s only, in any order"),

    /** @since 0.18.0 */
    IN_ORDER("%, in order"),

    /** @since 0.18.0 */
    IN_ORDER_ONLY("%s only, in order"),

    /** @since 0.18.0 */
    KEY_DOES_NOT_EXIST("❗❗ key does not exist"),

    /** @since 0.18.0 */
    WARNING_ADDITIONAL_ENTRIES("additional entries detected"),
}
