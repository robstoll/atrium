@file:JvmMultifileClass
@file:JvmName("IterableContainsInAnyOrderOnlyCreatorsKt")
@file:Suppress("DEPRECATION")
package ch.tutteli.atrium.api.cc.infix.en_GB

import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.domain.creating.iterable.contains.IterableContains
import ch.tutteli.atrium.domain.creating.iterable.contains.searchbehaviours.InAnyOrderOnlySearchBehaviour

@JvmName("nullableValueDeprecated")
@Deprecated("use `value` instead; will be removed with 1.0.0", ReplaceWith("this value expectedOrNull"))
infix fun <E : Any?, T : Iterable<E>> IterableContains.Builder<E, T, InAnyOrderOnlySearchBehaviour>.nullableValue(expectedOrNull: E): AssertionPlant<T>
    = this value expectedOrNull

@JvmName("nullableEntryDeprecated")
@Deprecated("use `entry` instead; will be removed with 1.0.0", ReplaceWith("this entry assertionCreatorOrNull"))
infix fun <E : Any, T : Iterable<E?>> IterableContains.Builder<E?, T, InAnyOrderOnlySearchBehaviour>.nullableEntry(assertionCreatorOrNull: (Assert<E>.() -> Unit)?): AssertionPlant<T>
    = this entry assertionCreatorOrNull

@Deprecated("use `the Values` instead; will be removed with 1.0.0", ReplaceWith("this the Values(nullableValues.expected, *nullableValues.otherExpected)"))
infix fun <E : Any?, T : Iterable<E>> IterableContains.Builder<E, T, InAnyOrderOnlySearchBehaviour>.the(nullableValues: NullableValues<E>): AssertionPlant<T>
    = this the Values(nullableValues.expected, *nullableValues.otherExpected)

@Deprecated("use `the Entries` instead; will be removed with 1.0.0", ReplaceWith("this the Entries(nullableEntries.assertionCreatorOrNull, *nullableEntries.otherAssertionCreatorsOrNulls)"))
infix fun <E : Any, T : Iterable<E?>> IterableContains.Builder<E?, T, InAnyOrderOnlySearchBehaviour>.the(nullableEntries: NullableEntries<E>): AssertionPlant<T>
    = this the Entries(nullableEntries.assertionCreatorOrNull, *nullableEntries.otherAssertionCreatorsOrNulls)
