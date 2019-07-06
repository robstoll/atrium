package ch.tutteli.atrium.domain.robstoll.lib.creating.changers

import ch.tutteli.atrium.assertions.builders.invisibleGroup
import ch.tutteli.atrium.checking.AssertionChecker
import ch.tutteli.atrium.core.None
import ch.tutteli.atrium.core.trueProvider
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.builders.AssertImpl
import ch.tutteli.atrium.domain.builders.ExpectImpl
import ch.tutteli.atrium.reporting.SHOULD_NOT_BE_SHOWN_TO_THE_USER_BUG
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
    return assertionChecker to Untranslatable(SHOULD_NOT_BE_SHOWN_TO_THE_USER_BUG)
}

fun <T, R> _changeSubject(
    originalAssertionContainer: Expect<T>,
    description: Translatable,
    representation: Any,
    canBeTransformed: (T) -> Boolean,
    transformation: (T) -> R,
    subAssertions: (Expect<R>.() -> Unit)?
): Expect<R> {

    // we can transform if maybeSubject is None as we have to be in an explaining like context in such a case.
    val shallTransform = originalAssertionContainer.maybeSubject.fold(trueProvider) {
        canBeTransformed(it)
    }

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
        if (subAssertions != null) {
            subAssertions(assertionContainer)
        }
    } else {
        val assertion = if (subAssertions != null) {
            val explanatoryAssertions = ExpectImpl.collector
                .forExplanation
                .throwIfNoAssertionIsCollected
                .collect(None, subAssertions)
            AssertImpl.builder.invisibleGroup
                .withAssertions(
                    descriptiveAssertion,
                    AssertImpl.builder.explanatoryGroup
                        .withDefaultType
                        .withAssertions(explanatoryAssertions)
                        .build()
                )
                .build()
        } else {
            descriptiveAssertion
        }
        assertionContainer.addAssertion(assertion)
    }
    return assertionContainer
}
