package ch.tutteli.atrium.reporting.translating

/**
 * Represents a [Translatable] which is not translatable but has only a fixed [name] which serves as [getDefault].
 *
 * Use this class to represent identifiers (method/property names etc.) and the like.
 *
 * @param representation A representation which is not intended to be translated.
 */
//TODO 1.3.0 define replacement
@Suppress("DEPRECATION")
@Deprecated(
    "Will be removed with 2.0.0 at the latest",
    ReplaceWith("Text(getDefault())", "ch.tutteli.atrium.reporting.Text")
)
class Untranslatable(representation: String) : Translatable {
    override val name: String = representation
    override fun getDefault() = name

    override fun toString(): String = "$name (Untranslatable)"

    companion object {
        /**
         * An empty string as [Untranslatable].
         */
        //TODO 1.3.0 define replacement
        @Deprecated(
            "will be removed with 2.0.0 at the latest without replacement",
            ReplaceWith("Text.EMPTY", "ch.tutteli.atrium.reporting.Text")
        )
        val EMPTY = Untranslatable("")
    }
}
