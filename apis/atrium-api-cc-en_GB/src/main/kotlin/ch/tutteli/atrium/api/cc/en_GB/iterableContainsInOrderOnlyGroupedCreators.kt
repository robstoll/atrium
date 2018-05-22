package ch.tutteli.atrium.api.cc.en_GB

import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.domain.builders.AssertImpl
import ch.tutteli.atrium.domain.builders.utils.GroupWithoutNullableEntries
import ch.tutteli.atrium.domain.builders.utils.GroupWithNullableEntries
import ch.tutteli.atrium.domain.builders.utils.groupsToList
import ch.tutteli.atrium.domain.creating.iterable.contains.IterableContains
import ch.tutteli.atrium.domain.creating.iterable.contains.searchbehaviours.InOrderOnlyGroupedWithinSearchBehaviour

/**
 * Finishes the specification of the sophisticated `contains` assertion where the expected [firstGroup] as well as
 * the [secondGroup] and optionally [otherExpectedGroups] of values need to be contained in [Iterable] as only elements
 * and in the specified order whereas the values within the groups can occur in any order.
 *
 * @param firstGroup A group of values which have to appear at first within the [Iterable].
 * @param secondGroup A group of values which have to appear after the values of the [firstGroup] within the [Iterable].
 * @param otherExpectedGroups Additional groups of values which are expected to appear after the [secondGroup] within
 *   [Iterable] whereas the groups have to appear in the given order.
 *
 * @return The [AssertionPlant] for which the assertion was built to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
fun <E : Any, T : Iterable<E>> IterableContains.Builder<E, T, InOrderOnlyGroupedWithinSearchBehaviour>.inAnyOrder(
    firstGroup: GroupWithoutNullableEntries<E>,
    secondGroup: GroupWithoutNullableEntries<E>,
    vararg otherExpectedGroups: GroupWithoutNullableEntries<E>
): AssertionPlant<T> = plant.addAssertion(
    AssertImpl.iterable.contains.valuesInOrderOnlyGrouped(
        this,
        groupsToList(firstGroup, secondGroup, otherExpectedGroups)
    )
)

/**
 * Finishes the specification of the sophisticated `contains` assertion where the expected [firstGroup] as well as
 * the [secondGroup] and optionally [otherExpectedGroups] of nullable values need to be contained in [Iterable]
 * as only elements and in the specified order whereas the values within the groups can occur in any order.
 *
 * @param firstGroup A group of values which have to appear at first within the [Iterable].
 * @param secondGroup A group of values which have to appear after the values of the [firstGroup] within the [Iterable].
 * @param otherExpectedGroups Additional groups of values which are expected to appear after the [secondGroup] within
 *   [Iterable] whereas the groups have to appear in the given order.
 *
 * @return The [AssertionPlant] for which the assertion was built to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
fun <E, T : Iterable<E>> IterableContains.Builder<E, T, InOrderOnlyGroupedWithinSearchBehaviour>.inAnyOrder(
    firstGroup: GroupWithNullableEntries<E>,
    secondGroup: GroupWithNullableEntries<E>,
    vararg otherExpectedGroups: GroupWithNullableEntries<E>
): AssertionPlant<T> = plant.addAssertion(
    AssertImpl.iterable.contains.valuesInOrderOnlyGrouped(
        this,
        groupsToList(firstGroup, secondGroup, otherExpectedGroups)
    )
)


/**
 * Finishes the specification of the sophisticated `contains` assertion where the expected [firstGroup] as well as
 * the [secondGroup] and optionally [otherExpectedGroups] of identification lambdas, identifying an entry,
 * need to be contained in [Iterable] as only elements and in the specified order whereas
 * the identification lambdas within the groups can occur in any order.
 *
 * Have a look at [entries] for more information about identification lambdas.
 *
 * @param firstGroup A group of identification lambdas which have to appear at first within the [Iterable].
 * @param secondGroup A group of identification lambdas which have to appear after the values of the [firstGroup] within the [Iterable].
 * @param otherExpectedGroups Additional groups of values which are expected to appear after the [secondGroup] within
 *   [Iterable] whereas the groups have to appear in the given order.
 *
 * @return The [AssertionPlant] for which the assertion was built to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@JvmName("inAnyOrderEntries")
fun <E : Any, T : Iterable<E>> IterableContains.Builder<E, T, InOrderOnlyGroupedWithinSearchBehaviour>.inAnyOrder(
    firstGroup: GroupWithoutNullableEntries<Assert<E>.() -> Unit>,
    secondGroup: GroupWithoutNullableEntries<Assert<E>.() -> Unit>,
    vararg otherExpectedGroups: GroupWithoutNullableEntries<Assert<E>.() -> Unit>
): AssertionPlant<T> = plant.addAssertion(
    AssertImpl.iterable.contains.entriesInOrderOnlyGrouped(
        this,
        groupsToList(firstGroup, secondGroup, otherExpectedGroups)
    )
)

/**
 * Finishes the specification of the sophisticated `contains` assertion where the expected [firstGroup] as well as
 * the [secondGroup] and optionally [otherExpectedGroups] of identification lambdas, identifying an entry,
 * need to be contained in [Iterable] as only elements and in the specified order whereas
 * the identification lambdas within the groups can occur in any order.
 *
 * An identification lambda can also be defined with `null` in which case it matches an entry which is `null` as well.
 * Have a look at [entries] for more information about identification lambdas.
 *
 * @param firstGroup A group of identification lambdas which have to appear at first within the [Iterable].
 * @param secondGroup A group of identification lambdas which have to appear after the values of the [firstGroup] within the [Iterable].
 * @param otherExpectedGroups Additional groups of values which are expected to appear after the [secondGroup] within
 *   [Iterable] whereas the groups have to appear in the given order.
 *
 * @return The [AssertionPlant] for which the assertion was built to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@JvmName("inAnyOrderNullableEntries")
fun <E : Any, T : Iterable<E?>> IterableContains.Builder<E?, T, InOrderOnlyGroupedWithinSearchBehaviour>.inAnyOrder(
    firstGroup: GroupWithNullableEntries<(Assert<E>.() -> Unit)?>,
    secondGroup: GroupWithNullableEntries<(Assert<E>.() -> Unit)?>,
    vararg otherExpectedGroups: GroupWithNullableEntries<(Assert<E>.() -> Unit)?>
): AssertionPlant<T> = plant.addAssertion(
    AssertImpl.iterable.contains.entriesInOrderOnlyGrouped(
        this,
        groupsToList(firstGroup, secondGroup, otherExpectedGroups)
    )
)
