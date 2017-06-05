package ch.tutteli.atrium.reporting.translating

import java.util.*

class Translator(
    private val translationSupplier: ITranslationSupplier,
    private val locale: Locale,
    private val fallbackLocales: Array<out Locale>
) : ITranslator {
    private val localeResolver = LocaleResolver()

    override fun translate(translatable: ITranslatable): String
        = getTranslationWithLocale(translatable).translation


    override fun translate(translatableWithArgs: ITranslatableWithArgs): String {
        val result = getTranslationWithLocale(translatableWithArgs.translatable)
        return String.format(result.locale, result.translation, translatableWithArgs.arguments)
    }

    private fun getTranslationWithLocale(translatable: ITranslatable): TranslationWithLocale {
        return localeResolver.resolve(locale, fallbackLocales)
            .map { createTranslationWithLocaleOrNull(translationSupplier.get(translatable, it), translatable) }
            .firstOrNull { it != null }
            ?: TranslationWithLocale(translatable.getDefault(), translatable.locale)
    }

    private fun createTranslationWithLocaleOrNull(translation: String?, translatable: ITranslatable): TranslationWithLocale? {
        return if (translation != null) {
            TranslationWithLocale(translation, translatable.locale)
        } else {
            null
        }
    }

    private class TranslationWithLocale(val translation: String, val locale: Locale)
}
