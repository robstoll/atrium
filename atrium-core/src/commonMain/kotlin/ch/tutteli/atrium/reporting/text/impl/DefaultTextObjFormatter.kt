package ch.tutteli.atrium.reporting.text.impl

import ch.tutteli.atrium.core.polyfills.fullName
import ch.tutteli.atrium.reporting.LazyRepresentation
import ch.tutteli.atrium.reporting.ObjectFormatter
import ch.tutteli.atrium.reporting.Text
import ch.tutteli.atrium.reporting.reportables.TextElement
import ch.tutteli.atrium.reporting.text.TextObjFormatter
import ch.tutteli.atrium.reporting.theming.text.StyledString
import ch.tutteli.atrium.reporting.theming.text.TextStyler
import ch.tutteli.atrium.reporting.theming.text.noStyle
import kotlin.reflect.KClass

class DefaultTextObjFormatter(private val deprecatedObjectFormatter: ObjectFormatter) : TextObjFormatter {
    override fun format(value: Any?): StyledString =
        when (value) {
            null -> format(Text.NULL)
            is Char -> "'$value'".noStyle()
            is Boolean -> value.toString().noStyle()
            is Int,
            is Long,
            is Float,
            is Double,
            is Number -> value.toString().noStyle()
            //TODO 1.4.0 format float, long, Date, etc. according to default locale specified in verb
            is String -> formatString(value)

            is TextElement -> value.string.noStyle(noLineBreak = false)
            //TODO 1.3.0 we should be able to return multiple columns in this case
            //TODO 1.4.0 introduce an Enum style
            is Enum<*> -> (value.toString() + INDENT + "(" + (value::class.fullName) + ")").noStyle()
            //TODO 1.4.0 introduce a Regex style
            is Throwable -> value::class.fullName.noStyle()
            else -> deprecatedObjectFormatter.format(value).noStyle(noLineBreak = false)
        }

    private fun limitRepresentation(value: String): String =
        if (value.length > LIMIT) "${value.substring(0, LIMIT)}..." else value

    private fun formatString(value: String): StyledString =
        if (value.contains(Regex("[\n\"]"))) {
            val s = "\"\"\"\n" +
                (if (value.length > LIMIT) "${value.substring(0, LIMIT)}\n..." else value) + "\n" +
                "\"\"\""
            s.noStyle(noLineBreak = false)
        } else {
            "\"${limitRepresentation(value)}\"".noStyle(noLineBreak = false)
        }

    companion object {
        const val LIMIT = 10_000

        //TODO 1.3.0 remove once we use a column
        internal const val INDENT: String = "        "
    }
}
