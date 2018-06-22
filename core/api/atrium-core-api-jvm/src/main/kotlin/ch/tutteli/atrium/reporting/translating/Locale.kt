package ch.tutteli.atrium.reporting.translating

/**
 * Returns [java.util.Locale.getDefault()].
 */
actual fun getLocaleDefault(): Locale {
    val defaultLocale = java.util.Locale.getDefault()
    return Locale(defaultLocale.language, defaultLocale.script, defaultLocale.country, defaultLocale.variant)
}

/**
 * Transforms this [Locale] into a [java.util.Locale].
 */
fun Locale.toJavaLocale(): java.util.Locale = java.util.Locale.Builder()
    .setLanguage(language)
    .setScript(script)
    .setRegion(country)
    .setVariant(variant)
    .build()
