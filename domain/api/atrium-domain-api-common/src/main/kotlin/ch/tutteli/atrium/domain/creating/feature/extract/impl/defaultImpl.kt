package ch.tutteli.atrium.domain.creating.feature.extract.impl

import ch.tutteli.atrium.domain.creating.feature.extract.FeatureExtractor

class RepresentationOptionImpl : FeatureExtractor.RepresentationOption {
    override fun feature(featureRepresentation: () -> String): FeatureExtractor.ParameterObjectOption
        = ParameterObjectOptionImpl(featureRepresentation)
}

class ParameterObjectOptionImpl(
    override val featureRepresentation: () -> String
) : FeatureExtractor.ParameterObjectOption
