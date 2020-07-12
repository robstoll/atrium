//TODO remove file with 1.0.0
@file:Suppress("DEPRECATION")
package ch.tutteli.atrium.domain.robstoll.lib.creating

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.builders.ExpectImpl
import ch.tutteli.atrium.domain.creating.MetaFeature
import ch.tutteli.atrium.domain.creating.changers.ExtractedFeaturePostStep
import ch.tutteli.atrium.reporting.Text

@Suppress("DeprecatedCallableAddReplaceWith")
@Deprecated("use the function from atrium-logic instead, will be removed with 1.0.0")
fun <T, R> _genericFeature(expect: Expect<T>, metaFeature: MetaFeature<R>): ExtractedFeaturePostStep<T, R> {
    val representation: Any = metaFeature.representation ?: Text.NULL
    return ExpectImpl.feature.extractor(expect)
        .withDescription(metaFeature.description)
        .withRepresentationForFailure(representation)
        .withFeatureExtraction { metaFeature.maybeSubject }
        .withOptions { withRepresentation { representation } }
        .build()
}
