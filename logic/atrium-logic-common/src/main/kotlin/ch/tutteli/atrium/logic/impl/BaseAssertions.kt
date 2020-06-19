package ch.tutteli.atrium.logic.impl

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.builders.assertionBuilder
import ch.tutteli.atrium.core.trueProvider
import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.creating.MetaFeature
import ch.tutteli.atrium.domain.creating.changers.ExtractedFeaturePostStep
import ch.tutteli.atrium.reporting.Text
import ch.tutteli.atrium.reporting.translating.Translatable

abstract class BaseAssertions {
    protected fun <T> AssertionContainer<T>.createDescriptiveAssertion(
        description: Translatable,
        representation: Any?,
        test: T.() -> Boolean
    ): Assertion = assertionBuilder.createDescriptive(description, representation) {
        maybeSubject.fold(trueProvider) { test(it) }
    }
//
//    protected fun <T, R> AssertionContainer<T>.manualFeature(
//        description: Translatable,
//        provider: T.() -> R
//    ): ExtractedFeaturePostStep<T, R> {
//        val representation: Any = metaFeature.representation ?: Text.NULL
//        return ExpectImpl.feature.extractor(expect)
//            .withDescription(metaFeature.description)
//            .withRepresentationForFailure(representation)
//            .withFeatureExtraction { metaFeature.maybeSubject }
//            .withOptions { withRepresentation { representation } }
//            .build()
//    }
//
//    protected fun <T, R> genericFeature(
//        expect: Expect<T>,
//        metaFeature: MetaFeature<R>
//    ): ExtractedFeaturePostStep<T, R> {
//
//    }
}