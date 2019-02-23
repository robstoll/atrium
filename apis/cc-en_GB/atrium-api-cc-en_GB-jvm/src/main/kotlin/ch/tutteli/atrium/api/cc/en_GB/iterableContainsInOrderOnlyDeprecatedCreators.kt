@file:JvmMultifileClass
@file:JvmName("IterableContainsInOrderOnlyCreatorsKt")
package ch.tutteli.atrium.api.cc.en_GB

import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.domain.builders.AssertImpl
import ch.tutteli.atrium.domain.creating.iterable.contains.IterableContains
import ch.tutteli.atrium.domain.creating.iterable.contains.searchbehaviours.InOrderOnlySearchBehaviour
import ch.tutteli.kbox.glue

@JvmName("nullableValueDeprecated")
@Deprecated("use `value` instead; will be removed with 1.0.0", ReplaceWith("value(expectedOrNull)"))
infix fun <E : Any?, T : Iterable<E>> IterableContains.Builder<E, T, InOrderOnlySearchBehaviour>.nullableValue(expectedOrNull: E): AssertionPlant<T>
    = value(expectedOrNull)

@JvmName("nullableEntryDeprecated")
@Deprecated("use `entry` instead; will be removed with 1.0.0", ReplaceWith("entry(assertionCreatorOrNull)"))
infix fun <E : Any, T : Iterable<E?>> IterableContains.Builder<E?, T, InOrderOnlySearchBehaviour>.nullableEntry(assertionCreatorOrNull: (Assert<E>.() -> Unit)?): AssertionPlant<T>
    = entry(assertionCreatorOrNull)

@JvmName("nullableValuesDeprecated")
@Deprecated("use `values` instead; will be removed with 1.0.0", ReplaceWith("values(expectedOrNull, *otherExpectedOrNulls)"))
fun <E : Any?, T : Iterable<E>> IterableContains.Builder<E, T, InOrderOnlySearchBehaviour>.nullableValues(expectedOrNull: E, vararg otherExpectedOrNulls: E): AssertionPlant<T>
    = values(expectedOrNull, *otherExpectedOrNulls)

@JvmName("nullableEntriesDeprecated")
@Deprecated("use `entries` instead; will be removed with 1.0.0", ReplaceWith("entries(assertionCreatorOrNull, *otherAssertionCreatorsOrNulls)"))
fun <E : Any, T : Iterable<E?>> IterableContains.Builder<E?, T, InOrderOnlySearchBehaviour>.nullableEntries(
    assertionCreatorOrNull: (Assert<E>.() -> Unit)?,
    vararg otherAssertionCreatorsOrNulls: (Assert<E>.() -> Unit)?
): AssertionPlant<T> = entries(assertionCreatorOrNull, *otherAssertionCreatorsOrNulls)
