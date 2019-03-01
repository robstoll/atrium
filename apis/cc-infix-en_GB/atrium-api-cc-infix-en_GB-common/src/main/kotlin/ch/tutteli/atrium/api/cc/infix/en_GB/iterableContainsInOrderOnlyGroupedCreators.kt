@file:JvmMultifileClass
@file:JvmName("IterableContainsInOrderOnlyGroupedCreatorsKt")

package ch.tutteli.atrium.api.cc.infix.en_GB

import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.domain.builders.AssertImpl
import ch.tutteli.atrium.domain.builders.utils.Group
import ch.tutteli.atrium.domain.builders.utils.groupsToList
import ch.tutteli.atrium.domain.creating.iterable.contains.IterableContains
import ch.tutteli.atrium.domain.creating.iterable.contains.searchbehaviours.InOrderOnlyGroupedWithinSearchBehaviour
import kotlin.jvm.JvmMultifileClass
import kotlin.jvm.JvmName

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
    order: Order<E, Group<E>>
): AssertionPlant<T> = plant.addAssertion(
    AssertImpl.iterable.contains.valuesInOrderOnlyGrouped(
        this,
        groupsToList(order.firstGroup, order.secondGroup, order.otherExpectedGroups)
    )
)

/**
 * Finishes the specification of the sophisticated `contains` assertion where the expected [Order.firstGroup] as well as
 * the [Order.secondGroup] and optionally [Order.otherExpectedGroups] of nullable values need to be
 * contained in [Iterable] in the specified order whereas the values within the groups can occur in any order.
 *
 * This function will be renamed on the JVM level to inAnyOrderNullableValues with 1.0.0;
 *
 * @param order A parameter object containing the different groups which have to appear in order in the [Iterable].
 *
 * @return The [AssertionPlant] for which the assertion was built to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@JvmName("inAnyOrderNullableGroupedValues")
infix fun <E, T : Iterable<E>> IterableContains.Builder<E, T, InOrderOnlyGroupedWithinSearchBehaviour>.inAny(
    order: Order<E, Group<E>>
): AssertionPlant<T> = plant.addAssertion(
    AssertImpl.iterable.contains.valuesInOrderOnlyGrouped(
        this,
        groupsToList(order.firstGroup, order.secondGroup, order.otherExpectedGroups)
    )
)


/**
 * Finishes the specification of the sophisticated `contains` assertion where the expected [Order.firstGroup] as well as
 * the [Order.secondGroup] and optionally [Order.otherExpectedGroups] of identification lambdas, identifying an entry,
 * need to be contained in [Iterable] in the specified order whereas the identification lambdas within the groups
 * can occur in any order.
 *
 * This function will be renamed on the JVM level to inAnyOrderEntries with 1.0.0;
 *
 * @param order A parameter object containing the different groups which have to appear in order in the [Iterable].
 *
 * @return The [AssertionPlant] for which the assertion was built to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@JvmName("inAnyOrderGroupedEntries")
infix fun <E : Any, T : Iterable<E>> IterableContains.Builder<E, T, InOrderOnlyGroupedWithinSearchBehaviour>.inAny(
    order: Order<Assert<E>.() -> Unit, Group<Assert<E>.() -> Unit>>
): AssertionPlant<T> = plant.addAssertion(
    AssertImpl.iterable.contains.entriesInOrderOnlyGrouped(
        this,
        groupsToList(order.firstGroup, order.secondGroup, order.otherExpectedGroups)
    )
)

/**
 * Finishes the specification of the sophisticated `contains` assertion where the expected [Order.firstGroup] as well as
 * the [Order.secondGroup] and optionally [Order.otherExpectedGroups] of identification lambdas, identifying an entry,
 * need to be contained in [Iterable] in the specified order whereas the identification lambdas within the groups
 * can occur in any order.
 *
 * An identification lambda can also be defined with `null` in which case it matches an entry which is `null` as well.
 *
 * This function will be renamed on the JVM level to inAnyOrderNullableEntries with 1.0.0;
 *
 * @param order A parameter object containing the different groups which have to appear in order in the [Iterable].
 *
 * @return The [AssertionPlant] for which the assertion was built to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@JvmName("inAnyOrderGroupedNullableEntries")
infix fun <E : Any, T : Iterable<E?>> IterableContains.Builder<E?, T, InOrderOnlyGroupedWithinSearchBehaviour>.inAny(
    order: Order<(Assert<E>.() -> Unit)?, Group<(Assert<E>.() -> Unit)?>>
): AssertionPlant<T> = plant.addAssertion(
    AssertImpl.iterable.contains.entriesInOrderOnlyGrouped(
        this,
        groupsToList(order.firstGroup, order.secondGroup, order.otherExpectedGroups)
    )
)
