package ch.tutteli.atrium.core.robstoll.lib.creating

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.builders.assertionBuilder
import ch.tutteli.atrium.assertions.builders.invisibleGroup
import ch.tutteli.atrium.core.coreFactory
import ch.tutteli.atrium.creating.AssertionContainerWithCommonFields
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.creating.ReportingAssertionContainer

class ReportingAssertionContainerImpl<T>(
    override val commonFields: AssertionContainerWithCommonFields.CommonFields<T>
) : MutableListBasedAssertionContainer<T>(commonFields.maybeSubject),
    ReportingAssertionContainer<T> {

    override fun addAssertion(assertion: Assertion): Expect<T> {
        super.addAssertion(assertion)
        checkAssertions()
        return this
    }

    private fun checkAssertions(): Expect<T> {
        try {
            commonFields.check(getCopyOfAssertions())
        } finally {
            clearAssertions()
        }
        return this
    }

    override fun addAssertionsCreatedBy(assertionCreator: Expect<T>.() -> Unit): Expect<T> {
        val assertions = coreFactory.newCollectingAssertionContainer(maybeSubject)
            .addAssertionsCreatedBy(assertionCreator)
            .getAssertions()
        //TODO I think we could reduce nesting for cases where we do not have an assertion group and
        // in case it is already an invisible group (in such cases we don't have to wrap it again)
        return addAssertion(assertionBuilder.invisibleGroup.withAssertions(assertions).build())
    }
}
