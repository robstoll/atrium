package ch.tutteli.atrium.reporting

import ch.tutteli.kbox.appendToStringBuilder
import kotlin.reflect.KCallable

/**
 * Responsible to format a method call in reporting where it represents arguments of a method call by using
 * their [Object.toString] representation with the exception of:
 * - [CharSequence], is wrapped in quotes (`"`)
 * - [Char] is wrapped in apostrophes (`'`)
 * */
object MethodCallFormatter : IMethodCallFormatter {
    override fun format(method: KCallable<*>, arguments: Array<out Any?>): () -> String = {
        val sb = StringBuilder(method.name).append("(")
        arguments.asList().appendToStringBuilder(sb, ", ") { it, sb ->
            sb.appendArgument(it)
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
