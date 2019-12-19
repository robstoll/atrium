package ch.tutteli.atrium.domain.robstoll.lib.creating.changers

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.builders.assertionBuilder
import ch.tutteli.atrium.assertions.builders.fixedClaimGroup
import ch.tutteli.atrium.core.None
import ch.tutteli.atrium.core.Option
import ch.tutteli.atrium.core.Some
import ch.tutteli.atrium.core.coreFactory
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.creating.FeatureExpect
import ch.tutteli.atrium.creating.FeatureExpectConfig
import ch.tutteli.atrium.domain.creating.collectors.assertionCollector
import ch.tutteli.atrium.domain.creating.collectors.collectAssertions
import ch.tutteli.atrium.reporting.translating.Translatable

fun <T, R> _extractFeature(
    originalAssertionContainer: Expect<T>,
    description: Translatable,
    representationForFailure: Any,
    featureExtraction: (T) -> Option<R>,
    maybeSubAssertions: Option<Expect<R>.() -> Unit>,
    representationInsteadOfFeature: ((R) -> Any)?
): FeatureExpect<T, R> =
    originalAssertionContainer.maybeSubject
        .flatMap { subject -> featureExtraction(subject) }
        .fold(
            {
                originalAssertionContainer.addAssertion(
                    assertionBuilder.fixedClaimGroup
                        .withFeatureType
                        .failing
                        .withDescriptionAndRepresentation(description, representationForFailure)
                        .withAssertions(maybeSubAssertions.fold({
                            listOf<Assertion>()
                        }) { assertionCreator ->
                            listOf(
                                assertionBuilder.explanatoryGroup.withDefaultType
                                    .collectAssertions(None, assertionCreator)
                                    .build()
                            )
                        })
                        .build()
                )
                coreFactory.newFeatureExpect(
                    originalAssertionContainer,
                    None,
                    FeatureExpectConfig.create(description, representationForFailure),
                    listOf()
                )
            },
            { subject ->
                coreFactory.newFeatureExpect(
                    originalAssertionContainer,
                    Some(subject),
                    FeatureExpectConfig.create(description, representationInsteadOfFeature?.invoke(subject) ?: subject),
                    maybeSubAssertions.fold({
                        listOf<Assertion>()
                    }) { assertionCreator ->
                        assertionCollector.collectForComposition(Some(subject), assertionCreator)
                    }
                )
            }
        )
