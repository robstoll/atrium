package ch.tutteli.atrium.logic

import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.domain.creating.MetaFeature
import ch.tutteli.atrium.logic.creating.transformers.FeatureExtractorBuilder
import ch.tutteli.atrium.reporting.translating.Translatable
import kotlin.reflect.KProperty1
import kotlin.reflect.KFunction1
import kotlin.reflect.KFunction2
import kotlin.reflect.KFunction3
import kotlin.reflect.KFunction4
import kotlin.reflect.KFunction5
import kotlin.reflect.KFunction6

/**
 * Collection of functions which help to create feature assertions by returning [FeatureExtractorBuilder.ExecutionStep].
 */
interface FeatureAssertions {
    //@formatter:off
    fun <T, TProperty> property(container: AssertionContainer<T>, property: KProperty1<in T, TProperty>): FeatureExtractorBuilder.ExecutionStep<T, TProperty>

    fun <T, R> f0(container: AssertionContainer<T>, f: KFunction1<T, R>): FeatureExtractorBuilder.ExecutionStep<T, R>

    fun <T, A1, R> f1(container: AssertionContainer<T>, f: KFunction2<T, A1, R>, a1: A1): FeatureExtractorBuilder.ExecutionStep<T, R>

    fun <T, A1, A2, R> f2(container: AssertionContainer<T>, f: KFunction3<T, A1, A2, R>, a1: A1, a2: A2): FeatureExtractorBuilder.ExecutionStep<T, R>

    fun <T, A1, A2, A3, R> f3(container: AssertionContainer<T>, f: KFunction4<T, A1, A2, A3, R>, a1: A1, a2: A2, a3: A3): FeatureExtractorBuilder.ExecutionStep<T, R>

    fun <T, A1, A2, A3, A4, R> f4(container: AssertionContainer<T>, f: KFunction5<T, A1, A2, A3, A4, R>, a1: A1, a2: A2, a3: A3, a4: A4): FeatureExtractorBuilder.ExecutionStep<T, R>

    fun <T, A1, A2, A3, A4, A5, R> f5(container: AssertionContainer<T>, f: KFunction6<T, A1, A2, A3, A4, A5, R>, a1: A1, a2: A2, a3: A3, a4: A4, a5: A5): FeatureExtractorBuilder.ExecutionStep<T, R>
    //@formatter:on

    fun <T, R> manualFeature(
        container: AssertionContainer<T>,
        description: String,
        provider: T.() -> R
    ): FeatureExtractorBuilder.ExecutionStep<T, R>

    fun <T, R> manualFeature(
        container: AssertionContainer<T>,
        description: Translatable,
        provider: T.() -> R
    ): FeatureExtractorBuilder.ExecutionStep<T, R>

    fun <T, R> genericSubjectBasedFeature(
        container: AssertionContainer<T>,
        provider: (T) -> MetaFeature<R>
    ): FeatureExtractorBuilder.ExecutionStep<T, R>

    fun <T, R> genericFeature(
        container: AssertionContainer<T>,
        metaFeature: MetaFeature<R>
    ): FeatureExtractorBuilder.ExecutionStep<T, R>
}
