package ch.tutteli.atrium.domain.creating.transformers.impl.subjectchanger

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.builders.assertionBuilder
import ch.tutteli.atrium.assertions.builders.invisibleGroup
import ch.tutteli.atrium.core.None
import ch.tutteli.atrium.core.Option
import ch.tutteli.atrium.creating.ProofContainer
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.creating.proofs.Proof
import ch.tutteli.atrium.domain.creating.transformers.SubjectChanger
import ch.tutteli.atrium.domain.reporting.buildProof

class DefaultFailureHandlerImpl<SubjectT, SubjectAfterChangeT> :
    SubjectChanger.FailureHandler<SubjectT, SubjectAfterChangeT> {

    override fun createProof(
        container: ProofContainer<SubjectT>,
        proof: Proof,
        maybeExpectationCreator: Option<Expect<SubjectAfterChangeT>.() -> Unit>
    ): Proof = maybeExpectationCreator.fold({
        proof
    }) { expectationCreator ->
        container.buildProof {
            add(proof)
            explanatoryGroup {
                collect(expectationCreator)
            }
        }
        assertionBuilder.invisibleGroup
            .withAssertions(
                descriptiveAssertion,
                assertionBuilder.explanatoryGroup
                    .withDefaultType
                    .collectAssertions(container, None, expectationCreator)
                    .build()
            )
            .build()
    }

}
