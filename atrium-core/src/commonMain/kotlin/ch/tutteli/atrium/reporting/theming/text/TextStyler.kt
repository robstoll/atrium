package ch.tutteli.atrium.reporting.theming.text

import ch.tutteli.atrium.core.Option
import ch.tutteli.atrium.reporting.HorizontalAlignment


// TODO 1.3.0 KDOC
interface TextStyler {

    fun style(
        monospaceLengthCalculator: MonospaceLengthCalculator,
        unstyledString: String,
        styleId: String,
        noLineBreak: Boolean = true,
        horizontalAlignment: HorizontalAlignment = HorizontalAlignment.DEFAULT,
    ): StyledString

    /**
     *
     * @param spaceToAppend adds the given space after the styled string, i.e. the space will not be affected by the
     *   style.
     * @param monospaceLength the monospace length of the StyledString including the [spaceToAppend]
     *   i.e. you can also add a space which has less than one monospace length or more, we are only interested on the
     *   total and this should always add up to an integer.
     * @param maxLineMonospaceLength the monospace length of the longest line taking into account that a space will be
     *   added to the last line in case [spaceToAppend] is defined.
     */
    fun styleWithPredefinedMonospaceLength(
        unstyledString: String,
        styleId: String,
        monospaceLength: Int,
        maxLineMonospaceLength: Int,
        spaceToAppend: String = "",
        noLineBreak: Boolean = true,
        horizontalAlignment: HorizontalAlignment = HorizontalAlignment.DEFAULT,
    ): StyledString
}

// TODO 1.3.0 KDOC
fun TextStyler.style(
    monospaceLengthCalculator: MonospaceLengthCalculator,
    unstyledString: String,
    maybeStyle: Option<Style>,
    noLineBreak: Boolean = true,
    horizontalAlignment: HorizontalAlignment = HorizontalAlignment.DEFAULT,
): StyledString =
    maybeStyle.fold(
        { unstyledString.noStyle(monospaceLengthCalculator, noLineBreak, horizontalAlignment) },
        { styledString ->
            style(monospaceLengthCalculator, unstyledString, styledString, noLineBreak, horizontalAlignment)
        }
    )

// TODO 1.3.0 KDOC
fun TextStyler.style(
    monospaceLengthCalculator: MonospaceLengthCalculator,
    unstyledString: String,
    style: Style,
    noLineBreak: Boolean = true,
    horizontalAlignment: HorizontalAlignment = HorizontalAlignment.DEFAULT,
): StyledString = style(monospaceLengthCalculator, unstyledString, style.styleId, noLineBreak, horizontalAlignment)

