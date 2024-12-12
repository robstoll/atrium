package ch.tutteli.atrium.reporting.theming.text.impl

import ch.tutteli.atrium.core.Option
import ch.tutteli.atrium.reporting.HorizontalAlignment
import ch.tutteli.atrium.reporting.theming.text.MonospaceLengthCalculator
import ch.tutteli.kbox.failIf

internal class CalculatedMonospaceStyledString(
    private val monospaceLengthCalculator: MonospaceLengthCalculator,
    unstyled: String,
    maybeStyled: Option<String>,
    noLineBreak: Boolean,
    horizontalAlignment: HorizontalAlignment,
) : MonospaceCalculatorBasedStyledString(
    unstyled,
    maybeStyled,
    noLineBreak,
    horizontalAlignment
) {

    override val monospaceLength: Int by lazy {
        monospaceLengthCalculator.calculate(unstyled)
    }
    override val maxLineMonospaceLength: Int by lazy {
        if (noLineBreak) monospaceLength
        else unstyled.split("\n").maxOf { line -> monospaceLengthCalculator.calculate(line) }
    }
}
