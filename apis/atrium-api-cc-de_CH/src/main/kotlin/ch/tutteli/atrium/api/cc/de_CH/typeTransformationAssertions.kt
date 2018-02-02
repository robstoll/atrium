package ch.tutteli.atrium.api.cc.de_CH

import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.creating.AssertionPlantNullable
import ch.tutteli.atrium.creating.TypeTransformationAssertions

/**
 * Makes the assertion that [AssertionPlantNullable.subject] is not null and if so, uses [assertionCreator]
 * which could create further assertions which are added as a group.
 *
 * @return Notice, that this assertion function cannot provide a fluent API because it depends on whether the first
 *   assertion ([AssertionPlant.subject] is not null) holds or not.
 * Define subsequent assertions via the [assertionCreator] lambda.
 *
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
inline fun <reified T : Any> AssertionPlantNullable<T?>.istNichtNull(noinline assertionCreator: Assert<T>.() -> Unit) {
    TypeTransformationAssertions.isNotNull(this, T::class, assertionCreator)
}

/**
 * Makes the assertion that [AssertionPlant.subject] *is a* [TSub] (the same type or a sub-type) and if so,
 * uses [assertionCreator] which could create further assertions which are added as a group.
 *
 * @return Notice, that this assertion function cannot provide a fluent API because it depends on whether the first
 *   assertion ([AssertionPlant.subject] *is a*   [TSub]) holds or not.
 * Define subsequent assertions via the [assertionCreator] lambda.
 *
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
inline fun <reified TSub : Any> Assert<Any>.istEin(noinline assertionCreator: AssertionPlant<TSub>.() -> Unit) {
    TypeTransformationAssertions.isA(this, TSub::class, assertionCreator)
}
