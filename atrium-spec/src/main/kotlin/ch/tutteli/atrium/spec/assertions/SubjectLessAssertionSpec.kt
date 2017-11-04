package ch.tutteli.atrium.spec.assertions

import ch.tutteli.atrium.AtriumFactory
import ch.tutteli.atrium.assertions.ExplanatoryAssertionGroup
import ch.tutteli.atrium.assertions.ExplanatoryAssertionGroupType
import ch.tutteli.atrium.creating.IAssertionPlant
import ch.tutteli.atrium.creating.ICollectingAssertionPlant
import ch.tutteli.atrium.spec.AssertionVerb
import org.jetbrains.spek.api.Spek

abstract class SubjectLessAssertionSpec<T : Any>(
    vararg createAssertions: Pair<String, IAssertionPlant<T>.() -> Unit>
) : Spek({

    group("assertion function can be used in ${ExplanatoryAssertionGroup::class.simpleName} and reported without failure") {

        createAssertions.forEach { (name, createAssertion) ->
            test(name) {
                val collectingPlant = AtriumFactory.newCollectingPlant<T>({ throw ICollectingAssertionPlant.PlantHasNoSubjectException("no subject in this test") })
                collectingPlant.createAssertion()
                val plant = AtriumFactory.newReportingPlantCheckLazily(AssertionVerb.ASSERT, 1.0,
                    AtriumFactory.newOnlyFailureReporter(
                        AtriumFactory.newAssertionFormatterFacade(AtriumFactory.newAssertionFormatterController())
                    ))
                val explanatoryGroup = ExplanatoryAssertionGroup(ExplanatoryAssertionGroupType, collectingPlant.getAssertions())
                plant.addAssertion(explanatoryGroup)
                plant.checkAssertions()
            }
        }
    }
})

/**
 * Helper function to map an arbitrary `IAssertionPlant<T>.(...) -> Unit` function to a parameter-less one.
 */
fun <T : Any> mapToCreateAssertion(createAssertion: IAssertionPlant<T>.() -> Unit): IAssertionPlant<T>.() -> Unit
    = createAssertion
