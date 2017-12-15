package ch.tutteli.atrium.reporting.translating

import java.util.*

/**
 * A supplier of translations for [ITranslatable]s for particular [Locale]s.
 */
interface ITranslationSupplier {

    /**
     * Returns the translation for the given [translatable] and the given [locale] or `null` if it does not have
     * any translation.
     *
     * @return The translation or null if no translation was found.
     * @throws IllegalArgumentException in case [Locale.ROOT] is passed for [locale].
     */
    fun get(translatable: ITranslatable, locale: Locale): String?
}
