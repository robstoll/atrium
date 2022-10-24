@file:Suppress("NOTHING_TO_INLINE")

package ch.tutteli.atrium.logic

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.assertions.builders.assertionBuilder
import ch.tutteli.atrium.assertions.builders.invisibleGroup
import ch.tutteli.atrium.core.None
import ch.tutteli.atrium.core.Option
import ch.tutteli.atrium.core.Some
import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.creating.CollectingExpect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.creating.ExperimentalComponentFactoryContainer
import ch.tutteli.atrium.logic.creating.transformers.TransformationExecutionStep


/**
 * Use this function if you want to make [Assertion]s about a feature or you perform a type transformation or any
 * other action which results in an [Expect] being created for a different subject and
 * you do not require this resulting [Expect].
 *
 * Or in other words, you do not want to make further assertions about the resulting subject in the resulting sub
 * [Expect].
 *
 * Note that an assertion will be added which fails in case [assertionCreator] does not create a single assertion.
 *
 * It uses the [AssertionContainer.maybeSubject] as subject of the given [assertionCreator] and
 * delegates to [collectBasedOnSubject].
 *
 * @param assertionCreator A lambda which defines the expectations for the [AssertionContainer.maybeSubject].
 *
 * @return The collected assertions.
 */
inline fun <T> AssertionContainer<T>.collect(noinline assertionCreator: Expect<T>.() -> Unit): Assertion =
    collectBasedOnSubject(maybeSubject, assertionCreator)


/**
 * Use this function if you want to collect [Assertion]s and use it as part of another [Assertion] (e.g. as part
 * of an [AssertionGroup]).
 *
 * Note that an assertion will be added which fails in case [assertionCreator] does not create a single assertion.
 *
 * It uses the [AssertionContainer.maybeSubject] as subject of the given [assertionCreator] and
 * delegates to [collectForCompositionBasedOnSubject].
 *
 * @param assertionCreator A lambda which defines the expectations for the [AssertionContainer.maybeSubject].
 *
 * @return The collected assertions as a `List<[Assertion]>`.
 */
//TODO 0.19.0 refactor with ProofContainer, return a flag which indicates whether no assertion was created by the assertionCreator
inline fun <T> AssertionContainer<T>.collectForComposition(
    noinline assertionCreator: Expect<T>.() -> Unit
): List<Assertion> = collectForCompositionBasedOnSubject(maybeSubject, assertionCreator)

/**
 * Use this function if you want to make [Assertion]s about a feature or you perform a type transformation or any
 * other action which results in an [Expect] being created for a different subject and
 * you do not require this resulting [Expect].
 *
 * Or in other words, you do not want to make further assertions about the resulting subject in the resulting sub
 * [Expect].
 *
 * Note that an assertion will be added which fails in case [assertionCreator] does not create a single assertion.
 *
 * @param maybeSubject Either [Some] wrapping the subject of the current assertion or
 *   [None] in case a previous subject transformation was not successful -
 *   this will be used as subject for the given [assertionCreator].
 * @param assertionCreator A lambda which defines the expectations for the given [maybeSubject].
 *
 * @return The collected assertions.
 */
//TODO check if it makes more sense to stay on the logic level for assertionCreator
//TODO 0.19.0 deprecate and move to ProofContainer
inline fun <T> AssertionContainer<*>.collectBasedOnSubject(
    maybeSubject: Option<T>,
    noinline assertionCreator: Expect<T>.() -> Unit
): Assertion {
    val collectedAssertions = collectForCompositionBasedOnSubject(maybeSubject, assertionCreator)
    return if (collectedAssertions.size > 1) {
        assertionBuilder.invisibleGroup.withAssertions(collectedAssertions).build()
    } else {
        collectedAssertions[0]
    }
}

/**
 * Use this function if you want to collect [Assertion]s and use it as part of another [Assertion] (e.g. as part
 * of an [AssertionGroup]).
 *
 * Note that an assertion will be added which fails in case [assertionCreator] does not create a single assertion.

 * @param assertionCreator A lambda which defines the expectations for the [AssertionContainer.maybeSubject].
 *
 * @return The collected assertions as a `List<[Assertion]>`.
 */
//TODO 0.19.0 refactor with ProofContainer, return a flag which indicates whether no assertion was created by the assertionCreator
@Suppress("DEPRECATION" /* RequiresOptIn is only available since 1.3.70 which we cannot use if we want to support 1.2 */)
@OptIn(ExperimentalComponentFactoryContainer::class)
inline fun <T> AssertionContainer<*>.collectForCompositionBasedOnSubject(
    maybeSubject: Option<T>,
    noinline assertionCreator: Expect<T>.() -> Unit
): List<Assertion> = CollectingExpect(maybeSubject, components)
    .appendAsGroup(assertionCreator)
    .getAssertions()

/**
 * Finishes the transformation process by appending the [Assertion]
 * which is returned when calling [TransformationExecutionStep.collectAndAppend] with [_logicAppend]
 * and the given [assertionCreator].
 *
 * See [collectBasedOnSubject] for more information.
 *
 * @return an [Expect] for the subject of this expectation.
 */
fun <T, R> TransformationExecutionStep<T, R, *>.collectAndLogicAppend(assertionCreator: AssertionContainer<R>.() -> Assertion): Expect<T> =
    collectAndAppend { _logicAppend(assertionCreator) }
