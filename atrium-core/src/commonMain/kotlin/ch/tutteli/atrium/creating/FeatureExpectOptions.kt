package ch.tutteli.atrium.creating

import ch.tutteli.atrium.core.ExperimentalNewExpectTypes

/**
 * Additional (non-mandatory) options to create a [FeatureExpect].
 *
 * @property description Defines a custom description if not null.
 * @property representationInsteadOfFeature Defines a custom representation based on a present subject if not null.
 */
@ExperimentalNewExpectTypes
data class FeatureExpectOptions<R>(
    //TODO 1.3.0 replace with InlineElement or make InlineElement a Translatable instead and provide a second
    // constructor? it's experimental we can also just change it (is most likely not used often
    @Suppress("DEPRECATION")
    val description: ch.tutteli.atrium.reporting.translating.Translatable? = null,
    val representationInsteadOfFeature: ((R) -> Any)? = null
) {
    /**
     * Merges the given [options] with this object creating a new [FeatureExpectOptions]
     * where defined properties in [options] will have precedence over properties defined in this instance.
     *
     * For instance, this object has defined [representationInsteadOfFeature] (meaning it is not `null`) and
     * the given [options] as well, then the resulting [FeatureExpectOptions] will have the
     * [representationInsteadOfFeature] of [options].
     */
    fun merge(options: FeatureExpectOptions<R>): FeatureExpectOptions<R> =
        FeatureExpectOptions(
            options.description ?: description,
            options.representationInsteadOfFeature ?: representationInsteadOfFeature
        )
}
