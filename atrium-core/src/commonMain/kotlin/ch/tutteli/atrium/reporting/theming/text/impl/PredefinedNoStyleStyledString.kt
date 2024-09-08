package ch.tutteli.atrium.reporting.theming.text.impl

import ch.tutteli.atrium.core.None
import ch.tutteli.atrium.core.Option
import ch.tutteli.atrium.reporting.HorizontalAlignment
import ch.tutteli.atrium.reporting.theming.text.noStyle
import ch.tutteli.atrium.reporting.theming.text.StyledString

internal class PredefinedNoStyleStyledString(
    override val unstyled: String,
    override val horizontalAlignment: HorizontalAlignment
) : StyledString {
    override val maybeStyled: Option<String> = None
    override val noLineBreak: Boolean = true
    override fun withoutLineBreaks() = this
    override fun result(): String = unstyled

    override fun withHorizontalAlignment(alignment: HorizontalAlignment): StyledString =
        // we don't return this as using appendUnstyled afterwards should reflect the choice
        StyledString(unstyled, maybeStyled, noLineBreak, alignment)

    override fun appendUnstyled(unstyledString: String): StyledString =
        (unstyled + unstyledString).noStyle(noLineBreak = noLineBreak, horizontalAlignment = horizontalAlignment)

    override fun toString(): String = "SS($unstyled)"
}
