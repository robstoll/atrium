package ch.tutteli.atrium.reporting.text

import ch.tutteli.atrium.reporting.AssertionPairFormatter
import ch.tutteli.atrium.reporting.ObjectFormatter
import ch.tutteli.atrium.reporting.text.impl.TextNextLineAssertionPairFormatter
import ch.tutteli.atrium.reporting.text.impl.TextSameLineAssertionPairFormatter
import ch.tutteli.atrium.reporting.translating.Translator

/**
 * Marker interface for [AssertionPairFormatter] which are intended for text output, e.g. for terminal output.
 */
interface TextAssertionPairFormatter : AssertionPairFormatter {
    companion object {
        fun newSameLine(objectFormatter: ObjectFormatter, translator: Translator): TextAssertionPairFormatter =
            TextSameLineAssertionPairFormatter(objectFormatter, translator)

        fun newNextLine(objectFormatter: ObjectFormatter, translator: Translator): TextAssertionPairFormatter =
            TextNextLineAssertionPairFormatter(objectFormatter, translator)
    }
}
