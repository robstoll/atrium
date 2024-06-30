@file:Suppress("NOTHING_TO_INLINE")

package ch.tutteli.atrium.domain

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.core.None
import ch.tutteli.atrium.core.Option
import ch.tutteli.atrium.core.Some
import ch.tutteli.atrium.creating.*
import ch.tutteli.atrium.creating.proofs.Proof
import ch.tutteli.atrium.domain.creating.transformers.TransformationExecutionStep
import ch.tutteli.atrium.domain.reporting.buildProof
import ch.tutteli.atrium.reporting.reportables.Reportable
import ch.tutteli.kbox.mapFirst

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
 * delegates to [collectBasedOnGivenSubject].
 *
 * @param expectationCreator A lambda which defines the expectations for the [ProofContainer.maybeSubject].
 *
 * @return The collected proof
 */
//TODO 1.3.0 document params, revise KDOC (especially regarding Assertion, same same for other functions)
inline fun <SubjectT> ProofContainer<SubjectT>.collect(
    expectationCreatorWithUsageHints: ExpectationCreatorWithUsageHints<SubjectT>
): Proof = collectBasedOnGivenSubject(maybeSubject, expectationCreatorWithUsageHints)


//TODO 1.3.0 KDOC and check name again, maybe better something like collectForFailureHint?
/**
 * @since 1.3.0
 */
//TODO 1.3.0 check if Boolean flag is used somewhere
inline fun <SomeSubjectT> ProofContainer<*>.collectForFailureHint(
    expectationCreatorWithUsageHints: ExpectationCreatorWithUsageHints<SomeSubjectT>
): Pair<List<Proof>, Boolean> =
    collectForCompositionBasedOnGivenSubject(None, expectationCreatorWithUsageHints).mapFirst { collectedProofs ->
        maybeSubject.fold({
            // already in an explanatory expectation-group, no need to wrap again
            collectedProofs
        }, {
            listOf(buildProof {
                invisibleFixedClaimGroup(holds = false) {
                    explanatoryGroup { addAll(collectedProofs) }
                }
            })
        })
    }

/**
 * Use this function if you want to collect [Assertion]s and use it as part of another [Assertion] (e.g. as part
 * of an [AssertionGroup]).
 *
 * Note that an assertion will be added which fails in case [expectationCreator] does not create a single assertion.
 *
 * It uses the [ProofContainer.maybeSubject] as subject of the given [expectationCreator] and
 * delegates to [collectForCompositionBasedOnGivenSubject].
 *
 * @param expectationCreator A lambda which defines the expectations for the [ProofContainer.maybeSubject].
 *
 * @return The collected assertions as a `List<[Assertion]>`.
 */
//TODO 1.3.0 document params
inline fun <SubjectT> ProofContainer<SubjectT>.collectForComposition(
    expectationCreatorWithUsageHints: ExpectationCreatorWithUsageHints<SubjectT>
): Pair<List<Proof>, Boolean> =
    collectForCompositionBasedOnGivenSubject(maybeSubject, expectationCreatorWithUsageHints)

/**
 * Use this function if you want to make [Assertion]s about a feature or you perform a type transformation or any
 * other action which results in an [Expect] being created for a different subject and
 * you do not require this resulting [Expect].
 *
 * Or in other words, you do not want to make further assertions about the resulting subject in the resulting sub
 * [Expect].
 *
 * Note that an assertion will be added which fails in case [expectationCreator] does not create a single assertion.
 *
 * @param maybeSubject Either [Some] wrapping the subject of the current assertion or
 *   [None] in case a previous subject transformation was not successful -
 *   this will be used as subject for the given [expectationCreator].
 * @param expectationCreator A lambda which defines the expectations for the given [maybeSubject].
 *
 * @return The collected assertions.
 */
//TODO check if it makes more sense to stay on the logic level for expectationCreator, i.e. to use ProofContainer<T>.() -> Unit
//TODO 1.3.0 document params
//TODO 1.3.0 check replaceWith of logic, we renamed the function from collectBasedOnSubject to collectBasedOnGivenSubject
inline fun <SomeSubjectT> ProofContainer<*>.collectBasedOnGivenSubject(
    maybeSubject: Option<SomeSubjectT>,
    expectationCreatorWithUsageHints: ExpectationCreatorWithUsageHints<SomeSubjectT>
): Proof {
    //TODO 1.3.0 check if it makes sense to return a Proof, maybe List<Proof> would be better?
    val (collectedProofs, _) = collectForCompositionBasedOnGivenSubject(maybeSubject, expectationCreatorWithUsageHints)
    return if (collectedProofs.size > 1) {
        buildProof { invisibleGroup { addAll(collectedProofs) } }
    } else {
        collectedProofs[0]
    }
}

/**
 * Use this function if you want to collect [Assertion]s and use it as part of another [Assertion] (e.g. as part
 * of an [AssertionGroup]).
 *
 * Note that an assertion will be added which fails in case [expectationCreator] does not create a single assertion.

 * @param expectationCreator A lambda which defines the expectations for the [ProofContainer.maybeSubject].
 *
 * @return The collected expectations as a `List<[Assertion]>`.
 */
//TODO 1.3.0 check where the flag is used, we had a todo that we should return a flag but so far I don't see a usage
// where it would make sense
//TODO 1.3.0 check replaceWith of logic, we renamed the function from collectForCompositionBasedOnSubject to collectForCompositionBasedOnGivenSubject
@OptIn(ExperimentalComponentFactoryContainer::class)
inline fun <SomeSubjectT> ProofContainer<*>.collectForCompositionBasedOnGivenSubject(
    maybeSubject: Option<SomeSubjectT>,
    expectationCreatorWithUsageHints: ExpectationCreatorWithUsageHints<SomeSubjectT>
): Pair<List<Proof>, Boolean> = CollectingExpect(maybeSubject, components)
    .appendAsGroupIndicateIfOneCollected(expectationCreatorWithUsageHints).mapFirst { it.getCollectedProofs() }

/**
 * Finishes the transformation process by appending the [Proof]
 * which is returned when calling [TransformationExecutionStep.collectAndAppend] with [_domainAppend]
 * and the given [expectationCreator].
 *
 * See [collectBasedOnGivenSubject] for more information.
 *
 * @return an [Expect] for the subject of this expectation.
 */
fun <SubjectT, SubjectAfterTransformationT, ExpectForNewSubjectT : Expect<SubjectAfterTransformationT>> TransformationExecutionStep<SubjectT, SubjectAfterTransformationT, ExpectForNewSubjectT>.collectAndDomainAppend(
    usageHintsOverloadWithoutExpectationCreator: List<Reportable>,
    expectationCreator: ProofContainer<SubjectAfterTransformationT>.() -> Proof
): Expect<SubjectT> =
    collectAndAppend(ExpectationCreatorWithUsageHints(usageHintsOverloadWithoutExpectationCreator) {
        _domainAppend(expectationCreator)
    })
