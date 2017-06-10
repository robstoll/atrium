package ch.tutteli.atrium.reporting.translating

/**
 * Represents an [ITranslatable] which is not translatable but has only a fixed [name] which serves as [getDefault].
 *
 * Use this class to represent identifiers (method/property names etc.) and the like.
 */
class Untranslatable(representation: () -> String) : ITranslatable {
    constructor(representation: String): this({representation})
    override val name : String = representation()
    override fun getDefault() = name
}
