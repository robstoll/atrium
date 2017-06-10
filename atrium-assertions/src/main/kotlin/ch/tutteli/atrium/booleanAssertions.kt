package ch.tutteli.atrium

import ch.tutteli.atrium.creating.IAssertionPlant

/**
 * Makes the assertion that [IAssertionPlant.subject] is `true`.
 *
 * Delegates to [toBe] with argument `true`.
 *
 * @return This plant to support a fluent-style API.
 * @throws AssertionError Might throw an [AssertionError] if the made assertion does not hold.
 */
fun IAssertionPlant<Boolean>.isTrue() = toBe(true)

/**
 * Makes the assertion that [IAssertionPlant.subject] is `false`.
 *
 * Delegates to [toBe] with argument `false`.
 *
 * @return This plant to support a fluent-style API.
 * @throws AssertionError Might throw an [AssertionError] if the made assertion does not hold.
 */
fun IAssertionPlant<Boolean>.isFalse() = toBe(false)
