package ch.tutteli.atrium.domain.robstoll.lib.creating.changers

import ch.tutteli.atrium.checking.AssertionChecker
import ch.tutteli.atrium.core.None
import ch.tutteli.atrium.core.trueProvider
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.builders.AssertImpl
import ch.tutteli.atrium.domain.builders.ExpectImpl
import ch.tutteli.atrium.domain.creating.changers.SubjectChanger
import ch.tutteli.atrium.reporting.SHOULD_NOT_BE_SHOWN_TO_THE_USER_BUG_TRANSLATABLE
import ch.tutteli.atrium.reporting.translating.Translatable
import ch.tutteli.atrium.reporting.translating.Untranslatable

fun <T, R> _changeSubjectUnreported(
    originalAssertionContainer: Expect<T>,
    transformation: (T) -> R
): Expect<R> {
    val (assertionChecker, assertionVerb) = createDelegatingAssertionCheckerAndVerb(originalAssertionContainer)
    return ExpectImpl.coreFactory.newReportingAssertionContainer(
        assertionVerb,
        originalAssertionContainer.maybeSubject.map(transformation),
        assertionChecker
    )
}

private fun <T> createDelegatingAssertionCheckerAndVerb(originalAssertionContainer: Expect<T>): Pair<AssertionChecker, Untranslatable> {
    //TODO wrap transformation with error handling. Could be interesting to see the exception in the context of the assertion
    val assertionChecker = ExpectImpl.coreFactory.newDelegatingAssertionChecker(originalAssertionContainer)
    return assertionChecker to SHOULD_NOT_BE_SHOWN_TO_THE_USER_BUG_TRANSLATABLE
}

fun <T, R> _changeSubject(
    originalAssertionContainer: Expect<T>,
    description: Translatable,
    representation: Any,
    canBeTransformed: (T) -> Boolean,
    transformation: (T) -> R,
    failureHandler: SubjectChanger.FailureHandler<T, R>,
    assertionCreator: (Expect<R>.() -> Unit)?
): Expect<R> {

    // we can transform if maybeSubject is None as we have to be in an explaining like context in such a case.
    val shallTransform = originalAssertionContainer.maybeSubject.fold(trueProvider, canBeTransformed)

    val (assertionChecker, assertionVerb) = createDelegatingAssertionCheckerAndVerb(originalAssertionContainer)
    val assertionContainer = ExpectImpl.coreFactory.newReportingAssertionContainer(
        assertionVerb,
        if (shallTransform) originalAssertionContainer.maybeSubject.map(transformation) else None,
        assertionChecker
    )

    val descriptiveAssertion = AssertImpl.builder.descriptive
        .withTest { shallTransform }
        .withDescriptionAndRepresentation(description, representation)
        .build()

    if (shallTransform) {
        assertionContainer.addAssertion(descriptiveAssertion)
        if (assertionCreator != null) {
            assertionContainer.addAssertionsCreatedBy(assertionCreator)
        }
    } else {
        val assertion = failureHandler.createAssertion(
            originalAssertionContainer, descriptiveAssertion, assertionCreator
        )
        assertionContainer.addAssertion(assertion)
    }
    return assertionContainer
}
