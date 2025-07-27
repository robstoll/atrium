// @formatter:off
//---------------------------------------------------
//  Generated content, don't change here but in:
//  gradle/build-logic/dev/src/main/kotlin/generation.kt
//  Enjoy the day 🙂
//---------------------------------------------------
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
