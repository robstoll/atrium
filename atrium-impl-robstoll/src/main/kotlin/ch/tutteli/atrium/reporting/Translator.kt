package ch.tutteli.atrium.reporting

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
