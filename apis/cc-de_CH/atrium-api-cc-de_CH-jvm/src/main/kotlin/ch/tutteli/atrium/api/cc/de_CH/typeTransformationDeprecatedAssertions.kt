@file:JvmMultifileClass
@file:JvmName("TypeTransformationAssertionsKt")
package ch.tutteli.atrium.api.cc.de_CH

import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.creating.AssertionPlantNullable


/**
 * Makes the assertion that [AssertionPlantNullable.subject] is not null but the [expected] value.
 *
 * Is a shortcut for `istNichtNull { ist(expected) }`
 *
 * @return Notice, that this assertion function cannot provide a fluent API because it depends on whether the first
 *   assertion ([Assert.subject][AssertionPlant.subject] is not null) holds or not.
 *
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@Deprecated("Will be removed with 1.0.0 because it is redundant in terms of `ist(expected)` without adding enough to be a legit alternative.", ReplaceWith("ist(expected)"))
inline fun <reified T : Any> AssertionPlantNullable<T?>.istNichtNullAber(expected: T) {
    istNichtNull { ist(expected) }
}
