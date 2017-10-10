package ch.tutteli.atrium.api.cc.en_UK

import ch.tutteli.atrium.assertions._contains
import ch.tutteli.atrium.assertions._containsNot
import ch.tutteli.atrium.assertions._hasSize
import ch.tutteli.atrium.assertions._isEmpty
import ch.tutteli.atrium.creating.IAssertionPlant

/**
 * Makes the assertion that [IAssertionPlant.subject] contains [expected]
 * and the [otherExpected] (if defined).
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
fun <E, T : Collection<E>> IAssertionPlant<T>.contains(expected: E, vararg otherExpected: E): IAssertionPlant<T>
    //TODO replace with the following once ready
    // = contains.atLeast(1).values(expected, *otherExpected)
    = addAssertion(_contains(this, expected, *otherExpected))

/**
 * Makes the assertion that [IAssertionPlant.subject] does not contain [expected]
 * and neither one of the [otherExpected] (if defined).
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
fun <E, T : Collection<E>> IAssertionPlant<T>.containsNot(expected: E, vararg otherExpected: E): IAssertionPlant<T>
    = addAssertion(_containsNot(this, expected, *otherExpected))

/**
 * Makes the assertion that [IAssertionPlant.subject]'s [Collection.size] is [size].
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
fun <T : Collection<*>> IAssertionPlant<T>.hasSize(size: Int): IAssertionPlant<T>
    = addAssertion(_hasSize(this, size))

/**
 * Makes the assertion that [IAssertionPlant.subject] is an empty [Collection].
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
fun <T : Collection<*>> IAssertionPlant<T>.isEmpty(): IAssertionPlant<T>
    = addAssertion(_isEmpty(this))
