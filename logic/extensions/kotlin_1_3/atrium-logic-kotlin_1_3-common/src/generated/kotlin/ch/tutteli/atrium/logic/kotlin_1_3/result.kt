//---------------------------------------------------
//  Generated content, modify:
//  logic/generateLogic.gradle
//  if necessary - enjoy the day 🙂
//---------------------------------------------------

package ch.tutteli.atrium.logic.kotlin_1_3

import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.domain.creating.changers.ChangedSubjectPostStep
import ch.tutteli.atrium.domain.creating.changers.ExtractedFeaturePostStep
import kotlin.reflect.KClass

import ch.tutteli.atrium.core.ExperimentalNewExpectTypes
import ch.tutteli.atrium.logic.kotlin_1_3.impl.DefaultResultAssertions

@Suppress("DEPRECATION" /* OptIn is only available since 1.3.70 which we cannot use if we want to support 1.2 */)
@UseExperimental(ExperimentalNewExpectTypes::class)
private inline val <T> AssertionContainer<T>.impl: ResultAssertions
    get() = getImpl(ResultAssertions::class) { DefaultResultAssertions() }

fun <E, T : Result<E>> AssertionContainer<T>.isSuccess(): ExtractedFeaturePostStep<T, E> = impl.isSuccess(this)

fun <TExpected : Throwable> AssertionContainer<out Result<*>>.isFailure(expectedType: KClass<TExpected>): ChangedSubjectPostStep<Throwable?, TExpected> = impl.isFailure(this, expectedType)
