//---------------------------------------------------
//  Generated content, modify:
//  logic/generateLogic.gradle
//  if necessary - enjoy the day 🙂
//---------------------------------------------------

package ch.tutteli.atrium.logic

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.domain.creating.changers.ExtractedFeaturePostStep
import ch.tutteli.atrium.logic.creating.iterable.contains.IterableLikeContains
import ch.tutteli.atrium.logic.creating.iterable.contains.searchbehaviours.NoOpSearchBehaviour
import ch.tutteli.atrium.logic.creating.iterable.contains.searchbehaviours.NotSearchBehaviour
import ch.tutteli.atrium.logic.creating.iterable.contains.steps.NotCheckerStep
import ch.tutteli.atrium.logic.creating.iterable.contains.steps.impl.NotCheckerStepImpl


fun <T : Any, E> AssertionContainer<T>.containsBuilder(converter: (T) -> Iterable<E>): IterableLikeContains.EntryPointStep<E, T, NoOpSearchBehaviour> = _iterableLikeImpl.containsBuilder(this, converter)

fun <T : Any, E> AssertionContainer<T>.containsNotBuilder(converter: (T) -> Iterable<E>): NotCheckerStep<E, T, NotSearchBehaviour> = _iterableLikeImpl.containsNotBuilder(this, converter)

fun <T : Any, E : Any> AssertionContainer<T>.all(converter: (T) -> Iterable<E?>, assertionCreatorOrNull: (Expect<E>.() -> Unit)?): Assertion =
    _iterableLikeImpl.all(this, converter, assertionCreatorOrNull)

fun <T : Any, E> AssertionContainer<T>.hasNext(converter: (T) -> Iterable<E>): Assertion = _iterableLikeImpl.hasNext(this, converter)

fun <T : Any, E> AssertionContainer<T>.hasNotNext(converter: (T) -> Iterable<E>): Assertion = _iterableLikeImpl.hasNotNext(this, converter)

fun <T : Any, E : Comparable<E>> AssertionContainer<T>.min(converter: (T) -> Iterable<E>): ExtractedFeaturePostStep<T, E> = _iterableLikeImpl.min(this, converter)

fun <T : Any, E : Comparable<E>> AssertionContainer<T>.max(converter: (T) -> Iterable<E>): ExtractedFeaturePostStep<T, E> = _iterableLikeImpl.max(this, converter)
