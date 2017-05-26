package ch.tutteli.atrium.reporting.translating

import java.util.*
import java.util.concurrent.ConcurrentHashMap

class Translator(
    private val translationProvider: ITranslationProvider,
    private val locale: Locale,
    private val fallbackLocales: Array<out Locale>
) : ITranslator {
    private val translations = ConcurrentHashMap<Locale, Map<ITranslatable, String>>()
    private val localeResolver = LocaleResolver()

    override fun translate(translatable: ITranslatable): String
        = getTranslationWithLocale(translatable).translation


    override fun translate(translatableWithArgs: ITranslatableWithArgs): String {
        val result = getTranslationWithLocale(translatableWithArgs.translatable)
        return String.format(result.locale, result.translation, translatableWithArgs.arguments)
    }

    private fun getTranslationWithLocale(translatable: ITranslatable): TranslationWithLocale {
        return localeResolver.resolve(locale, fallbackLocales)
            .map { createTranslationWithLocaleOrNull(getOrPut(it), translatable) }
            .firstOrNull { it != null }
            ?: TranslationWithLocale(translatable.getDefault(), translatable.locale)
    }

    private fun createTranslationWithLocaleOrNull(translations: Map<ITranslatable, String>, translatable: ITranslatable): TranslationWithLocale? {
        val translation = translations[translatable]
        return if (translation != null) {
            TranslationWithLocale(translation, translatable.locale)
        } else {
            null
        }
    }

    private fun getOrPut(locale: Locale): Map<ITranslatable, String> {
        return translations.getOrPut(locale) {
            translationProvider.get(locale)
        }
    }

    private class TranslationWithLocale(val translation: String, val locale: Locale)
}
