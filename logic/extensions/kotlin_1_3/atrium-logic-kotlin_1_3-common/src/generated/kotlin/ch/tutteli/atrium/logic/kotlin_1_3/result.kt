// @formatter:off
//---------------------------------------------------
//  Generated content, modify:
//  logic/generateLogic.gradle
//  if necessary - enjoy the day 🙂
//---------------------------------------------------

package ch.tutteli.atrium.logic.kotlin_1_3

import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.logic.creating.transformers.FeatureExtractorBuilder
import ch.tutteli.atrium.logic.creating.transformers.SubjectChangerBuilder
import kotlin.reflect.KClass
import ch.tutteli.atrium.core.ExperimentalNewExpectTypes
import ch.tutteli.atrium.logic.kotlin_1_3.impl.DefaultResultAssertions

fun <E, T : Result<E>> AssertionContainer<T>.isSuccess(): FeatureExtractorBuilder.ExecutionStep<T, E> = impl.isSuccess(this)

fun <TExpected : Throwable> AssertionContainer<out Result<*>>.isFailureOfType(expectedType: KClass<TExpected>): SubjectChangerBuilder.ExecutionStep<Throwable?, TExpected> = impl.isFailureOfType(this, expectedType)

@Suppress("DEPRECATION" /* OptIn is only available since 1.3.70 which we cannot use if we want to support 1.2 */)
@UseExperimental(ExperimentalNewExpectTypes::class)
private inline val <T> AssertionContainer<T>.impl: ResultAssertions
    get() = getImpl(ResultAssertions::class) { DefaultResultAssertions() }
