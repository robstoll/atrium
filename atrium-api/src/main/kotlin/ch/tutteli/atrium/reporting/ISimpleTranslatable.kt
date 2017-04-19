package ch.tutteli.atrium.reporting

interface ISimpleTranslatable : ITranslatable {
    val value: String
    override fun getDefault() = value
}
