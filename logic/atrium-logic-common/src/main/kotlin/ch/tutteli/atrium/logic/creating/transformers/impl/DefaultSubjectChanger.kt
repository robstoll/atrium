package ch.tutteli.atrium.logic.creating.transformers.impl

import ch.tutteli.atrium.assertions.builders.assertionBuilder
import ch.tutteli.atrium.core.Option
import ch.tutteli.atrium.core.trueProvider
import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.creating.DelegatingExpect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.creating.transformers.SubjectChanger
import ch.tutteli.atrium.reporting.translating.Translatable

class DefaultSubjectChanger : SubjectChanger {
    override fun <T, R> unreported(container: AssertionContainer<T>, transformation: (T) -> R): Expect<R> =
        DelegatingExpect(
            container,
            //TODO wrap transformation with error handling.
            // Could be interesting to see the exception in the context of the assertion
            container.maybeSubject.map(transformation)
        )

    override fun <T, R> reported(
        container: AssertionContainer<T>,
        description: Translatable,
        representation: Any,
        transformation: (T) -> Option<R>,
        failureHandler: SubjectChanger.FailureHandler<T, R>,
        maybeSubAssertions: Option<Expect<R>.() -> Unit>
    ): Expect<R> {
        val expect = DelegatingExpect(
            container,
            // TODO wrap transformation with error handling.
            //  Could be interesting to see the exception in the context of the assertion
            container.maybeSubject.flatMap(transformation)
        )
        // we can transform if maybeSubject is None as we have to be in an explaining like context in such a case,
        // even if the transformation cannot be carried out
        val shallTransform =
            container.maybeSubject.fold(trueProvider, { expect.maybeSubject.isDefined() })

        val descriptiveAssertion = assertionBuilder.descriptive
            .withTest { shallTransform }
            .withDescriptionAndRepresentation(description, representation)
            .build()

        if (shallTransform) {
            expect.addAssertion(descriptiveAssertion)
            maybeSubAssertions.fold({ /* nothing to do */ }) { assertionCreator ->
                expect.addAssertionsCreatedBy(assertionCreator)
            }
        } else {
            val assertion = failureHandler.createAssertion(container, descriptiveAssertion, maybeSubAssertions)
            expect.addAssertion(assertion)
        }
        return expect
    }
}
