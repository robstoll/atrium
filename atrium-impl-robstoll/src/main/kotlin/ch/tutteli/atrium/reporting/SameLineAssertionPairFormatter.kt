package ch.tutteli.atrium.reporting

import ch.tutteli.atrium.assertions.IBasicAssertion
import ch.tutteli.atrium.reporting.translating.ITranslatable
import ch.tutteli.atrium.reporting.translating.ITranslator

/**
 * Represents a formatter of assertion pairs -- which consists of a [ITranslatable] and a representation -- where it
 * puts them on the same line in the form: `translation: representation`.
 *
 * @property objectFormatter Used to format objects such as [IBasicAssertion.expected].
 * @property translator Used to translate [ITranslatable]s such as [IBasicAssertion.description].
 *
 * @constructor
 * @param objectFormatter Used to format objects such as [IBasicAssertion.expected].
 * @param translator Used to translate [ITranslatable]s such as [IBasicAssertion.description].
 */
internal class SameLineAssertionPairFormatter(
    private val objectFormatter: IObjectFormatter,
    private val translator: ITranslator
) : IAssertionPairFormatter {

    override fun format(methodObject: AssertionFormatterMethodObject, translatable: ITranslatable, representation: Any) {
        methodObject.sb.append(translator.translate(translatable)).append(": ").append(objectFormatter.format(representation))
    }
}
