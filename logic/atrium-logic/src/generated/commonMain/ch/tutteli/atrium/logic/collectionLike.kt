// @formatter:off
//---------------------------------------------------
//  Generated content, modify:
//  buildSrc/generation.kt
//  if necessary - enjoy the day 🙂
//---------------------------------------------------
//TODO 2.0.0 remove file
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.logic

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.logic.creating.transformers.FeatureExtractorBuilder
import ch.tutteli.atrium.logic.creating.typeutils.CollectionLike
import ch.tutteli.atrium.core.ExperimentalNewExpectTypes
import ch.tutteli.atrium.logic.impl.DefaultCollectionLikeAssertions


    @Deprecated(
        "Migrate from AssertionContainer to ProofContainer and use toBeEmpty, will be removed with 2.0.0 at the latest",
        ReplaceWith("this.toBeEmpty()", "ch.tutteli.atrium.creating.proofs.toBeEmpty")
    )
fun <T : CollectionLike> AssertionContainer<T>.isEmpty(converter: (T) -> Collection<*>): Assertion = impl.isEmpty(this, converter)

    @Deprecated(
        "Migrate from AssertionContainer to ProofContainer and use notToBeEmpty, will be removed with 2.0.0 at the latest",
        ReplaceWith("this.notToBeEmpty()", "ch.tutteli.atrium.creating.proofs.notToBeEmpty")
    )
fun <T : CollectionLike> AssertionContainer<T>.isNotEmpty(converter: (T) -> Collection<*>): Assertion = impl.isNotEmpty(this, converter)

fun <T : CollectionLike> AssertionContainer<T>.size(converter: (T) -> Collection<*>): FeatureExtractorBuilder.ExecutionStep<T, Int> = impl.size(this, converter)

@OptIn(ExperimentalNewExpectTypes::class)
private inline val <T> AssertionContainer<T>.impl: CollectionLikeAssertions
    get() = getImpl(CollectionLikeAssertions::class) { DefaultCollectionLikeAssertions() }
