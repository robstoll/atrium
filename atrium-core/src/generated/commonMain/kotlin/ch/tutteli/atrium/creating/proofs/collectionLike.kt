// @formatter:off
//---------------------------------------------------
//  Generated content, modify:
//  gradle/build-logic/dev/src/main/kotlin/generation.kt
//  if necessary - enjoy the day 🙂
//---------------------------------------------------
package ch.tutteli.atrium.creating.proofs

import ch.tutteli.atrium.creating.ProofContainer
import ch.tutteli.atrium.creating.transformers.FeatureExtractorBuilder
import ch.tutteli.atrium.creating.typeutils.CollectionLike
import ch.tutteli.atrium.core.ExperimentalNewExpectTypes
import ch.tutteli.atrium.creating.proofs.impl.DefaultCollectionLikeProofs


    /** @since 1.3.0 */
fun <SubjectT : CollectionLike> ProofContainer<SubjectT>.toBeEmpty(converter: (SubjectT) -> Collection<*>): Proof =
    impl.toBeEmpty(this, converter)

    /** @since 1.3.0 */
fun <SubjectT : CollectionLike> ProofContainer<SubjectT>.notToBeEmpty(converter: (SubjectT) -> Collection<*>): Proof =
    impl.notToBeEmpty(this, converter)

    /** @since 1.3.0 */
fun <SubjectT : CollectionLike> ProofContainer<SubjectT>.size(converter: (SubjectT) -> Collection<*>): FeatureExtractorBuilder.ExecutionStep<SubjectT, Int> =
    impl.size(this, converter)

@OptIn(ExperimentalNewExpectTypes::class)
private inline val <T> ProofContainer<T>.impl: CollectionLikeProofs
    get() = getImpl(CollectionLikeProofs::class) { DefaultCollectionLikeProofs() }
