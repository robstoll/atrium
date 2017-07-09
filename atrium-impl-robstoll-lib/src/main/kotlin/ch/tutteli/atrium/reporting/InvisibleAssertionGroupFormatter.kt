package ch.tutteli.atrium.reporting

import ch.tutteli.atrium.assertions.IAssertion
import ch.tutteli.atrium.assertions.IAssertionGroup
import ch.tutteli.atrium.assertions.IInvisibleAssertionGroupType

/**
 * Represents an [IAssertionFormatter] which formats [IAssertionGroup]s with an [IInvisibleAssertionGroupType].
 */
class InvisibleAssertionGroupFormatter(
    private val assertionFormatterController: IAssertionFormatterController
) : SingleAssertionGroupTypeFormatter<IInvisibleAssertionGroupType>(IInvisibleAssertionGroupType::class.java) {

    override fun formatSpecificGroup(assertionGroup: IAssertionGroup, methodObject: AssertionFormatterMethodObject, formatAssertions: ((IAssertion) -> Unit) -> Unit) {
        var isNotFirst = false
        formatAssertions {
            if (isNotFirst) {
                methodObject.sb.appendln()
                methodObject.indent()
            }
            isNotFirst = true
            assertionFormatterController.format(it, methodObject)
        }
    }
}
