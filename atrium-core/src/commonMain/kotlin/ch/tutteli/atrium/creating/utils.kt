package ch.tutteli.atrium.creating

import ch.tutteli.atrium.creating.transformers.FeatureExtractorBuilder
import ch.tutteli.atrium.creating.transformers.SubjectChangerBuilder

/**
 * Entry point to use the [SubjectChangerBuilder] based on this [ProofContainer].
 */
val <T> ProofContainer<T>.changeSubject: SubjectChangerBuilder.KindStep<T>
    get() = SubjectChangerBuilder(this)

/**
 * Entry point to use the [FeatureExtractorBuilder] based on this [ProofContainer].
 */
val <T> ProofContainer<T>.extractFeature: FeatureExtractorBuilder.DescriptionStep<T>
    get() = FeatureExtractorBuilder(this)
