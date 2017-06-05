package ch.tutteli.atrium.reporting.translating

import java.util.*

/**
 * A supplier of translations for [ITranslatable]s for particular [Locale]s.
 */
interface ITranslationSupplier {

    /**
     * Returns the translation for the given [translatable] for the given [locale].
     *
     * @return The translations.
     */
    fun get(translatable: ITranslatable, locale: Locale): String?
}
