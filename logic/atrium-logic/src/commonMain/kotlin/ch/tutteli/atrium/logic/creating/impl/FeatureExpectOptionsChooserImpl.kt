package ch.tutteli.atrium.logic.creating.impl

import ch.tutteli.atrium.core.ExperimentalNewExpectTypes
import ch.tutteli.atrium.creating.FeatureExpectOptions
import ch.tutteli.atrium.logic.creating.FeatureExpectOptionsChooser

@ExperimentalNewExpectTypes
class FeatureExpectOptionsChooserImpl<R> : FeatureExpectOptionsChooser<R> {
    //TODO 1.3.0 replace with Representable and remove suppression
    @Suppress("DEPRECATION")
    private var description: ch.tutteli.atrium.reporting.translating.Translatable? = null
    private var representationInsteadOfFeature: ((R) -> Any)? = null

    //TODO 1.3.0 replace with Representable and remove suppression
    @Suppress("DEPRECATION")
    override fun withDescription(description: ch.tutteli.atrium.reporting.translating.Translatable) {
        this.description = description
    }

    override fun withRepresentation(representationProvider: (R) -> Any) {
        this.representationInsteadOfFeature = representationProvider
    }

    fun build(): FeatureExpectOptions<R> = FeatureExpectOptions(description, representationInsteadOfFeature)
}
