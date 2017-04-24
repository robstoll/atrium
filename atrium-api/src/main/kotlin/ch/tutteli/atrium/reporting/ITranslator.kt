package ch.tutteli.atrium.reporting

interface ITranslator {
    fun translate(translatable: ITranslatable): String

    fun add(translatable: ITranslatable, translation: String)
}
