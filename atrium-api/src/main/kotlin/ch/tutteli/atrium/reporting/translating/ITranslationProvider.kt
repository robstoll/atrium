package ch.tutteli.atrium.reporting.translating

import java.util.*

/**
 * A provider of translations for [ITranslatable]s for particular [Locale]s.
 */
interface ITranslationProvider {
    /**
     * Returns all translations the provider can provide.
     *
     * @return The translations.
     */
    fun get(): Map<Locale, Map<ITranslatable, String>>
}
