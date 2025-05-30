@file:Suppress("NOTHING_TO_INLINE")

package ch.tutteli.atrium.creating

import ch.tutteli.atrium._coreAppend
import ch.tutteli.atrium.core.None
import ch.tutteli.atrium.core.Option
import ch.tutteli.atrium.core.Some
import ch.tutteli.atrium.creating.proofs.Proof
import ch.tutteli.atrium.creating.proofs.ProofGroup
import ch.tutteli.atrium.creating.proofs.builders.buildProof
import ch.tutteli.atrium.creating.transformers.TransformationExecutionStep
import ch.tutteli.atrium.reporting.reportables.InlineElement
import ch.tutteli.atrium.reporting.reportables.Reportable
import ch.tutteli.kbox.mapFirst

/**
 * Use this function if you want to state expectations about a feature of the subject or you perform a
 * type transformation or any other action which results in an [Expect] being created for a different subject than the
 * current and you do not require this resulting [Expect] but are only interested in the resulting proofs.
 *
 * Or in other words, you do not want to state further expectations about the resulting subject in the resulting sub
 * [Expect].
 *
 * Note that a proof will be added which fails in case the given [expectationCreatorWithUsageHints] does not append a single
 * [Proof].
 *
 * It uses the [ProofContainer.maybeSubject] as subject of the given [ExpectationCreatorWithUsageHints.expectationCreator]
 * and delegates to [collectBasedOnGivenSubject].
 *
 * @param expectationCreatorWithUsageHints defines the [ExpectationCreator]-lambda as well as the usage hints which
 *   are shown in case no expectation is created within the lambda.
 *
 * @return The collected proof
 */
inline fun <SubjectT> ProofContainer<SubjectT>.collect(
    expectationCreatorWithUsageHints: ExpectationCreatorWithUsageHints<SubjectT>
): Proof = collectBasedOnGivenSubject(maybeSubject, expectationCreatorWithUsageHints)


//TODO 1.3.0 KDOC (see next function) and check if Boolean flag is used somewhere
//TODO 1.3.0 rename to collectForProofExplanation?
/**
 * @since 1.3.0
 */
inline fun <SomeSubjectT> ProofContainer<*>.collectForFailureHint(
    expectationCreatorWithUsageHints: ExpectationCreatorWithUsageHints<SomeSubjectT>
): Pair<List<Proof>, Boolean> =
    collectForCompositionBasedOnGivenSubject(None, expectationCreatorWithUsageHints).mapFirst { collectedProofs ->
        maybeSubject.fold({
            // already in a proofExplanation group no need to wrap again
            collectedProofs
        }, {
            listOf(buildProof {
                invisibleFailingProofGroup {
                    proofExplanation { addAll(collectedProofs) }
                }
            })
        })
    }

/**
 * Use this function if you want to collect [Proof]s and use it as part of another [Proof] (e.g. as part
 * of a [ProofGroup]).
 *
 * Note that a proof will be added which fails in case the given [expectationCreatorWithUsageHints] does not append a single
 * [Proof].
 *
 * It uses the [ProofContainer.maybeSubject] as subject of the given [ExpectationCreatorWithUsageHints.expectationCreator]
 * and delegates to [collectBasedOnGivenSubject].
 *
 * @param expectationCreatorWithUsageHints defines the [ExpectationCreator]-lambda as well as the usage hints which
 *   are shown in case no expectation is created within the lambda.
 *
 * @return The collected proofs and a boolean flag indicating whether at least one proof was appended or not.
 */
//TODO 1.3.0 better rename to collectForFailureHint as well?
inline fun <SubjectT> ProofContainer<SubjectT>.collectForComposition(
    expectationCreatorWithUsageHints: ExpectationCreatorWithUsageHints<SubjectT>
): Pair<List<Proof>, Boolean> =
    collectForCompositionBasedOnGivenSubject(maybeSubject, expectationCreatorWithUsageHints)

/**
 * Use this function if you want to state expectations about a feature of the subject or you perform a
 * type transformation or any other action which results in an [Expect] being created for a different subject than the
 * current and you do not require this resulting [Expect] but are only interested in the resulting proofs.
 *
 * Or in other words, you do not want to state further expectations about the resulting subject in the resulting sub
 * [Expect].
 *
 * Note that a proof will be added which fails in case the given [expectationCreatorWithUsageHints] does not append a single
 * [Proof].
 *
 * @param maybeSubject Either [Some] wrapping the subject of the current expectation or
 *   [None] in case a previous subject transformation was not successful -
 *   this will be used as subject for the given [expectationCreatorWithUsageHints].
 * @param expectationCreatorWithUsageHints defines the [ExpectationCreator]-lambda as well as the usage hints which
 *   are shown in case no expectation is created within the lambda.
 *
 * @return The collected proofs and a boolean flag indicating whether at least one proof was appended or not.
 */
//TODO check if it makes more sense to stay on the core level for expectationCreatorWithUsageHints, i.e. to use ProofContainer<T>.() -> Unit instead of Expect<T>.() -> Unit
//TODO 1.3.0 document params
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
 * Use this function if you want to collect [Proof]s and use it as part of another [Proof] (e.g. as part
 * of a [ProofGroup]).
 *
 * Note that a proof will be added which fails in case the given [expectationCreatorWithUsageHints] does not append a single
 * [Proof].
 *
 * @param maybeSubject Either [Some] wrapping the subject of the current expectation or
 *   [None] in case a previous subject transformation was not successful -
 *   this will be used as subject for the given [expectationCreatorWithUsageHints].
 * @param expectationCreatorWithUsageHints defines the [ExpectationCreator]-lambda as well as the usage hints which
 *   are shown in case no expectation is created within the lambda.
 *
 * @return The collected proofs and a boolean flag indicating whether at least one proof was appended or not.
 */
//TODO 1.3.0 check where the flag is used, we had a todo that we should return a flag but so far I don't see a usage
// where it would make sense
//TODO 1.3.0 better rename to collectForFailureHintBasedOnGivenSubject -- if you rename, adjust @Deprecated in logic?
@OptIn(ExperimentalComponentFactoryContainer::class)
inline fun <SomeSubjectT> ProofContainer<*>.collectForCompositionBasedOnGivenSubject(
    maybeSubject: Option<SomeSubjectT>,
    expectationCreatorWithUsageHints: ExpectationCreatorWithUsageHints<SomeSubjectT>
): Pair<List<Proof>, Boolean> = CollectingExpect(maybeSubject, components)
    .appendAsGroupIndicateIfOneCollected(expectationCreatorWithUsageHints).mapFirst { it.getCollectedProofs() }

/**
 * Finishes the transformation process by appending the [Proof]
 * which is returned when calling [TransformationExecutionStep.collectAndAppend] with [_coreAppend]
 * and the given [expectationCreator].
 *
 * See [collectBasedOnGivenSubject] for more information.
 *
 * @return an [Expect] for the subject of this expectation.
 */
//TODO 1.3.0 remove, if yes, then adjust @Deprecated in logic
fun <SubjectT, SubjectAfterTransformationT, ExpectForNewSubjectT : Expect<SubjectAfterTransformationT>> TransformationExecutionStep<SubjectT, SubjectAfterTransformationT, ExpectForNewSubjectT>.collectAndCoreAppend(
    usageHintsAlternativeWithoutExpectationCreator: List<InlineElement>,
    expectationCreator: ProofContainer<SubjectAfterTransformationT>.() -> Proof
): Expect<SubjectT> =
    collectAndAppend(ExpectationCreatorWithUsageHints(usageHintsAlternativeWithoutExpectationCreator) {
        _coreAppend(expectationCreator)
    })
