package ch.tutteli.atrium.reporting.theming.text

import ch.tutteli.atrium.core.Option
import ch.tutteli.atrium.reporting.HorizontalAlignment

// TODO 1.3.0 KDOC
interface TextStyler {
    fun style(unstyledString: String, maybeStyle: Option<Style>, noLineBreak: Boolean = true): StyledString =
        maybeStyle.fold(
            { unstyledString.noStyle(noLineBreak) },
            { styledString -> style(unstyledString, styledString, noLineBreak) }
        )

    fun style(
        unstyledString: String,
        style: Style,
        noLineBreak: Boolean = true,
        align: HorizontalAlignment = HorizontalAlignment.DEFAULT
    ): StyledString = style(unstyledString, style.styleId, noLineBreak, align)

    fun style(
        unstyledString: String,
        styleId: String,
        noLineBreak: Boolean = true,
        align: HorizontalAlignment = HorizontalAlignment.DEFAULT
    ): StyledString
}
