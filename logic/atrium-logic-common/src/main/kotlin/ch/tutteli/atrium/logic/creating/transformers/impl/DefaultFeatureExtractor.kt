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
import ch.tutteli.atrium.logic.toExpect
import ch.tutteli.atrium.reporting.AtriumErrorAdjuster
import ch.tutteli.atrium.reporting.translating.Translatable
import ch.tutteli.atrium.reporting.translating.TranslatableWithArgs
import ch.tutteli.atrium.translations.DescriptionFunLikeAssertion

class DefaultFeatureExtractor : FeatureExtractor {
    @Suppress("DEPRECATION" /* OptIn is only available since 1.3.70 which we cannot use if we want to support 1.2 */)
    @UseExperimental(ExperimentalNewExpectTypes::class, ExperimentalComponentFactoryContainer::class)
    override fun <T, R> extract(
        container: AssertionContainer<T>,
        description: Translatable,
        representationForFailure: Any,
        featureExtraction: (T) -> Option<R>,
        maybeSubAssertions: Option<Expect<R>.() -> Unit>,
        featureExpectOptions: FeatureExpectOptions<R>
    ): FeatureExpect<T, R> {
        fun createFeatureExpect(subject: Option<R>, assertions: List<Assertion>) =
            FeatureExpect(
                container.toExpect(),
                subject,
                description,
                assertions,
                featureExpectOptions
            )

        val either: Either<Option<Throwable>, R> = container.maybeSubject.fold({ Left(None) }, { subject ->
            try {
                featureExtraction(subject).fold({ Left(None) }, { Right(it) })
            } catch (throwable: Throwable) {
                container.components.build<AtriumErrorAdjuster>().adjust(throwable)
                Left(Some(throwable))
            }
        })

        return either.fold(
            { maybeThrowable ->
                val (failureHintAssertions, repForFailure) = maybeThrowable.fold(
                    { listOf<Assertion>() to representationForFailure },
                    { throwable ->
                        listOf(ThrowableThrownFailureHandler.propertiesOfThrowable(throwable)) to
                            TranslatableWithArgs(DescriptionFunLikeAssertion.THREW, throwable::class.fullName)
                    })

                val subAssertions = maybeSubAssertions.fold({
                    listOf<Assertion>()
                }) { assertionCreator ->
                    // TODO 0.18.0: factor out in common pattern, should not be the concern of the average expectation
                    // function writer
                    container.maybeSubject.fold({
                        // already in an explanatory assertion group, no need to wrap again
                        container.collectForCompositionBasedOnSubject(None, assertionCreator)
                    }, {
                        listOf(
                            assertionBuilder.explanatoryGroup.withDefaultType
                                .collectAssertions(container, None, assertionCreator)
                                .build()
                        )
                    })
                }
                container.addAssertion(
                    assertionBuilder.fixedClaimGroup
                        .withFeatureType
                        .failing
                        .withDescriptionAndRepresentation(description, repForFailure)
                        .withAssertions(failureHintAssertions + subAssertions)
                        .build()
                )
                createFeatureExpect(None, listOf())
            },
            { subject ->
                createFeatureExpect(Some(subject), maybeSubAssertions.fold({
                    listOf<Assertion>()
                }) { assertionCreator ->
                    container.collectForCompositionBasedOnSubject(Some(subject), assertionCreator)
                })
            }
        )
    }
}
