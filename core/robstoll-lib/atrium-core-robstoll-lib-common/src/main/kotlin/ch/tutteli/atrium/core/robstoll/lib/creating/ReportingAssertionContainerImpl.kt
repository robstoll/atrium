@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.core.robstoll.lib.creating

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.builders.assertionBuilder
import ch.tutteli.atrium.assertions.builders.invisibleGroup
import ch.tutteli.atrium.checking.AssertionChecker
import ch.tutteli.atrium.core.coreFactory
import ch.tutteli.atrium.creating.*

@Deprecated("Use RootExpect, will be removed with 1.0.0")
class ReportingAssertionContainerImpl<T>(
    private val assertionCheckerDecorator: ReportingAssertionContainer.AssertionCheckerDecorator<T>
) : MutableListBasedAssertionContainer<T>(assertionCheckerDecorator.maybeSubject),
    ReportingAssertionContainer<T> {

    override val assertionChecker: AssertionChecker get() = assertionCheckerDecorator.assertionChecker

    override fun addAssertionsCreatedBy(assertionCreator: Expect<T>.() -> Unit): Expect<T> {
        val assertions = coreFactory.newCollectingAssertionContainer(maybeSubject)
            .addAssertionsCreatedBy(assertionCreator)
            .getAssertions()
        return when (assertions.size) {
            0 -> this
            1 -> addAssertion(assertions.first())
            else -> addAssertion(assertionBuilder.invisibleGroup.withAssertions(assertions).build())
        }
    }

    override fun addAssertion(assertion: Assertion): Expect<T> {
        super.addAssertion(assertion)
        checkAndClearAssertions()
        return this
    }

    private fun checkAndClearAssertions(): Expect<T> {
        try {
            assertionCheckerDecorator.check(getCopyOfAssertions())
        } finally {
            clearAssertions()
        }
        return this
    }
}
