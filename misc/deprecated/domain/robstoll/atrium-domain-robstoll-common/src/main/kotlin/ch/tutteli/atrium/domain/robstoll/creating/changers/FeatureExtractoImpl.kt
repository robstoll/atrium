//TODO remove with 1.0.0
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.domain.robstoll.creating.changers

import ch.tutteli.atrium.core.Option
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.creating.FeatureExpect
import ch.tutteli.atrium.domain.creating.changers.FeatureExtractor
import ch.tutteli.atrium.domain.robstoll.lib.creating.changers._extractFeature
import ch.tutteli.atrium.reporting.translating.Translatable

class FeatureExtractorImpl : FeatureExtractor {

    override fun <T, R> extract(
        originalAssertionContainer: Expect<T>,
        description: Translatable,
        representationForFailure: Any,
        featureExtraction: (T) -> Option<R>,
        maybeSubAssertions: Option<Expect<R>.() -> Unit>,
        representationInsteadOfFeature: ((R) -> Any)?
    ): FeatureExpect<T, R> = _extractFeature(
        originalAssertionContainer,
        description,
        representationForFailure,
        featureExtraction,
        maybeSubAssertions,
        representationInsteadOfFeature
    )
}
