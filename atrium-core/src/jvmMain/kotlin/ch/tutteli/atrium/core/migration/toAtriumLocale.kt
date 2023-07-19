package ch.tutteli.atrium.core.migration

import ch.tutteli.atrium.reporting.translating.Locale
import ch.tutteli.kbox.blankToNull

internal fun java.util.Locale.toAtriumLocale(): Locale = Locale(
    language,
    script.blankToNull(),
    country.blankToNull(),
    variant.blankToNull()
)
