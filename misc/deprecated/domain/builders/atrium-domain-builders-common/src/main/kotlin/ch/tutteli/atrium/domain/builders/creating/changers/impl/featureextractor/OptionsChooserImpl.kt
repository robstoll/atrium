//TODO remove with 1.0.0
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.domain.builders.creating.changers.impl.featureextractor

import ch.tutteli.atrium.domain.builders.creating.changers.FeatureExtractorBuilder
import ch.tutteli.atrium.domain.builders.creating.changers.FeatureOptions
import ch.tutteli.atrium.reporting.translating.Translatable

class OptionsChooserImpl<R> : FeatureExtractorBuilder.OptionsChooser<R> {
    private var description: Translatable? = null
    private var representationInsteadOfFeature: ((R) -> Any)? = null

    override fun withDescription(description: Translatable) {
        this.description = description
    }

    override fun withRepresentation(representationProvider: (R) -> Any) {
        this.representationInsteadOfFeature = representationProvider
    }

    fun build(): FeatureOptions<R> = FeatureOptions(description, representationInsteadOfFeature)
}
