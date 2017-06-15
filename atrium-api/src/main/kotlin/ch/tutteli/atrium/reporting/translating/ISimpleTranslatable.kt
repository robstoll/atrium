package ch.tutteli.atrium.reporting.translating

/**
 * Something which is [ITranslatable] and provides a default representation by [value].
 */
interface ISimpleTranslatable : ITranslatable {
    /**
     * The default representation of this [ITranslatable].
     */
    val value: String

    /**
     * Returns the default representation of this [ITranslatable] which is [value].
     *
     * @return [value].
     */
    override fun getDefault() = value
}
