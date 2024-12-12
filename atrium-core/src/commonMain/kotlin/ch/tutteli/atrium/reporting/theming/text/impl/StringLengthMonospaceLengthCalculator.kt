package ch.tutteli.atrium.reporting.theming.text.impl

import ch.tutteli.atrium.reporting.theming.text.MonospaceLengthCalculator

object StringLengthMonospaceLengthCalculator : MonospaceLengthCalculator {
    override fun calculate(string: String): Int = string.length
}
