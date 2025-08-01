// @formatter:off
//---------------------------------------------------
//  Generated content, don't change here but in:
//  gradle/build-logic/dev/src/main/kotlin/generation.kt
//  Enjoy the day 🙂
//---------------------------------------------------
package ch.tutteli.atrium.logic

import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.creating.transformers.FeatureExtractorBuilder
import ch.tutteli.atrium.reporting.translating.Translatable
import kotlin.reflect.*
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

fun <T, R> AssertionContainer<T>.manualFeature(description: Translatable, provider: T.() -> R): FeatureExtractorBuilder.ExecutionStep<T, R> =
    impl.manualFeature(this, description, provider)

fun <T> AssertionContainer<T>.extractSubject(failureDescription: String?, assertionCreator: Expect<T>.(T) -> Unit): Expect<T> =
    impl.extractSubject(this, failureDescription, assertionCreator)

@OptIn(ExperimentalNewExpectTypes::class)
private inline val <T> AssertionContainer<T>.impl: FeatureAssertions
    get() = getImpl(FeatureAssertions::class) { DefaultFeatureAssertions() }
