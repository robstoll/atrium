package ch.tutteli.atrium.api.cc.infix.en_GB

import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.domain.builders.AssertImpl
import ch.tutteli.atrium.domain.builders.utils.groupsToList
import ch.tutteli.atrium.domain.creating.iterable.contains.IterableContains
import ch.tutteli.atrium.domain.creating.iterable.contains.searchbehaviours.InOrderOnlyGroupedWithinSearchBehaviour

/**
 * Finishes the specification of the sophisticated `contains` assertion where the expected [Order.firstGroup] as well as
 * the [Order.secondGroup] and optionally [Order.otherExpectedGroups] of values need to be contained in [Iterable] in
 * the specified order whereas the values within the groups can occur in any order.
 *
 * @param order A parameter object containing the different groups which have to appear in order in the [Iterable].
 *
 * @return The [AssertionPlant] for which the assertion was built to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */

infix fun <E : Any, T : Iterable<E>> IterableContains.Builder<E, T, InOrderOnlyGroupedWithinSearchBehaviour>.inAny(
    order: Order<E>
): AssertionPlant<T> = plant.addAssertion(
    AssertImpl.iterable.contains.valuesInOrderOnlyGrouped(
        this,
        groupsToList(order.firstGroup, order.secondGroup, order.otherExpectedGroups)
    )
)
