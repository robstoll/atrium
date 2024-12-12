package ch.tutteli.atrium.reporting.theming.text.impl

import ch.tutteli.atrium.core.None
import ch.tutteli.atrium.core.Some
import ch.tutteli.atrium.reporting.HorizontalAlignment
import ch.tutteli.atrium.reporting.theming.text.*

internal class DefaultTextStyler(
    private val textThemeProvider: TextThemeProvider,
) : TextStyler {

    override fun style(
        monospaceLengthCalculator: MonospaceLengthCalculator,
        unstyledString: String,
        styleId: String,
        noLineBreak: Boolean,
        horizontalAlignment: HorizontalAlignment
    ): StyledString {
        val maybeStyled = ch.tutteli.kbox.takeIf(textThemeProvider.supportsAnsiColours) {
            textThemeProvider.render(unstyledString, styleId)?.let { Some(it) }
        } ?: None
        return StyledString.create(
            monospaceLengthCalculator,
            unstyledString,
            maybeStyled = maybeStyled,
            noLineBreak = noLineBreak,
            horizontalAlignment = horizontalAlignment
        )
    }

    override fun styleWithPredefinedMonospaceLength(
        unstyledString: String,
        styleId: String,
        monospaceLength: Int,
        maxLineMonospaceLength: Int,
        spaceToAppend: String,
        noLineBreak: Boolean,
        horizontalAlignment: HorizontalAlignment
    ): StyledString {
        val maybeStyled = ch.tutteli.kbox.takeIf(textThemeProvider.supportsAnsiColours) {
            textThemeProvider.render(unstyledString, styleId)?.let {
                Some(it + spaceToAppend)
            }
        } ?: None
        return StyledString.createWithPredefinedMonospaceLength(
            unstyledString + spaceToAppend,
            maybeStyled = maybeStyled,
            noLineBreak = noLineBreak,
            horizontalAlignment = horizontalAlignment,
            monospaceLength = monospaceLength,
            maxLineMonospaceLength = maxLineMonospaceLength
        )
    }
}
