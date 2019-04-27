package ch.tutteli.atrium.core.migration

import ch.tutteli.atrium.reporting.translating.Locale
import ch.tutteli.kbox.blankToNull

@Suppress("DeprecatedCallableAddReplaceWith")
@Deprecated("Only here to ease migration, use Atrium's Locale directly; will be removed with 1.0.0")
fun java.util.Locale.toAtriumLocale(): Locale
    = ch.tutteli.atrium.reporting.translating.Locale(
        language,
        script.blankToNull(),
        country.blankToNull(),
        variant.blankToNull()
    )
