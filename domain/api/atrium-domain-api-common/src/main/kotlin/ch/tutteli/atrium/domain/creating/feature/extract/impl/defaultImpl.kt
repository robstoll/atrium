package ch.tutteli.atrium.domain.creating.feature.extract.impl

import ch.tutteli.atrium.domain.creating.feature.extract.FeatureExtractor
import ch.tutteli.atrium.reporting.translating.Translatable

internal class RepresentationOptionImpl : FeatureExtractor.RepresentationOption {
    override fun withDescription(translatable: Translatable): FeatureExtractor.ParameterObjectOption
        = ParameterObjectOptionImpl(translatable)
}

internal class ParameterObjectOptionImpl(
    override val featureRepresentation: Translatable
) : FeatureExtractor.ParameterObjectOption
