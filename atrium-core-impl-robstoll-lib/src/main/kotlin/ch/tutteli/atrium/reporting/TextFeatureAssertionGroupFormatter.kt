package ch.tutteli.atrium.reporting

import ch.tutteli.atrium.assertions.IAssertion
import ch.tutteli.atrium.assertions.IAssertionGroup
import ch.tutteli.atrium.assertions.IFeatureAssertionGroupType
import ch.tutteli.atrium.assertions.IListAssertionGroupType
import ch.tutteli.atrium.reporting.translating.TranslatableWithArgs
import ch.tutteli.atrium.reporting.translating.Untranslatable

/**
 * Represents an [IAssertionFormatter] which formats [IAssertionGroup]s with an [IListAssertionGroupType] by
 * putting each assertion on an own line prefixed with a bullet point.
 *
 * Its usage is intended for text output (e.g. to the console).
 *
 * @constructor Represents an [IAssertionFormatter] which formats [IAssertionGroup]s with an [IListAssertionGroupType]
 *              by putting each assertion on an own line prefixed with a bullet point.
 * @param arrow The symbol used to prefix a feature name (to mark the beginning of a feature assertion).
 * @param bulletPoint The bullet point used to prefix each made assertion about the feature.
 * @param assertionFormatterController The controller to which this formatter gives back the control
 *        when it comes to format children of an [IAssertionGroup].
 * @param assertionPairFormatter The formatter used to format assertion pairs.
 */
class TextFeatureAssertionGroupFormatter(
    private val arrow: String,
    bulletPoint: String,
    assertionFormatterController: IAssertionFormatterController,
    private val assertionPairFormatter: IAssertionPairFormatter
) : SingleAssertionGroupTypeFormatter<IFeatureAssertionGroupType>(IFeatureAssertionGroupType::class.java) {
    private val prefix = "$bulletPoint "
    private val formatter = TextPrefixBasedAssertionGroupFormatter(prefix, assertionFormatterController)

    override fun formatSpecificGroup(assertionGroup: IAssertionGroup, methodObject: AssertionFormatterMethodObject, formatAssertions: ((IAssertion) -> Unit) -> Unit) {
        methodObject.sb.appendln()
        methodObject.indent()
        methodObject.sb.append(methodObject.prefix)
        val translatable = TranslatableWithArgs(Untranslatable("$arrow %s"), assertionGroup.name)
        assertionPairFormatter.format(methodObject, translatable, assertionGroup.subject)
        val childMethodObject = methodObject.createChildWithNewPrefixAndAdditionalIndent(prefix, arrow.length + 1)
        formatter.format(childMethodObject, formatAssertions)
    }
}
