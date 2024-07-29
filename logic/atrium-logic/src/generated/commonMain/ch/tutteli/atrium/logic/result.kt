// @formatter:off
//---------------------------------------------------
//  Generated content, modify:
//  buildSrc/generation.kt
//  if necessary - enjoy the day 🙂
//---------------------------------------------------
//TODO 2.0.0 remove file
@file:Suppress("DEPRECATION")package ch.tutteli.atrium.logic

import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.logic.creating.transformers.FeatureExtractorBuilder
import ch.tutteli.atrium.logic.creating.transformers.SubjectChangerBuilder
import kotlin.reflect.KClass

/**
 * Collection of assertion functions and builders which are applicable to subjects with a [Result] type.
 */
import ch.tutteli.atrium.core.ExperimentalNewExpectTypes
import ch.tutteli.atrium.logic.impl.DefaultResultAssertions

fun <E, T : Result<E>> AssertionContainer<T>.isSuccess(): FeatureExtractorBuilder.ExecutionStep<T, E> = impl.isSuccess(this)

fun <ExpectedThrowableT : Throwable> AssertionContainer<out Result<*>>.isFailureOfType(expectedType: KClass<ExpectedThrowableT>): SubjectChangerBuilder.ExecutionStep<Throwable?, ExpectedThrowableT> = impl.isFailureOfType(this, expectedType)

@OptIn(ExperimentalNewExpectTypes::class)
private inline val <T> AssertionContainer<T>.impl: ResultAssertions
    get() = getImpl(ResultAssertions::class) { DefaultResultAssertions() }
