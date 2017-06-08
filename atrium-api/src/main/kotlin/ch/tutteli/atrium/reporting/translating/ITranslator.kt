package ch.tutteli.atrium.reporting.translating

/**
 * Represents a translator of [ITranslatable]s.
 */
interface ITranslator {
    /**
     * Returns the translation of the given [translatable] or its [getDefault][ITranslatable.getDefault]
     * in case there is not a translation defined for it.
     *
     * In case of a [ITranslatableWithArgs] it translates the [ITranslatable] of the given [ITranslatableWithArgs]
     * and formats it with its [arguments][ITranslatableWithArgs.arguments].
     *
     * @return The result of the translation for the given [translatable].
     */
    fun translate(translatable: ITranslatable): String
}
