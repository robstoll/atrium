@file:Suppress("DEPRECATION" /* will be removed with 1.0.0 */)
@file:JvmMultifileClass
@file:JvmName("IterableContainsInAnyOrderCreatorsKt")
package ch.tutteli.atrium.api.cc.en_GB

import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.domain.creating.iterable.contains.IterableContains
import ch.tutteli.atrium.domain.creating.iterable.contains.searchbehaviours.InAnyOrderSearchBehaviour

@Deprecated("use `value` instead; will be removed with 1.0.0", ReplaceWith("value(expectedOrNull)"))
infix fun <E : Any?, T : Iterable<E>> IterableContains.CheckerOption<E, T, InAnyOrderSearchBehaviour>.nullableValue(expectedOrNull: E): AssertionPlant<T>
    = value(expectedOrNull)

@Deprecated("use `entry` instead; will be removed with 1.0.0", ReplaceWith("entry(assertionCreatorOrNull)"))
infix fun <E : Any, T : Iterable<E?>> IterableContains.CheckerOption<E?, T, InAnyOrderSearchBehaviour>.nullableEntry(assertionCreatorOrNull: (Assert<E>.() -> Unit)?): AssertionPlant<T>
    = entry(assertionCreatorOrNull)

@Deprecated("use `values` instead; will be removed with 1.0.0", ReplaceWith("values(expectedOrNull, *otherExpectedOrNulls)"))
fun <E : Any?, T : Iterable<E>> IterableContains.CheckerOption<E, T, InAnyOrderSearchBehaviour>.nullableValues(expectedOrNull: E, vararg otherExpectedOrNulls: E): AssertionPlant<T>
    = values(expectedOrNull, *otherExpectedOrNulls)

@Deprecated("use `entries` instead; will be removed with 1.0.0", ReplaceWith("entries(assertionCreatorOrNull, *otherAssertionCreatorsOrNulls)"))
fun <E : Any, T : Iterable<E?>> IterableContains.CheckerOption<E?, T, InAnyOrderSearchBehaviour>.nullableEntries(
    assertionCreatorOrNull: (Assert<E>.() -> Unit)?,
    vararg otherAssertionCreatorsOrNulls: (Assert<E>.() -> Unit)?
): AssertionPlant<T> = entries(assertionCreatorOrNull, *otherAssertionCreatorsOrNulls)
