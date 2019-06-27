package ch.tutteli.atrium.domain.robstoll.lib.creating.changers

import ch.tutteli.atrium.assertions.builders.invisibleGroup
import ch.tutteli.atrium.checking.AssertionChecker
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.creating.MaybeSubject
import ch.tutteli.atrium.creating.PlantHasNoSubjectException
import ch.tutteli.atrium.domain.builders.AssertImpl
import ch.tutteli.atrium.domain.builders.ExpectImpl
import ch.tutteli.atrium.reporting.SHOULD_NOT_BE_SHOWN_TO_THE_USER_BUG
import ch.tutteli.atrium.reporting.translating.Translatable
import ch.tutteli.atrium.reporting.translating.Untranslatable

fun <T, R> _changeSubjectUnreported(
    originalAssertionContainer: Expect<T>,
    subjectProvider: () -> R
): Expect<R> {
    val (assertionChecker, assertionVerb) = createDelegatingAssertionCheckerAndVerb(originalAssertionContainer)
    return ExpectImpl.coreFactory.newReportingAssertionContainer(assertionVerb, subjectProvider, assertionChecker)
}

private fun <T> createDelegatingAssertionCheckerAndVerb(originalAssertionContainer: Expect<T>): Pair<AssertionChecker, Untranslatable> {
    //TODO wrap subjectProvider with error handling. Could be interesting to see the exception in the context of the assertion
    val assertionChecker = ExpectImpl.coreFactory.newDelegatingAssertionChecker(originalAssertionContainer)
    return assertionChecker to Untranslatable(SHOULD_NOT_BE_SHOWN_TO_THE_USER_BUG)
}

fun <T, R> _changeSubject(
    originalAssertionContainer: Expect<T>,
    description: Translatable,
    representation: Any,
    canBeTransformed: (T) -> Boolean,
    subjectProvider: () -> R,
    subAssertions: (Expect<R>.() -> Unit)?
): Expect<R> {
    //TODO #88 I think it would make things easier if we do it more in a functional way. Maybe not only hide subject but use something like Option<T> instead, then we can finally get rid of this hack (and use map instead)
    //TODO #88 if we use Option then it would probably makes sense to use flatMap instead of map and combine canBeTransformed and subjectProvider
    val shallTransform = try {
        canBeTransformed(originalAssertionContainer.subject)
    } catch (e: PlantHasNoSubjectException) {
        true
    }

    val (assertionChecker, assertionVerb) = createDelegatingAssertionCheckerAndVerb(originalAssertionContainer)
    val assertionContainer = ExpectImpl.coreFactory.newReportingAssertionContainer(
        assertionVerb,
        if (shallTransform) subjectProvider else { { throw PlantHasNoSubjectException() } },
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
                .collect(MaybeSubject.Absent, subAssertions)
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
