package ch.tutteli.atrium.reporting.text.impl

import ch.tutteli.atrium.reporting.reportables.TextElement
import ch.tutteli.atrium.reporting.text.TextObjFormatter
import ch.tutteli.atrium.reporting.theming.text.StyledString
import ch.tutteli.atrium.reporting.theming.text.TextStyler
import ch.tutteli.atrium.reporting.theming.text.noStyle

class DefaultTextObjFormatter(private val styler: TextStyler) : TextObjFormatter {
    override fun format(value: Any?): StyledString =
        when (value) {
            is TextElement -> value.string.noStyle(noLineBreak = false)
            is String -> formatString(value)
            else -> value.toString().noStyle(noLineBreak = false)
        }

    private fun formatString(value: String): StyledString =
        if (value.contains(Regex("[\n\"]"))) {
            val s = "\"\"\"\n" +
                value + "\n" +
                "\"\"\""
            s.noStyle(noLineBreak = false)
        } else {
            "\"$value\"".noStyle(noLineBreak = false)
        }
}
