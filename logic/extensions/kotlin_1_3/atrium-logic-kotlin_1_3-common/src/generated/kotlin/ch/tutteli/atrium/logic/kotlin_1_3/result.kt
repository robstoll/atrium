//---------------------------------------------------
//  Generated content, modify:
//  logic/generateLogic.gradle
//  if necessary - enjoy the day 🙂
//---------------------------------------------------

package ch.tutteli.atrium.logic.kotlin_1_3

import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.domain.creating.changers.ExtractedFeaturePostStep
import ch.tutteli.atrium.logic.creating.transformers.SubjectChangerBuilder
import kotlin.reflect.KClass

fun <E, T : Result<E>> AssertionContainer<T>.isSuccess(): ExtractedFeaturePostStep<T, E> = _resultImpl.isSuccess(this)

fun <TExpected : Throwable> AssertionContainer<out Result<*>>.isFailureOfType(expectedType: KClass<TExpected>): SubjectChangerBuilder.ExecutionStep<Throwable?, TExpected> = _resultImpl.isFailureOfType(this, expectedType)
