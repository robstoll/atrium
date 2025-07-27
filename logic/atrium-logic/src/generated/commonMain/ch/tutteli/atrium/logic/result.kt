// @formatter:off
//---------------------------------------------------
//  Generated content, don't change here but in:
//  gradle/build-logic/dev/src/main/kotlin/generation.kt
//  Enjoy the day 🙂
//---------------------------------------------------
package ch.tutteli.atrium.logic

import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.logic.creating.transformers.FeatureExtractorBuilder
import ch.tutteli.atrium.logic.creating.transformers.SubjectChangerBuilder
import kotlin.reflect.KClass
import ch.tutteli.atrium.core.ExperimentalNewExpectTypes
import ch.tutteli.atrium.logic.impl.DefaultResultAssertions

fun <E, T : Result<E>> AssertionContainer<T>.isSuccess(): FeatureExtractorBuilder.ExecutionStep<T, E> = impl.isSuccess(this)

fun <ExpectedThrowableT : Throwable> AssertionContainer<out Result<*>>.isFailureOfType(expectedType: KClass<ExpectedThrowableT>): SubjectChangerBuilder.ExecutionStep<Throwable?, ExpectedThrowableT> = impl.isFailureOfType(this, expectedType)

@OptIn(ExperimentalNewExpectTypes::class)
private inline val <T> AssertionContainer<T>.impl: ResultAssertions
    get() = getImpl(ResultAssertions::class) { DefaultResultAssertions() }
