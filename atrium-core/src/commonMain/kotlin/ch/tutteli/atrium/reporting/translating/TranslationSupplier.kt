//TODO remove with 2.0.0 at the latest
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.reporting.translating

/**
 * A supplier of translations for [Translatable]s for particular [Locale]s.
 */
@Deprecated("will be removed with 2.0.0 at the latest without replacement")
interface TranslationSupplier {

    /**
     * Returns the translation for the given [translatable] and the given [locale] or `null` if it does not have
     * any translation.
     *
     * @return The translation or null if no translation was found.
     */
    @Deprecated("will be removed with 2.0.0 at the latest without replacement")
    fun get(translatable: Translatable, locale: Locale): String?
}
