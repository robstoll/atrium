//---------------------------------------------------
//  Generated content, modify:
//  logic/generateLogic.gradle
//  if necessary - enjoy the day 🙂
//---------------------------------------------------

package ch.tutteli.atrium.logic

import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.logic.creating.transformers.SubjectChangerBuilder
import kotlin.reflect.KClass


fun <TExpected : Throwable> AssertionContainer<out Throwable>.causeIsA(expectedType: KClass<TExpected>): SubjectChangerBuilder.ExecutionStep<Throwable?, TExpected> = _throwableImpl.causeIsA(this, expectedType)
