package ch.tutteli.atrium.reporting

import ch.tutteli.kbox.appendToStringBuilder

/**
 * Responsible to format a method call for text output (e.g. to the console) where it represents arguments of a
 * method call by using their [Object.toString] representation with a few exceptions.
 *
 * The exceptions are:
 * - [CharSequence], is wrapped in quotes (`"`) and \r as well as \n are escaped.
 * - [Char] is wrapped in apostrophes (`'`)
 */
object TextMethodCallFormatter : MethodCallFormatter {
    override fun format(name: String, arguments: Array<out Any?>): () -> String = {
        val sb = StringBuilder(name).append("(")
        arguments.asList().appendToStringBuilder(sb, ", ") { it, innerSb ->
            innerSb.appendArgument(it)
        }
        sb.append(")").toString()
    }

    private fun StringBuilder.appendArgument(arg: Any?) {
        append(when (arg) {
            null -> RawString.NULL.string
            is CharSequence -> "\"$arg\"".replace("\r", "\\r").replace("\n", "\\n")
            is Char -> "'$arg'"
            else -> arg.toString()
        })
    }
}
