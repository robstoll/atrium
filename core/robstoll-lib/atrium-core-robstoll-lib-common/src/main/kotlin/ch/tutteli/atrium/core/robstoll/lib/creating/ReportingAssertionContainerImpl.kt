package ch.tutteli.atrium.core.robstoll.lib.creating

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.builders.assertionBuilder
import ch.tutteli.atrium.assertions.builders.invisibleGroup
import ch.tutteli.atrium.checking.AssertionChecker
import ch.tutteli.atrium.core.coreFactory
import ch.tutteli.atrium.creating.*

class ReportingAssertionContainerImpl<T>(
    private val assertionCheckerDecorator: ReportingAssertionContainer.AssertionCheckerDecorator<T>
) : MutableListBasedAssertionContainer<T>(assertionCheckerDecorator.maybeSubject),
    ReportingAssertionContainer<T> {

    @UseExperimental(ExperimentalExpectConfig::class)
    override val config: RootExpectConfig =
        RootExpectConfig.create(
            assertionCheckerDecorator.assertionVerb,
            assertionCheckerDecorator.representation
        )

    override val assertionChecker: AssertionChecker get() = assertionCheckerDecorator.assertionChecker

    override fun addAssertionsCreatedBy(assertionCreator: Expect<T>.() -> Unit): Expect<T> {
        val assertions = coreFactory.newCollectingAssertionContainer(maybeSubject)
            .addAssertionsCreatedBy(assertionCreator)
            .getAssertions()
        //TODO I think we could reduce nesting for cases where we do not have an assertion group and
        // in case it is already an invisible group (in such cases we don't have to wrap it again)
        return addAssertion(assertionBuilder.invisibleGroup.withAssertions(assertions).build())
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
