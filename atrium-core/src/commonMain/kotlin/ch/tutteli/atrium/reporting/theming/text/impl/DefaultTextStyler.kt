package ch.tutteli.atrium.reporting.theming.text.impl

import ch.tutteli.atrium.core.None
import ch.tutteli.atrium.core.Some
import ch.tutteli.atrium.reporting.HorizontalAlignment
import ch.tutteli.atrium.reporting.theming.text.StyledString
import ch.tutteli.atrium.reporting.theming.text.TextStyler
import ch.tutteli.atrium.reporting.theming.text.TextThemeProvider

internal class DefaultTextStyler(
    private val textThemeProvider: TextThemeProvider,
) : TextStyler {

    override fun style(
        unstyledString: String,
        styleId: String,
        noLineBreak: Boolean,
        horizontalAlignment: HorizontalAlignment
    ): StyledString {
        val maybeStyled = ch.tutteli.kbox.takeIf(textThemeProvider.supportsAnsi) {
            textThemeProvider.render(unstyledString, styleId)?.let { Some(it) }
        } ?: None
        return StyledString(
            unstyledString,
            maybeStyled = maybeStyled,
            noLineBreak = noLineBreak,
            horizontalAlignment = horizontalAlignment
        )
    }
}
