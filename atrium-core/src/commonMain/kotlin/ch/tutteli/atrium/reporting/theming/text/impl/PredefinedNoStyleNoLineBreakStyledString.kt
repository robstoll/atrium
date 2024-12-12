package ch.tutteli.atrium.reporting.theming.text.impl

import ch.tutteli.atrium.core.None
import ch.tutteli.atrium.core.Option
import ch.tutteli.atrium.reporting.HorizontalAlignment
import ch.tutteli.atrium.reporting.theming.text.StyledString

internal class PredefinedNoStyleNoLineBreakStyledString(
    override val unstyled: String,
    override val horizontalAlignment: HorizontalAlignment
) : StyledString {
    override fun toString(): String = "SS($unstyled)"

    override val maybeStyled: Option<String> = None
    override val noLineBreak: Boolean = true
    override val monospaceLength: Int get() = unstyled.length
    override val maxLineMonospaceLength: Int get() = monospaceLength

    override fun withoutLineBreaks() = this
    override fun withHorizontalAlignment(alignment: HorizontalAlignment): StyledString =
        StyledString.createWithPredefinedMonospaceLength(
            unstyled,
            maybeStyled,
            monospaceLength = monospaceLength,
            maxLineMonospaceLength = monospaceLength,
            noLineBreak = true,
            horizontalAlignment = alignment
        )

    override fun getString(): String = unstyled
}
