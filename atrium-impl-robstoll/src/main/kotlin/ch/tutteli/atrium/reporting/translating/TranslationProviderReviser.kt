package ch.tutteli.atrium.reporting.translating

import java.util.*
import java.util.concurrent.ConcurrentHashMap

class TranslationProviderReviser(translationProvider: ITranslationProvider) : ITranslationProviderReviser {

    private val translations = ConcurrentHashMap<Locale, ConcurrentHashMap<ITranslatable, String>>()

    init {
        translationProvider.get().forEach { k, v ->
            translations.put(k, ConcurrentHashMap(v))
        }
    }

    override fun get() = translations

    override fun add(locale: Locale, translatable: ITranslatable, translation: String): ITranslationProviderReviser {
        translations.getOrPut(locale, ::ConcurrentHashMap).put(translatable, translation)
        return this
    }

    override fun add(locale: Locale, vararg translations: Pair<ITranslatable, String>): ITranslationProviderReviser {
        this.translations.getOrPut(locale, ::ConcurrentHashMap).putAll(translations)
        return this
    }
}
