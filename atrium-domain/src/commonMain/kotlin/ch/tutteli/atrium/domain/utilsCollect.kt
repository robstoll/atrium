@file:Suppress("NOTHING_TO_INLINE")

package ch.tutteli.atrium.domain

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.assertions.builders.assertionBuilder
import ch.tutteli.atrium.assertions.builders.invisibleGroup
import ch.tutteli.atrium.core.None
import ch.tutteli.atrium.core.Option
import ch.tutteli.atrium.core.Some
import ch.tutteli.atrium.creating.ProofContainer
import ch.tutteli.atrium.creating.CollectingExpect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.creating.ExperimentalComponentFactoryContainer
import ch.tutteli.atrium.creating.proofs.Proof
import ch.tutteli.atrium.domain.creating.transformers.TransformationExecutionStep
import ch.tutteli.atrium.reporting.reportables.Reportable


/**
 * Use this function if you want to make [Assertion]s about a feature or you perform a type transformation or any
 * other action which results in an [Expect] being created for a different subject and
 * you do not require this resulting [Expect].
 *
 * Or in other words, you do not want to state further expectations about the resulting subject in the resulting sub
 * [Expect].
 *
 * Note that a proof will be added which fails in case [expectationCreator] does not append a single [Proof].
 *
 * It uses the [ProofContainer.maybeSubject] as subject of the given [expectationCreator] and
 * delegates to [collectBasedOnSubject].
 *
 * @param expectationCreator A lambda which defines the expectations for the [ProofContainer.maybeSubject].
 *
 * @return The collected proof
 */
inline fun <T> ProofContainer<T>.collect(noinline expectationCreator: Expect<T>.() -> Unit): Proof =
    collectBasedOnSubject(maybeSubject, expectationCreator)


/**
 * Use this function if you want to collect [Assertion]s and use it as part of another [Assertion] (e.g. as part
 * of an [AssertionGroup]).
 *
 * Note that an assertion will be added which fails in case [assertionCreator] does not create a single assertion.
 *
 * It uses the [ProofContainer.maybeSubject] as subject of the given [assertionCreator] and
 * delegates to [collectForCompositionBasedOnSubject].
 *
 * @param assertionCreator A lambda which defines the expectations for the [ProofContainer.maybeSubject].
 *
 * @return The collected assertions as a `List<[Assertion]>`.
 */
//TODO 1.3.0 refactor with ProofContainer, return a flag which indicates whether no assertion was created by the assertionCreator
inline fun <T> ProofContainer<T>.collectForComposition(
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
//TODO 1.3.0 deprecate and move to ProofContainer
inline fun <T> ProofContainer<*>.collectBasedOnSubject(
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

 * @param assertionCreator A lambda which defines the expectations for the [ProofContainer.maybeSubject].
 *
 * @return The collected assertions as a `List<[Assertion]>`.
 */
//TODO 1.3.0 refactor with ProofContainer, return a flag which indicates whether no assertion was created by the assertionCreator
@OptIn(ExperimentalComponentFactoryContainer::class)
inline fun <T> ProofContainer<*>.collectForCompositionBasedOnSubject(
    maybeSubject: Option<T>,
    usageHintsOverloadWithoutExpectationCreator: List<Reportable>,
    noinline assertionCreator: Expect<T>.() -> Unit
): Pair<List<Proof>, Boolean> = CollectingExpect(maybeSubject, components)
    .appendAsGroupIndicateIfOneCollected(
        assertionCreator,
        usageHintsOverloadWithoutExpectationCreator
    ).let { (expect, oneCollected) ->
        //TODO replace
        expect.getCollectedProofs() to oneCollected
    }

/**
 * Finishes the transformation process by appending the [Proof]
 * which is returned when calling [TransformationExecutionStep.collectAndAppend] with [_domainAppend]
 * and the given [expectationCreator].
 *
 * See [collectBasedOnSubject] for more information.
 *
 * @return an [Expect] for the subject of this expectation.
 */
fun <T, R> TransformationExecutionStep<T, R, *>.collectAndDomainAppend(expectationCreator: ProofContainer<R>.() -> Proof): Expect<T> =
    collectAndAppend { _domainAppend(expectationCreator) }
