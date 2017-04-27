package ch.tutteli.atrium.reporting.translating

import java.util.*

/**
 * Represents a reviser for a [ITranslationProvider] which allows to overwrite/redefine
 * translations of the [ITranslationProvider].
 */
interface ITranslationProviderReviser : ITranslationProvider {
    /**
     * Defines a [translation] in the given [locale] for the given [translatable]
     * and redefines the translation in case it was already defined.
     *
     * @param translatable The [ITranslatable] for which [translation] should be defined.
     * @param locale The [Locale] in which the [translation] is defined.
     * @param translation The translation for [translatable].
     *
     * @return This reviser for a fluent-style API.
     */
    fun add(locale: Locale, translatable: ITranslatable, translation: String) : ITranslationProviderReviser

    /**
     * Defines the given [translations] in the given [locale]
     * and redefines a translation in case it was already defined.
     *
     * @param locale The [Locale] in which the [translations] are defined.
     * @param translations The translations which shall be defined.
     *
     * @return This reviser for a fluent-style API.
     */
    fun add(locale: Locale, vararg translations: Pair<ITranslatable, String>) : ITranslationProviderReviser
}
