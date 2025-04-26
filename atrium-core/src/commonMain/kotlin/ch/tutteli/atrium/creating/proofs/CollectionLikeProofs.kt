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

    /** @since 1.3.0 */
    fun <SubjectT : CollectionLike> toBeEmpty(container: ProofContainer<SubjectT>, converter: (SubjectT) -> Collection<*>): Proof

    /** @since 1.3.0 */
    fun <SubjectT : CollectionLike> notToBeEmpty(container: ProofContainer<SubjectT>, converter: (SubjectT) -> Collection<*>): Proof

    /** @since 1.3.0 */
    fun <SubjectT : CollectionLike> size(
        container: ProofContainer<SubjectT>,
        converter: (SubjectT) -> Collection<*>
    ): FeatureExtractorBuilder.ExecutionStep<SubjectT, Int>
}
