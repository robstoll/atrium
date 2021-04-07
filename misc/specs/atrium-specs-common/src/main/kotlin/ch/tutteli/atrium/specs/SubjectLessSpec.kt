package ch.tutteli.atrium.specs

import ch.tutteli.atrium.api.fluent.en_GB.all
import ch.tutteli.atrium.api.fluent.en_GB.feature
import ch.tutteli.atrium.api.fluent.en_GB.toEqual
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.assertions.ExplanatoryAssertionGroupType
import ch.tutteli.atrium.assertions.builders.assertionBuilder
import ch.tutteli.atrium.core.ExperimentalNewExpectTypes
import ch.tutteli.atrium.core.None
import ch.tutteli.atrium.creating.CollectingExpect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.creating.ExperimentalComponentFactoryContainer
import ch.tutteli.atrium.logic._logic
import ch.tutteli.atrium.logic.creating.RootExpectBuilder
import ch.tutteli.atrium.reporting.AtriumErrorAdjuster
import ch.tutteli.atrium.reporting.erroradjusters.NoOpAtriumErrorAdjuster
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

abstract class SubjectLessSpec<T>(
    groupPrefix: String,
    vararg assertionCreator: Pair<String, Expect<T>.() -> Unit>
) : Spek({

    describe("${groupPrefix}assertion function can be used in an ${AssertionGroup::class.simpleName} with an ${ExplanatoryAssertionGroupType::class.simpleName} and report without failure") {
        assertionCreator.forEach { (name, createAssertion) ->
            it("fun `$name`") {
                @Suppress("DEPRECATION" /* OptIn is only available since 1.3.70 which we cannot use if we want to support 1.2 */)
                @UseExperimental(ExperimentalNewExpectTypes::class, ExperimentalComponentFactoryContainer::class)
                val assertions = CollectingExpect<T>(None, expect(1)._logic.components)
                    .addAssertionsCreatedBy(createAssertion)
                    .getAssertions()

                expandAssertionGroups(assertions)


                @Suppress("DEPRECATION" /* OptIn is only available since 1.3.70 which we cannot use if we want to support 1.2 */)
                @UseExperimental(ExperimentalNewExpectTypes::class, ExperimentalComponentFactoryContainer::class)
                val expect = RootExpectBuilder.forSubject(1.0)
                    .withVerb("custom assertion verb")
                    .withOptions {
                        withComponent(AtriumErrorAdjuster::class) { _ -> NoOpAtriumErrorAdjuster }
                    }
                    .build()

                val explanatoryGroup = assertionBuilder.explanatoryGroup
                    .withDefaultType
                    .withAssertions(assertions)
                    .build()
                expect._logic.appendAssertion(explanatoryGroup)
            }
        }
    }

    describe("${groupPrefix}assertion function does not hold if there is no subject") {
        assertionCreator.forEach { (name, createAssertion) ->
            it("fun `$name`") {
                @Suppress("DEPRECATION" /* OptIn is only available since 1.3.70 which we cannot use if we want to support 1.2 */)
                @UseExperimental(ExperimentalNewExpectTypes::class, ExperimentalComponentFactoryContainer::class)
                val assertions = CollectingExpect<T>(None, expect(1)._logic.components)
                    .addAssertionsCreatedBy(createAssertion)
                    .getAssertions()
                expect(assertions).all { feature(Assertion::holds).toEqual(false) }
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
