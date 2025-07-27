// @formatter:off
//---------------------------------------------------
//  Generated content, don't change here but in:
//  gradle/build-logic/dev/src/main/kotlin/generation.kt
//  Enjoy the day 🙂
//---------------------------------------------------
package ch.tutteli.atrium.logic

import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.logic.creating.transformers.FeatureExtractorBuilder
import ch.tutteli.atrium.logic.creating.transformers.SubjectChangerBuilder
import kotlin.reflect.KClass
import ch.tutteli.atrium.core.ExperimentalNewExpectTypes
import ch.tutteli.atrium.logic.impl.DefaultFun0Assertions


fun <ExpectedThrowableT : Throwable> AssertionContainer<out () -> Any?>.toThrow(expectedType: KClass<ExpectedThrowableT>): SubjectChangerBuilder.ExecutionStep<*, ExpectedThrowableT> = impl.toThrow(this, expectedType)

fun <R, T : () -> R> AssertionContainer<T>.notToThrow(): FeatureExtractorBuilder.ExecutionStep<*, R> = impl.notToThrow(this)

@OptIn(ExperimentalNewExpectTypes::class)
private inline val <T> AssertionContainer<T>.impl: Fun0Assertions
    get() = getImpl(Fun0Assertions::class) { DefaultFun0Assertions() }
