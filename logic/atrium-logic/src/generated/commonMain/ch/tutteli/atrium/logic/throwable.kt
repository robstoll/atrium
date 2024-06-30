// @formatter:off
//---------------------------------------------------
//  Generated content, modify:
//  buildSrc/generation.kt
//  if necessary - enjoy the day 🙂
//---------------------------------------------------
//TODO 2.0.0 remove file
@file:Suppress("DEPRECATION")package ch.tutteli.atrium.logic

import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.logic.creating.transformers.SubjectChangerBuilder
import kotlin.reflect.KClass

/**
 * Collection of assertion functions and builders which are applicable to subjects with a [Throwable] type.
 */
import ch.tutteli.atrium.core.ExperimentalNewExpectTypes
import ch.tutteli.atrium.logic.impl.DefaultThrowableAssertions


fun <ExpectedThrowableT : Throwable> AssertionContainer<out Throwable>.causeIsA(expectedType: KClass<ExpectedThrowableT>): SubjectChangerBuilder.ExecutionStep<Throwable?, ExpectedThrowableT> = impl.causeIsA(this, expectedType)

@OptIn(ExperimentalNewExpectTypes::class)
private inline val <T> AssertionContainer<T>.impl: ThrowableAssertions
    get() = getImpl(ThrowableAssertions::class) { DefaultThrowableAssertions() }
