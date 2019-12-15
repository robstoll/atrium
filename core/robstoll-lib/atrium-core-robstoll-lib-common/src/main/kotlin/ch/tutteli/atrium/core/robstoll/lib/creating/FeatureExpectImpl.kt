package ch.tutteli.atrium.core.robstoll.lib.creating

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.builders.assertionBuilder
import ch.tutteli.atrium.assertions.builders.invisibleGroup
import ch.tutteli.atrium.checking.AssertionChecker
import ch.tutteli.atrium.core.Option
import ch.tutteli.atrium.core.coreFactory
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.creating.FeatureExpect
import ch.tutteli.atrium.creating.FeatureExpectConfig

class FeatureExpectImpl<T, R>(
    override val previousExpect: Expect<T>,
    override val maybeSubject: Option<R>,
    override val config: FeatureExpectConfig,
    private val assertionChecker: AssertionChecker,
    assertions: List<Assertion>
) : MutableListBasedAssertionContainer<R>(maybeSubject),
    FeatureExpect<T, R> {

    init {
        if (assertions.isNotEmpty()) {
            addAssertions(assertions)
        }
    }

    override fun addAssertionsCreatedBy(assertionCreator: Expect<R>.() -> Unit): Expect<R> {
        val assertions = coreFactory.newCollectingAssertionContainer(maybeSubject)
            .addAssertionsCreatedBy(assertionCreator)
            .getAssertions()
        //TODO I think we could reduce nesting for cases where we do not have an assertion group and
        // in case it is already an invisible group (in such cases we don't have to wrap it again)
        return addAssertions(assertions)
    }

    private fun addAssertions(assertions: List<Assertion>) =
        addAssertion(assertionBuilder.invisibleGroup.withAssertions(assertions).build())

    override fun addAssertion(assertion: Assertion): Expect<R> {
        super.addAssertion(assertion)
        checkAndClearAssertions()
        return this
    }

    private fun checkAndClearAssertions(): Expect<R> {
        try {
            // TODO roadmap#11 we don't have to add the assertions every time. Instead keep them (don't clear) and
            // only add if the assertions do not hold (will not work once we want HTML-reporting but that's fine)
            assertionChecker.check(config.description, config.representation, getCopyOfAssertions())
        } finally {
            clearAssertions()
        }
        return this
    }

    override fun getAssertions(): List<Assertion> = getCopyOfAssertions()
}
