// TODO remove with 2.0.0 at the latest
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.reporting.reportables.descriptions

import ch.tutteli.atrium.reporting.reportables.Description
import ch.tutteli.atrium.reporting.translating.StringBasedTranslatable

/**
 * Contains [Description]s that are related to expectation functions which are applicable to [Map] like subjects.
 */
enum class DescriptionMapLikeProof(override val value: String) : StringBasedTranslatable {
    /** @since 1.3.0 (was in DescriptionMapLikeExpectation since 0.18.0) */
    TO_CONTAIN("to contain"),

    /** @since 1.3.0 (was in DescriptionMapLikeExpectation since 0.18.0) */
    TO_CONTAIN_KEY("to contain key"),

    /** @since 1.3.0 (was in DescriptionMapLikeExpectation since 0.18.0) */
    NOT_TO_CONTAIN_KEY("not to contain key"),

    /** @since 1.3.0 (was in DescriptionMapLikeExpectation since 0.18.0) */
    ENTRY_WITH_KEY("entry %s"),

    /** @since 1.3.0 (was in DescriptionMapLikeExpectation since 0.18.0) */
    IN_ANY_ORDER("%s, in any order"),

    /** @since 1.3.0 (was in DescriptionMapLikeExpectation since 0.18.0) */
    IN_ANY_ORDER_ONLY("%s only, in any order"),

    /** @since 1.3.0 (was in DescriptionMapLikeExpectation since 0.18.0) */
    IN_ORDER("%, in order"),

    /** @since 1.3.0 (was in DescriptionMapLikeExpectation since 0.18.0) */
    IN_ORDER_ONLY("%s only, in order"),

    /** @since 1.3.0 (was in DescriptionMapLikeExpectation since 0.18.0) */
    KEY_DOES_NOT_EXIST("❗❗ key does not exist"),

    /** @since 1.3.0 (was in DescriptionMapLikeExpectation since 0.18.0) */
    WARNING_ADDITIONAL_ENTRIES("additional entries detected"),
}
