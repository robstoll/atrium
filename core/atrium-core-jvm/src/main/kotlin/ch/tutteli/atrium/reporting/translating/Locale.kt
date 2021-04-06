@file:Suppress(
    // TODO remove once https://youtrack.jetbrains.com/issue/KT-35343 is fixed
    "JAVA_MODULE_DOES_NOT_READ_UNNAMED_MODULE"
)

package ch.tutteli.atrium.reporting.translating

import ch.tutteli.atrium.core.migration.toAtriumLocale

/**
 * Returns [java.util.Locale.getDefault].
 */
@Suppress("DEPRECATION" /* that's fine, we want the java getDefault behaviour */)
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
