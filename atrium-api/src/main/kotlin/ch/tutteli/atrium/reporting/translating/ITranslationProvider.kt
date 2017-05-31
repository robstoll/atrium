package ch.tutteli.atrium.reporting.translating

import java.util.*

/**
 * A provider of translations for [ITranslatable]s for particular [Locale]s.
 */
interface ITranslationProvider {

    /**
     * Returns the translation for the given [translatable] for the given [locale].
     *
     * A translation provider should not cache translations itself.
     *
     * @return The translations.
     */
    fun get(translatable: ITranslatable, locale: Locale): String?
}
