package ch.tutteli.atrium.api.cc.de_CH

import ch.tutteli.atrium.creating._isGreaterOrEquals
import ch.tutteli.atrium.creating._isGreaterThan
import ch.tutteli.atrium.creating._isLessOrEquals
import ch.tutteli.atrium.creating._isLessThan
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.creating.AssertionPlant

/**
 * Makes the assertion that [AssertionPlant.subject] is less than [expected].
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
fun <T : Comparable<T>> Assert<T>.istKleinerAls(expected: T)
    = addAssertion(_isLessThan(this, expected))

/**
 * Makes the assertion that [AssertionPlant.subject] is less than or equals [expected].
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
fun <T : Comparable<T>> Assert<T>.istKleinerOderGleich(expected: T)
    = addAssertion(_isLessOrEquals(this, expected))

/**
 * Makes the assertion that [AssertionPlant.subject] is greater than [expected].
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
fun <T : Comparable<T>> Assert<T>.istGroesserAls(expected: T)
    = addAssertion(_isGreaterThan(this, expected))

/**
 * Makes the assertion that [AssertionPlant.subject] is greater than or equals [expected].
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
fun <T : Comparable<T>> Assert<T>.istGroesserOderGleich(expected: T)
    = addAssertion(_isGreaterOrEquals(this, expected))

