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


fun <T : CollectionLike> ProofContainer<T>.toBeEmpty(converter: (T) -> Collection<*>): Proof = impl.toBeEmpty(this, converter)
fun <T : CollectionLike> ProofContainer<T>.notToBeEmpty(converter: (T) -> Collection<*>): Proof = impl.notToBeEmpty(this, converter)

fun <T : CollectionLike> ProofContainer<T>.size(converter: (T) -> Collection<*>): FeatureExtractorBuilder.ExecutionStep<T, Int> = impl.size(this, converter)

@OptIn(ExperimentalNewExpectTypes::class)
private inline val <T> ProofContainer<T>.impl: CollectionLikeProofs
    get() = getImpl(CollectionLikeProofs::class) { DefaultCollectionLikeProofs() }
