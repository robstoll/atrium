package ch.tutteli.atrium.reporting.translating

import ch.tutteli.atrium.core.migration.toAtriumLocale

/**
 * Returns [java.util.Locale.getDefault].
 */
actual fun getDefaultLocale(): Locale = java.util.Locale.getDefault().toAtriumLocale()

/**
 * Transforms this [Locale] into a [java.util.Locale].
 */
fun Locale.toJavaLocale(): java.util.Locale = java.util.Locale.Builder()
    .setLanguage(language)
    .setScript(script)
    .setRegion(country)
    .setVariant(variant)
    .build()
