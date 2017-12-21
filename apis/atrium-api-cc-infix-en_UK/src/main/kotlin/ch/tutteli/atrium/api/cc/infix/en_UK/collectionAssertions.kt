package ch.tutteli.atrium.api.cc.infix.en_UK

import ch.tutteli.atrium.assertions._hasSize
import ch.tutteli.atrium.assertions._isEmpty
import ch.tutteli.atrium.assertions._isNotEmpty
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.creating.AssertionPlant

/**
 * Makes the assertion that [AssertionPlant.subject]'s [Collection.size] is [size].
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
infix fun <T : Collection<*>> Assert<T>.hasSize(size: Int)
    = addAssertion(_hasSize(this, size))

/**
 * Makes the assertion that [AssertionPlant.subject] is an empty [Collection].
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
infix fun <T : Collection<*>> Assert<T>.toBe(@Suppress("UNUSED_PARAMETER") Empty: Empty)
    = addAssertion(_isEmpty(this))

/**
 * Makes the assertion that [AssertionPlant.subject] is not an empty [Collection].
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
infix fun <T : Collection<*>> Assert<T>.notToBe(@Suppress("UNUSED_PARAMETER") Empty: Empty)
    = addAssertion(_isNotEmpty(this))
