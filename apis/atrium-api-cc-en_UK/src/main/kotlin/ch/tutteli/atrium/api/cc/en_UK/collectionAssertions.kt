package ch.tutteli.atrium.api.cc.en_UK

import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.creating.CollectionAssertions

/**
 * Makes the assertion that [AssertionPlant.subject]'s [Collection.size] is [size].
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
fun <T : Collection<*>> Assert<T>.hasSize(size: Int)
    = addAssertion(CollectionAssertions.hasSize(this, size))

/**
 * Makes the assertion that [AssertionPlant.subject] is an empty [Collection].
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
fun <T : Collection<*>> Assert<T>.isEmpty()
    = addAssertion(CollectionAssertions.isEmpty(this))

/**
 * Makes the assertion that [AssertionPlant.subject] is not an empty [Collection].
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
fun <T : Collection<*>> Assert<T>.isNotEmpty()
    = addAssertion(CollectionAssertions.isNotEmpty(this))
