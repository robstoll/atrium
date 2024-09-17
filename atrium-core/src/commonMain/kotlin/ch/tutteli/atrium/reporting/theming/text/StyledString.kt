package ch.tutteli.atrium.reporting.theming.text

import ch.tutteli.atrium.core.None
import ch.tutteli.atrium.core.Option
import ch.tutteli.atrium.core.polyfills.appendSpaces
import ch.tutteli.atrium.reporting.BUG_REPORT_URL
import ch.tutteli.atrium.reporting.HorizontalAlignment
import ch.tutteli.atrium.reporting.theming.text.impl.DefaultStyledString
import ch.tutteli.atrium.reporting.theming.text.impl.PredefinedNoStyleStyledString

//TODO 1.3.0 KDOC
//TODO 1.3.0 could we use this for HTML output? if so, then move this to theming
interface StyledString {
    val unstyled: String
    val maybeStyled: Option<String>
    val noLineBreak: Boolean
    val horizontalAlignment: HorizontalAlignment

    //TODO 1.3.0 find a better name, result is not clear at all of what
    fun result(): String

    fun withoutLineBreaks(): StyledString
    fun appendUnstyled(unstyledString: String): StyledString
    fun withHorizontalAlignment(alignment: HorizontalAlignment): StyledString

    companion object {
        operator fun invoke(
            unstyled: String,
            maybeStyled: Option<String>,
            noLineBreak: Boolean = true,
            horizontalAlignment: HorizontalAlignment = HorizontalAlignment.DEFAULT,
        ): StyledString = DefaultStyledString(
            unstyled = unstyled,
            maybeStyled = maybeStyled,
            noLineBreak = noLineBreak,
            horizontalAlignment = horizontalAlignment,
        )

        val EMPTY_STRING: StyledString = PredefinedNoStyleStyledString("", HorizontalAlignment.DEFAULT)
        val COLON_SEPARATOR: StyledString = PredefinedNoStyleStyledString(" : ", HorizontalAlignment.CENTRE)
    }
}

// TODO 1.3.0 KDOC
fun StyledString.replaceWrap(s: String) =
    if (noLineBreak) s.replace('\n', ' ') else s

// TODO 1.3.0 KDOC
fun StyledString.pad(length: Int): String =
    maybeStyled.fold(
        {
            val txt = replaceWrap(unstyled)
            when (horizontalAlignment) {
                HorizontalAlignment.LEFT -> txt.padEnd(length)
                HorizontalAlignment.CENTRE -> txt.padStart(length / 2 + ((length - txt.length) / 2)).padEnd(length)
                HorizontalAlignment.RIGHT -> txt.padStart(length)
            }
        },
        { styled ->
            // Note, if someone should define ansi colours in the unstyled string -- for instance
            // `Text("\u001b[31mred\u001b[0m")` -- then the length is wrong, i.e. we don't handle/support this for now
            val unstyledLength = unstyled.length
            val styleDiff = styled.length - unstyledLength
            val sb = StringBuilder(length + styleDiff)
            val pad = length - unstyledLength

            when (horizontalAlignment) {
                HorizontalAlignment.LEFT -> {
                    // no padding before
                }

                HorizontalAlignment.CENTRE -> {
                    // pad half before
                    sb.appendSpaces(pad / 2)
                }

                HorizontalAlignment.RIGHT -> {
                    // pad all before
                    sb.appendSpaces(pad)
                }
            }

            // append text as such
            sb.append(replaceWrap(styled))

            when (horizontalAlignment) {
                HorizontalAlignment.LEFT -> {
                    // pad all after
                    sb.appendSpaces(pad)
                }

                HorizontalAlignment.CENTRE -> {
                    // pad half after
                    sb.appendSpaces(pad - (pad / 2))
                }

                HorizontalAlignment.RIGHT -> {
                    // no padding after
                }
            }

            sb.toString()
        }
    )


// TODO 1.3.0 KDOC
fun String.noStyle(
    noLineBreak: Boolean = true,
    horizontalAlignment: HorizontalAlignment = HorizontalAlignment.DEFAULT
): StyledString =
    if (this == "") StyledString.EMPTY_STRING
    else StyledString(this, None, noLineBreak = noLineBreak, horizontalAlignment = horizontalAlignment)


// TODO 1.3.0 KDOC
internal fun checkIsNoLineBreakDueToMergeColumns(styledString: StyledString) {
    if (styledString.noLineBreak.not()) {
        throw UnsupportedOperationException("cannot merge columns which allow line breaks at the same time, behaviour not defined yet, please open a feature request $BUG_REPORT_URL")
    }
}
