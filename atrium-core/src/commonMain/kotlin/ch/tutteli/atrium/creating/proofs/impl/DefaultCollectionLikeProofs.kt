@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.creating.proofs.impl

import ch.tutteli.atrium.creating.ProofContainer
import ch.tutteli.atrium.creating.proofs.CollectionLikeProofs
import ch.tutteli.atrium.creating.proofs.Proof
import ch.tutteli.atrium.creating.proofs.builders.buildSimpleProof
import ch.tutteli.atrium.creating.transformers.FeatureExtractorBuilder
import ch.tutteli.atrium.creating.typeutils.CollectionLike
import ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionBasic.NOT_TO_BE
import ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionBasic.TO_BE
import ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionCollectionProof.EMPTY

class DefaultCollectionLikeProofs : CollectionLikeProofs {

    override fun <T : CollectionLike> toBeEmpty(container: ProofContainer<T>, converter: (T) -> Collection<*>): Proof =
        container.buildSimpleProof(TO_BE, EMPTY) { converter(it).isEmpty() }

    override fun <T : CollectionLike> notToBeEmpty(container: ProofContainer<T>, converter: (T) -> Collection<*>): Proof =
        container.buildSimpleProof(NOT_TO_BE, EMPTY) { converter(it).isNotEmpty() }

    override fun <T : CollectionLike> size(container: ProofContainer<T>, converter: (T) -> Collection<*>): FeatureExtractorBuilder.ExecutionStep<T, Int> =
        TODO("1.3.0")

}
