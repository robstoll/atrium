package ch.tutteli.atrium.reporting.translating

/**
 * Represents a [Translatable] which is not translatable but has only a fixed [name] which serves as [getDefault].
 *
 * Use this class to represent identifiers (method/property names etc.) and the like.
 *
 * @param representation A representation which is not intended to be translated.
 */
class Untranslatable(representation: String) : Translatable {
    override val name: String = representation
    override fun getDefault() = name

    override fun toString(): String = "$name (Untranslatable)"

    companion object {
        /**
         * An empty string as [Untranslatable].
         */
        val EMPTY = Untranslatable("")
    }
}
