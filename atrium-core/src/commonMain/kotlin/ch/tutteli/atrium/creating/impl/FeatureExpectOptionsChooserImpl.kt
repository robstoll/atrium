package ch.tutteli.atrium.creating.impl

import ch.tutteli.atrium.core.ExperimentalNewExpectTypes
import ch.tutteli.atrium.creating.FeatureExpectOptions
import ch.tutteli.atrium.creating.FeatureExpectOptionsChooser
import ch.tutteli.atrium.reporting.reportables.InlineElement

@ExperimentalNewExpectTypes
class FeatureExpectOptionsChooserImpl<R> : FeatureExpectOptionsChooser<R> {
    private var description: InlineElement? = null
    private var representationInsteadOfFeature: ((R) -> Any)? = null


    override fun withDescription(description: InlineElement) {
        this.description = description
    }

    override fun withRepresentationIfSubjectDefined(representationProvider: (R) -> Any) {
        this.representationInsteadOfFeature = representationProvider
    }

    fun build(): FeatureExpectOptions<R> = FeatureExpectOptions(description, representationInsteadOfFeature)
}
