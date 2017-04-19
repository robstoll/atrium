package ch.tutteli.atrium.reporting

interface ITranslator {
    fun translate(translatable: ITranslatable): String
}
