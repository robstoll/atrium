// @formatter:off
//---------------------------------------------------
//  Generated content, modify:
//  logic/generateLogic.gradle
//  if necessary - enjoy the day 🙂
//---------------------------------------------------

package ch.tutteli.atrium.logic

import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.logic.creating.transformers.FeatureExtractorBuilder
import ch.tutteli.atrium.logic.creating.transformers.SubjectChangerBuilder
import kotlin.reflect.KClass
import ch.tutteli.atrium.core.ExperimentalNewExpectTypes
import ch.tutteli.atrium.logic.impl.DefaultFun0Assertions


fun <TExpected : Throwable> AssertionContainer<out () -> Any?>.toThrow(expectedType: KClass<TExpected>): SubjectChangerBuilder.ExecutionStep<*, TExpected> = impl.toThrow(this, expectedType)

fun <R, T : () -> R> AssertionContainer<T>.notToThrow(): FeatureExtractorBuilder.ExecutionStep<*, R> = impl.notToThrow(this)

@Suppress("DEPRECATION" /* OptIn is only available since 1.3.70 which we cannot use if we want to support 1.2 */)
@UseExperimental(ExperimentalNewExpectTypes::class)
private inline val <T> AssertionContainer<T>.impl: Fun0Assertions
    get() = getImpl(Fun0Assertions::class) { DefaultFun0Assertions() }
