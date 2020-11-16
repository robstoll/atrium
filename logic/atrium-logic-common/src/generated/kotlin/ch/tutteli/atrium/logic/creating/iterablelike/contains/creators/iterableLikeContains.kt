// @formatter:off
//---------------------------------------------------
//  Generated content, modify:
//  logic/generateLogic.gradle
//  if necessary - enjoy the day 🙂
//---------------------------------------------------

@file:Suppress("DEPRECATION" /* TODO remove annotation with 1.0.0 */)

package ch.tutteli.atrium.logic.creating.iterablelike.contains.creators

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.creating.iterablelike.contains.IterableLikeContains
import ch.tutteli.atrium.logic.creating.iterablelike.contains.searchbehaviours.InAnyOrderOnlySearchBehaviour
import ch.tutteli.atrium.logic.creating.iterablelike.contains.searchbehaviours.InOrderOnlyGroupedSearchBehaviour
import ch.tutteli.atrium.logic.creating.iterablelike.contains.searchbehaviours.InOrderOnlySearchBehaviour
import ch.tutteli.atrium.logic.creating.typeutils.IterableLike
import ch.tutteli.atrium.core.ExperimentalNewExpectTypes
import ch.tutteli.atrium.logic.creating.iterablelike.contains.creators.impl.DefaultIterableLikeContainsAssertions


fun <E, T : IterableLike> IterableLikeContains.EntryPointStepLogic<E, T, InAnyOrderOnlySearchBehaviour>.valuesInAnyOrderOnly(expected: List<E>): Assertion = impl.valuesInAnyOrderOnly(this, expected)

fun <E : Any, T : IterableLike> IterableLikeContains.EntryPointStepLogic<out E?, T, InAnyOrderOnlySearchBehaviour>.entriesInAnyOrderOnly(assertionCreators: List<(Expect<E>.() -> Unit)?>): Assertion = impl.entriesInAnyOrderOnly(this, assertionCreators)


fun <E, T : IterableLike> IterableLikeContains.EntryPointStepLogic<E, T, InOrderOnlySearchBehaviour>.valuesInOrderOnly(expected: List<E>): Assertion = impl.valuesInOrderOnly(this, expected)

fun <E : Any, T : IterableLike> IterableLikeContains.EntryPointStepLogic<out E?, T, InOrderOnlySearchBehaviour>.entriesInOrderOnly(assertionCreators: List<(Expect<E>.() -> Unit)?>): Assertion = impl.entriesInOrderOnly(this, assertionCreators)


fun <E, T : IterableLike> IterableLikeContains.EntryPointStepLogic<E, T, InOrderOnlyGroupedSearchBehaviour>.valuesInOrderOnlyGrouped(groups: List<List<E>>): Assertion = impl.valuesInOrderOnlyGrouped(this, groups)

fun <E : Any, T : IterableLike> IterableLikeContains.EntryPointStepLogic<out E?, T, InOrderOnlyGroupedSearchBehaviour>.entriesInOrderOnlyGrouped(groups: List<List<(Expect<E>.() -> Unit)?>>): Assertion = impl.entriesInOrderOnlyGrouped(this, groups)

@Suppress("DEPRECATION" /* OptIn is only available since 1.3.70 which we cannot use if we want to support 1.2 */)
@UseExperimental(ExperimentalNewExpectTypes::class)
private inline val <E, T : Any, S : IterableLikeContains.SearchBehaviour> IterableLikeContains.EntryPointStepLogic<E, T, S>.impl: IterableLikeContainsAssertions
    get() = container.getImpl(IterableLikeContainsAssertions::class) { DefaultIterableLikeContainsAssertions() }
