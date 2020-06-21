//---------------------------------------------------
//  Generated content, modify: 
//  logic/atrium-logic-common/build.gradle 
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

    //@formatter:off
fun <T, TProperty> AssertionContainer<T>.property(property: KProperty1<in T, TProperty>): ExtractedFeaturePostStep<T, TProperty> = _featureImpl.property(this, property)

fun <T, R> AssertionContainer<T>.f0(f: KFunction1<T, R>): ExtractedFeaturePostStep<T, R> = _featureImpl.f0(this, f)

fun <T, A1, R> AssertionContainer<T>.f1(f: KFunction2<T, A1, R>, a1: A1): ExtractedFeaturePostStep<T, R> =
    _featureImpl.f1(this, f, a1)

fun <T, A1, A2, R> AssertionContainer<T>.f2(f: KFunction3<T, A1, A2, R>, a1: A1, a2: A2): ExtractedFeaturePostStep<T, R> =
    _featureImpl.f2(this, f, a1, a2)

fun <T, A1, A2, A3, R> AssertionContainer<T>.f3(f: KFunction4<T, A1, A2, A3, R>, a1: A1, a2: A2, a3: A3): ExtractedFeaturePostStep<T, R> =
    _featureImpl.f3(this, f, a1, a2, a3)

fun <T, A1, A2, A3, A4, R> AssertionContainer<T>.f4(f: KFunction5<T, A1, A2, A3, A4, R>, a1: A1, a2: A2, a3: A3, a4: A4): ExtractedFeaturePostStep<T, R> =
    _featureImpl.f4(this, f, a1, a2, a3, a4)

fun <T, A1, A2, A3, A4, A5, R> AssertionContainer<T>.f5(f: KFunction6<T, A1, A2, A3, A4, A5, R>, a1: A1, a2: A2, a3: A3, a4: A4, a5: A5): ExtractedFeaturePostStep<T, R> =
    _featureImpl.f5(this, f, a1, a2, a3, a4, a5)

    //@formatter:on

fun <T, R> AssertionContainer<T>.manualFeature(description: String, provider: T.() -> R): ExtractedFeaturePostStep<T, R> =
    _featureImpl.manualFeature(this, description, provider)

fun <T, R> AssertionContainer<T>.manualFeature(description: Translatable, provider: T.() -> R): ExtractedFeaturePostStep<T, R> =
    _featureImpl.manualFeature(this, description, provider)

fun <T, R> AssertionContainer<T>.genericSubjectBasedFeature(provider: (T) -> MetaFeature<R>): ExtractedFeaturePostStep<T, R> = _featureImpl.genericSubjectBasedFeature(this, provider)

fun <T, R> AssertionContainer<T>.genericFeature(metaFeature: MetaFeature<R>): ExtractedFeaturePostStep<T, R> = _featureImpl.genericFeature(this, metaFeature)

