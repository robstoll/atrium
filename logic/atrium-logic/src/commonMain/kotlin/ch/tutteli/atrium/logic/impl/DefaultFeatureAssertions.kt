package ch.tutteli.atrium.logic.impl

import ch.tutteli.atrium.assertions.builders.assertionBuilder
import ch.tutteli.atrium.core.Some
import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.creating.ExperimentalComponentFactoryContainer
import ch.tutteli.atrium.creating.build
import ch.tutteli.atrium.logic.FeatureAssertions
import ch.tutteli.atrium.logic.collect
import ch.tutteli.atrium.logic.creating.transformers.FeatureExtractorBuilder
import ch.tutteli.atrium.logic.extractFeature
import ch.tutteli.atrium.reporting.MethodCallFormatter
import ch.tutteli.atrium.reporting.Text
import ch.tutteli.atrium.translations.ErrorMessages
import kotlin.reflect.*

class DefaultFeatureAssertions : FeatureAssertions {

    //@formatter:off
    override fun <T, TProperty> property(container: AssertionContainer<T>, property: KProperty1<in T, TProperty>): FeatureExtractorBuilder.ExecutionStep<T, TProperty> =
        extractFeature(container, property.name, property::get)

    override fun <T, R> f0(container: AssertionContainer<T>, f: KFunction1<T, R>): FeatureExtractorBuilder.ExecutionStep<T, R> =
        extractFeature(container, buildMethodCallFormatter(container).formatCall(f.name, arrayOf()), f::invoke)


    override fun <T, A1, R> f1(container: AssertionContainer<T>, f: KFunction2<T, A1, R>, a1: A1): FeatureExtractorBuilder.ExecutionStep<T, R> =
        extractFeature(container, buildMethodCallFormatter(container).formatCall(f.name, arrayOf<Any?>(a1))) { f(it, a1) }

    override fun <T, A1, A2, R> f2(container: AssertionContainer<T>, f: KFunction3<T, A1, A2, R>, a1: A1, a2: A2): FeatureExtractorBuilder.ExecutionStep<T, R> =
        extractFeature(container ,buildMethodCallFormatter(container).formatCall(f.name, arrayOf(a1, a2))) { f(it, a1, a2) }

    override fun <T, A1, A2, A3, R> f3(container: AssertionContainer<T>, f: KFunction4<T, A1, A2, A3, R>, a1: A1, a2: A2, a3: A3): FeatureExtractorBuilder.ExecutionStep<T, R> =
        extractFeature(container, buildMethodCallFormatter(container).formatCall(f.name, arrayOf(a1, a2, a3))) { f(it, a1, a2, a3) }

    override fun <T, A1, A2, A3, A4, R> f4(container: AssertionContainer<T>, f: KFunction5<T, A1, A2, A3, A4, R>, a1: A1, a2: A2, a3: A3, a4: A4): FeatureExtractorBuilder.ExecutionStep<T, R> =
        extractFeature(container, buildMethodCallFormatter(container).formatCall(f.name, arrayOf(a1, a2, a3, a4))) { f(it, a1, a2, a3, a4) }

    override fun <T, A1, A2, A3, A4, A5, R> f5(container: AssertionContainer<T>, f: KFunction6<T, A1, A2, A3, A4, A5, R>, a1: A1, a2: A2, a3: A3, a4: A4, a5: A5): FeatureExtractorBuilder.ExecutionStep<T, R> =
        extractFeature(container, buildMethodCallFormatter(container).formatCall(f.name, arrayOf(a1, a2, a3, a4, a5))) { f(it, a1, a2, a3, a4, a5) }
    //@formatter:on

    @OptIn(ExperimentalComponentFactoryContainer::class)
    private fun <T> buildMethodCallFormatter(container: AssertionContainer<T>) =
        container.components.build<MethodCallFormatter>()

    //TODO 1.3.0 remove suppress again, use InlineElement instead
    @Suppress("DEPRECATION")
    override fun <T, R> manualFeature(
        container: AssertionContainer<T>,
        description: ch.tutteli.atrium.reporting.translating.Translatable,
        provider: T.() -> R
    ): FeatureExtractorBuilder.ExecutionStep<T, R> = extractFeature(container, description, provider)

    //TODO 1.3.0 remove suppress again, use InlineElement instead
    @Suppress("DEPRECATION")
    private fun <T, R> extractFeature(
        container: AssertionContainer<T>,
        description: String,
        provider: (T) -> R
    ): FeatureExtractorBuilder.ExecutionStep<T, R> = extractFeature(container, ch.tutteli.atrium.reporting.translating.Untranslatable(description), provider)

    //TODO 1.3.0 remove suppress again, use InlineElement instead
    @Suppress("DEPRECATION")
    private fun <T, R> extractFeature(
        container: AssertionContainer<T>,
        description: ch.tutteli.atrium.reporting.translating.Translatable,
        provider: (T) -> R
    ): FeatureExtractorBuilder.ExecutionStep<T, R> {
        return container.extractFeature
            .withDescription(description)
            .withRepresentationForFailure(ErrorMessages.REPRESENTATION_BASED_ON_SUBJECT_NOT_DEFINED)
            .withFeatureExtraction { Some(provider(it)) }
            .withoutOptions()
            .build()
    }

    override fun <T> extractSubject(
        container: AssertionContainer<T>,
        failureDescription: String?,
        assertionCreator: Expect<T>.(T) -> Unit,
    ): Expect<T> = container.append(
        container.maybeSubject.fold({
            assertionBuilder.representationOnly
                .failing
                .withRepresentation(
                    Text(
                        failureDescription
                            ?: "❗❗ subject extraction not possible, previous expectation failed, cannot show sub-expectations"
                    )
                )
                .build()
        }) { subject ->
            container.collect { this.assertionCreator(subject) }
        }
    )
}
