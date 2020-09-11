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
import ch.tutteli.atrium.logic.creating.iterable.contains.searchbehaviours.InAnyOrderOnlySearchBehaviour
import ch.tutteli.atrium.logic.creating.iterable.contains.searchbehaviours.InOrderOnlyGroupedSearchBehaviour
import ch.tutteli.atrium.logic.creating.iterable.contains.searchbehaviours.InOrderOnlySearchBehaviour
import ch.tutteli.atrium.logic.creating.typeutils.IterableLike


fun <E, T : IterableLike> IterableLikeContains.EntryPointStepLogic<E, T, InAnyOrderOnlySearchBehaviour>.valuesInAnyOrderOnly(expected: List<E>): Assertion = _iterableLikeContainsImpl.valuesInAnyOrderOnly(this, expected)

fun <E : Any, T : IterableLike> IterableLikeContains.EntryPointStepLogic<out E?, T, InAnyOrderOnlySearchBehaviour>.entriesInAnyOrderOnly(assertionCreators: List<(Expect<E>.() -> Unit)?>): Assertion = _iterableLikeContainsImpl.entriesInAnyOrderOnly(this, assertionCreators)


fun <E, T : IterableLike> IterableLikeContains.EntryPointStepLogic<E, T, InOrderOnlySearchBehaviour>.valuesInOrderOnly(expected: List<E>): Assertion = _iterableLikeContainsImpl.valuesInOrderOnly(this, expected)

fun <E : Any, T : IterableLike> IterableLikeContains.EntryPointStepLogic<out E?, T, InOrderOnlySearchBehaviour>.entriesInOrderOnly(assertionCreators: List<(Expect<E>.() -> Unit)?>): Assertion = _iterableLikeContainsImpl.entriesInOrderOnly(this, assertionCreators)


fun <E, T : IterableLike> IterableLikeContains.EntryPointStepLogic<E, T, InOrderOnlyGroupedSearchBehaviour>.valuesInOrderOnlyGrouped(groups: List<List<E>>): Assertion = _iterableLikeContainsImpl.valuesInOrderOnlyGrouped(this, groups)

fun <E : Any, T : IterableLike> IterableLikeContains.EntryPointStepLogic<out E?, T, InOrderOnlyGroupedSearchBehaviour>.entriesInOrderOnlyGrouped(groups: List<List<(Expect<E>.() -> Unit)?>>): Assertion = _iterableLikeContainsImpl.entriesInOrderOnlyGrouped(this, groups)
