// @formatter:off
//---------------------------------------------------
//  Generated content, modify:
//  buildSrc/generation.kt
//  if necessary - enjoy the day 🙂
//---------------------------------------------------
//TODO 2.0.0 remove file
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.logic

import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.logic.creating.transformers.FeatureExtractorBuilder
import ch.tutteli.atrium.logic.creating.transformers.SubjectChangerBuilder
import kotlin.reflect.KClass

/**
 * Collection of assertion functions and builders which are applicable to subjects with a [kotlin.Function0] type.
 */
import ch.tutteli.atrium.core.ExperimentalNewExpectTypes
import ch.tutteli.atrium.logic.impl.DefaultFun0Assertions


fun <ExpectedThrowableT : Throwable> AssertionContainer<out () -> Any?>.toThrow(expectedType: KClass<ExpectedThrowableT>): SubjectChangerBuilder.ExecutionStep<*, ExpectedThrowableT> = impl.toThrow(this, expectedType)

fun <R, T : () -> R> AssertionContainer<T>.notToThrow(): FeatureExtractorBuilder.ExecutionStep<*, R> = impl.notToThrow(this)

@OptIn(ExperimentalNewExpectTypes::class)
private inline val <T> AssertionContainer<T>.impl: Fun0Assertions
    get() = getImpl(Fun0Assertions::class) { DefaultFun0Assertions() }
