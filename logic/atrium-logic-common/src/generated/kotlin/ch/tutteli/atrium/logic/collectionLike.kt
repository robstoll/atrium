// @formatter:off
//---------------------------------------------------
//  Generated content, modify:
//  logic/generateLogic.gradle
//  if necessary - enjoy the day 🙂
//---------------------------------------------------

package ch.tutteli.atrium.logic

import ch.tutteli.atrium.assertions.Assertionimport ch.tutteli.atrium.core.ExperimentalNewExpectTypesimport ch.tutteli.atrium.creating.AssertionContainerimport ch.tutteli.atrium.logic.creating.transformers.FeatureExtractorBuilderimport ch.tutteli.atrium.logic.creating.typeutils.CollectionLikeimport ch.tutteli.atrium.logic.impl.DefaultCollectionLikeAssertions


fun <T : CollectionLike> AssertionContainer<T>.isEmpty(converter: (T) -> Collection<*>): Assertion = impl.isEmpty(this, converter)
fun <T : CollectionLike> AssertionContainer<T>.isNotEmpty(converter: (T) -> Collection<*>): Assertion = impl.isNotEmpty(this, converter)

fun <T : CollectionLike> AssertionContainer<T>.size(converter: (T) -> Collection<*>): FeatureExtractorBuilder.ExecutionStep<T, Int> = impl.size(this, converter)

@Suppress("DEPRECATION" /* OptIn is only available since 1.3.70 which we cannot use if we want to support 1.2 */)
@UseExperimental(ExperimentalNewExpectTypes::class)
private inline val <T> AssertionContainer<T>.impl: CollectionLikeAssertions
    get() = getImpl(CollectionLikeAssertions::class) { DefaultCollectionLikeAssertions() }
