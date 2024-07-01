package ch.tutteli.atrium.creating.transformers.impl.builders.subjectchanger

import ch.tutteli.atrium.core.Option
import ch.tutteli.atrium.creating.ExpectationCreatorWithUsageHints
import ch.tutteli.atrium.creating.ProofContainer
import ch.tutteli.atrium.creating.proofs.Proof
import ch.tutteli.atrium.creating.transformers.SubjectChanger
import ch.tutteli.atrium.creating.proofs.buildProof

//TODO 1.3.0 merge with other failure handlers?
class DefaultFailureHandlerImpl<SubjectT, SubjectAfterChangeT> :
    SubjectChanger.FailureHandler<SubjectT, SubjectAfterChangeT> {

    override fun createProof(
        container: ProofContainer<SubjectT>,
        proof: Proof,
        maybeExpectationCreatorWithUsageHints: Option<ExpectationCreatorWithUsageHints<SubjectAfterChangeT>>
    ): Proof = maybeExpectationCreatorWithUsageHints.fold(
        { proof }
    ) { expectationCreatorWithHints ->
        container.buildProof {
            add(proof)
            explanatoryGroup {
                collect(expectationCreatorWithHints)
            }
        }
    }
}
