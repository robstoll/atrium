// @formatter:off
//---------------------------------------------------
//  Generated content, modify:
//  buildSrc/generation.kt
//  if necessary - enjoy the day 🙂
//---------------------------------------------------
package ch.tutteli.atrium.logic

import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.logic.creating.transformers.SubjectChangerBuilder
import kotlin.reflect.KClass
import ch.tutteli.atrium.core.ExperimentalNewExpectTypes
import ch.tutteli.atrium.logic.impl.DefaultThrowableAssertions


fun <TExpected : Throwable> AssertionContainer<Throwable>.causeIsA(expectedType: KClass<TExpected>): SubjectChangerBuilder.ExecutionStep<Throwable?, TExpected> = impl.causeIsA(this, expectedType)

@OptIn(ExperimentalNewExpectTypes::class)
private inline val <T> AssertionContainer<T>.impl: ThrowableAssertions
    get() = getImpl(ThrowableAssertions::class) { DefaultThrowableAssertions() }
