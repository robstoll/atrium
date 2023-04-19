@file:Suppress(
    // TODO remove once https://youtrack.jetbrains.com/issue/KT-35343 is fixed
    "JAVA_MODULE_DOES_NOT_READ_UNNAMED_MODULE"
)

package ch.tutteli.atrium.core.migration

import ch.tutteli.atrium.reporting.translating.Locale
import ch.tutteli.kbox.blankToNull

internal fun java.util.Locale.toAtriumLocale(): Locale = Locale(
    language,
    script.blankToNull(),
    country.blankToNull(),
    variant.blankToNull()
)
