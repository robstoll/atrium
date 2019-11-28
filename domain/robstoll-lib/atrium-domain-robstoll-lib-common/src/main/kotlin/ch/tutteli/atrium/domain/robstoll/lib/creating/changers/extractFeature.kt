package ch.tutteli.atrium.domain.robstoll.lib.creating.changers

import ch.tutteli.atrium.assertions.builders.fixedClaimGroup
import ch.tutteli.atrium.core.*
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.creating.ReportingAssertionContainer
import ch.tutteli.atrium.domain.builders.ExpectImpl
import ch.tutteli.atrium.domain.builders.creating.collectors.collectAssertions
import ch.tutteli.atrium.reporting.RawString
import ch.tutteli.atrium.reporting.SHOULD_NOT_BE_SHOWN_TO_THE_USER_BUG_TRANSLATABLE
import ch.tutteli.atrium.reporting.translating.Translatable

fun <T, R> _extractFeature(
    originalAssertionContainer: Expect<T>,
    description: Translatable,
    representationForFailure: Any,
    featureExtraction: (T) -> Option<R>,
    maybeSubAssertions: Option<Expect<R>.() -> Unit>,
    representationInsteadOfFeature: Any?
): Expect<R> {

    return originalAssertionContainer.maybeSubject
        .flatMap { subject -> featureExtraction(subject) }
        .fold({
            val assertionContainer = coreFactory.newReportingAssertionContainer<R>(
                ReportingAssertionContainer.AssertionCheckerDecorator.create(
                    SHOULD_NOT_BE_SHOWN_TO_THE_USER_BUG_TRANSLATABLE,
                    None,
                    representationForFailure,
                    coreFactory.newDelegatingAssertionChecker(originalAssertionContainer),
                    RawString.NULL
                )
            )
            assertionContainer.addAssertion(
                maybeSubAssertions.fold({
                    ExpectImpl.builder.createDescriptive(description, representationForFailure, falseProvider)
                }) { assertionCreator ->
                    ExpectImpl.builder.fixedClaimGroup
                        .withFeatureType
                        .failing
                        .withDescriptionAndRepresentation(description, representationForFailure)
                        .withAssertion(
                            ExpectImpl.builder.explanatoryGroup.withDefaultType
                                .collectAssertions(assertionContainer, assertionCreator)
                                .build()
                        )
                        .build()
                }
            )
            assertionContainer
        }) { subject ->
            val assertionContainer = coreFactory.newReportingAssertionContainer(
                ReportingAssertionContainer.AssertionCheckerDecorator.createLazy(
                    description,
                    { Some(subject) },
                    { representationInsteadOfFeature ?: subject },
                    coreFactory.newFeatureAssertionChecker(originalAssertionContainer),
                    RawString.NULL
                )
            )
            maybeSubAssertions.fold({ /* nothing to do */ }) { assertionCreator ->
                assertionContainer.addAssertionsCreatedBy(assertionCreator)
            }
            assertionContainer
        }
}
