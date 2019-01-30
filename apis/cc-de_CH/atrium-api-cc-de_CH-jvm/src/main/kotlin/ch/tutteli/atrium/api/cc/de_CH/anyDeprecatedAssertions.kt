@file:JvmMultifileClass
@file:JvmName("AnyAssertionsKt")
package ch.tutteli.atrium.api.cc.de_CH

import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.creating.AssertionPlantNullable

/**
 * Makes the assertion that the [Assert.subject][AssertionPlant.subject] is `null`.
 *
 * @return Does not support a fluent API because: what else would you want to assert about `null` anyway?
 *
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@Deprecated("Will be removed with 1.0.0 because it is redundant in terms of `ist(null)` without adding enough to be a legit alternative.", ReplaceWith("ist(null)"))
fun <T : Any?> AssertionPlantNullable<T>.istNull() {
    ist(null)
}
