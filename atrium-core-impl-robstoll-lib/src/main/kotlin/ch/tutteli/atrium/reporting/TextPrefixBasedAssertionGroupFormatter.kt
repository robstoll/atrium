package ch.tutteli.atrium.reporting

import ch.tutteli.atrium.assertions.IAssertion
import ch.tutteli.atrium.assertions.IAssertionGroup

class TextPrefixBasedAssertionGroupFormatter(
    private val prefix: String,
    private val assertionFormatterController: IAssertionFormatterController
) {
    fun formatWithGroupName(assertionPairFormatter: IAssertionPairFormatter, assertionGroup: IAssertionGroup, methodObject: AssertionFormatterMethodObject, formatAssertions: ((IAssertion) -> Unit) -> Unit) {
        assertionPairFormatter.format(methodObject, assertionGroup.name, assertionGroup.subject)
        val childMethodObject = methodObject.createChildWithNewPrefix(prefix)
        format(childMethodObject, formatAssertions)
    }

    fun format(childMethodObject: AssertionFormatterMethodObject, formatAssertions: ((IAssertion) -> Unit) -> Unit) {
        formatAssertions {
            childMethodObject.sb.appendln()
            childMethodObject.indent()
            childMethodObject.sb.append(prefix)
            assertionFormatterController.format(it, childMethodObject)
        }
    }
}
