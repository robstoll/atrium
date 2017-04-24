package ch.tutteli.atrium.reporting

import ch.tutteli.atrium.reporting.translating.ITranslatable
import ch.tutteli.atrium.reporting.translating.ITranslator

object Translator : ITranslator {
    val translations: MutableMap<ITranslatable, String> = HashMap()

    override fun translate(translatable: ITranslatable): String {
        val result = translations[translatable]
        return result ?: translatable.getDefault()
    }

    override fun add(translatable: ITranslatable, translation: String) {
        translations.put(translatable, translation)
    }
}
