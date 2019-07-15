@file:JvmMultifileClass
@file:JvmName("IterableContainsInOrderOnlyGroupedCreatorsKt")
@file:Suppress("DEPRECATION" /* TODO remove with 1.0.0 */)
package ch.tutteli.atrium.api.cc.en_GB

import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.domain.builders.utils.GroupWithNullableEntries
import ch.tutteli.atrium.domain.builders.utils.GroupWithoutNullableEntries
import ch.tutteli.atrium.domain.creating.iterable.contains.IterableContains
import ch.tutteli.atrium.domain.creating.iterable.contains.searchbehaviours.InOrderOnlyGroupedWithinSearchBehaviour


@Deprecated(
    "Use the extension function which expects Group instead; will be removed with 1.0.0",
    ReplaceWith("builder.inAnyOrder(firstGroup, secondGroup, *otherExpectedGroups)")
)
fun <E : Any, T : Iterable<E>> inAnyOrder(
    builder: IterableContains.Builder<E, T, InOrderOnlyGroupedWithinSearchBehaviour>,
    firstGroup: GroupWithoutNullableEntries<E>,
    secondGroup: GroupWithoutNullableEntries<E>,
    vararg otherExpectedGroups: GroupWithoutNullableEntries<E>
): AssertionPlant<T> = builder.inAnyOrder(firstGroup, secondGroup, *otherExpectedGroups)

@Deprecated(
    "Use the extension function which expects Group instead; will be removed with 1.0.0",
    ReplaceWith("builder.inAnyOrder(firstGroup, secondGroup, *otherExpectedGroups)")
)
fun <E, T : Iterable<E>> inAnyOrder(
    builder: IterableContains.Builder<E, T, InOrderOnlyGroupedWithinSearchBehaviour>,
    firstGroup: GroupWithNullableEntries<E>,
    secondGroup: GroupWithNullableEntries<E>,
    vararg otherExpectedGroups: GroupWithNullableEntries<E>
): AssertionPlant<T> = builder.inAnyOrder(firstGroup, secondGroup, *otherExpectedGroups)

@Deprecated(
    "Use the extension function which expects Group instead; will be removed with 1.0.0",
    ReplaceWith("builder.inAnyOrder(firstGroup, secondGroup, *otherExpectedGroups)")
)
@JvmName("inAnyOrderEntries")
fun <E : Any, T : Iterable<E>> inAnyOrder(
    builder: IterableContains.Builder<E, T, InOrderOnlyGroupedWithinSearchBehaviour>,
    firstGroup: GroupWithoutNullableEntries<Assert<E>.() -> Unit>,
    secondGroup: GroupWithoutNullableEntries<Assert<E>.() -> Unit>,
    vararg otherExpectedGroups: GroupWithoutNullableEntries<Assert<E>.() -> Unit>
): AssertionPlant<T> = builder.inAnyOrder(firstGroup, secondGroup, *otherExpectedGroups)


@Deprecated(
    "Use the extension function which expects Group instead; will be removed with 1.0.0",
    ReplaceWith("builder.inAnyOrder(firstGroup, secondGroup, *otherExpectedGroups)")
)
@JvmName("inAnyOrderNullableEntries")
fun <E : Any, T : Iterable<E?>> inAnyOrder(
    builder: IterableContains.Builder<E?, T, InOrderOnlyGroupedWithinSearchBehaviour>,
    firstGroup: GroupWithNullableEntries<(Assert<E>.() -> Unit)?>,
    secondGroup: GroupWithNullableEntries<(Assert<E>.() -> Unit)?>,
    vararg otherExpectedGroups: GroupWithNullableEntries<(Assert<E>.() -> Unit)?>
): AssertionPlant<T> = builder.inAnyOrder(firstGroup, secondGroup, *otherExpectedGroups)
