//---------------------------------------------------
//  Generated content, modify:
//  logic/generateLogic.gradle
//  if necessary - enjoy the day 🙂
//---------------------------------------------------

@file:Suppress("DEPRECATION" /* TODO remove annotation with 1.0.0 */)

package ch.tutteli.atrium.logic.creating.iterable.contains.creators

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.creating.typeutils.IterableLike
import ch.tutteli.atrium.logic.creating.iterable.contains.IterableLikeContains
import ch.tutteli.atrium.logic.creating.iterable.contains.searchbehaviours.InAnyOrderSearchBehaviour


fun <E, T : IterableLike> IterableLikeContains.CheckerStepLogic<E, T, InAnyOrderSearchBehaviour>.values(expected: List<E>): Assertion = _iterableLikeContainsInAnyOrderImpl.values(this, expected)

fun <E : Any, T : IterableLike> IterableLikeContains.CheckerStepLogic<out E?, T, InAnyOrderSearchBehaviour>.entries(assertionCreators: List<(Expect<E>.() -> Unit)?>): Assertion = _iterableLikeContainsInAnyOrderImpl.entries(this, assertionCreators)
