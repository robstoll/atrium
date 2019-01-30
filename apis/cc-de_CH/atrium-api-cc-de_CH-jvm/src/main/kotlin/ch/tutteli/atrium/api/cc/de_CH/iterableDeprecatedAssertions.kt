@file:Suppress("DEPRECATION" /* TODO remove with 1.0.0 */)
@file:JvmMultifileClass
@file:JvmName("IterableAssertionsKt")
package ch.tutteli.atrium.api.cc.de_CH

import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.domain.builders.AssertImpl
import ch.tutteli.atrium.domain.creating.iterable.contains.searchbehaviours.NoOpSearchBehaviour
import ch.tutteli.atrium.api.cc.de_CH.assertions.iterable.contains.builders.IterableContainsNotCheckerBuilder as DeprecatedNotCheckerBuilder
import ch.tutteli.atrium.assertions.iterable.contains.builders.IterableContainsBuilder as DeprecatedBuilder

@Deprecated("Use the extension fun `enthaelt` instead. This fun is only here to retain binary compatibility; will be removed with 1.0.0", ReplaceWith("plant.enthaelt"))
fun <E, T : Iterable<E>> getEnthaelt(plant: Assert<T>): DeprecatedBuilder<E, T, NoOpSearchBehaviour>
    = DeprecatedBuilder(plant, plant.enthaelt.searchBehaviour)

@Deprecated("Use the extension fun `enthaeltNicht` instead. This fun is only here to retain binary compatibility; will be removed with 1.0.0", ReplaceWith("plant.enthaeltNicht"))
fun <E, T : Iterable<E>> getEnthaeltNicht(plant: Assert<T>): DeprecatedNotCheckerBuilder<E, T>
    = DeprecatedNotCheckerBuilder(AssertImpl.iterable.containsNotBuilder(plant))

@Deprecated("Use `enthaeltNullableWert/enthaeltNullableWerte` instead. This fun is only here to retain binary compatibility; will be removed with 1.0.0", ReplaceWith("enthaeltNullableWerte(expected, *otherExpected)"))
fun  <E: Any?, T : Iterable<E>> Assert<T>.enthaelt(expected: E, vararg otherExpected: E): AssertionPlant<T>
    = enthaeltNullableWerte(expected, *otherExpected)

/**
 * Makes the assertion that the [Assert.subject][AssertionPlant.subject] contains an entry holding the assertions created by the
 * [assertionCreator] or an entry which is `null` in case [assertionCreator] is `null`
 * as well -- likewise an entry (can be the same) is searched for each
 * of the [otherAssertionCreators].
 *
 * It is a shortcut for `enthaelt.inBeliebigerReihenfolge.zumindest(1).eintraege(assertionCreator, *otherAssertionCreators)`
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@JvmName("enthaelt?")
@Deprecated("Use `enthaeltNullableEintrag/enthaeltNullableEintraege` instead.", ReplaceWith("enthaeltNullableEintraege(assertionCreator, *otherAssertionCreators)"))
fun <E : Any, T : Iterable<E?>> Assert<T>.enthaelt(assertionCreator: (Assert<E>.() -> Unit)?, vararg otherAssertionCreators: (Assert<E>.() -> Unit)?): AssertionPlant<T>
    = enthaeltNullableEintraege(assertionCreator, *otherAssertionCreators)

@Deprecated("Use `enthaeltNullableEintraege`, will be removd with 1.0.0", ReplaceWith("enthaeltNullableEintraege(assertionCreator, *otherAssertionCreators)"))
fun <E : Any, T : Iterable<E?>> enthaeltNullable(plant: Assert<T>, assertionCreator: (Assert<E>.() -> Unit)?, vararg otherAssertionCreators: (Assert<E>.() -> Unit)?): AssertionPlant<T>
    = plant.enthaeltNullableEintraege(assertionCreator, *otherAssertionCreators)

@JvmName("enthaeltStrikt?")
@Deprecated("Use `enthaeltStriktNullableEintrag/enthaeltStriktNullableEintraege` instead. This fun is only here to retain binary compatibility; will be removed with 1.0.0", ReplaceWith("enthaeltStriktNullableEintraege(assertionCreator, *otherAssertionCreators)"))
fun <E : Any, T : Iterable<E?>> Assert<T>.enthaeltStrikt(assertionCreator: (Assert<E>.() -> Unit)?, vararg otherAssertionCreators: (Assert<E>.() -> Unit)?): AssertionPlant<T>
    = enthaeltStriktNullableEintraege(assertionCreator, *otherAssertionCreators)

@Deprecated("Use the extension fun `enthaeltStriktNullableEintraege` instead; will be removed with 1.0.0", ReplaceWith("plant.enthaeltStriktNullableEintraege(assertionCreator, *otherAssertionCreators)"))
fun <E : Any, T : Iterable<E?>> enthaeltStriktNullable(plant: Assert<T>, assertionCreator: (Assert<E>.() -> Unit)?, vararg otherAssertionCreators: (Assert<E>.() -> Unit)?): AssertionPlant<T>
    = plant.enthaeltStriktNullableEintraege(assertionCreator, *otherAssertionCreators)

@Deprecated("Use `enthaeltNicht.nullableWerte` instead. This fun is only here to retain binary compatibility; will be removed with 1.0.0", ReplaceWith("enthaeltNicht.nullableWerte(expected, *otherExpected)"))
fun <E, T : Iterable<E>> Assert<T>.enthaeltNicht(expected: E, vararg otherExpected: E)
    = enthaeltNicht.nullableWerte(expected, *otherExpected)
