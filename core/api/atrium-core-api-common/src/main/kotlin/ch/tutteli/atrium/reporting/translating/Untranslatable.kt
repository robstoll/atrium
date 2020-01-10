package ch.tutteli.atrium.reporting.translating

/**
 * Represents a [Translatable] which is not translatable but has only a fixed [name] which serves as [getDefault].
 *
 * Use this class to represent identifiers (method/property names etc.) and the like.
 *
 * @param representation A representation which is not intended to be translated.
 */
class Untranslatable(representation: String) : Translatable {

    @Suppress("DEPRECATION")
    @Deprecated("Use the overload which expects String; will be removed with 0.10.0")
    constructor(representation: CharSequence) : this({ representation.toString() })

    @Deprecated("Use the overload which expects String; will be removed with 0.10.0")
    constructor(representation: () -> String) : this(representation())

    override val name: String = representation
    override fun getDefault() = name

    companion object {
        /**
         * An empty string as [Untranslatable].
         */
        val EMPTY = Untranslatable("")
    }
}
