package ch.tutteli.atrium.api.cc.de_CH

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.creating.AssertionPlantNullable
import ch.tutteli.atrium.domain.builders.AssertImpl

/**
 * Makes the assertion that the given [index] is within the bounds of [AssertionPlant.subject] and that
 * the corresponding entry holds all assertions the given [assertionCreator] might create for it.
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if a created [Assertion]s (by calling [assertionCreator])
 *   does not hold.
 * @throws IllegalArgumentException in case the given [assertionCreator] did not create a single assertion.
 */
fun <E: Any, T: List<E>> Assert<T>.get(index: Int, assertionCreator: Assert<E>.() -> Unit)
    = addAssertion(AssertImpl.list.get(this, index, assertionCreator))

/**
 * Makes the assertion that the given [index] is within the bounds of [AssertionPlant.subject] and that
 * the corresponding entry holds all assertions the given [assertionCreator] might create for it.
 *
 * Notice, that the corresponding entry for the given [index] can be `null` as the [List] has a
 * nullable entry type.
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if a created [Assertion]s (by calling [assertionCreator])
 *   does not hold.
 * @throws IllegalArgumentException in case the given [assertionCreator] did not create a single assertion.
 */
fun <E, T: List<E>> Assert<T>.getNullable(index: Int, assertionCreator: AssertionPlantNullable<E>.() -> Unit)
    = addAssertion(AssertImpl.list.getNullable(this, index, assertionCreator))
