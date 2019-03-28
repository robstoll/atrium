@file:JvmMultifileClass
@file:JvmName("IterableContainsInOrderOnlyGroupedCreatorsKt")
@file:Suppress("DEPRECATION")
package ch.tutteli.atrium.api.cc.infix.en_GB

import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.domain.builders.utils.GroupWithNullableEntries
import ch.tutteli.atrium.domain.builders.utils.GroupWithoutNullableEntries
import ch.tutteli.atrium.domain.creating.iterable.contains.IterableContains
import ch.tutteli.atrium.domain.creating.iterable.contains.searchbehaviours.InOrderOnlyGroupedWithinSearchBehaviour

@Deprecated(
    "Use the extension function which expects Group instead; will be removed with 1.0.0",
    ReplaceWith("builder inAny order")
)
@JvmName("inAnyOrderNullableValues")
fun <E, T : Iterable<E>> inAny(
    builder: IterableContains.Builder<E, T, InOrderOnlyGroupedWithinSearchBehaviour>,
    order: Order<E, GroupWithNullableEntries<E>>
): AssertionPlant<T> = builder inAny order

@Deprecated(
    "Use the extension function which expects Group instead; will be removed with 1.0.0",
    ReplaceWith(" builder inAny order")
)
@JvmName("inAnyOrderNullableEntries")
fun <E : Any, T : Iterable<E?>> inAny(
    builder: IterableContains.Builder<E?, T, InOrderOnlyGroupedWithinSearchBehaviour>,
    order: Order<(Assert<E>.() -> Unit)?, GroupWithNullableEntries<(Assert<E>.() -> Unit)?>>
): AssertionPlant<T> = builder inAny order
