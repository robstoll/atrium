package ch.tutteli.atrium.reporting.translating

/**
 * Represents an [ITranslatable] which is not translatable but has only a fixed [name] which serves as [getDefault].
 *
 * Use this class to represent identifiers (method/property names etc.) and the like.
 */
class Untranslatable(override val name: String) : ITranslatable {
    override fun getDefault() = name
}
