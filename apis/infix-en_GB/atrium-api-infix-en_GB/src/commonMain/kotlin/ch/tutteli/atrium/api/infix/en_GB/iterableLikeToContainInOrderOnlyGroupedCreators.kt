package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.api.infix.en_GB.creating.iterable.Order
import ch.tutteli.atrium.api.infix.en_GB.creating.iterable.WithInAnyOrderOnlyReportingOptions
import ch.tutteli.atrium.api.infix.en_GB.creating.iterable.WithInOrderOnlyReportingOptions
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic._logicAppend
import ch.tutteli.atrium.logic.creating.iterable.contains.IterableLikeContains.EntryPointStep
import ch.tutteli.atrium.logic.creating.iterable.contains.creators.entriesInOrderOnlyGrouped
import ch.tutteli.atrium.logic.creating.iterable.contains.creators.valuesInOrderOnlyGrouped
import ch.tutteli.atrium.logic.creating.iterable.contains.searchbehaviours.InOrderOnlyGroupedWithinSearchBehaviour
import ch.tutteli.atrium.logic.creating.iterablelike.contains.reporting.InAnyOrderOnlyReportingOptions
import ch.tutteli.atrium.logic.creating.iterablelike.contains.reporting.InOrderOnlyReportingOptions
import ch.tutteli.atrium.logic.creating.typeutils.IterableLike
import ch.tutteli.atrium.logic.utils.Group
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
): Expect<T> = inAny(withoutReportingOptions(order))

private fun <E> withoutReportingOptions(order: Order<E, Group<E>>) =
    WithInOrderOnlyReportingOptions({}, WithInAnyOrderOnlyReportingOptions({}, order))

/**
 * Finishes the specification of the sophisticated `contains` assertion where the expected [Order.firstGroup] as well as
 * the [Order.secondGroup] and optionally [Order.otherExpectedGroups] of values need to be
 * contained in [IterableLike] in the specified order whereas the values within the groups can occur in any order.
 *
 * @param order A parameter object containing the different groups which have to appear in order in the [IterableLike]
 *   plus one lambda configuring the [InOrderOnlyReportingOptions] and another configuring the
 *   [InAnyOrderOnlyReportingOptions] -- use the function:
 *   - `order(group, group, ..., report = { ... })` to create a [WithInOrderOnlyReportingOptions] with a wrapped
 *   [WithInAnyOrderOnlyReportingOptions] which specifies nothing and in turn wraps an [Order].
 *   - `order(group, group, ..., reportInGroup = { ... })` to create a [WithInOrderOnlyReportingOptions] which specifies
 *   nothing but wraps a [WithInOrderOnlyReportingOptions] which is configured via `reportInGroup` and in turn wraps an
 *   [Order].
 *   - `order(group, group, ..., report = { ... }, reportInGroup = { ... } )` to create a
 *   [WithInOrderOnlyReportingOptions] (configured via `report`) with a wrapped  [WithInAnyOrderOnlyReportingOptions]
 *   (configured via `reportInGroup`) which in turn wraps an [Order].
 *
 *   where `group` above (in all examples) is either `value(e)` or `values(e, ...)`;
 *   so a call could look as follows: `inAny order(values(1, 2), value(2), values(3, 2), report = { showOnlyFailing() })
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @since 0.18.0
 */
infix fun <E, T : IterableLike> EntryPointStep<E, T, InOrderOnlyGroupedWithinSearchBehaviour>.inAny(
    order: WithInOrderOnlyReportingOptions<WithInAnyOrderOnlyReportingOptions<Order<E, Group<E>>>>
): Expect<T> = _logicAppend {
    valuesInOrderOnlyGrouped(order.t.t.toList(), order.options, order.t.options)
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
): Expect<T> = inAny(withoutReportingOptions(order))

/**
 * Finishes the specification of the sophisticated `contains` assertion where the expected [Order.firstGroup] as well as
 * the [Order.secondGroup] and optionally [Order.otherExpectedGroups] of identification lambdas, identifying an entry,
 * need to be contained in [IterableLike] in the specified order whereas the identification lambdas within the groups
 * can occur in any order.
 *
 * An identification lambda can also be defined as `null` in which case it matches an entry which is `null` as well.
 *
 * @param order A parameter object containing the different groups which have to appear in order in the [IterableLike]
 *   plus one lambda configuring the [InOrderOnlyReportingOptions] and another configuring the
 *   [InAnyOrderOnlyReportingOptions] -- use the function:
 *   - `order(group, group, ..., report = { ... })` to create a [WithInOrderOnlyReportingOptions] with a wrapped
 *   [WithInAnyOrderOnlyReportingOptions] which specifies nothing and in turn wraps an [Order].
 *   - `order(group, group, ..., reportInGroup = { ... })` to create a [WithInOrderOnlyReportingOptions] which specifies
 *   nothing but wraps a [WithInOrderOnlyReportingOptions] which is configured via `reportInGroup` and in turn wraps an
 *   [Order].
 *   - `order(group, group, ..., report = { ... }, reportInGroup = { ... } )` to create a
 *   [WithInOrderOnlyReportingOptions] (configured via `report`) with a wrapped  [WithInAnyOrderOnlyReportingOptions]
 *   (configured via `reportInGroup`) which in turn wraps an [Order].
 *    plus a lambda configuring the [InOrderOnlyReportingOptions] -- use the function
 *   `order(group, group, ..., report = { ... })`  to create a [WithInOrderOnlyReportingOptions] with a wrapped [Order]
 *   where group is either `entry { ... }` or `entries({ ... }, ...)`; so a call could look as follows:
 *   ```
 *   inAny order(
 *     entry { it toEqual 1 },
 *     entries({ it lessThan 2 }, { it toEqual 3 })
 *     report = { showOnlyFailing }
 *     reportInGroup = { showOnlyFailingIfMoreElementsThan(5) }
 *   )
 *   ```
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @since 0.18.0
 */
@JvmName("inAnyOrderEntries")
infix fun <E : Any, T : IterableLike> EntryPointStep<out E?, T, InOrderOnlyGroupedWithinSearchBehaviour>.inAny(
    order: WithInOrderOnlyReportingOptions<WithInAnyOrderOnlyReportingOptions<Order<(Expect<E>.() -> Unit)?, Group<(Expect<E>.() -> Unit)?>>>>
): Expect<T> = _logicAppend {
    entriesInOrderOnlyGrouped(order.t.t.toList(), order.options, order.t.options)
}

/**
 * Helper function to create an [Order] based on the given [firstGroup], [secondGroup] and [otherExpectedGroups].
 */
fun <E> order(
    firstGroup: Group<E>,
    secondGroup: Group<E>,
    vararg otherExpectedGroups: Group<E>
): Order<E, Group<E>> = Order(firstGroup, secondGroup, otherExpectedGroups)

/**
 * Helper function to create a [WithInOrderOnlyReportingOptions] wrapping a [WithInAnyOrderOnlyReportingOptions]
 * which wraps in turn an [Order] based on the given
 * [firstGroup], [secondGroup] and [otherExpectedGroups] as well as the given [report]-configuration-lambda for
 * the [WithInOrderOnlyReportingOptions].
 *
 * @since 0.18.0
 */
fun <E> order(
    firstGroup: Group<E>,
    secondGroup: Group<E>,
    vararg otherExpectedGroups: Group<E>,
    report: InOrderOnlyReportingOptions.() -> Unit
): WithInOrderOnlyReportingOptions<WithInAnyOrderOnlyReportingOptions<Order<E, Group<E>>>> =
    order(firstGroup, secondGroup, *otherExpectedGroups, report = report, reportInGroup = { })

/**
 * Helper function to create a [WithInOrderOnlyReportingOptions] wrapping a [WithInAnyOrderOnlyReportingOptions]
 * which wraps in turn an [Order] based on the given
 * [firstGroup], [secondGroup] and [otherExpectedGroups] as well as the given [reportInGroup]-configuration-lambda for
 * the [WithInAnyOrderOnlyReportingOptions].
 *
 * @since 0.18.0
 */
@JvmName("orderWithReportInGroup")
fun <E> order(
    firstGroup: Group<E>,
    secondGroup: Group<E>,
    vararg otherExpectedGroups: Group<E>,
    reportInGroup: InAnyOrderOnlyReportingOptions.() -> Unit
): WithInOrderOnlyReportingOptions<WithInAnyOrderOnlyReportingOptions<Order<E, Group<E>>>> =
    order(firstGroup, secondGroup, *otherExpectedGroups, report = {}, reportInGroup = reportInGroup)

/**
 * Helper function to create a [WithInOrderOnlyReportingOptions] wrapping a [WithInAnyOrderOnlyReportingOptions]
 * which wraps in turn an [Order] based on the given
 * [firstGroup], [secondGroup] and [otherExpectedGroups] as well as the given [report]-configuration-lambda for
 * the [WithInOrderOnlyReportingOptions] and the [reportInGroup]-configuration-lambda for
 * the [WithInAnyOrderOnlyReportingOptions].
 *
 * @since 0.18.0
 */
fun <E> order(
    firstGroup: Group<E>,
    secondGroup: Group<E>,
    vararg otherExpectedGroups: Group<E>,
    report: InOrderOnlyReportingOptions.() -> Unit,
    reportInGroup: InAnyOrderOnlyReportingOptions.() -> Unit
): WithInOrderOnlyReportingOptions<WithInAnyOrderOnlyReportingOptions<Order<E, Group<E>>>> =
    WithInOrderOnlyReportingOptions(
        report,
        WithInAnyOrderOnlyReportingOptions(reportInGroup, Order(firstGroup, secondGroup, otherExpectedGroups))
    )
