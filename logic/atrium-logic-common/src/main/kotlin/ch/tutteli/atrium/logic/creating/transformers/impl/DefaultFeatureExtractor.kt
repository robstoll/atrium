package ch.tutteli.atrium.logic.creating.transformers.impl

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.builders.assertionBuilder
import ch.tutteli.atrium.assertions.builders.fixedClaimGroup
import ch.tutteli.atrium.core.ExperimentalNewExpectTypes
import ch.tutteli.atrium.core.None
import ch.tutteli.atrium.core.Option
import ch.tutteli.atrium.core.Some
import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.creating.FeatureExpect
import ch.tutteli.atrium.creating.FeatureExpectOptions
import ch.tutteli.atrium.domain.builders.creating.collectors.collectAssertions
import ch.tutteli.atrium.domain.creating.collectors.assertionCollector
import ch.tutteli.atrium.logic.creating.transformers.FeatureExtractor
import ch.tutteli.atrium.logic.toExpect
import ch.tutteli.atrium.reporting.translating.Translatable

class DefaultFeatureExtractor : FeatureExtractor {
    @Suppress("DEPRECATION" /* OptIn is only available since 1.3.70 which we cannot use if we want to support 1.2 */)
    @UseExperimental(ExperimentalNewExpectTypes::class)
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

        return container.maybeSubject
            .flatMap { subject -> featureExtraction(subject) }
            .fold(
                {
                    container.addAssertion(
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
                    createFeatureExpect(None, listOf())
                },
                { subject ->
                    createFeatureExpect(Some(subject), maybeSubAssertions.fold({
                        listOf<Assertion>()
                    }) { assertionCreator ->
                        assertionCollector.collectForComposition(Some(subject), assertionCreator)
                    })
                }
            )
    }

}
