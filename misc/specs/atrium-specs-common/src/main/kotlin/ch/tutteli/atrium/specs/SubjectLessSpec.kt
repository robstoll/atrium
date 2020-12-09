package ch.tutteli.atrium.specs

import ch.tutteli.atrium.api.fluent.en_GB.all
import ch.tutteli.atrium.api.fluent.en_GB.feature
import ch.tutteli.atrium.api.fluent.en_GB.toBe
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.assertions.ExplanatoryAssertionGroupType
import ch.tutteli.atrium.assertions.builders.assertionBuilder
import ch.tutteli.atrium.core.None
import ch.tutteli.atrium.core.coreFactory
import ch.tutteli.atrium.creating.CollectingExpect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.builders.reporting.ExpectBuilder
import ch.tutteli.atrium.domain.builders.reporting.ExpectOptions
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

abstract class SubjectLessSpec<T>(
    groupPrefix: String,
    vararg assertionCreator: Pair<String, Expect<T>.() -> Unit>
) : Spek({

    describe("${groupPrefix}assertion function can be used in an ${AssertionGroup::class.simpleName} with an ${ExplanatoryAssertionGroupType::class.simpleName} and report without failure") {
        assertionCreator.forEach { (name, createAssertion) ->
            it("fun `$name`") {
                val assertions = CollectingExpect<T>(None)
                    .addAssertionsCreatedBy(createAssertion)
                    .getAssertions()

                expandAssertionGroups(assertions)

                val container = ExpectBuilder.forSubject(1.0)
                    .withVerb("custom assertion verb")
                    .withOptions(
                        ExpectOptions(
                            reporter = coreFactory.newOnlyFailureReporter(
                                coreFactory.newAssertionFormatterFacade(coreFactory.newAssertionFormatterController()),
                                coreFactory.newNoOpAtriumErrorAdjuster()
                            )
                        )
                    )
                    .build()

                val explanatoryGroup = assertionBuilder.explanatoryGroup
                    .withDefaultType
                    .withAssertions(assertions)
                    .build()
                container.addAssertion(explanatoryGroup)
            }
        }
    }

    describe("${groupPrefix}assertion function does not hold if there is no subject") {
        assertionCreator.forEach { (name, createAssertion) ->
            it("fun `$name`") {
                val assertions = CollectingExpect<T>(None)
                    .addAssertionsCreatedBy(createAssertion)
                    .getAssertions()
                expect(assertions).all { feature(Assertion::holds).toBe(false) }
            }
        }
    }

}) {
    companion object {
        /**
         * Calls recursively [AssertionGroup.assertions] on every assertion group contained in [assertions].
         */
        private tailrec fun expandAssertionGroups(assertions: List<Assertion>) {
            if (assertions.isEmpty()) return

            expandAssertionGroups(
                assertions
                    .asSequence()
                    .filterIsInstance<AssertionGroup>()
                    .flatMap { it.assertions.asSequence() }
                    .toList()
            )
        }
    }
}
