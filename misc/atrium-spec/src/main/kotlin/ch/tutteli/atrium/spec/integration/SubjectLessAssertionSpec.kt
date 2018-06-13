package ch.tutteli.atrium.spec.integration

import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.assertions.ExplanatoryAssertionGroupType
import ch.tutteli.atrium.core.coreFactory
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.creating.PlantHasNoSubjectException
import ch.tutteli.atrium.domain.builders.AssertImpl
import ch.tutteli.atrium.spec.AssertionVerb
import org.jetbrains.spek.api.Spek

abstract class SubjectLessAssertionSpec<T : Any>(
    groupPrefix: String,
    vararg assertionCreator: Pair<String, Assert<T>.() -> Unit>
) : Spek({

    group("${groupPrefix}assertion function can be used in an ${AssertionGroup::class.simpleName} with an ${ExplanatoryAssertionGroupType::class.simpleName} and reported without failure") {
        assertionCreator.forEach { (name, createAssertion) ->
            test("fun `$name`") {
                val assertions = coreFactory.newCollectingPlant<T>({ throw PlantHasNoSubjectException() })
                    .addAssertionsCreatedBy(createAssertion)
                    .getAssertions()
                val plant = coreFactory.newReportingPlant(AssertionVerb.ASSERT, { 1.0 },
                    coreFactory.newOnlyFailureReporter(
                        coreFactory.newAssertionFormatterFacade(coreFactory.newAssertionFormatterController())
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
 * Helper function to map an arbitrary `IAssert<T>.(...) -> Unit` function to a parameter-less one.
 */
fun <T : Any> mapToCreateAssertion(createAssertion: Assert<T>.() -> Unit): Assert<T>.() -> Unit
    = createAssertion
