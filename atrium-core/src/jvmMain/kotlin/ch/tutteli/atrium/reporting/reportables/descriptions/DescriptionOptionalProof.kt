package ch.tutteli.atrium.reporting.reportables.descriptions

import ch.tutteli.atrium.reporting.reportables.Description

enum class DescriptionOptionalProof(override val string: String) : Description {
    /** @since 1.3.0 (but was in atrium-translations DescriptionOptionalProof before) */
    EMPTY("empty"),
    
    /** @since 1.3.0 (but was in atrium-translations DescriptionOptionalProof before) */
    IS_NOT_PRESENT("❗❗ is not present")
}
