package ch.tutteli.atrium.reporting.translating

/**
 * Something which is translatable with a default representation given by [getDefault].
 */
interface ITranslatable {
    /**
     * @return The default representation of this [ITranslatable].
     */
    fun getDefault(): String
}
