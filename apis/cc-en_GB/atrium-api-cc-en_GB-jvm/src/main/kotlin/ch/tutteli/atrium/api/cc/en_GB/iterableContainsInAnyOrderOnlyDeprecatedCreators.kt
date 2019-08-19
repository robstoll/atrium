@file:Suppress("DEPRECATION" /* will be removed with 1.0.0 */)
@file:JvmMultifileClass
@file:JvmName("IterableContainsInAnyOrderOnlyCreatorsKt")
package ch.tutteli.atrium.api.cc.en_GB

import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.domain.builders.AssertImpl
import ch.tutteli.atrium.domain.creating.iterable.contains.IterableContains
import ch.tutteli.atrium.domain.creating.iterable.contains.searchbehaviours.InAnyOrderOnlySearchBehaviour
import ch.tutteli.kbox.glue

@Deprecated("use `value` instead; will be removed with 1.0.0", ReplaceWith("value(expectedOrNull)"))
infix fun <E : Any?, T : Iterable<E>> IterableContains.Builder<E, T, InAnyOrderOnlySearchBehaviour>.nullableValue(expectedOrNull: E): AssertionPlant<T>
    = value(expectedOrNull)

@Deprecated("use `entry` instead; will be removed with 1.0.0", ReplaceWith("entry(assertionCreatorOrNull)"))
infix fun <E : Any, T : Iterable<E?>> IterableContains.Builder<E?, T, InAnyOrderOnlySearchBehaviour>.nullableEntry(assertionCreatorOrNull: (Assert<E>.() -> Unit)?): AssertionPlant<T>
    = entry(assertionCreatorOrNull)

@Deprecated("use `values` instead; will be removed with 1.0.0", ReplaceWith("values(expectedOrNull, *otherExpectedOrNulls)"))
fun <E : Any?, T : Iterable<E>> IterableContains.Builder<E, T, InAnyOrderOnlySearchBehaviour>.nullableValues(expectedOrNull: E, vararg otherExpectedOrNulls: E): AssertionPlant<T>
    = values(expectedOrNull, *otherExpectedOrNulls)

@Deprecated("use `entries` instead; will be removed with 1.0.0", ReplaceWith("entries(assertionCreatorOrNull, *otherAssertionCreatorsOrNulls)"))
fun <E : Any, T : Iterable<E?>> IterableContains.Builder<E?, T, InAnyOrderOnlySearchBehaviour>.nullableEntries(
    assertionCreatorOrNull: (Assert<E>.() -> Unit)?,
    vararg otherAssertionCreatorsOrNulls: (Assert<E>.() -> Unit)?
): AssertionPlant<T> = entries(assertionCreatorOrNull, *otherAssertionCreatorsOrNulls)
