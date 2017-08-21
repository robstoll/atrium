package ch.tutteli.atrium

import ch.tutteli.atrium.assertions._isNotSame
import ch.tutteli.atrium.assertions._isSame
import ch.tutteli.atrium.assertions._notToBe
import ch.tutteli.atrium.assertions._toBe
import ch.tutteli.atrium.creating.IAssertionPlant

/**
 * Makes the assertion that [IAssertionPlant.subject] is (equals) [expected].
 *
 * This method might enforce in the future, that [expected] has to be the same type as [IAssertionPlant.subject].
 * Currently the following is possible: `assert(1).toBe(1.0)`
 *
 * @return This plant to support a fluent-style API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct
 */
fun <T : Any> IAssertionPlant<T>.toBe(expected: T): IAssertionPlant<T>
    = addAssertion(_toBe(this, expected))

/**
 * Makes the assertion that [IAssertionPlant.subject] is not (does not equal) [expected].
 *
 * This method might enforce in the future, that [expected] has to be the same type as [IAssertionPlant.subject].
 * Currently the following is possible: `assert(1).notToBe(1.0)`
 *
 * @return This plant to support a fluent-style API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct
 */
fun <T : Any> IAssertionPlant<T>.notToBe(expected: T): IAssertionPlant<T>
    = addAssertion(_notToBe(this, expected))

/**
 * Makes the assertion that [IAssertionPlant.subject] is the same instance as [expected].
 *
 * This method might enforce in the future, that [expected] has to be the same type as [IAssertionPlant.subject].
 * Currently the following is possible: `assert(1).isSame(1.0)`
 *
 * @return This plant to support a fluent-style API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct
 */
fun <T : Any> IAssertionPlant<T>.isSame(expected: T): IAssertionPlant<T>
    = addAssertion(_isSame(this, expected))

/**
 * Makes the assertion that [IAssertionPlant.subject] is not the same instance as [expected].
 *
 * This method might enforce in the future, that [expected] has to be the same type as [IAssertionPlant.subject].
 * Currently the following is possible: `assert(1).isNotSame(1.0)`
 *
 * @return This plant to support a fluent-style API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct
 */
fun <T : Any> IAssertionPlant<T>.isNotSame(expected: T): IAssertionPlant<T>
    = addAssertion(_isNotSame(this, expected))

