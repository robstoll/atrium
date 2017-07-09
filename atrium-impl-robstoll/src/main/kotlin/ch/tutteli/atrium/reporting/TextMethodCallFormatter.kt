package ch.tutteli.atrium.reporting

import ch.tutteli.kbox.appendToStringBuilder
import kotlin.reflect.KCallable

/**
 * Responsible to format a method call for text output (e.g. the console) where it represents arguments of a
 * method call by using their [Object.toString] representation with a few exceptions.
 *
 * The exceptions are:
 * - [CharSequence], is wrapped in quotes (`"`) and \r as well as \n are escaped.
 * - [Char] is wrapped in apostrophes (`'`)
 */
internal object TextMethodCallFormatter : IMethodCallFormatter {
    override fun format(method: KCallable<*>, arguments: Array<out Any?>): () -> String = {
        val sb = StringBuilder(method.name).append("(")
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
