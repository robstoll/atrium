package ch.tutteli.atrium.spec.assertions

import ch.tutteli.atrium.CoreFactory
import ch.tutteli.atrium.assertions.AssertionBuilder
import ch.tutteli.atrium.assertions.ExplanatoryAssertionGroup
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.creating.PlantHasNoSubjectException
import ch.tutteli.atrium.spec.AssertionVerb
import org.jetbrains.spek.api.Spek

abstract class SubjectLessAssertionSpec<T : Any>(
    groupPrefix: String,
    vararg assertionCreator: Pair<String, Assert<T>.() -> Unit>
) : Spek({

    group("${groupPrefix}assertion function can be used in ${ExplanatoryAssertionGroup::class.simpleName} and reported without failure") {
        assertionCreator.forEach { (name, createAssertion) ->
            test("fun `$name`") {
                val assertions = CoreFactory.newCollectingPlant<T>({ throw PlantHasNoSubjectException("subject was accessed outside of the Assertion::holds scope") })
                    .addAssertionsCreatedBy(createAssertion)
                    .getAssertions()
                val plant = CoreFactory.newReportingPlant(AssertionVerb.ASSERT, 1.0,
                    CoreFactory.newOnlyFailureReporter(
                        CoreFactory.newAssertionFormatterFacade(CoreFactory.newAssertionFormatterController())
                    ))
                val explanatoryGroup = AssertionBuilder.explanatoryGroup.withDefault.create(assertions)
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
