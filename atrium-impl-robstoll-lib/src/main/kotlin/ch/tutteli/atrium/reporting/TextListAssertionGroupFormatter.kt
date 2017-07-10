package ch.tutteli.atrium.reporting

import ch.tutteli.atrium.assertions.IAssertion
import ch.tutteli.atrium.assertions.IAssertionGroup
import ch.tutteli.atrium.assertions.IInvisibleAssertionGroupType
import ch.tutteli.atrium.assertions.IListAssertionGroupType

/**
 * Represents an [IAssertionFormatter] which formats [IAssertionGroup]s with an [IListAssertionGroupType] by
 * putting each assertion on an own line prefixed with a bullet point.
 *
 * Its usage is intended for text output (e.g. to the console).
 *
 * @constructor Represents an [IAssertionFormatter] which formats [IAssertionGroup]s with an [IListAssertionGroupType]
 *              by putting each assertion on an own line prefixed with a bullet point.
 * @param assertionFormatterController The controller to which this formatter gives back the control
 *        when it comes to format children of an [IAssertionGroup].
 * @param assertionPairFormatter The formatter used to format assertion pairs.
 */
class TextListAssertionGroupFormatter(
    private val assertionFormatterController: IAssertionFormatterController,
    private val assertionPairFormatter: IAssertionPairFormatter
) : SingleAssertionGroupTypeFormatter<IListAssertionGroupType>(IListAssertionGroupType::class.java) {

    override fun formatSpecificGroup(assertionGroup: IAssertionGroup, methodObject: AssertionFormatterMethodObject, formatAssertions: ((IAssertion) -> Unit) -> Unit) {
        assertionPairFormatter.format(methodObject, assertionGroup.name, assertionGroup.subject)
        val listElement = "◾ "
        val newMethodObject = AssertionFormatterMethodObject(
            methodObject.sb,
            methodObject.indentLevel + listElement.length,
            methodObject.assertionFilter)
        formatAssertions {
            methodObject.sb.appendln()
            methodObject.indent()
            methodObject.sb.append(listElement)
            assertionFormatterController.format(it, newMethodObject)
        }
    }
}
