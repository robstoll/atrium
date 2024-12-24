package ch.tutteli.atrium.reporting.theming.text

import ch.tutteli.atrium.core.None
import ch.tutteli.atrium.core.Option
import ch.tutteli.atrium.core.polyfills.appendSpaces
import ch.tutteli.atrium.reporting.BUG_REPORT_URL
import ch.tutteli.atrium.reporting.HorizontalAlignment
import ch.tutteli.atrium.reporting.theming.text.impl.CalculatedMonospaceStyledString
import ch.tutteli.atrium.reporting.theming.text.impl.PrecalculatedMonospaceStyledString
import ch.tutteli.atrium.reporting.theming.text.impl.PredefinedNoStyleNoLineBreakStyledString
import ch.tutteli.kbox.identity

interface NotYetStyledString {
    val unstyled: String
    val monospaceLength: Int

    companion object {
        fun create(unstyled: String, monospaceLength: Int = unstyled.length): NotYetStyledString =
            DefaultNotYetStyledString(unstyled, monospaceLength)
    }
}

internal class DefaultNotYetStyledString(
    override val unstyled: String,
    override val monospaceLength: Int
) : NotYetStyledString

//TODO 1.3.0 KDOC
//TODO 1.3.0 could we use this for HTML output? if so, then move this to theming
interface StyledString {
    val unstyled: String
    val maybeStyled: Option<String>
    val noLineBreak: Boolean
    val horizontalAlignment: HorizontalAlignment

    /**
     * Returns the length in terms of mono-width space.
     *
     * Wich means it is based on [unstyled] and foreground/background colour etc. is ignored. Moreover, it takes into
     * account if a char is wider or less wide than 1 monospace char (e.g. surrogate chars or variant selectors don't
     * take up any monospace).
     *
     * In the end it heavily depends on the font and the renderer how much space a certain char really takes
     * (especially for emoji), so it is clear that this is only an approximation.
     */
    val monospaceLength: Int

    /**
     * Same as [monospaceLength] in case [noLineBreak] = true, otherwise the longest line length in terms of
     * mono-width space.
     */
    val maxLineMonospaceLength: Int

    fun withoutLineBreaks(): StyledString
    fun withHorizontalAlignment(alignment: HorizontalAlignment): StyledString

    /**
     * Returns the resulting [String] for this [StyledString].
     *
     * Taking into account that it [maybeStyled] and line breaks need to be replaced by spaced in case
     * [noLineBreak] = true.
     */
    fun getString(): String
    fun padString(monospaceLength: Int): String = padMonospace(monospaceLength)


    companion object {

        fun createWithPredefinedMonospaceLength(
            unstyled: String,
            maybeStyled: Option<String>,
            monospaceLength: Int,
            maxLineMonospaceLength: Int = monospaceLength,
            noLineBreak: Boolean = true,
            horizontalAlignment: HorizontalAlignment = HorizontalAlignment.DEFAULT,
        ): StyledString = PrecalculatedMonospaceStyledString(
            unstyled,
            maybeStyled,
            noLineBreak,
            horizontalAlignment,
            monospaceLength,
            maxLineMonospaceLength
        )

        fun create(
            monospaceLengthCalculator: MonospaceLengthCalculator,
            unstyled: String,
            maybeStyled: Option<String>,
            noLineBreak: Boolean = true,
            horizontalAlignment: HorizontalAlignment = HorizontalAlignment.DEFAULT,
        ): StyledString = CalculatedMonospaceStyledString(
            monospaceLengthCalculator,
            unstyled,
            maybeStyled,
            noLineBreak,
            horizontalAlignment
        )

        val EMPTY_STRING: StyledString = PredefinedNoStyleNoLineBreakStyledString("", HorizontalAlignment.DEFAULT)
        val COLON_SEPARATOR: StyledString = PredefinedNoStyleNoLineBreakStyledString(" : ", HorizontalAlignment.CENTRE)
    }
}

// TODO 1.3.0 KDOC
fun StyledString.replaceWrap(s: String): String =
    if (noLineBreak) Regex("\r\n|\n").replace(s, " ") else s

// TODO 1.3.0 KDOC
fun StyledString.padMonospace(monospaceLength: Int): String {
    val pad = monospaceLength - this.monospaceLength
    if (pad <= 0) return getString()

    // the actual string might be longer than the monospacedLength of this StyledString, the capacity of the
    // StringBuilder needs to be longer for that amount
    val diff = maybeStyled.fold({ this.unstyled.length }, { styled -> styled.length }) - this.monospaceLength
    val sb = StringBuilder(monospaceLength + diff)

    when (horizontalAlignment) {
        HorizontalAlignment.LEFT -> {
            // no padding before
        }

        HorizontalAlignment.CENTRE -> {
            // pad half before, if `pad` is odd, then we pad more on the right than on the left
            sb.appendSpaces(pad / 2)
        }

        HorizontalAlignment.RIGHT -> {
            // pad all before
            sb.appendSpaces(pad)
        }
    }

    // append text as such
    sb.append(replaceWrap(maybeStyled.fold({ this.unstyled }, ::identity)))

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

    return sb.toString()
}


// TODO 1.3.0 KDOC
fun String.noStyle(
    monospaceLengthCalculator: MonospaceLengthCalculator,
    noLineBreak: Boolean = true,
    horizontalAlignment: HorizontalAlignment = HorizontalAlignment.DEFAULT,
): StyledString =
    if (this == "") StyledString.EMPTY_STRING
    else StyledString.create(monospaceLengthCalculator, this, None, noLineBreak, horizontalAlignment)

fun String.noStyle(
    monospaceLength: Int,
    maxLineMonospaceLength: Int = monospaceLength,
    noLineBreak: Boolean = true,
    horizontalAlignment: HorizontalAlignment = HorizontalAlignment.DEFAULT
): StyledString = StyledString.createWithPredefinedMonospaceLength(
    this,
    maybeStyled = None,
    monospaceLength = monospaceLength,
    maxLineMonospaceLength = maxLineMonospaceLength,
    noLineBreak = noLineBreak,
    horizontalAlignment = horizontalAlignment
)


// TODO 1.3.0 KDOC
internal fun checkIsNoLineBreakDueToMergeColumns(styledString: StyledString) {
    if (styledString.noLineBreak.not()) {
        throw UnsupportedOperationException("cannot merge columns which allow line breaks at the same time, behaviour not defined yet, please open a feature request $BUG_REPORT_URL")
    }
}
