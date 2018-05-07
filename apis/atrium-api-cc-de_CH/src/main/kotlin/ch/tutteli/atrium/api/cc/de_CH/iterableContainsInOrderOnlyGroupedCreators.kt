package ch.tutteli.atrium.api.cc.de_CH

import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.domain.builders.AssertImpl
import ch.tutteli.atrium.domain.builders.utils.Group
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

fun <E : Any, T : Iterable<E>> IterableContains.Builder<E, T, InOrderOnlyGroupedWithinSearchBehaviour>.inBeliebigerReihenfolge(
    firstGroup: Group<E>,
    secondGroup: Group<E>,
    vararg otherExpectedGroups: Group<E>
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
 * Have a look at [eintraege] for more information about identification lambdas.
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
fun <E : Any, T : Iterable<E>> IterableContains.Builder<E, T, InOrderOnlyGroupedWithinSearchBehaviour>.inBeliebigerReihenfolge(
    firstGroup: Group<(AssertionPlant<E>.() -> Unit)?>,
    secondGroup: Group<(AssertionPlant<E>.() -> Unit)?>,
    vararg otherExpectedGroups: Group<(AssertionPlant<E>.() -> Unit)?>
): AssertionPlant<T> = plant.addAssertion(
    AssertImpl.iterable.contains.entriesInOrderOnlyGrouped(
        this,
        groupsToList(firstGroup, secondGroup, otherExpectedGroups)
    )
)
