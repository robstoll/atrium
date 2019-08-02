package ch.tutteli.atrium.domain.robstoll.lib.creating.changers

import ch.tutteli.atrium.core.None
import ch.tutteli.atrium.core.Some
import ch.tutteli.atrium.core.coreFactory
import ch.tutteli.atrium.core.falseProvider
import ch.tutteli.atrium.creating.AssertionContainerWithCommonFields
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.builders.ExpectImpl
import ch.tutteli.atrium.assertions.builders.fixedClaimGroup
import ch.tutteli.atrium.domain.builders.creating.collectors.collectAssertions
import ch.tutteli.atrium.reporting.RawString
import ch.tutteli.atrium.reporting.SHOULD_NOT_BE_SHOWN_TO_THE_USER_BUG_TRANSLATABLE
import ch.tutteli.atrium.reporting.translating.Translatable

fun <T, R> _extractFeature(
    originalAssertionContainer: Expect<T>,
    description: Translatable,
    representationForFailure: Any,
    canBeExtracted: (T) -> Boolean,
    featureExtraction: (T) -> R,
    subAssertions: (Expect<R>.() -> Unit)?,
    representationInsteadOfFeature: Any?
): Expect<R> {
    return originalAssertionContainer.maybeSubject
        .filter(canBeExtracted)
        .fold({
            val assertionContainer = coreFactory.newReportingAssertionContainer<R>(
                AssertionContainerWithCommonFields.CommonFields(
                    SHOULD_NOT_BE_SHOWN_TO_THE_USER_BUG_TRANSLATABLE,
                    None,
                    representationForFailure,
                    coreFactory.newDelegatingAssertionChecker(originalAssertionContainer),
                    RawString.NULL
                )
            )
            assertionContainer.addAssertion(
                if (subAssertions != null) {
                    ExpectImpl.builder.fixedClaimGroup
                        .withFeatureType
                        .failing
                        .withDescriptionAndRepresentation(description, representationForFailure)
                        .withAssertion(
                            ExpectImpl.builder.explanatoryGroup.withDefaultType
                                .collectAssertions(assertionContainer, subAssertions)
                                .build()
                        )
                        .build()
                } else {
                    ExpectImpl.builder.createDescriptive(description, representationForFailure, falseProvider)
                }
            )
            assertionContainer
        }) { subject ->
            val feature = featureExtraction(subject)
            val assertionContainer = coreFactory.newReportingAssertionContainer(
                AssertionContainerWithCommonFields.CommonFields(
                    description,
                    Some(feature),
                    representationInsteadOfFeature ?: feature,
                    coreFactory.newFeatureAssertionChecker(originalAssertionContainer),
                    RawString.NULL
                )
            )
            if (subAssertions != null) assertionContainer.subAssertions()
            assertionContainer
        }
}
