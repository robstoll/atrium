package ch.tutteli.atrium

import ch.tutteli.atrium.assertions._isGreaterOrEquals
import ch.tutteli.atrium.assertions._isGreaterThan
import ch.tutteli.atrium.assertions._isLessOrEquals
import ch.tutteli.atrium.assertions._isLessThan
import ch.tutteli.atrium.creating.IAssertionPlant

/**
 * Makes the assertion that [IAssertionPlant.subject] is less than [expected].
 *
 * @return This plant to support a fluent-style API.
 */
fun <T> IAssertionPlant<T>.isLessThan(expected: T): IAssertionPlant<T> where T : Number, T : Comparable<T>
    = addAssertion(_isLessThan(this, expected))

/**
 * Makes the assertion that [IAssertionPlant.subject] is less than or equals [expected].
 *
 * @return This plant to support a fluent-style API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct
 */
fun <T> IAssertionPlant<T>.isLessOrEquals(expected: T): IAssertionPlant<T> where T : kotlin.Number, T : kotlin.Comparable<T>
    = addAssertion(_isLessOrEquals(this, expected))

/**
 * Makes the assertion that [IAssertionPlant.subject] is greater than [expected].
 *
 * @return This plant to support a fluent-style API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct
 */
fun <T> IAssertionPlant<T>.isGreaterThan(expected: T): IAssertionPlant<T> where T : kotlin.Number, T : kotlin.Comparable<T>
    = addAssertion(_isGreaterThan(this, expected))

/**
 * Makes the assertion that [IAssertionPlant.subject] is greater than or equals [expected].
 *
 * @return This plant to support a fluent-style API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct
 */
fun <T> IAssertionPlant<T>.isGreaterOrEquals(expected: T): IAssertionPlant<T> where T : kotlin.Number, T : kotlin.Comparable<T>
    = addAssertion(_isGreaterOrEquals(this, expected))

