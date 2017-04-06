package ch.tutteli.atrium.creating

import ch.tutteli.atrium.AtriumFactory
import ch.tutteli.atrium.IAtriumFactory
import ch.tutteli.atrium.assertions.IAssertion
import ch.tutteli.atrium.reporting.IReporter

/**
 * Use this function to create a custom assertion verb function which lazy evaluates the assertions created by [createAssertions].
 *
 * This function will create an [IAssertionPlant] which does not check the created assertions until one calls [IAssertionPlant.checkAssertions].
 * However, it uses the given [createAssertions] function immediately after creating the [IAssertionPlant] which might add some assertions
 * and it then calls [IAssertionPlant.checkAssertions].
 * In case all assertions added so far hold, then it will not evaluate further added assertions until one calls [IAssertionPlant.checkAssertions] again.
 *
 * It creates a [IAtriumFactory.newThrowingAssertionChecker] based on the given [reporter] for assertion checking.
 *
 * @return The newly created [IAssertionPlant] which can be used to postulate further assertions.
 * @throws AssertionError The newly created [IAssertionPlant] might throw an [AssertionError] in case a created [IAssertion] does not hold.
 */
inline fun <T : Any> IAtriumFactory.newCheckLazilyAtTheEnd(assertionVerb: String, subject: T, reporter: IReporter, createAssertions: IAssertionPlant<T>.() -> Unit)
    = createAssertionsAndCheckThem(AtriumFactory.newCheckLazily(assertionVerb, subject, reporter), createAssertions)

/**
 * Uses the given [plant] as receiver of the given [createAssertions] function and then calls [IAssertionPlant.checkAssertions].
 *
 * @return the given [plant]
 * @throws AssertionError the [plant] might throw an [AssertionError] in case a created [IAssertion] does not hold.
 */
inline fun <T : Any> IAtriumFactory.createAssertionsAndCheckThem(plant: IAssertionPlant<T>, createAssertions: IAssertionPlant<T>.() -> Unit): IAssertionPlant<T> {
    plant.createAssertions()
    plant.checkAssertions()
    return plant
}
