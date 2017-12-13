package ch.tutteli.atrium.api.cc.en_UK

import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.creating.IAssertionPlant

/**
 * Makes the assertion that [IAssertionPlant.subject] is `true`.
 *
 * Delegates to [toBe] with argument `true`.
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
fun Assert<Boolean>.isTrue() = toBe(true)

/**
 * Makes the assertion that [IAssertionPlant.subject] is `false`.
 *
 * Delegates to [toBe] with argument `false`.
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
fun Assert<Boolean>.isFalse() = toBe(false)
