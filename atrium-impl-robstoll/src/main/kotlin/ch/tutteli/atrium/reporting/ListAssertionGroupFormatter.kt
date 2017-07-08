package ch.tutteli.atrium.reporting

import ch.tutteli.atrium.assertions.IAssertion
import ch.tutteli.atrium.assertions.IAssertionGroup
import ch.tutteli.atrium.assertions.IListAssertionGroupType

class ListAssertionGroupFormatter(
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
