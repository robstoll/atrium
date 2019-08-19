@file:Suppress("DEPRECATION" /* will be removed with 1.0.0 */)
package ch.tutteli.atrium.api.cc.infix.en_GB

import ch.tutteli.atrium.api.cc.infix.en_GB.creating.list.get.builders.ListGetNullableOption
import ch.tutteli.atrium.api.cc.infix.en_GB.creating.list.get.builders.ListGetOption
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.domain.builders.AssertImpl

/**
 * Makes the assertion that the given [index] is within the bounds of [Assert.subject][AssertionPlant.subject],
 * creates a feature assertion plant for the corresponding element and returns the newly created plant.
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the given [index] is out of bound.
 */
@Suppress("DEPRECATION")
infix fun <E: Any, T: List<E>> Assert<T>.get(index: Int)
    = AssertImpl.list.get(this, index)

/**
 * Prepares the assertion about the return value of calling [get][List.get] with the given [index].
 *
 * @return A fluent builder to finish the assertion.
 */
infix fun <E : Any, T: List<E>> Assert<T>.get(index: Index): ListGetOption<E, T>
    = ListGetOption.create(this, index.index)


/**
 * Makes the assertion that the given [index] is within the bounds of [Assert.subject][AssertionPlant.subject],
 * creates a feature assertion plant for the corresponding nullable element and returns the newly created plant.
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the given [index] is out of bound.
 */
@Suppress("DEPRECATION")
infix fun <E, T: List<E>> Assert<T>.get(index: Int)
    = AssertImpl.list.getNullable(this, index)

/**
 * Prepares the assertion about the nullable return value of calling [get][List.get] with the given [index].
 *
 * Notice, that the corresponding element of the given [index] can be `null` even if the index is within bounds
 * as the [List] has a nullable element type.
 *
 * @return A fluent builder to finish the assertion.
 */
infix fun <E, T: List<E>> Assert<T>.get(index: Index): ListGetNullableOption<E, T>
    = ListGetNullableOption.create(this, index.index)

