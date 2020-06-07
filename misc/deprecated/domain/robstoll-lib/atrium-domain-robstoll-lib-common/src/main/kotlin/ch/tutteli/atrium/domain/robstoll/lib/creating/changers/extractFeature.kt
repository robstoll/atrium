package ch.tutteli.atrium.domain.robstoll.lib.creating.changers

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.builders.fixedClaimGroup
import ch.tutteli.atrium.core.*
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.creating.FeatureExpect
import ch.tutteli.atrium.creating.FeatureExpectOptions
import ch.tutteli.atrium.domain.builders.ExpectImpl
import ch.tutteli.atrium.domain.builders.creating.collectors.collectAssertions
import ch.tutteli.atrium.reporting.translating.Translatable

@Suppress("DEPRECATION" /* OptIn is only available since 1.3.70 which we cannot use if we want to support 1.2 */)
@UseExperimental(ExperimentalNewExpectTypes::class)
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
                    ExpectImpl.builder.fixedClaimGroup
                        .withFeatureType
                        .failing
                        .withDescriptionAndRepresentation(description, representationForFailure)
                        .withAssertions(maybeSubAssertions.fold({
                            listOf<Assertion>()
                        }) { assertionCreator ->
                            listOf(
                                ExpectImpl.builder.explanatoryGroup.withDefaultType
                                    .collectAssertions(None, assertionCreator)
                                    .build()
                            )
                        })
                        .build()
                )
                FeatureExpect(
                    originalAssertionContainer,
                    None,
                    description,
                    listOf(),
                    FeatureExpectOptions(representationInsteadOfFeature = { representationForFailure })
                )
            },
            { subject ->
                FeatureExpect(
                    originalAssertionContainer,
                    Some(subject),
                    description,
                    maybeSubAssertions.fold({
                        listOf<Assertion>()
                    }) { assertionCreator ->
                        ExpectImpl.collector.collectForComposition(Some(subject), assertionCreator)
                    },
                    FeatureExpectOptions(
                        representationInsteadOfFeature = representationInsteadOfFeature
                    )
                )
            }
        )
