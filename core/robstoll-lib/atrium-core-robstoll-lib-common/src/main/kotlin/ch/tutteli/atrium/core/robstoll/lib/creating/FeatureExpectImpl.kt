package ch.tutteli.atrium.core.robstoll.lib.creating

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.builders.assertionBuilder
import ch.tutteli.atrium.assertions.builders.invisibleGroup
import ch.tutteli.atrium.checking.AssertionChecker
import ch.tutteli.atrium.core.Option
import ch.tutteli.atrium.core.coreFactory
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.creating.ExperimentalExpectConfig
import ch.tutteli.atrium.creating.FeatureExpect
import ch.tutteli.atrium.creating.FeatureExpectConfig

class FeatureExpectImpl<T, R>(
    override val previousExpect: Expect<T>,
    override val maybeSubject: Option<R>,
    @Suppress("DEPRECATION" /* OptIn is only available since 1.3.70 which we cannot use if we want to support 1.2 */)
    @UseExperimental(ExperimentalExpectConfig::class)
    override val config: FeatureExpectConfig,
    private val assertionChecker: AssertionChecker,
    assertions: List<Assertion>
) : MutableListBasedAssertionContainer<R>(maybeSubject),
    FeatureExpect<T, R> {

    init {
        addAssertions(assertions)
    }

    //TODO duplication of ReportingAssertionPlantImpl
    override fun addAssertionsCreatedBy(assertionCreator: Expect<R>.() -> Unit): Expect<R> {
        val assertions = coreFactory.newCollectingAssertionContainer(maybeSubject)
            .addAssertionsCreatedBy(assertionCreator)
            .getAssertions()
        return addAssertions(assertions)
    }

    private fun addAssertions(assertions: List<Assertion>) =
        when (assertions.size) {
            0 -> this
            1 -> addAssertion(assertions.first())
            else -> addAssertion(assertionBuilder.invisibleGroup.withAssertions(assertions).build())
        }

    override fun addAssertion(assertion: Assertion): Expect<R> {
        super.addAssertion(assertion)
        checkAndClearAssertions()
        return this
    }

    @Suppress("DEPRECATION" /* OptIn is only available since 1.3.70 which we cannot use if we want to support 1.2 */)
    @UseExperimental(ExperimentalExpectConfig::class)
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
