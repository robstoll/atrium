//---------------------------------------------------
//  Generated content, modify:
//  logic/generateLogic.gradle
//  if necessary - enjoy the day 🙂
//---------------------------------------------------

package ch.tutteli.atrium.logic

import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.domain.creating.MetaFeature
import ch.tutteli.atrium.domain.creating.changers.ExtractedFeaturePostStep
import ch.tutteli.atrium.reporting.translating.Translatable
import kotlin.reflect.KProperty1
import kotlin.reflect.KFunction1
import kotlin.reflect.KFunction2
import kotlin.reflect.KFunction3
import kotlin.reflect.KFunction4
import kotlin.reflect.KFunction5
import kotlin.reflect.KFunction6

import ch.tutteli.atrium.core.ExperimentalNewExpectTypes
import ch.tutteli.atrium.logic.impl.DefaultFeatureAssertions

@Suppress("DEPRECATION" /* OptIn is only available since 1.3.70 which we cannot use if we want to support 1.2 */)
@UseExperimental(ExperimentalNewExpectTypes::class)
private inline val <T> AssertionContainer<T>.impl: FeatureAssertions
    get() = getImpl(FeatureAssertions::class) { DefaultFeatureAssertions() }

    //@formatter:off
fun <T, TProperty> AssertionContainer<T>.property(property: KProperty1<in T, TProperty>): ExtractedFeaturePostStep<T, TProperty> = impl.property(this, property)

fun <T, R> AssertionContainer<T>.f0(f: KFunction1<T, R>): ExtractedFeaturePostStep<T, R> = impl.f0(this, f)

fun <T, A1, R> AssertionContainer<T>.f1(f: KFunction2<T, A1, R>, a1: A1): ExtractedFeaturePostStep<T, R> =
    impl.f1(this, f, a1)

fun <T, A1, A2, R> AssertionContainer<T>.f2(f: KFunction3<T, A1, A2, R>, a1: A1, a2: A2): ExtractedFeaturePostStep<T, R> =
    impl.f2(this, f, a1, a2)

fun <T, A1, A2, A3, R> AssertionContainer<T>.f3(f: KFunction4<T, A1, A2, A3, R>, a1: A1, a2: A2, a3: A3): ExtractedFeaturePostStep<T, R> =
    impl.f3(this, f, a1, a2, a3)

fun <T, A1, A2, A3, A4, R> AssertionContainer<T>.f4(f: KFunction5<T, A1, A2, A3, A4, R>, a1: A1, a2: A2, a3: A3, a4: A4): ExtractedFeaturePostStep<T, R> =
    impl.f4(this, f, a1, a2, a3, a4)

fun <T, A1, A2, A3, A4, A5, R> AssertionContainer<T>.f5(f: KFunction6<T, A1, A2, A3, A4, A5, R>, a1: A1, a2: A2, a3: A3, a4: A4, a5: A5): ExtractedFeaturePostStep<T, R> =
    impl.f5(this, f, a1, a2, a3, a4, a5)
    //@formatter:on

fun <T, R> AssertionContainer<T>.manualFeature(description: String, provider: T.() -> R): ExtractedFeaturePostStep<T, R> =
    impl.manualFeature(this, description, provider)

fun <T, R> AssertionContainer<T>.manualFeature(description: Translatable, provider: T.() -> R): ExtractedFeaturePostStep<T, R> =
    impl.manualFeature(this, description, provider)

fun <T, R> AssertionContainer<T>.genericSubjectBasedFeature(provider: (T) -> MetaFeature<R>): ExtractedFeaturePostStep<T, R> = impl.genericSubjectBasedFeature(this, provider)

fun <T, R> AssertionContainer<T>.genericFeature(metaFeature: MetaFeature<R>): ExtractedFeaturePostStep<T, R> = impl.genericFeature(this, metaFeature)
