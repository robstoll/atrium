package ch.tutteli.atrium.api.cc.en_UK

import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.creating.AssertionPlantNullable
import ch.tutteli.atrium.domain.builders.AssertImpl

/**
 * Makes the assertion that [AssertionPlantNullable.subject] is not null and if so, uses [assertionCreator]
 * which could create further assertions which are added as a group.
 *
 * @return Notice, that this assertion function cannot provide a fluent API because it depends on whether the first
 *   assertion ([Assert.subject][AssertionPlant.subject] is not null) holds or not.
 * Define subsequent assertions via the [assertionCreator] lambda.
 *
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@Deprecated("Use pendant from package en_GB; will be removed with 1.0.0", ReplaceWith("ch.tutteli.atrium.api.cc.en_GB.notToBeNull(assertionCreator)"))
inline fun <reified T : Any> AssertionPlantNullable<T?>.isNotNull(noinline assertionCreator: Assert<T>.() -> Unit) {
    AssertImpl.any.typeTransformation.isNotNull(this, T::class, assertionCreator)
}

/**
 * Makes the assertion that [Assert.subject][AssertionPlant.subject] *is a* [TSub] (the same type or a sub-type) and if so,
 * uses [assertionCreator] which could create further assertions which are added as a group.
 *
 * @return Notice, that this assertion function cannot provide a fluent API because it depends on whether the first
 *   assertion ([Assert.subject][AssertionPlant.subject] *is a*   [TSub]) holds or not.
 * Define subsequent assertions via the [assertionCreator] lambda.
 *
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@Deprecated("Use pendant from package en_GB; will be removed with 1.0.0", ReplaceWith("ch.tutteli.atrium.api.cc.en_GB.isA(assertionCreator)"))
inline fun <reified TSub : Any> Assert<Any>.isA(noinline assertionCreator: AssertionPlant<TSub>.() -> Unit) {
    AssertImpl.any.typeTransformation.isA(this, TSub::class, assertionCreator)
}
