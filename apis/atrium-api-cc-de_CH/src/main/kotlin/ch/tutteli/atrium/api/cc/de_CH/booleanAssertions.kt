package ch.tutteli.atrium.api.cc.de_CH

import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.creating.AssertionPlant

/**
 * Makes the assertion that [AssertionPlant.subject] is `true`.
 *
 * Delegates to [ist] with argument `true`.
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@Deprecated("will be removed with 1.0.0 because it is redundant in terms of `ist(true)` without adding enough to be a legit alternative.", ReplaceWith("ist(true)"))
fun Assert<Boolean>.istTrue() = ist(true)

/**
 * Makes the assertion that [AssertionPlant.subject] is `false`.
 *
 * Delegates to [ist] with argument `false`.
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@Deprecated("will be removed with 1.0.0 because it is redundant with `ist(false)` without without adding enough to be a legit alternative.", ReplaceWith("ist(false)"))
fun Assert<Boolean>.istFalse() = ist(false)
