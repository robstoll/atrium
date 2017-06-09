package ch.tutteli.atrium.reporting.translating

/**
 * Something which is [ITranslatable] and provides a default representation by [value].
 */
interface ISimpleTranslatable : ITranslatable {
    val value: String
    override fun getDefault() = value
}
