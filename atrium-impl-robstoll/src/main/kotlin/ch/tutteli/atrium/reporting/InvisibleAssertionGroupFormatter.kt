package ch.tutteli.atrium.reporting

import ch.tutteli.atrium.assertions.IAssertion
import ch.tutteli.atrium.assertions.IAssertionGroup
import ch.tutteli.atrium.assertions.IInvisibleAssertionGroupType

class InvisibleAssertionGroupFormatter(private val assertionFormatterController: IAssertionFormatterController) : IAssertionFormatter {
    override fun canFormat(assertion: IAssertion)
        = assertion is IAssertionGroup && assertion.type is IInvisibleAssertionGroupType

    override fun format(assertion: IAssertion, methodObject: AssertionFormatterMethodObject) = when (assertion) {
        is IAssertionGroup -> IAssertionFormatter.notIntendedForAssertionGroups()
        else -> throw UnsupportedOperationException("supports only ${IInvisibleAssertionGroupType::class.simpleName} for which one has to call ${IAssertionFormatter::formatGroup.name}")
    }

    override fun formatGroup(assertionGroup: IAssertionGroup, methodObject: AssertionFormatterMethodObject, formatAssertions: ((IAssertion) -> Unit) -> Unit) = when (assertionGroup.type) {
        is IInvisibleAssertionGroupType -> formatInvisibleGroup(methodObject, formatAssertions)
        else -> throw UnsupportedOperationException("supports only ${IInvisibleAssertionGroupType::class.simpleName}")
    }

    fun formatInvisibleGroup(methodObject: AssertionFormatterMethodObject, formatAssertions: ((IAssertion) -> Unit) -> Unit) {
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
