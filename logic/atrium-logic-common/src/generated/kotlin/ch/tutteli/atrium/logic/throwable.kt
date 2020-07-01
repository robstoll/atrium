//---------------------------------------------------
//  Generated content, modify:
//  logic/generateLogic.gradle
//  if necessary - enjoy the day 🙂
//---------------------------------------------------

package ch.tutteli.atrium.logic

import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.domain.creating.changers.ChangedSubjectPostStep
import kotlin.reflect.KClass


fun <TExpected : Throwable> AssertionContainer<out Throwable>.cause(expectedType: KClass<TExpected>): ChangedSubjectPostStep<Throwable?, TExpected> = _throwableImpl.cause(this, expectedType)
