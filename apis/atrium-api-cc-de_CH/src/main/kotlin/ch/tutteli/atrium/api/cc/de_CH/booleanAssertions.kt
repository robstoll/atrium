package ch.tutteli.atrium.api.cc.de_CH

import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.creating.IAssertionPlant

/**
 * Makes the assertion that [IAssertionPlant.subject] is `true`.
 *
 * Delegates to [ist] with argument `true`.
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
fun Assert<Boolean>.istTrue() = ist(true)

/**
 * Makes the assertion that [IAssertionPlant.subject] is `false`.
 *
 * Delegates to [ist] with argument `false`.
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
fun Assert<Boolean>.istFalse() = ist(false)
