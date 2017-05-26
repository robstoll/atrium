package ch.tutteli.atrium.reporting.translating

/**
 * This translator does not translate but uses [ITranslatable.getDefault] and
 * [ITranslatableWithArgs.getDefault] instead.
 */
object UsingDefaultTranslator : ITranslator {
    override fun translate(translatable: ITranslatable)
        = translatable.getDefault()

    override fun translate(translatableWithArgs: ITranslatableWithArgs)
        = translatableWithArgs.getDefault()
}
