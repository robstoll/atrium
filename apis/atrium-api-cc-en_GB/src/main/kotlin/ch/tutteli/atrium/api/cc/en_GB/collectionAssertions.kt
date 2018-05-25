package ch.tutteli.atrium.api.cc.en_GB

import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.creating.AssertMarker
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.domain.builders.AssertImpl

/**
 * Makes the assertion that [AssertionPlant.subject]'s [Collection.size] is [size].
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@AssertMarker
fun <T : Collection<*>> Assert<T>.hasSize(size: Int)
    = addAssertion(AssertImpl.collection.hasSize(this, size))

/**
 * Makes the assertion that [AssertionPlant.subject] is an empty [Collection].
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@AssertMarker
fun <T : Collection<*>> Assert<T>.isEmpty()
    = addAssertion(AssertImpl.collection.isEmpty(this))

/**
 * Makes the assertion that [AssertionPlant.subject] is not an empty [Collection].
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@AssertMarker
fun <T : Collection<*>> Assert<T>.isNotEmpty()
    = addAssertion(AssertImpl.collection.isNotEmpty(this))
