package ch.tutteli.atrium.domain.creating.transformers.impl

import ch.tutteli.atrium.assertions.builders.assertionBuilder
import ch.tutteli.atrium.core.Option
import ch.tutteli.atrium.core.trueProvider
import ch.tutteli.atrium.creating.ProofContainer
import ch.tutteli.atrium.creating.DelegatingExpect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain._domain
import ch.tutteli.atrium.domain.creating.transformers.SubjectChanger
import ch.tutteli.atrium.reporting.reportables.InlineElement

class DefaultSubjectChanger : SubjectChanger {
    override fun <SubjectT, SubjectAfterChangeT> unreported(
        container: ProofContainer<SubjectT>,
        transformation: (SubjectT) -> SubjectAfterChangeT
    ): Expect<SubjectAfterChangeT> =
        DelegatingExpect(
            container,
            //TODO https://github.com/robstoll/atrium/issues/387 wrap transformation with error handling.
            // Could be interesting to see the exception in the context of the assertion
            container.maybeSubject.map(transformation)
        )

    override fun <SubjectT, SubjectAfterChangeT> reported(
        container: ProofContainer<SubjectT>,
        description: InlineElement,
        representation: Any,
        transformation: (SubjectT) -> Option<SubjectAfterChangeT>,
        failureHandler: SubjectChanger.FailureHandler<SubjectT, SubjectAfterChangeT>,
        maybeSubExpectationCreator: Option<Expect<SubjectAfterChangeT>.() -> Unit>
    ): Expect<SubjectAfterChangeT> {
        val expect = DelegatingExpect(
            container,
            // TODO https://github.com/robstoll/atrium/issues/387 wrap transformation with error handling.
            //  Could be interesting to see the exception in the context of the assertion
            container.maybeSubject.flatMap(transformation)
        )
        // we can transform if maybeSubject is None as we have to be in an explaining like context in such a case,
        // even if the transformation cannot be carried out
        val shallTransform =
            container.maybeSubject.fold(trueProvider) { expect._domain.maybeSubject.isDefined() }

        val descriptiveAssertion = assertionBuilder.descriptive
            .withTest(expect) {
                // only here if transformation could be carried out
                true
            }
            .withDescriptionAndRepresentation(description, representation)
            .build()

        return if (shallTransform) {
            val e = expect._logic.append(descriptiveAssertion)
            maybeSubExpectationCreator.fold({ e }) { assertionCreator ->
                expect._logic.appendAsGroup(assertionCreator)
            }
        } else {
            val assertion = failureHandler.createProof(container, descriptiveAssertion, maybeSubExpectationCreator)
            expect._logic.append(assertion)
        }
    }
}
