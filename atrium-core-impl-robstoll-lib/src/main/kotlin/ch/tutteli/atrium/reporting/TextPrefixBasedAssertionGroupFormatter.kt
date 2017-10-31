package ch.tutteli.atrium.reporting

import ch.tutteli.atrium.assertions.IAssertion
import ch.tutteli.atrium.assertions.IAssertionGroup

class TextPrefixBasedAssertionGroupFormatter(
    private val prefix: String,
    private val assertionFormatterController: IAssertionFormatterController
) {
    fun formatWithGroupName(assertionPairFormatter: IAssertionPairFormatter, assertionGroup: IAssertionGroup, methodObject: AssertionFormatterMethodObject, formatAssertions: ((IAssertion) -> Unit) -> Unit) {
        methodObject.sb.appendln()
        methodObject.indent()
        methodObject.sb.append(methodObject.prefix)
        formatAfterAppendLnEtc(assertionPairFormatter, assertionGroup, methodObject, formatAssertions)
    }

    fun formatAfterAppendLnEtc(assertionPairFormatter: IAssertionPairFormatter, assertionGroup: IAssertionGroup, methodObject: AssertionFormatterMethodObject, formatAssertions: ((IAssertion) -> Unit) -> Unit) {
        assertionPairFormatter.format(methodObject, assertionGroup.name, assertionGroup.subject)
        val childMethodObject = methodObject.createChildWithNewPrefix(prefix)
        format(childMethodObject, formatAssertions)
    }

    fun format(childMethodObject: AssertionFormatterMethodObject, formatAssertions: ((IAssertion) -> Unit) -> Unit) {
        formatAssertions {
            assertionFormatterController.format(it, childMethodObject)
        }
    }
}
