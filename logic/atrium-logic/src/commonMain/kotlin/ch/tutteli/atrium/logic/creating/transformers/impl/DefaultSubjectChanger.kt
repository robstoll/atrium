package ch.tutteli.atrium.logic.creating.transformers.impl

import ch.tutteli.atrium.assertions.builders.assertionBuilder
import ch.tutteli.atrium.core.Option
import ch.tutteli.atrium.core.trueProvider
import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.creating.DelegatingExpect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic._logic
import ch.tutteli.atrium.logic.creating.transformers.SubjectChanger
import ch.tutteli.atrium.reporting.translating.Translatable

class DefaultSubjectChanger : SubjectChanger {
    override fun <SubjectT, SubjectAfterChangeT> unreported(
        container: AssertionContainer<SubjectT>,
        transformation: (SubjectT) -> SubjectAfterChangeT
    ): Expect<SubjectAfterChangeT> =
        DelegatingExpect(
            container,
            //TODO https://github.com/robstoll/atrium/issues/387 wrap transformation with error handling.
            // Could be interesting to see the exception in the context of the assertion
            container.maybeSubject.map(transformation)
        )

    override fun <SubjectT, SubjectAfterChangeT> reported(
        container: AssertionContainer<SubjectT>,
        description: Translatable,
        representation: Any,
        transformation: (SubjectT) -> Option<SubjectAfterChangeT>,
        failureHandler: SubjectChanger.FailureHandler<SubjectT, SubjectAfterChangeT>,
        maybeSubAssertions: Option<Expect<SubjectAfterChangeT>.() -> Unit>
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
            container.maybeSubject.fold(trueProvider) { expect._logic.maybeSubject.isDefined() }

        val descriptiveAssertion = assertionBuilder.descriptive
            .withTest(expect) {
                // only here if transformation could be carried out
                true
            }
            .withDescriptionAndRepresentation(description, representation)
            .build()

        return if (shallTransform) {
            val e = expect._logic.append(descriptiveAssertion)
            maybeSubAssertions.fold({ e }) { assertionCreator ->
                expect._logic.appendAsGroup(assertionCreator)
            }
        } else {
            val assertion = failureHandler.createAssertion(container, descriptiveAssertion, maybeSubAssertions)
            expect._logic.append(assertion)
        }
    }
}
