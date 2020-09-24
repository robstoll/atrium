//TODO remove with 1.0.0
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.domain.robstoll.lib.creating.changers

import ch.tutteli.atrium.assertions.builders.assertionBuilder
import ch.tutteli.atrium.core.Option
import ch.tutteli.atrium.core.trueProvider
import ch.tutteli.atrium.creating.DelegatingExpect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.creating.changers.SubjectChanger
import ch.tutteli.atrium.reporting.translating.Translatable

fun <T, R> _changeSubjectUnreported(
    originalAssertionContainer: Expect<T>,
    transformation: (T) -> R
): Expect<R> = DelegatingExpect(
    originalAssertionContainer,
    //TODO wrap transformation with error handling. Could be interesting to see the exception in the context of the assertion
    originalAssertionContainer.maybeSubject.map(transformation)
)

fun <T, R> _changeSubject(
    originalAssertionContainer: Expect<T>,
    description: Translatable,
    representation: Any,
    transformation: (T) -> Option<R>,
    failureHandler: SubjectChanger.FailureHandler<T, R>,
    maybeAssertionCreator: Option<Expect<R>.() -> Unit>
): Expect<R> {

    val expect = DelegatingExpect(
        originalAssertionContainer,
        // TODO wrap transformation with error handling. Could be interesting to see the exception in the context of the assertion
        originalAssertionContainer.maybeSubject.flatMap(transformation)
    )
    // we can transform if maybeSubject is None as we have to be in an explaining like context in such a case,
    // even if the transformation cannot be carried out
    val shallTransform =
        originalAssertionContainer.maybeSubject.fold(trueProvider, { expect.maybeSubject.isDefined() })

    val descriptiveAssertion = assertionBuilder.descriptive
        .withTest { shallTransform }
        .withDescriptionAndRepresentation(description, representation)
        .build()

    if (shallTransform) {
        expect.addAssertion(descriptiveAssertion)
        maybeAssertionCreator.fold({ /*nothing to do */ }) { assertionCreator ->
            expect.addAssertionsCreatedBy(assertionCreator)
        }
    } else {
        val assertion = failureHandler.createAssertion(
            originalAssertionContainer, descriptiveAssertion, maybeAssertionCreator
        )
        expect.addAssertion(assertion)
    }
    return expect
}
