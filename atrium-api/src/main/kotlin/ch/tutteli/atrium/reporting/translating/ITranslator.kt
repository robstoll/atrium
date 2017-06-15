package ch.tutteli.atrium.reporting.translating

/**
 * Represents a translator of [ITranslatable]s.
 */
interface ITranslator {
    /**
     * Returns the translation of the given [translatable] or its [getDefault][ITranslatable.getDefault]
     * in case there is not a translation defined for it.
     *
     *  @param translatable The [ITranslatable] which shall be translated.
     *
     * @return The result of the translation for the given [translatable].
     */
    fun translate(translatable: ITranslatable): String
}
