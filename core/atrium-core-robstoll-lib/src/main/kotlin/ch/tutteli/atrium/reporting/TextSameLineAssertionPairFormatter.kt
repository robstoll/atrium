package ch.tutteli.atrium.reporting

import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.assertions.DescriptiveAssertion
import ch.tutteli.atrium.reporting.translating.Translatable
import ch.tutteli.atrium.reporting.translating.Translator

/**
 * Represents an [AssertionPairFormatter] formatter of assertion pairs -- which consists of a [Translatable]
 * and a representation -- where it puts them on the same line in the form: `translation: representation`.
 *
 * Its usage is intended for text output (e.g. to the console).
 *
 * @property objectFormatter Used to format objects such as [DescriptiveAssertion.representation].
 * @property translator Used to translate [Translatable]s such as [DescriptiveAssertion.description].
 *
 * @constructor Represents an [AssertionPairFormatter] formatter of assertion pairs -- which consists of a
 *   [Translatable] and a representation -- where it puts them on the same line in the form:
 *   `translation: representation`.
 * @param objectFormatter Used to format objects such as [DescriptiveAssertion.representation].
 * @param translator Used to translate [Translatable]s such as [DescriptiveAssertion.description].
 */
class TextSameLineAssertionPairFormatter(
    private val objectFormatter: ObjectFormatter,
    private val translator: Translator
) : AssertionPairFormatter {

    override fun formatGroupHeader(parameterObject: AssertionFormatterParameterObject, assertionGroup: AssertionGroup, newParameterObject: AssertionFormatterParameterObject)
        = format(parameterObject, assertionGroup.name, assertionGroup.subject)

    override fun format(parameterObject: AssertionFormatterParameterObject, translatable: Translatable, representation: Any) {
        parameterObject.sb.append(translator.translate(translatable)).append(": ").append(objectFormatter.format(representation))
    }
}
