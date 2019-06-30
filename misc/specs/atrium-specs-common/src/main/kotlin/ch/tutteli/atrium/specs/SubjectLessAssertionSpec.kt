package ch.tutteli.atrium.specs

import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.assertions.ExplanatoryAssertionGroupType
import ch.tutteli.atrium.core.None
import ch.tutteli.atrium.core.coreFactory
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.builders.AssertImpl
import ch.tutteli.atrium.reporting.translating.Untranslatable
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

abstract class SubjectLessAssertionSpec<T>(
    groupPrefix: String,
    vararg assertionCreator: Pair<String, Expect<T>.() -> Unit>
) : Spek({

    describe("${groupPrefix}assertion function can be used in an ${AssertionGroup::class.simpleName} with an ${ExplanatoryAssertionGroupType::class.simpleName} and reportBuilder without failure") {
        assertionCreator.forEach { (name, createAssertion) ->
            it("fun `$name`") {
                val assertions = coreFactory.newCollectingAssertionContainer<T>(None)
                    .addAssertionsCreatedBy(createAssertion)
                    .getAssertions()
                val plant = coreFactory.newReportingPlant(
                    Untranslatable("custom assertion verb"), { 1.0 },
                    coreFactory.newOnlyFailureReporter(
                        coreFactory.newAssertionFormatterFacade(coreFactory.newAssertionFormatterController()),
                        coreFactory.newNoOpAtriumErrorAdjuster()
                    )
                )
                val explanatoryGroup = AssertImpl.builder.explanatoryGroup
                    .withDefaultType
                    .withAssertions(assertions)
                    .build()
                plant.addAssertion(explanatoryGroup)
            }
        }
    }
})

/**
 * Helper function to map an arbitrary `Expect<T>.(...) -> Unit` function to a parameter-less one.
 */
fun <T> expectLambda(createAssertion: Expect<T>.() -> Unit): Expect<T>.() -> Unit = createAssertion
