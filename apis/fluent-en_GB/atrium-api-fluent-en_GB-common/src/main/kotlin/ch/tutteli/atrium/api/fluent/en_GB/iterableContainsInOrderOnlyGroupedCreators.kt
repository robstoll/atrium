@file:JvmMultifileClass
@file:JvmName("IterableContainsInOrderOnlyGroupedCreatorsKt")
@file:Suppress("DEPRECATION")
package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.builders.ExpectImpl
import ch.tutteli.atrium.domain.builders.creating.basic.contains.addAssertion
import ch.tutteli.atrium.domain.builders.utils.Group
import ch.tutteli.atrium.domain.builders.utils.groupsToList
import ch.tutteli.atrium.domain.creating.iterable.contains.IterableContains
import ch.tutteli.atrium.domain.creating.iterable.contains.searchbehaviours.InOrderOnlyGroupedWithinSearchBehaviour
import kotlin.jvm.JvmMultifileClass
import kotlin.jvm.JvmName

/**
 * Finishes the specification of the sophisticated `contains` assertion where the expected [firstGroup] as well as
 * the [secondGroup] and optionally [otherExpectedGroups] of values need to be contained in [Iterable]
 * as only elements and in the specified order whereas the values within the groups can occur in any order.
 *
 * This function will be renamed on a JVM level to `inAnyOrder` with v1.0.0
 *
 * @param firstGroup A group of values which have to appear at first within the [Iterable].
 * @param secondGroup A group of values which have to appear after the values of the [firstGroup] within the [Iterable].
 * @param otherExpectedGroups Additional groups of values which are expected to appear after the [secondGroup] within
 *   [Iterable] whereas the groups have to appear in the given order.
 *
 * @return The [Expect] for which the assertion was built to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@JvmName("inAnyOrderNullableValues")
fun <E, T : Iterable<E>> IterableContains.Builder<E, T, InOrderOnlyGroupedWithinSearchBehaviour>.inAnyOrder(
    firstGroup: Group<E>,
    secondGroup: Group<E>,
    vararg otherExpectedGroups: Group<E>
): Expect<T> = addAssertion(
    ExpectImpl.iterable.contains.valuesInOrderOnlyGrouped(
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
 * An identification lambda can also be defined as `null` in which case it matches an entry which is `null` as well.
 * Have a look at [entries] for more information about identification lambdas.
 *
 * This function will be renamed on a JVM level to `inAnyOrderEntries` with v1.0.0
 *
 * @param firstGroup A group of identification lambdas which have to appear at first within the [Iterable].
 * @param secondGroup A group of identification lambdas which have to appear after the values of the [firstGroup] within the [Iterable].
 * @param otherExpectedGroups Additional groups of values which are expected to appear after the [secondGroup] within
 *   [Iterable] whereas the groups have to appear in the given order.
 *
 * @return The [Expect] for which the assertion was built to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@JvmName("inAnyOrderNullableEntries")
fun <E : Any, T : Iterable<E?>> IterableContains.Builder<E?, T, InOrderOnlyGroupedWithinSearchBehaviour>.inAnyOrder(
    firstGroup: Group<(Expect<E>.() -> Unit)?>,
    secondGroup: Group<(Expect<E>.() -> Unit)?>,
    vararg otherExpectedGroups: Group<(Expect<E>.() -> Unit)?>
): Expect<T> = addAssertion(
    ExpectImpl.iterable.contains.entriesInOrderOnlyGrouped(
        this,
        groupsToList(firstGroup, secondGroup, otherExpectedGroups)
    )
)
