package ch.tutteli.atrium.reporting.translating

import java.util.*

/**
 * A provider of translations for [ITranslatable]s for particular [Locale]s.
 */
interface ITranslationProvider {

    /**
     * Returns all translations for the given [locale].
     *
     * A translation provider should not cache translations itself.
     *
     * @return The translations.
     */
    fun get(locale: Locale): Map<ITranslatable, String>
}
