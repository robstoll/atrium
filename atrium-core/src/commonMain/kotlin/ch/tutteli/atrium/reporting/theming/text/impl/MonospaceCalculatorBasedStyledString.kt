package ch.tutteli.atrium.reporting.theming.text.impl

import ch.tutteli.atrium.core.Option
import ch.tutteli.atrium.core.getOrElse
import ch.tutteli.atrium.reporting.HorizontalAlignment
import ch.tutteli.atrium.reporting.theming.text.MonospaceLengthCalculator
import ch.tutteli.atrium.reporting.theming.text.StyledString
import ch.tutteli.atrium.reporting.theming.text.replaceWrap

abstract class MonospaceCalculatorBasedStyledString(
    override val unstyled: String,
    override val maybeStyled: Option<String>,
    override val noLineBreak: Boolean,
    override val horizontalAlignment: HorizontalAlignment,
) : StyledString {
    override fun toString(): String = "SS(u=$unstyled, n=$noLineBreak, a=${horizontalAlignment.name})"

    final override fun withoutLineBreaks(): StyledString =
        if (this.noLineBreak) this
        else StyledString.createWithPredefinedMonospaceLength(
            unstyled,
            maybeStyled,
            noLineBreak = true,
            horizontalAlignment = horizontalAlignment,
            monospaceLength = monospaceLength,
            maxLineMonospaceLength = maxLineMonospaceLength,
        )

    final override fun withHorizontalAlignment(alignment: HorizontalAlignment): StyledString =
        if (horizontalAlignment == alignment) this
        else StyledString.createWithPredefinedMonospaceLength(
            unstyled,
            maybeStyled,
            noLineBreak = noLineBreak,
            horizontalAlignment = alignment,
            monospaceLength = monospaceLength,
            maxLineMonospaceLength = maxLineMonospaceLength,
        )

    final override fun getString(): String {
        val s = maybeStyled.getOrElse { unstyled }
        return replaceWrap(s)
    }
}
