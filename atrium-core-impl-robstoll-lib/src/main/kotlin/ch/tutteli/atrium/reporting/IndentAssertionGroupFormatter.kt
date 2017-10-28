package ch.tutteli.atrium.reporting

import ch.tutteli.atrium.assertions.IAssertion
import ch.tutteli.atrium.assertions.IAssertionGroup
import ch.tutteli.atrium.assertions.IIndentAssertionGroupType

/**
 * Represents an [IAssertionFormatter] which formats [IAssertionGroup]s with an [IIndentAssertionGroupType] by adding
 * an extra indent to the [assertions][IAssertionGroup.assertions] starting from [IIndentAssertionGroupType.indentIndex]
 * and prefix them with the given [bulletPoint].
 *
 * @constructor Represents an [IAssertionFormatter] which formats [IAssertionGroup]s with an [IIndentAssertionGroupType]
 *              by adding an extra indent to the [assertions][IAssertionGroup.assertions] starting from
 *              [IIndentAssertionGroupType.indentIndex] and prefix them with the given [bulletPoint].
 * @param bulletPoint The bullet point (might also be more than one character) which shall be used.
 * @param assertionFormatterController The controller to which this formatter gives back the control
 *        when it comes to format children of an [IAssertionGroup].
 */
class IndentAssertionGroupFormatter(
    private val bulletPoint: String,
    private val assertionFormatterController: IAssertionFormatterController
) : SingleAssertionGroupTypeFormatter<IIndentAssertionGroupType>(IIndentAssertionGroupType::class.java) {

    override fun formatSpecificGroup(assertionGroup: IAssertionGroup, methodObject: AssertionFormatterMethodObject, formatAssertions: ((IAssertion) -> Unit) -> Unit) {
        val groupType = assertionGroup.type as IIndentAssertionGroupType
        val childMethodObject = methodObject.createChildWithNewPrefix(" $bulletPoint ")
        var count = 0
        formatAssertions {
            if (count >= groupType.indentIndex) {
                childMethodObject.sb.appendln()
                childMethodObject.indent()
                childMethodObject.sb.append(childMethodObject.prefix)
            } else if (count > 0) {
                //behaves like an InvisibleAssertionGroupFormatter, formatting based on the current methodObject
                methodObject.sb.appendln()
                methodObject.indent()
                methodObject.sb.append(methodObject.prefix)
            }
            ++count
            assertionFormatterController.format(it, childMethodObject)
        }
    }

}
