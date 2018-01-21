package ch.tutteli.atrium.spec.assertions

import ch.tutteli.atrium.AtriumFactory
import ch.tutteli.atrium.assertions.AssertionGroupBuilder
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
                val collectingPlant = AtriumFactory.newCollectingPlant<T>({ throw PlantHasNoSubjectException("subject was accessed outside of the Assertion::holds scope") })
                collectingPlant.createAssertion()
                val plant = AtriumFactory.newReportingPlant(AssertionVerb.ASSERT, 1.0,
                    AtriumFactory.newOnlyFailureReporter(
                        AtriumFactory.newAssertionFormatterFacade(AtriumFactory.newAssertionFormatterController())
                    ))
                val explanatoryGroup = AssertionGroupBuilder.explanatory.withDefault.create(collectingPlant.getAssertions())
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
