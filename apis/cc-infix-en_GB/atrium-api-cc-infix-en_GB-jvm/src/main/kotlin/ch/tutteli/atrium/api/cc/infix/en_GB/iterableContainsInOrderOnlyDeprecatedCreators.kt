@file:JvmMultifileClass
@file:JvmName("IterableContainsInOrderOnlyCreatorsKt")
package ch.tutteli.atrium.api.cc.infix.en_GB

import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.domain.creating.iterable.contains.IterableContains
import ch.tutteli.atrium.domain.creating.iterable.contains.searchbehaviours.InOrderOnlySearchBehaviour

@JvmName("nullableValueDeprecated")
@Deprecated("use `value` instead; will be removed with 1.0.0", ReplaceWith("this value expectedOrNull"))
infix fun <E : Any?, T : Iterable<E>> IterableContains.Builder<E, T, InOrderOnlySearchBehaviour>.nullableValue(expectedOrNull: E): AssertionPlant<T>
    = this value expectedOrNull

@JvmName("nullableEntryDeprecated")
@Deprecated("use `entry` instead; will be removed with 1.0.0", ReplaceWith("this entry assertionCreatorOrNull"))
infix fun <E : Any, T : Iterable<E?>> IterableContains.Builder<E?, T, InOrderOnlySearchBehaviour>.nullableEntry(assertionCreatorOrNull: (Assert<E>.() -> Unit)?): AssertionPlant<T>
    = this entry assertionCreatorOrNull
