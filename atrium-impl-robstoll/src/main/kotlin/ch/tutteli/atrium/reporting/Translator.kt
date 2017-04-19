package ch.tutteli.atrium.reporting

object Translator : ITranslator {

    override fun translate(translatable: ITranslatable): String {
        //TODO implement translation
        return translatable.getDefault()
    }
}
