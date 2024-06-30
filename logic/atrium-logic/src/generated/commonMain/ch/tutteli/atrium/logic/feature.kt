// @formatter:off
//---------------------------------------------------
//  Generated content, modify:
//  buildSrc/generation.kt
//  if necessary - enjoy the day 🙂
//---------------------------------------------------
//TODO 2.0.0 remove file
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.logic

import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.creating.transformers.FeatureExtractorBuilder
import kotlin.reflect.*

/**
 * Collection of functions which help to create feature assertions by returning [FeatureExtractorBuilder.ExecutionStep].
 */
import ch.tutteli.atrium.core.ExperimentalNewExpectTypes
import ch.tutteli.atrium.logic.impl.DefaultFeatureAssertions

    //@formatter:off
fun <T, TProperty> AssertionContainer<T>.property(property: KProperty1<in T, TProperty>): FeatureExtractorBuilder.ExecutionStep<T, TProperty> = impl.property(this, property)

fun <T, R> AssertionContainer<T>.f0(f: KFunction1<T, R>): FeatureExtractorBuilder.ExecutionStep<T, R> = impl.f0(this, f)

fun <T, A1, R> AssertionContainer<T>.f1(f: KFunction2<T, A1, R>, a1: A1): FeatureExtractorBuilder.ExecutionStep<T, R> =
    impl.f1(this, f, a1)

fun <T, A1, A2, R> AssertionContainer<T>.f2(f: KFunction3<T, A1, A2, R>, a1: A1, a2: A2): FeatureExtractorBuilder.ExecutionStep<T, R> =
    impl.f2(this, f, a1, a2)

fun <T, A1, A2, A3, R> AssertionContainer<T>.f3(f: KFunction4<T, A1, A2, A3, R>, a1: A1, a2: A2, a3: A3): FeatureExtractorBuilder.ExecutionStep<T, R> =
    impl.f3(this, f, a1, a2, a3)

fun <T, A1, A2, A3, A4, R> AssertionContainer<T>.f4(f: KFunction5<T, A1, A2, A3, A4, R>, a1: A1, a2: A2, a3: A3, a4: A4): FeatureExtractorBuilder.ExecutionStep<T, R> =
    impl.f4(this, f, a1, a2, a3, a4)

fun <T, A1, A2, A3, A4, A5, R> AssertionContainer<T>.f5(f: KFunction6<T, A1, A2, A3, A4, A5, R>, a1: A1, a2: A2, a3: A3, a4: A4, a5: A5): FeatureExtractorBuilder.ExecutionStep<T, R> =
    impl.f5(this, f, a1, a2, a3, a4, a5)
    //@formatter:on

    //TODO 1.3.0 provide replacement?
    @Suppress("DEPRECATION")
fun <T, R> AssertionContainer<T>.manualFeature(description: ch.tutteli.atrium.reporting.translating.Translatable, provider: T.() -> R): FeatureExtractorBuilder.ExecutionStep<T, R> =
    impl.manualFeature(this, description, provider)

    //TODO 1.3.0 provide another overload which expects InlineElement?
fun <T> AssertionContainer<T>.extractSubject(failureDescription: String?, assertionCreator: Expect<T>.(T) -> Unit): Expect<T> =
    impl.extractSubject(this, failureDescription, assertionCreator)

@OptIn(ExperimentalNewExpectTypes::class)
private inline val <T> AssertionContainer<T>.impl: FeatureAssertions
    get() = getImpl(FeatureAssertions::class) { DefaultFeatureAssertions() }
