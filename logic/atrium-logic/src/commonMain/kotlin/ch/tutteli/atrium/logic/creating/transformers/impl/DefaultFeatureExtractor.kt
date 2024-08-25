package ch.tutteli.atrium.logic.creating.transformers.impl

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.builders.assertionBuilder
import ch.tutteli.atrium.assertions.builders.fixedClaimGroup
import ch.tutteli.atrium.core.*
import ch.tutteli.atrium.core.polyfills.fullName
import ch.tutteli.atrium.creating.*
import ch.tutteli.atrium.logic.collectForCompositionBasedOnSubject
import ch.tutteli.atrium.logic.creating.collectors.collectAssertions
import ch.tutteli.atrium.logic.creating.transformers.FeatureExtractor
import ch.tutteli.atrium.logic.creating.transformers.propertiesOfThrowable
import ch.tutteli.atrium.logic.toExpect
import ch.tutteli.atrium.reporting.translating.Translatable
import ch.tutteli.atrium.reporting.translating.TranslatableWithArgs
import ch.tutteli.atrium.translations.DescriptionFunLikeExpectation

class DefaultFeatureExtractor : FeatureExtractor {
    @OptIn(ExperimentalNewExpectTypes::class, ExperimentalComponentFactoryContainer::class)
    override fun <SubjectT, FeatureT> extract(
        container: AssertionContainer<SubjectT>,
        description: Translatable,
        representationForFailure: Any,
        featureExtraction: (SubjectT) -> Option<FeatureT>,
        maybeSubAssertions: Option<Expect<FeatureT>.() -> Unit>,
        featureExpectOptions: FeatureExpectOptions<FeatureT>
    ): FeatureExpect<SubjectT, FeatureT> {
        fun createFeatureExpect(subject: Option<FeatureT>, assertions: List<Assertion>) =
            FeatureExpect(
                container.toExpect(),
                subject,
                description,
                assertions,
                featureExpectOptions
            )

        val either: Either<Option<Throwable>, FeatureT> = container.maybeSubject.fold({ Left(None) }, { subject ->
            try {
                featureExtraction(subject).fold({ Left(None) }, { Right(it) })
            } catch (throwable: Throwable) {
                Left(Some(throwable))
            }
        })

        return either.fold(
            { maybeThrowable ->
                val (failureHintAssertions, repForFailure) = maybeThrowable.fold(
                    { emptyList<Assertion>() to representationForFailure },
                    { throwable ->
                        listOf(propertiesOfThrowable(throwable, container )) to
                            TranslatableWithArgs(DescriptionFunLikeExpectation.THREW, throwable::class.fullName)
                    })

                val subAssertions = maybeSubAssertions.fold({
                    emptyList()
                }) { assertionCreator ->
                    // TODO 1.3.0: factor out in common pattern, should not be the concern of the average expectation
                    // function writer
                    container.maybeSubject.fold({
                        // already in an explanatory expectation-group, no need to wrap again
                        container.collectForCompositionBasedOnSubject(None, assertionCreator)
                    }, {
                        listOf(
                            assertionBuilder.explanatoryGroup.withDefaultType
                                .collectAssertions(container, None, assertionCreator)
                                .build()
                        )
                    })
                }
                val featureAssertions = failureHintAssertions + subAssertions
                val fixedClaimGroup = assertionBuilder.fixedClaimGroup
                    .withFeatureType
                    .failing
                    .withDescriptionAndRepresentation(description, repForFailure)
                    .withAssertions(featureAssertions)
                    .build()
                container.maybeSubject.fold({
                    // If the feature extraction fails because the subject is already None, then we don't need/want to
                    // show the fixedClaimGroup in case it is empty because the feature as such will already be shown
                    // via explanatory expectation-group
                    if (featureAssertions.isNotEmpty()) {
                        container.append(fixedClaimGroup)
                    }
                }, {
                    // on the other hand, if the subject is defined, then we need the fixedClaimGroup which inter alia
                    // shows why the extraction went wrong (e.g. index out of bound)
                    container.append(fixedClaimGroup)
                })
                createFeatureExpect(None, emptyList())
            },
            { subject ->
                createFeatureExpect(Some(subject), maybeSubAssertions.fold({
                    emptyList()
                }) { assertionCreator ->
                    container.collectForCompositionBasedOnSubject(Some(subject), assertionCreator)
                })
            }
        )
    }
}
