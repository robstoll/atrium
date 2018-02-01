package ch.tutteli.atrium.reporting

import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.assertions.DescriptiveAssertion
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

    override fun formatGroupHeader(methodObject: AssertionFormatterMethodObject, assertionGroup: AssertionGroup, newMethodObject: AssertionFormatterMethodObject)
        = format(methodObject, assertionGroup.name, assertionGroup.subject, newMethodObject)

    override fun format(methodObject: AssertionFormatterMethodObject, translatable: Translatable, representation: Any)
        = format(methodObject, translatable, representation, methodObject)

    private fun format(methodObject: AssertionFormatterMethodObject, translatable: Translatable, representation: Any, newMethodObject: AssertionFormatterMethodObject) {
        methodObject.sb.append(translator.translate(translatable)).append(":")
        if (representation !is RawString || representation != RawString.EMPTY) {
            newMethodObject.appendLnAndIndent()
            newMethodObject.indent(newMethodObject.prefix.length)
            methodObject.sb.append(objectFormatter.format(representation))
        }
    }
}
