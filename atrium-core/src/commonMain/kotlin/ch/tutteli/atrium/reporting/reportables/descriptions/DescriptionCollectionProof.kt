package ch.tutteli.atrium.reporting.reportables.descriptions

import ch.tutteli.atrium.reporting.reportables.Description

/**
 * Contains [Description]s that are related to expectation functions which are applicable to [Collection]Like types.
 */
enum class DescriptionCollectionProof(override val string: String) : Description {
    /** @since 1.3.0 (but was since 0.18.0 in atrium-translations DescriptionCollectionExpectation)*/
    EMPTY("empty"),

    /** @since 1.3.0 (but was since 0.18.0 in atrium-translations DescriptionCollectionExpectation)*/
    SIZE("size"),
}
