package ch.tutteli.atrium.reporting.theming.text.impl

import ch.tutteli.atrium.core.Option
import ch.tutteli.atrium.reporting.HorizontalAlignment
import ch.tutteli.atrium.reporting.theming.text.MonospaceLengthCalculator

internal class PrecalculatedMonospaceStyledString(
    unstyled: String,
    maybeStyled: Option<String>,
    noLineBreak: Boolean,
    horizontalAlignment: HorizontalAlignment,
    override val monospaceLength: Int,
    override val maxLineMonospaceLength: Int,
) : MonospaceCalculatorBasedStyledString(
    unstyled,
    maybeStyled,
    noLineBreak,
    horizontalAlignment
)
