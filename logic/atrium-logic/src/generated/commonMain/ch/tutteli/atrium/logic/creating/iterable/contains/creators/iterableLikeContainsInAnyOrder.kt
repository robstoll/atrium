// @formatter:off
//---------------------------------------------------
//  Generated content, modify:
//  buildSrc/generation.kt
//  if necessary - enjoy the day 🙂
//---------------------------------------------------
//TODO remove file with 2.0.0 at the latest
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.logic.creating.iterable.contains.creators

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.creating.iterable.contains.IterableLikeContains
import ch.tutteli.atrium.logic.creating.iterable.contains.searchbehaviours.InAnyOrderSearchBehaviour
import ch.tutteli.atrium.logic.creating.typeutils.IterableLike
import ch.tutteli.atrium.core.ExperimentalNewExpectTypes
import ch.tutteli.atrium.logic.creating.iterable.contains.creators.impl.DefaultIterableLikeContainsInAnyOrderAssertions


fun <E, T : IterableLike> IterableLikeContains.CheckerStepLogic<E, T, InAnyOrderSearchBehaviour>.values(expected: List<E>, notToHaveNextOrNoneFunName: String): Assertion =
    impl.values(this, expected, notToHaveNextOrNoneFunName)

fun <E : Any, T : IterableLike> IterableLikeContains.CheckerStepLogic<out E?, T, InAnyOrderSearchBehaviour>.entries(assertionCreators: List<(Expect<E>.() -> Unit)?>, notToHaveNextOrNoneFunName: String): Assertion =
    impl.entries(this, assertionCreators, notToHaveNextOrNoneFunName)

@OptIn(ExperimentalNewExpectTypes::class)
private inline val <E, T : Any> IterableLikeContains.CheckerStepLogic<E, T, InAnyOrderSearchBehaviour>.impl: IterableLikeContainsInAnyOrderAssertions
    get() = entryPointStepLogic.container.getImpl(IterableLikeContainsInAnyOrderAssertions::class) { DefaultIterableLikeContainsInAnyOrderAssertions() }
