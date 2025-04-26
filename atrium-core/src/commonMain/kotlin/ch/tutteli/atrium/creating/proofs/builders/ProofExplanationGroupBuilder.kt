package ch.tutteli.atrium.creating.proofs.builders

import ch.tutteli.atrium.core.None
import ch.tutteli.atrium.creating.ExpectationCreatorWithUsageHints
import ch.tutteli.atrium.creating.ProofContainer
import ch.tutteli.atrium.creating.collectForCompositionBasedOnGivenSubject
import ch.tutteli.atrium.creating.proofs.Proof
import ch.tutteli.atrium.creating.proofs.builders.impl.BaseSubGroupBuilder
import ch.tutteli.atrium.reporting.reportables.Diagnostic
import ch.tutteli.atrium.reporting.reportables.ProofExplanation

/** @since 1.3.0 */
class ProofExplanationGroupBuilder<SubjectT>(
    proofContainer: ProofContainer<SubjectT>,
) : BaseSubGroupBuilder<SubjectT, ProofExplanation, ProofExplanationGroupBuilder<SubjectT>>(
    proofContainer,
    { children -> Diagnostic.proofExplanation(Proof.invisibleGroup(children)) }
) {

    //TODO 1.3.0 add KDoc
    //TODO 1.3.0 remove again? don't see it used yet
    fun <SomeSubjectT> collectWithoutSubject(
        expectationCreatorWithUsageHints: ExpectationCreatorWithUsageHints<SomeSubjectT>
    ): Boolean = proofContainer.collectForCompositionBasedOnGivenSubject(None, expectationCreatorWithUsageHints)
        .let { (proofs, oneCollected) ->
            addAll(proofs)
            oneCollected
        }
}
