//TODO 1.3.0 remove again and switch to core
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.api.fluent.logic.based.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.utils.Group
import ch.tutteli.atrium.logic.utils.groupsToList
import ch.tutteli.atrium.logic.creating.typeutils.IterableLike
import ch.tutteli.atrium.logic._logicAppend
import ch.tutteli.atrium.logic.creating.iterable.contains.IterableLikeContains.EntryPointStep
import ch.tutteli.atrium.logic.creating.iterable.contains.creators.entriesInOrderOnlyGrouped
import ch.tutteli.atrium.logic.creating.iterable.contains.creators.valuesInOrderOnlyGrouped
import ch.tutteli.atrium.logic.creating.iterable.contains.searchbehaviours.InOrderOnlyGroupedWithinSearchBehaviour
import ch.tutteli.atrium.logic.creating.iterablelike.contains.reporting.InAnyOrderOnlyReportingOptions
import ch.tutteli.atrium.logic.creating.iterablelike.contains.reporting.InOrderOnlyReportingOptions
import kotlin.jvm.JvmName

/**
 * Finishes the specification of the sophisticated `to contain` expectation where the expected [firstGroup] as well as
 * the [secondGroup] and optionally [otherExpectedGroups] of values need to be contained in [IterableLike]
 * as only elements and in the specified order whereas the values within the groups can occur in any order.
 *
 * @param firstGroup A group of values which have to appear at first within the [IterableLike].
 * @param secondGroup A group of values which have to appear after the values of the [firstGroup]
 *   within the [IterableLike].
 * @param otherExpectedGroups Additional groups of values which are expected to appear after the [secondGroup] within
 *   [IterableLike] whereas the groups have to appear in the given order.
 * @param report The lambda configuring the [InOrderOnlyReportingOptions] -- it is optional where
 *   the default [InOrderOnlyReportingOptions] apply if not specified.
 *   since 0.17.0
 * @param reportInGroup The lambda configuring the [InAnyOrderOnlyReportingOptions] -- it is optional where
 *   the default [InOrderOnlyReportingOptions] apply if not specified.
 *   since 0.18.0
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @since 0.14.0 -- API existed for [Iterable] but not for [IterableLike].
 */
fun <E, T : IterableLike> EntryPointStep<E, T, InOrderOnlyGroupedWithinSearchBehaviour>.inAnyOrder(
    firstGroup: Group<E>,
    secondGroup: Group<E>,
    vararg otherExpectedGroups: Group<E>,
    //TODO 2.0.0 consider to introduce an own Option class as showOnlyFailingIfMoreExpectedElementsThan is a bit misleading in the context of groups
    //i.e. OnlyReportingOptions
    report: InOrderOnlyReportingOptions.() -> Unit = {},
    reportInGroup: InAnyOrderOnlyReportingOptions.() -> Unit = {}
): Expect<T> = _logicAppend {
    valuesInOrderOnlyGrouped(groupsToList(firstGroup, secondGroup, otherExpectedGroups), report, reportInGroup)
}

/**
 * Finishes the specification of the sophisticated `to contain` expectation where the expected [firstGroup] as well as
 * the [secondGroup] and optionally [otherExpectedGroups] of identification lambdas, identifying an entry,
 * need to be contained in [IterableLike] as only elements and in the specified order whereas
 * the identification lambdas within the groups can occur in any order.
 *
 * An identification lambda can also be defined as `null` in which case it matches an entry which is `null` as well.
 * Have a look at [entries] for more information about identification lambdas.
 *
 * @param firstGroup A group of identification lambdas which have to appear at first within the [IterableLike].
 * @param secondGroup A group of identification lambdas which have to appear after the values of the [firstGroup]
 *   within the [IterableLike].
 * @param otherExpectedGroups Additional groups of values which are expected to appear after the [secondGroup] within
 *   [IterableLike] whereas the groups have to appear in the given order.
 * @param report The lambda configuring the [InOrderOnlyReportingOptions] -- it is optional where
 *   the default [InOrderOnlyReportingOptions] apply if not specified.
 *   since 0.17.0
 * @param reportInGroup The lambda configuring the [InAnyOrderOnlyReportingOptions] -- it is optional where
 *   the default [InOrderOnlyReportingOptions] apply if not specified.
 *   since 0.18.0
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @since 0.14.0 -- API existed for [Iterable] but not for [IterableLike].
 */
@JvmName("inAnyOrderEntries")
fun <E : Any, T : IterableLike> EntryPointStep<out E?, T, InOrderOnlyGroupedWithinSearchBehaviour>.inAnyOrder(
    firstGroup: Group<(Expect<E>.() -> Unit)?>,
    secondGroup: Group<(Expect<E>.() -> Unit)?>,
    vararg otherExpectedGroups: Group<(Expect<E>.() -> Unit)?>,
    report: InOrderOnlyReportingOptions.() -> Unit = {},
    reportInGroup: InAnyOrderOnlyReportingOptions.() -> Unit = {}
): Expect<T> = _logicAppend {
    entriesInOrderOnlyGrouped(groupsToList(firstGroup, secondGroup, otherExpectedGroups), report, reportInGroup)
}
