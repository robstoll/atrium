package ch.tutteli.atrium.reporting.translating

/**
 * Represents a translator of [ITranslatable]s.
 */
interface ITranslator {
    /**
     * Returns the translation of the given [translatable] or its [getDefault][ITranslatable.getDefault]
     * in case there was no default defined.
     *
     * @return The result of the translation for the given [translatable].
     */
    fun translate(translatable: ITranslatable): String

    /**
     * Add a [translation] for the given [translatable] and redefines the translation in case it was already defined.
     *
     * @param translatable The [ITranslatable] for which [translation] should be defined.
     * @param translation The translation for [translatable].
     */
    fun add(translatable: ITranslatable, translation: String)
}
