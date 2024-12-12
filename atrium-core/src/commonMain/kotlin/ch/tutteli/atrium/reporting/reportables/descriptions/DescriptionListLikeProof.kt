package ch.tutteli.atrium.reporting.reportables.descriptions

import ch.tutteli.atrium.reporting.reportables.Description

/**
 * Contains [Description]s that are related to expectation functions which are applicable to [List] like subjects.
 */
enum class DescriptionListLikeProof(override val string: String) : Description {
    /** @since 0.18.0 */
    INDEX_OUT_OF_BOUNDS("❗❗ Index out of bounds")
}
