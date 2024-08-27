package ch.tutteli.atrium.creating.transformers.impl

import ch.tutteli.atrium.core.Option
import ch.tutteli.atrium.creating.ExpectationCreatorWithUsageHints
import ch.tutteli.atrium.creating.ProofContainer
import ch.tutteli.atrium.creating.proofs.AnyBuilder
import ch.tutteli.atrium.creating.proofs.Proof
import ch.tutteli.atrium.creating.proofs.buildProof
import ch.tutteli.atrium.creating.transformers.SubjectChanger
import ch.tutteli.atrium.creating.transformers.propertiesOfThrowable

class ThrowableThrownFailureHandler<SubjectT : Throwable?, SubjectAfterChangeT> :
    SubjectChanger.FailureHandler<SubjectT, SubjectAfterChangeT> {

    override fun createProof(
        container: ProofContainer<SubjectT>,
        proof: Proof,
        maybeExpectationCreatorWithUsageHints: Option<ExpectationCreatorWithUsageHints<SubjectAfterChangeT>>
    ): Proof = container.buildProof {
        add(proof)
        maybeExpectationCreatorWithUsageHints.ifDefined { expectationCreatorWithUsageHints ->
            proofExplanation {
                collectWithoutSubject(expectationCreatorWithUsageHints)
            }
        }
        container.maybeSubject.ifDefined {
            if (it != null) add(container.propertiesOfThrowable(it))
        }
    }
}

/**
 * Hook for platform specific additional hints.
 */
expect fun AnyBuilder.addAdditionalHints(throwable: Throwable)
