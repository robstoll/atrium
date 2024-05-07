package ch.tutteli.atrium.reporting.text.impl

import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.assertions.DescriptiveAssertion
import ch.tutteli.atrium.reporting.AssertionFormatterParameterObject
import ch.tutteli.atrium.reporting.AssertionPairFormatter
import ch.tutteli.atrium.reporting.ObjectFormatter
import ch.tutteli.atrium.reporting.text.TextAssertionPairFormatter

/**
 * Represents an [AssertionPairFormatter] formatter of assertion pairs -- which consists of a [ch.tutteli.atrium.reporting.translating.Translatable]
 * and a representation -- where it puts them on the same line in the form: `translation: representation`.
 *
 * Its usage is intended for text output (e.g. to the console).
 *
 * @property objectFormatter Used to format objects such as [DescriptiveAssertion.representation].
 *
 * @constructor Represents an [AssertionPairFormatter] formatter of assertion pairs -- which consists of a
 *   [ch.tutteli.atrium.reporting.translating.Translatable] and a representation -- where it puts them on the same line in the form:
 *   `translation: representation`.
 * @param objectFormatter Used to format objects such as [DescriptiveAssertion.representation].
 */
class TextSameLineAssertionPairFormatter(
    private val objectFormatter: ObjectFormatter,
) : AssertionPairFormatter, TextAssertionPairFormatter {

    override fun formatGroupHeader(
        parameterObject: AssertionFormatterParameterObject,
        assertionGroup: AssertionGroup,
        newParameterObject: AssertionFormatterParameterObject
    ): Unit = format(parameterObject, assertionGroup.description.getDefault(), assertionGroup.representation)

    override fun format(
        parameterObject: AssertionFormatterParameterObject,
        description: String,
        representation: Any
    ) {
        parameterObject.sb.append(description).append(": ")
            .append(objectFormatter.format(representation))
    }
}
