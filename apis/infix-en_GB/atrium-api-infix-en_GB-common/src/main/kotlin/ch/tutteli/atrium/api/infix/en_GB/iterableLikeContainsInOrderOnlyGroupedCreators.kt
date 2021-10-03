//TODO rename file to iterableLikeToContain... in 0.18.0
package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.api.infix.en_GB.creating.iterable.Order
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.utils.Group
import ch.tutteli.atrium.logic.creating.typeutils.IterableLike
import ch.tutteli.atrium.logic._logicAppend
import ch.tutteli.atrium.logic.creating.iterable.contains.IterableLikeContains.EntryPointStep
import ch.tutteli.atrium.logic.creating.iterable.contains.creators.entriesInOrderOnlyGrouped
import ch.tutteli.atrium.logic.creating.iterable.contains.creators.valuesInOrderOnlyGrouped
import ch.tutteli.atrium.logic.creating.iterable.contains.searchbehaviours.InOrderOnlyGroupedWithinSearchBehaviour
import kotlin.jvm.JvmName

/**
 * Finishes the specification of the sophisticated `contains` assertion where the expected [Order.firstGroup] as well as
 * the [Order.secondGroup] and optionally [Order.otherExpectedGroups] of values need to be
 * contained in [IterableLike] in the specified order whereas the values within the groups can occur in any order.
 *
 * @param order A parameter object containing the different groups which have to appear in order in the [IterableLike]
 *   -- use `order(group, group, ...)` to create an [Order] where group is either `value(e)` or `values(e, ...)`;
 *   so a call could look as follows: `inAny order(values(1, 2), value(2), values(3, 2))
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @since 0.14.0 -- API existed for [Iterable] but not for [IterableLike].
 */

infix fun <E, T : IterableLike> EntryPointStep<E, T, InOrderOnlyGroupedWithinSearchBehaviour>.inAny(
    order: Order<E, Group<E>>
): Expect<T> = _logicAppend {
    valuesInOrderOnlyGrouped(
        order.toList(),
        //TODO 0.18.0 add: report: InOrderOnlyReportingOptions.() -> Unit = {}
        {}
    )
}

/**
 * Finishes the specification of the sophisticated `contains` assertion where the expected [Order.firstGroup] as well as
 * the [Order.secondGroup] and optionally [Order.otherExpectedGroups] of identification lambdas, identifying an entry,
 * need to be contained in [IterableLike] in the specified order whereas the identification lambdas within the groups
 * can occur in any order.
 *
 * An identification lambda can also be defined as `null` in which case it matches an entry which is `null` as well.
 *
 * @param order A parameter object containing the different groups which have to appear in order in the [IterableLike]
 *   -- use `order(group, group, ...)` to create an [Order] where group is either `entry { ... }` or
 *   `entries({ ... }, ...)`; so a call could look as follows:
 *   ```
 *   inAny order(
 *     entry { it toEqual 1 },
 *     entries({ it lessThan 2 }, { it toEqual 3 })
 *   )
 *   ```
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @since 0.14.0 -- API existed for [Iterable] but not for [IterableLike].
 */
@JvmName("inAnyOrderEntries")
infix fun <E : Any, T : IterableLike> EntryPointStep<out E?, T, InOrderOnlyGroupedWithinSearchBehaviour>.inAny(
    order: Order<(Expect<E>.() -> Unit)?, Group<(Expect<E>.() -> Unit)?>>
): Expect<T> = _logicAppend {
    entriesInOrderOnlyGrouped(
        order.toList(),
        //TODO 0.18.0 add: report: InOrderOnlyReportingOptions.() -> Unit = {}
        {}
    )
}

/**
 * Helper function to create an [Order] based on the given [firstGroup], [secondGroup] and [otherExpectedGroups].
 */
fun <E> order(
    firstGroup: Group<E>,
    secondGroup: Group<E>,
    vararg otherExpectedGroups: Group<E>
): Order<E, Group<E>> = Order(firstGroup, secondGroup, otherExpectedGroups)
