package ch.tutteli.atrium.reporting

import ch.tutteli.atrium.assertions.IAssertion
import ch.tutteli.atrium.assertions.IAssertionGroup
import ch.tutteli.atrium.assertions.IAssertionGroupType

abstract class SingleAssertionGroupTypeFormatter<T : IAssertionGroupType>(
    private val clazz: Class<T>
) : IAssertionFormatter {

    override final fun canFormat(assertion: IAssertion)
        = assertion is IAssertionGroup && clazz.isAssignableFrom(assertion.type::class.java)

    override final fun format(assertion: IAssertion, methodObject: AssertionFormatterMethodObject) = when (assertion) {
        is IAssertionGroup -> IAssertionFormatter.throwNotIntendedForAssertionGroups()
        else -> throw UnsupportedOperationException("supports only ${clazz.name} for which one has to call ${IAssertionFormatter::formatGroup.name}")
    }

    override final fun formatGroup(assertionGroup: IAssertionGroup, methodObject: AssertionFormatterMethodObject, formatAssertions: ((IAssertion) -> Unit) -> Unit) = when {
        clazz.isAssignableFrom(assertionGroup.type::class.java) -> formatSpecificGroup(assertionGroup, methodObject, formatAssertions)
        else -> throw UnsupportedOperationException("supports only ${clazz.name}")
    }

    abstract fun formatSpecificGroup(assertionGroup: IAssertionGroup, methodObject: AssertionFormatterMethodObject, formatAssertions: ((IAssertion) -> Unit) -> Unit): Unit

}
