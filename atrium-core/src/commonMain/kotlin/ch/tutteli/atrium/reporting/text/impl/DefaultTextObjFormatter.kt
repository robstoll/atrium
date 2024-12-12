package ch.tutteli.atrium.reporting.text.impl

import ch.tutteli.atrium.core.polyfills.fullName
import ch.tutteli.atrium.reporting.ObjectFormatter
import ch.tutteli.atrium.reporting.Text
import ch.tutteli.atrium.reporting.theming.text.MonospaceLengthCalculator
import ch.tutteli.atrium.reporting.theming.text.impl.StringLengthMonospaceLengthCalculator
import ch.tutteli.atrium.reporting.reportables.TextElement
import ch.tutteli.atrium.reporting.text.TextObjFormatter
import ch.tutteli.atrium.reporting.theming.text.StyledString
import ch.tutteli.atrium.reporting.theming.text.noStyle

class DefaultTextObjFormatter(
    private val deprecatedObjectFormatter: ObjectFormatter,
    private val monospaceLengthCalculator: MonospaceLengthCalculator
) : TextObjFormatter {
    override fun format(value: Any?): StyledString =
        when (value) {
            null -> format(Text.NULL)
            //TODO 1.4.0 introduce different styles for different types, i.e. char, boolean, string etc. so that
            // we have more like pretty printing in the output
            is Char -> "'$value'".noStyle(monospaceLengthCalculator)
            is Boolean -> value.toString().noStyle(StringLengthMonospaceLengthCalculator)

            is Int,
            is Long,
            is Float,
            is Double,
            is Number ->
                //TODO 1.4.0 format float, long, Date, etc. according to default locale specified in verb
                value.toString().noStyle(StringLengthMonospaceLengthCalculator)

            is String -> formatString(value)

            is TextElement -> value.string.noStyle(monospaceLengthCalculator, noLineBreak = false)
            //TODO 1.3.0 we should be able to return multiple columns in this case
            //TODO 1.4.0 introduce an Enum style
            is Enum<*> -> (value.toString() + INDENT + "(" + (value::class.fullName) + ")").noStyle(
                monospaceLengthCalculator
            )

            // Note, in theory a class name can contain any Unicode character on the JVM (even a zero-width space).
            // We assume that no other chars than ascii letters are used and hence no need to calculate the
            // monospaceLength
            is Throwable -> value::class.fullName.noStyle(StringLengthMonospaceLengthCalculator)
            else -> deprecatedObjectFormatter.format(value).noStyle(monospaceLengthCalculator, noLineBreak = false)

            //TODO 1.4.0 introduce a Regex style
        }

    private fun limitRepresentation(value: String): String =
        if (value.length > LIMIT) "${value.substring(0, LIMIT)}..." else value

    private fun formatString(value: String): StyledString =
        if (value.contains(Regex("[\n\"]"))) {
            val s = "\"\"\"\n" +
                (if (value.length > LIMIT) "${value.substring(0, LIMIT)}\n..." else value) + "\n" +
                "\"\"\""
            s.noStyle(monospaceLengthCalculator, noLineBreak = false)
        } else {
            "\"${limitRepresentation(value)}\"".noStyle(monospaceLengthCalculator, noLineBreak = false)
        }

    companion object {
        const val LIMIT = 10_000

        //TODO 1.3.0 remove once we use a column
        internal const val INDENT: String = "        "
    }
}
