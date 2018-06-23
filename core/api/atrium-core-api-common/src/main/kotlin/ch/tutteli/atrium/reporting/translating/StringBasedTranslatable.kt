package ch.tutteli.atrium.reporting.translating

/**
 * Something which is [Translatable] and provides a default representation by [value].
 */
interface StringBasedTranslatable : Translatable {

    /**
     * The default representation of this [Translatable].
     */
    val value: String

    /**
     * Returns the default representation of this [Translatable] which is [value].
     *
     * @return [value].
     */
    override fun getDefault() = value
}
