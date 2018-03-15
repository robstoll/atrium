package ch.tutteli.atrium.core.robstoll.lib.reporting

import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.assertions.DescriptiveAssertion
import ch.tutteli.atrium.reporting.AssertionFormatterParameterObject
import ch.tutteli.atrium.reporting.AssertionPairFormatter
import ch.tutteli.atrium.reporting.ObjectFormatter
import ch.tutteli.atrium.reporting.RawString
import ch.tutteli.atrium.reporting.translating.Translatable
import ch.tutteli.atrium.reporting.translating.Translator

/**
 * Represents an [AssertionPairFormatter] formatter of assertion pairs -- which consists of a [Translatable]
 * and a representation -- where it puts the translation on one line and the representation on the next line
 * (including indentation as if the representation is a child).
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
class TextNextLineAssertionPairFormatter(
    private val objectFormatter: ObjectFormatter,
    private val translator: Translator
) : AssertionPairFormatter {

    override fun formatGroupHeader(parameterObject: AssertionFormatterParameterObject, assertionGroup: AssertionGroup, newParameterObject: AssertionFormatterParameterObject)
        = format(parameterObject, assertionGroup.name, assertionGroup.subject, newParameterObject)

    override fun format(parameterObject: AssertionFormatterParameterObject, translatable: Translatable, representation: Any)
        = format(parameterObject, translatable, representation, parameterObject)

    private fun format(parameterObject: AssertionFormatterParameterObject, translatable: Translatable, representation: Any, newParameterObject: AssertionFormatterParameterObject) {
        parameterObject.sb.append(translator.translate(translatable)).append(":")
        if (representation !is RawString || representation != RawString.EMPTY) {
            newParameterObject.appendLnAndIndent()
            newParameterObject.indent(newParameterObject.prefix.length)
            parameterObject.sb.append(objectFormatter.format(representation))
        }
    }
}
