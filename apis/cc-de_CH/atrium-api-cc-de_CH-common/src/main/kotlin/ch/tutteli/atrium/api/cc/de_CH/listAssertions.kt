package ch.tutteli.atrium.api.cc.de_CH

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.creating.AssertionPlantNullable
import ch.tutteli.atrium.domain.builders.AssertImpl

/**
 * Makes the assertion that the given [index] is within the bounds of [Assert.subject][AssertionPlant.subject],
 * creates a feature assertion plant for the corresponding element and returns the newly created plant.
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the given [index] is out of bound.
 */
@Suppress("DEPRECATION")
fun <E: Any, T: List<E>> Assert<T>.get(index: Int): Assert<E>
    = AssertImpl.list.get(this, index)

/**
 * Makes the assertion that the given [index] is within the bounds of [Assert.subject][AssertionPlant.subject] and that
 * the corresponding element holds all assertions the given [assertionCreator] might create for it.
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if a created [Assertion]s (by calling [assertionCreator])
 *   does not hold.
 * @throws IllegalArgumentException in case the given [assertionCreator] did not create a single assertion.
 */
@Suppress("DEPRECATION")
fun <E: Any, T: List<E>> Assert<T>.get(index: Int, assertionCreator: Assert<E>.() -> Unit)
    = addAssertion(AssertImpl.list.get(this, index, assertionCreator))

/**
 * Makes the assertion that the given [index] is within the bounds of [Assert.subject][AssertionPlant.subject],
 * creates a feature assertion plant for the corresponding nullable element and returns the newly created plant.
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the given [index] is out of bound.
 */
@Suppress("DEPRECATION")
fun <E, T: List<E>> Assert<T>.get(index: Int): AssertionPlantNullable<E>
    = AssertImpl.list.getNullable(this, index)
