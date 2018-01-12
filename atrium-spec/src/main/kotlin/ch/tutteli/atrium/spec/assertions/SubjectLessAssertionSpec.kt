package ch.tutteli.atrium.spec.assertions

import ch.tutteli.atrium.AtriumFactory
import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.assertions.ExplanatoryAssertionGroup
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.creating.PlantHasNoSubjectException
import ch.tutteli.atrium.spec.AssertionVerb
import org.jetbrains.spek.api.Spek

abstract class SubjectLessAssertionSpec<T : Any>(
    groupPrefix: String,
    vararg assertionCreator: Pair<String, AssertionPlant<T>.() -> Unit>
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
                val explanatoryGroup = AssertionGroup.Builder.explanatory.withDefault.create(collectingPlant.getAssertions())
                plant.addAssertion(explanatoryGroup)
            }
        }
    }
})

/**
 * Helper function to map an arbitrary `IAssertionPlant<T>.(...) -> Unit` function to a parameter-less one.
 */
fun <T : Any> mapToCreateAssertion(createAssertion: AssertionPlant<T>.() -> Unit): AssertionPlant<T>.() -> Unit
    = createAssertion
