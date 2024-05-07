package ch.tutteli.atrium.reporting.text

import ch.tutteli.atrium.reporting.AssertionPairFormatter
import ch.tutteli.atrium.reporting.ObjectFormatter
import ch.tutteli.atrium.reporting.text.impl.TextNextLineAssertionPairFormatter
import ch.tutteli.atrium.reporting.text.impl.TextSameLineAssertionPairFormatter

/**
 * Marker interface for [AssertionPairFormatter] which are intended for text output, e.g. for terminal output.
 */
interface TextAssertionPairFormatter : AssertionPairFormatter {
    companion object {
        fun newSameLine(objectFormatter: ObjectFormatter) : TextAssertionPairFormatter =
            TextSameLineAssertionPairFormatter(objectFormatter)

        @Suppress("DeprecatedCallableAddReplaceWith", "DEPRECATION")
        @Deprecated("passed translator is ignored, use the other overload without Translator instead, Translation support was dropped with Atrium 1.3.0 and related classes will be removed with 2.0.0 at the latest")
        fun newSameLine(objectFormatter: ObjectFormatter, @Suppress("UNUSED_PARAMETER") translator: ch.tutteli.atrium.reporting.translating.Translator): TextAssertionPairFormatter =
            newSameLine(objectFormatter)

        fun newNextLine(objectFormatter: ObjectFormatter): TextAssertionPairFormatter =
            TextNextLineAssertionPairFormatter(objectFormatter)

        @Suppress("DeprecatedCallableAddReplaceWith", "DEPRECATION")
        @Deprecated("passed translator is ignored, use the other overload without Translator instead, Translation support was dropped with Atrium 1.3.0 and related classes will be removed with 2.0.0 at the latest")
        fun newNextLine(objectFormatter: ObjectFormatter, @Suppress("UNUSED_PARAMETER") translator: ch.tutteli.atrium.reporting.translating.Translator): TextAssertionPairFormatter =
            newNextLine(objectFormatter)
    }
}
