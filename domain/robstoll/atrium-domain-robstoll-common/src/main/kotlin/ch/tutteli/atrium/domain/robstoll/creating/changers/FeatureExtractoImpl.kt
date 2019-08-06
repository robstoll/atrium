package ch.tutteli.atrium.domain.robstoll.creating.changers

import ch.tutteli.atrium.core.Option
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.creating.changers.FeatureExtractor
import ch.tutteli.atrium.domain.robstoll.lib.creating.changers._extractFeature
import ch.tutteli.atrium.reporting.translating.Translatable

class FeatureExtractorImpl : FeatureExtractor {

    override fun <T, R> extract(
        originalAssertionContainer: Expect<T>,
        description: Translatable,
        representationForFailure: Any,
        canBeExtracted: (T) -> Boolean,
        featureExtraction: (T) -> R,
        maybeSubAssertions: Option<Expect<R>.() -> Unit>,
        representationInsteadOfFeature: Any?
    ): Expect<R> = _extractFeature(
        originalAssertionContainer,
        description,
        representationForFailure,
        canBeExtracted,
        featureExtraction,
        maybeSubAssertions,
        representationInsteadOfFeature
    )
}
