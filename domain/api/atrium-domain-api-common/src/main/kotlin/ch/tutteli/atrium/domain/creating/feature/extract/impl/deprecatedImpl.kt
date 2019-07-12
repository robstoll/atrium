@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.domain.creating.feature.extract.impl

import ch.tutteli.atrium.domain.creating.feature.extract.FeatureExtractor
import ch.tutteli.atrium.reporting.translating.Translatable

@Deprecated("will be removed with 1.0.0")
internal class DescriptionOptionImpl : FeatureExtractor.DescriptionOption {
    override fun withDescription(translatable: Translatable): FeatureExtractor.ParameterObjectOption
        = ParameterObjectOptionImpl(translatable)
}

@Deprecated("will be removed with 1.0.0")
internal class ParameterObjectOptionImpl(
    override val featureDescription: Translatable
) : FeatureExtractor.ParameterObjectOption
