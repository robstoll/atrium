package ch.tutteli.atrium.reporting

import ch.tutteli.atrium.reporting.reportables.TextElement

/**
 * Use this class to represent a [String] which should be treated as raw [String] in reporting.
 * @see ObjectFormatter
 *
 * @property string The string which should be treated as raw [String].
 * @param string The string which should be treated as raw [String].
 */
//TODO add when using Kotlin apiLevel 2.0.0
//@ConsistentCopyVisibility
//TODO 2.0.0 remove data
data class Text private constructor(override val string: String) : TextElement {

    override fun toString(): String = "Text($string)"

    companion object {

        operator fun invoke(string: String): Text {
            require(string.isNotEmpty()) { "use Text.EMPTY instead" }
            val warning = if (string.contains(Regex("[\r\n]"))) {
                "\n-- Text should not contain line breaks and such, will error with Atrium 2.0.0"
            } else ""
            return Text(string + warning)
        }

        /**
         * The representation of `null` as [Text].
         */
        val NULL = Text("null")

        /**
         * An empty string as [Text].
         */
        val EMPTY = Text("")

        /**
         * One space as [Text].
         *
         * @since 1.3.0
         */
        val SPACE = Text(" ")

        /**
         * A provider which returns [EMPTY].
         * @since 1.1.0
         */
        val EMPTY_PROVIDER = { EMPTY }
    }
}
