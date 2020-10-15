//---------------------------------------------------
//  Generated content, modify:
//  logic/generateLogic.gradle
//  if necessary - enjoy the day 🙂
//---------------------------------------------------

package ch.tutteli.atrium.logic

import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.logic.creating.transformers.SubjectChangerBuilder
import kotlin.reflect.KClass
import ch.tutteli.atrium.core.ExperimentalNewExpectTypes
import ch.tutteli.atrium.logic.impl.DefaultThrowableAssertions


fun <TExpected : Throwable> AssertionContainer<out Throwable>.causeIsA(expectedType: KClass<TExpected>): SubjectChangerBuilder.ExecutionStep<Throwable?, TExpected> = impl.causeIsA(this, expectedType)

@Suppress("DEPRECATION" /* OptIn is only available since 1.3.70 which we cannot use if we want to support 1.2 */)
@UseExperimental(ExperimentalNewExpectTypes::class)
private inline val <T> AssertionContainer<T>.impl: ThrowableAssertions
    get() = getImpl(ThrowableAssertions::class) { DefaultThrowableAssertions() }
