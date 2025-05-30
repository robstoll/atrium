package ch.tutteli.atrium.logic

import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.creating.transformers.FeatureExtractorBuilder
import kotlin.reflect.*

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

    //TODO 1.3.0 provide replacement?
    @Suppress("DEPRECATION")
    fun <T, R> manualFeature(
        container: AssertionContainer<T>,
        description: ch.tutteli.atrium.reporting.translating.Translatable,
        provider: T.() -> R
    ): FeatureExtractorBuilder.ExecutionStep<T, R>

    fun <T> extractSubject(
        container: AssertionContainer<T>,
        failureDescription: String?,
        assertionCreator: Expect<T>.(T) -> Unit
    ): Expect<T>
}
