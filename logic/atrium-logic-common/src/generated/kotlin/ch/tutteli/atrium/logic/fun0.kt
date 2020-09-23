//---------------------------------------------------
//  Generated content, modify:
//  logic/generateLogic.gradle
//  if necessary - enjoy the day 🙂
//---------------------------------------------------

package ch.tutteli.atrium.logic

import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.logic.creating.transformers.SubjectChangerBuilder
import kotlin.reflect.KClass


fun <TExpected : Throwable> AssertionContainer<out () -> Any?>.toThrow(expectedType: KClass<TExpected>): SubjectChangerBuilder.ExecutionStep<*, TExpected> = _fun0Impl.toThrow(this, expectedType)

fun <R, T : () -> R> AssertionContainer<T>.notToThrow(): SubjectChangerBuilder.ExecutionStep<*, R> = _fun0Impl.notToThrow(this)
