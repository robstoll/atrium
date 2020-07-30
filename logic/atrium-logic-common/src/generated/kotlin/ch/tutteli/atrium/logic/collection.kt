//---------------------------------------------------
//  Generated content, modify:
//  logic/generateLogic.gradle
//  if necessary - enjoy the day 🙂
//---------------------------------------------------

package ch.tutteli.atrium.logic

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.domain.creating.changers.ExtractedFeaturePostStep

import ch.tutteli.atrium.core.ExperimentalNewExpectTypes
import ch.tutteli.atrium.logic.impl.DefaultCollectionAssertions

@Suppress("DEPRECATION" /* OptIn is only available since 1.3.70 which we cannot use if we want to support 1.2 */)
@UseExperimental(ExperimentalNewExpectTypes::class)
private inline val <T> AssertionContainer<T>.impl: CollectionAssertions
    get() = getImpl(CollectionAssertions::class) { DefaultCollectionAssertions() }

fun <T : Collection<*>> AssertionContainer<T>.isEmpty(): Assertion = impl.isEmpty(this)
fun <T : Collection<*>> AssertionContainer<T>.isNotEmpty(): Assertion = impl.isNotEmpty(this)
fun <T : Collection<*>> AssertionContainer<T>.size(): ExtractedFeaturePostStep<T, Int> = impl.size(this)
