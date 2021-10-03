// @formatter:off
//---------------------------------------------------
//  Generated content, modify:
//  logic/generateLogic.gradle
//  if necessary - enjoy the day 🙂
//---------------------------------------------------

@file:Suppress("DEPRECATION" /* TODO remove annotation with 1.0.0 */)

package ch.tutteli.atrium.logic.creating.iterable.contains.creators

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.creating.iterable.contains.IterableLikeContains
import ch.tutteli.atrium.logic.creating.iterablelike.contains.reporting.InOrderOnlyReportingOptions
import ch.tutteli.atrium.logic.creating.iterable.contains.searchbehaviours.InAnyOrderOnlySearchBehaviour
import ch.tutteli.atrium.logic.creating.iterable.contains.searchbehaviours.InOrderOnlyGroupedSearchBehaviour
import ch.tutteli.atrium.logic.creating.iterable.contains.searchbehaviours.InOrderOnlySearchBehaviour
import ch.tutteli.atrium.logic.creating.typeutils.IterableLike
import ch.tutteli.atrium.core.ExperimentalNewExpectTypes
import ch.tutteli.atrium.logic.creating.iterable.contains.creators.impl.DefaultIterableLikeContainsAssertions


fun <E, T : IterableLike> IterableLikeContains.EntryPointStepLogic<E, T, InAnyOrderOnlySearchBehaviour>.valuesInAnyOrderOnly(expected: List<E>): Assertion = impl.valuesInAnyOrderOnly(this, expected)

fun <E : Any, T : IterableLike> IterableLikeContains.EntryPointStepLogic<out E?, T, InAnyOrderOnlySearchBehaviour>.entriesInAnyOrderOnly(assertionCreators: List<(Expect<E>.() -> Unit)?>): Assertion = impl.entriesInAnyOrderOnly(this, assertionCreators)


fun <E, T : IterableLike> IterableLikeContains.EntryPointStepLogic<E, T, InOrderOnlySearchBehaviour>.valuesInOrderOnly(expected: List<E>, reportingOptions: InOrderOnlyReportingOptions.() -> Unit): Assertion =
    impl.valuesInOrderOnly(this, expected, reportingOptions)

fun <E : Any, T : IterableLike> IterableLikeContains.EntryPointStepLogic<out E?, T, InOrderOnlySearchBehaviour>.entriesInOrderOnly(assertionCreators: List<(Expect<E>.() -> Unit)?>, reportingOptions: InOrderOnlyReportingOptions.() -> Unit): Assertion =
    impl.entriesInOrderOnly(this, assertionCreators, reportingOptions)


fun <E, T : IterableLike> IterableLikeContains.EntryPointStepLogic<E, T, InOrderOnlyGroupedSearchBehaviour>.valuesInOrderOnlyGrouped(groups: List<List<E>>, reportingOptions: InOrderOnlyReportingOptions.() -> Unit): Assertion =
    impl.valuesInOrderOnlyGrouped(this, groups, reportingOptions)

fun <E : Any, T : IterableLike> IterableLikeContains.EntryPointStepLogic<out E?, T, InOrderOnlyGroupedSearchBehaviour>.entriesInOrderOnlyGrouped(groups: List<List<(Expect<E>.() -> Unit)?>>, reportingOptions: InOrderOnlyReportingOptions.() -> Unit): Assertion =
    impl.entriesInOrderOnlyGrouped(this, groups, reportingOptions)

@Suppress("DEPRECATION" /* OptIn is only available since 1.3.70 which we cannot use if we want to support 1.2 */)
@UseExperimental(ExperimentalNewExpectTypes::class)
private inline val <E, T : Any, S : IterableLikeContains.SearchBehaviour> IterableLikeContains.EntryPointStepLogic<E, T, S>.impl: IterableLikeContainsAssertions
    get() = container.getImpl(IterableLikeContainsAssertions::class) { DefaultIterableLikeContainsAssertions() }
