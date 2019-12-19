package ch.tutteli.atrium.domain.robstoll.lib.creating

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.creating._domain
import ch.tutteli.atrium.domain.creating.MetaFeature
import ch.tutteli.atrium.domain.creating.changers.ExtractedFeaturePostStep
import ch.tutteli.atrium.reporting.RawString

fun <T, R> _genericFeature(assertionContainer: Expect<T>, metaFeature: MetaFeature<R>): ExtractedFeaturePostStep<T, R> {
    val representation: Any = metaFeature.representation ?: RawString.NULL
    return assertionContainer._domain.featureExtractor
        .withDescription(metaFeature.description)
        .withRepresentationForFailure(representation)
        .withFeatureExtraction { metaFeature.maybeSubject }
        .withOptions { withRepresentation(representation) }
        .build()
}
