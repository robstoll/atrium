package ch.tutteli.atrium.creating.proofs

import ch.tutteli.atrium.creating.ProofContainer
import ch.tutteli.atrium.creating.transformers.FeatureExtractorBuilder
import ch.tutteli.atrium.creating.typeutils.CollectionLike

/**
 * Collection of proof functions and builders which are applicable to [Collection] and types which are collection like,
 * i.e. which can be transformed to a collection such as [Map].
 *
 * @since 1.3.0
 */
interface CollectionLikeProofs {

    fun <T : CollectionLike> toBeEmpty(container: ProofContainer<T>, converter: (T) -> Collection<*>): Proof
    fun <T : CollectionLike> notToBeEmpty(container: ProofContainer<T>, converter: (T) -> Collection<*>): Proof

    fun <T : CollectionLike> size(
        container: ProofContainer<T>,
        converter: (T) -> Collection<*>
    ): FeatureExtractorBuilder.ExecutionStep<T, Int>
}
