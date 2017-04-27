package ch.tutteli.atrium.reporting.translating

import java.util.*

class Translator(translationProvider: ITranslationProvider, locale: Locale, private val fallbackLocales: Array<out Locale>) : ITranslator {
    private val fallbackTranslations: Map<Locale, Map<ITranslatable, String>> = translationProvider.get()
    private val translations: Map<ITranslatable, String> = fallbackTranslations[locale] ?: emptyMap()

    override fun translate(translatable: ITranslatable): String
        = translations[translatable]
        ?: getFallbackTranslation(translatable)
        ?: translatable.getDefault()


    private fun getFallbackTranslation(translatable: ITranslatable): String?
        = fallbackLocales.asSequence()
        .map { fallbackTranslations[it]?.get(translatable) }
        .firstOrNull { it != null }

    override fun translate(translatableWithArgs: ITranslatableWithArgs): String {
        val translation = translate(translatableWithArgs.translatable)
        return String.format(translation, translatableWithArgs.arguments)
    }
}
