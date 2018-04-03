package ch.tutteli.atrium.reporting.translating

/**
 * Represents a [Translatable] which is not translatable but has only a fixed [name] which serves as [getDefault].
 *
 * Use this class to represent identifiers (method/property names etc.) and the like.
 *
 * @constructor Use this overload if the creation of the representation is expensive.
 *   If it is cheap, then you might want to use the other overload with [CharSequence] as parameter type.
 * @param representation A lambda which will create the representation of this [Translatable].
 */
class Untranslatable(representation: () -> String) : Translatable {

    /**
     * Use this overload if the creation of the representation is cheap -- use the other overload with the
     * lambda instead, if the creation of the representation is expensive.
     *
     * @param representation The representation of this [Translatable].
     */
    constructor(representation: CharSequence) : this({ representation.toString() })

    override val name: String = representation()
    override fun getDefault() = name

    companion object {
        /**
         * An empty string as [Untranslatable].
         */
        val EMPTY = Untranslatable("")
    }
}
