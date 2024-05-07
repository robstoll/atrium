package ch.tutteli.atrium.reporting

/**
 * Use this class to represent a [String] which should be treated as raw [String] in reporting.
 * @see ObjectFormatter
 *
 * @property string The string which should be treated as raw [String].
 * @param string The string which should be treated as raw [String].
 */
//TODO add when using Kotlin apiLevel 2.0.0
//@ConsistentCopyVisibility
//TODO 2.0.0 remove data?
data class Text private constructor(val string: String)  {

    /**
     * @suppress No need to document this behaviour.
     */
    override fun toString(): String {
        return "$string (Text)"
    }

    companion object {

        operator fun invoke(string: String): Text {
            require(string.isNotEmpty()) { "use Text.Empty instead" }
            return Text(string)
        }

        /**
         * The representation of `null` as [Text].
         */
        val NULL = Text("null")

        /**
         * An empty string as [Text]
         */
        val EMPTY = Text("")

        /**
         * A provider which returns [EMPTY].
         * @since 1.1.0
         */
        val EMPTY_PROVIDER = { EMPTY }
    }
}
