// TODO remove file with 1.0.0
@file:Suppress("DEPRECATION", "TYPEALIAS_EXPANSION_DEPRECATION")
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
 * the [Order.secondGroup] and optionally [Order.otherExpectedGroups] of values need to be
 * contained in [Iterable] in the specified order whereas the values within the groups can occur in any order.
 *
 * @param order A parameter object containing the different groups which have to appear in order in the [Iterable].
 *
 * @return The [AssertionPlant] for which the assertion was built to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@Deprecated(
    "Switch from api-cc-infix-en_GB to api-infix-en_GB; will be removed with 1.0.0 -- see https://github.com/robstoll/atrium/releases/tag/v0.9.0#migration for migration hints and scripts.",
    ReplaceWith(
        "this.inAny(ch.tutteli.atrium.api.infix.en_GB.order(order.firstGroup, order.secondGroup, *order.otherExpectedGroups))",
        "ch.tutteli.atrium.api.fluent.en_GB.inAny",
        "ch.tutteli.atrium.api.fluent.en_GB.order"
    )
)
infix fun <E, T : Iterable<E>> IterableContains.Builder<E, T, InOrderOnlyGroupedWithinSearchBehaviour>.inAny(
    order: Order<E, Group<E>>
): AssertionPlant<T> = addAssertion(
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
 * An identification lambda can also be defined as `null` in which case it matches an entry which is `null` as well.
 *
 * @param order A parameter object containing the different groups which have to appear in order in the [Iterable].
 *
 * @return The [AssertionPlant] for which the assertion was built to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@Deprecated(
    "Switch from api-cc-infix-en_GB to api-infix-en_GB; will be removed with 1.0.0 -- see https://github.com/robstoll/atrium/releases/tag/v0.9.0#migration for migration hints and scripts.",
    ReplaceWith(
        "this.inAny(ch.tutteli.atrium.api.infix.en_GB.order(" +
            "asExpectGroup(order.firstGroup)," +
            "asExpectGroup(order.secondGroup), " +
            "*order.otherExpectedGroups.map { asExpectGroup(it) }.toTypedArray())" +
            ")",
        "ch.tutteli.atrium.api.fluent.en_GB.inAny",
        "ch.tutteli.atrium.api.fluent.en_GB.order",
        "ch.tutteli.atrium.domain.builders.migration.asExpectGroup"
    )
)
@JvmName("inAnyOrderEntries")
infix fun <E : Any, T : Iterable<E?>> IterableContains.Builder<E?, T, InOrderOnlyGroupedWithinSearchBehaviour>.inAny(
    order: Order<(Assert<E>.() -> Unit)?, Group<(Assert<E>.() -> Unit)?>>
): AssertionPlant<T> = addAssertion(
    AssertImpl.iterable.contains.entriesInOrderOnlyGroupedWithAssert(
        this,
        groupsToList(order.firstGroup, order.secondGroup, order.otherExpectedGroups)
    )
)
