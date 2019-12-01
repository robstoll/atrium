package ch.tutteli.atrium.domain.builders.creating.changers.impl.featureextractor

import ch.tutteli.atrium.domain.builders.creating.changers.FeatureExtractorBuilder
import ch.tutteli.atrium.domain.builders.creating.changers.FeatureOptions
import ch.tutteli.atrium.reporting.translating.Translatable

class OptionsChooserImpl<R> : FeatureExtractorBuilder.OptionsChooser<R> {
    private var description: Translatable? = null
    private var representationProvider: ((R) -> Any)? = null

    override fun withDescription(description: Translatable) {
        this.description = description
    }

    override fun withSubjectBasedRepresentation(representationProvider: (R) -> Any) {
        this.representationProvider = representationProvider
    }

    fun build(): FeatureOptions<R> = FeatureOptions(description, representationProvider)

}
