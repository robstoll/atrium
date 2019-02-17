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

@Deprecated("Use `enthaelt` instead; will be removed with 1.0.0", ReplaceWith("enthaelt(expectedOrNull)"))
fun <E: Any?, T: Iterable<E>> Assert<T>.enthaeltNullableWert(expectedOrNull: E): AssertionPlant<T>
    = enthaelt(expectedOrNull)

@Deprecated("Use `enthaelt` instead; will be removed with 1.0.0", ReplaceWith("enthaelt(expectedOrNull, *otherExpectedOrNulls)"))
fun <E: Any?, T: Iterable<E>> Assert<T>.enthaeltNullableWerte(expectedOrNull: E, vararg otherExpectedOrNulls: E): AssertionPlant<T>
    = enthaelt(expectedOrNull, *otherExpectedOrNulls)

@Deprecated("Use `enthaelt` instead; will be removed with 1.0.0", ReplaceWith("enthaelt(assertionCreatorOrNull)"))
fun <E: Any, T: Iterable<E?>> Assert<T>.enthaeltNullableEintrag(assertionCreatorOrNull: (Assert<E>.() -> Unit)?): AssertionPlant<T>
    = enthaelt(assertionCreatorOrNull)

@Deprecated("Use `enthaelt` instead; will be removed with 1.0.0", ReplaceWith("enthaelt(assertionCreatorOrNull, *otherAssertionCreatorsOrNulls)"))
fun <E: Any, T: Iterable<E?>> Assert<T>.enthaeltNullableEintraege(assertionCreatorOrNull: (Assert<E>.() -> Unit)?, vararg otherAssertionCreatorsOrNulls: (Assert<E>.() -> Unit)?): AssertionPlant<T>
    = enthaelt(assertionCreatorOrNull, *otherAssertionCreatorsOrNulls)


@JvmName("enthaeltStriktDeprecated")
@Deprecated("Use `enthaeltExakt` instead. This fun is only here to retain binary compatibility; will be removed with 1.0.0", ReplaceWith("enthaeltStriktNullableEintraege(assertionCreator, *otherAssertionCreators)"))
fun <E : Any, T : Iterable<E?>> Assert<T>.enthaeltStrikt(assertionCreator: (Assert<E>.() -> Unit)?, vararg otherAssertionCreators: (Assert<E>.() -> Unit)?): AssertionPlant<T>
    = enthaeltExakt(assertionCreator, *otherAssertionCreators)

@Deprecated("Use the extension fun `enthaeltStriktNullableEintraege` instead; will be removed with 1.0.0", ReplaceWith("plant.enthaeltStriktNullableEintraege(assertionCreator, *otherAssertionCreators)"))
fun <E : Any, T : Iterable<E?>> enthaeltStriktNullable(plant: Assert<T>, assertionCreator: (Assert<E>.() -> Unit)?, vararg otherAssertionCreators: (Assert<E>.() -> Unit)?): AssertionPlant<T>
    = plant.enthaeltStriktNullableEintraege(assertionCreator, *otherAssertionCreators)

@Deprecated("Use `enthaeltNicht.nullableWerte` instead. This fun is only here to retain binary compatibility; will be removed with 1.0.0", ReplaceWith("enthaeltNicht.nullableWerte(expected, *otherExpected)"))
fun <E, T : Iterable<E>> Assert<T>.enthaeltNicht(expected: E, vararg otherExpected: E)
    = enthaeltNicht.nullableWerte(expected, *otherExpected)

@Deprecated(
    "Replaced with enthaeltExakt for clearer naming; will be removed with 1.0.0",
    ReplaceWith("enthaeltExakt(expected,*otherExpected)", "ch.tutteli.atrium.api.cc.de_CH.enthaeltExakt")
)
fun <E : Any, T : Iterable<E>> Assert<T>.enthaeltStrikt(expected: E, vararg otherExpected: E): AssertionPlant<T>
    = enthaeltExakt(expected, *otherExpected)

@Deprecated(
    "Replaced with enthaeltExakt for clearer naming; will be removed with 1.0.0",
    ReplaceWith("enthaeltExakt(expectedOrNull)", "ch.tutteli.atrium.api.cc.de_CH.enthaeltExakt")
)
fun <E : Any?, T : Iterable<E>> Assert<T>.enthaeltStriktNullableWert(expectedOrNull: E): AssertionPlant<T>
    = enthaeltExakt(expectedOrNull)

@Deprecated(
    "Replaced with enthaeltExakt for clearer naming; will be removed with 1.0.0",
    ReplaceWith("enthaeltExakt(expectedOrNull,*otherExpectedOrNulls)", "ch.tutteli.atrium.api.cc.de_CH.enthaeltExakt")
)
fun <E : Any?, T : Iterable<E>> Assert<T>.enthaeltStriktNullableWerte(expectedOrNull: E, vararg otherExpectedOrNulls: E): AssertionPlant<T>
    = enthaeltExakt(expectedOrNull, *otherExpectedOrNulls)

@Deprecated(
    "Replaced with enthaeltExakt for clearer naming; will be removed with 1.0.0",
    ReplaceWith("enthaeltExakt(assertionCreator,*otherAssertionCreators)", "ch.tutteli.atrium.api.cc.de_CH.enthaeltExakt")
)
fun <E : Any, T : Iterable<E>> Assert<T>.enthaeltStrikt(assertionCreator: Assert<E>.() -> Unit, vararg otherAssertionCreators: Assert<E>.() -> Unit): AssertionPlant<T>
    = enthaeltExakt(assertionCreator, *otherAssertionCreators)

@Deprecated(
    "Replaced with enthaeltExaktNullableEintrag for clearer naming; will be removed with 1.0.0",
    ReplaceWith("enthaeltExaktNullableEintrag(assertionCreatorOrNull)", "ch.tutteli.atrium.api.cc.de_CH.enthaeltExaktNullableEintrag")
)
fun <E : Any, T : Iterable<E?>> Assert<T>.enthaeltStriktNullableEintrag(assertionCreatorOrNull: (Assert<E>.() -> Unit)?): AssertionPlant<T>
    = enthaeltExakt(assertionCreatorOrNull)

@Deprecated(
    "Replaced with enthaeltExakt for clearer naming; will be removed with 1.0.0",
    ReplaceWith("enthaeltExakt(assertionCreatorOrNull,*otherAssertionCreatorsOrNulls)", "ch.tutteli.atrium.api.cc.de_CH.enthaeltExakt")
)
fun <E : Any, T : Iterable<E?>> Assert<T>.enthaeltStriktNullableEintraege(assertionCreatorOrNull: (Assert<E>.() -> Unit)?, vararg otherAssertionCreatorsOrNulls: (Assert<E>.() -> Unit)?): AssertionPlant<T>
    = enthaeltExakt(assertionCreatorOrNull, *otherAssertionCreatorsOrNulls)


@JvmName("irgendEinNullableDeprecated")
@Deprecated("Use `irgendEiner` instead; will be removed with 1.0.0", ReplaceWith("irgendEiner(assertionCreatorOrNull)"))
fun <E: Any, T: Iterable<E?>> Assert<T>.irgendEinNullable(assertionCreatorOrNull: (Assert<E>.() -> Unit)?): AssertionPlant<T>
    = irgendEiner(assertionCreatorOrNull)

@JvmName("keinerDerNullableDeprecated")
@Deprecated("Use `keiner` instead; will be removed with 1.0.0", ReplaceWith("keiner(assertionCreatorOrNull)"))
fun <E : Any, T : Iterable<E?>> Assert<T>.keinerDerNullable(assertionCreatorOrNull: (Assert<E>.() -> Unit)?)
    = keiner(assertionCreatorOrNull)

@JvmName("alleDerNullableDeprecated")
@Deprecated("Use `alle` instead; will be removed with 1.0.0", ReplaceWith("alle(assertionCreatorOrNull)"))
fun <E : Any, T : Iterable<E?>> Assert<T>.alleDerNullable(assertionCreatorOrNull: (Assert<E>.() -> Unit)?)
    = alle(assertionCreatorOrNull)
