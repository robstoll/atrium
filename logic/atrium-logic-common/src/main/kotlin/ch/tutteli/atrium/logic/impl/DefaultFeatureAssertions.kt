package ch.tutteli.atrium.logic.impl

import ch.tutteli.atrium.core.None
import ch.tutteli.atrium.core.coreFactory
import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.domain.builders.ExpectImpl
import ch.tutteli.atrium.domain.builders.creating.NewFeatureAssertionsBuilder
import ch.tutteli.atrium.domain.creating.MetaFeature
import ch.tutteli.atrium.domain.creating.changers.ExtractedFeaturePostStep
import ch.tutteli.atrium.logic.FeatureAssertions
import ch.tutteli.atrium.logic.genericFeature
import ch.tutteli.atrium.logic.toExpect
import ch.tutteli.atrium.reporting.Text
import ch.tutteli.atrium.reporting.translating.Translatable
import ch.tutteli.atrium.reporting.translating.Untranslatable
import ch.tutteli.atrium.translations.ErrorMessages
import kotlin.reflect.*

class DefaultFeatureAssertions : FeatureAssertions{

    //@formatter:off
    override fun <T, TProperty> property(container: AssertionContainer<T>, property: KProperty1<in T, TProperty>): ExtractedFeaturePostStep<T, TProperty> =
        extractFeature(container, property.name, property::get)

    override fun <T, R> f0(container: AssertionContainer<T>, f: KFunction1<T, R>): ExtractedFeaturePostStep<T, R> =
        extractFeature(container, coreFactory.newMethodCallFormatter().formatCall(f.name, arrayOf()), f::invoke)

    override fun <T, A1, R> f1(container: AssertionContainer<T>, f: KFunction2<T, A1, R>, a1: A1): ExtractedFeaturePostStep<T, R> =
        extractFeature(container, coreFactory.newMethodCallFormatter().formatCall(f.name, arrayOf<Any?>(a1))) { f(it, a1) }

    override fun <T, A1, A2, R> f2(container: AssertionContainer<T>, f: KFunction3<T, A1, A2, R>, a1: A1, a2: A2): ExtractedFeaturePostStep<T, R> =
        extractFeature(container ,coreFactory.newMethodCallFormatter().formatCall(f.name, arrayOf(a1, a2))) { f(it, a1, a2) }

    override fun <T, A1, A2, A3, R> f3(container: AssertionContainer<T>, f: KFunction4<T, A1, A2, A3, R>, a1: A1, a2: A2, a3: A3): ExtractedFeaturePostStep<T, R> =
        extractFeature(container, coreFactory.newMethodCallFormatter().formatCall(f.name, arrayOf(a1, a2, a3))) { f(it, a1, a2, a3) }

    override fun <T, A1, A2, A3, A4, R> f4(container: AssertionContainer<T>, f: KFunction5<T, A1, A2, A3, A4, R>, a1: A1, a2: A2, a3: A3, a4: A4): ExtractedFeaturePostStep<T, R> =
        extractFeature(container, coreFactory.newMethodCallFormatter().formatCall(f.name, arrayOf(a1, a2, a3, a4))) { f(it, a1, a2, a3, a4) }

    override fun <T, A1, A2, A3, A4, A5, R> f5(container: AssertionContainer<T>, f: KFunction6<T, A1, A2, A3, A4, A5, R>, a1: A1, a2: A2, a3: A3, a4: A4, a5: A5): ExtractedFeaturePostStep<T, R> =
        extractFeature(container, coreFactory.newMethodCallFormatter().formatCall(f.name, arrayOf(a1, a2, a3, a4, a5))) { f(it, a1, a2, a3, a4, a5) }
    //@formatter:on

    override fun <T, R> manualFeature(
        container: AssertionContainer<T>,
        description: String,
        provider: T.() -> R
    ): ExtractedFeaturePostStep<T, R> = extractFeature(container, description, provider)

    override fun <T, R> manualFeature(
        container: AssertionContainer<T>,
        description: Translatable,
        provider: T.() -> R
    ): ExtractedFeaturePostStep<T, R> = container.genericFeature(createMetaFeature(container, description, provider))

    override fun <T, R> genericSubjectBasedFeature(
        container: AssertionContainer<T>,
        provider: (T) -> MetaFeature<R>
    ): ExtractedFeaturePostStep<T, R> =
        container.genericFeature(createSubjectBasedMetaFeature(container, provider))

    private fun <T, R> extractFeature(
        container: AssertionContainer<T>,
        description: String,
        provider: (T) -> R
    ): ExtractedFeaturePostStep<T, R> =
        container.genericFeature(createMetaFeature(container, description, provider))

    // TODO probably not the best place
    private fun <T, R> createMetaFeature(
        container: AssertionContainer<T>,
        description: String,
        provider: (T) -> R
    ): MetaFeature<R> = createMetaFeature(container, Untranslatable(description), provider)

    // TODO probably not the best place
    private fun <T, R> createMetaFeature(
        container: AssertionContainer<T>,
        description: Translatable,
        provider: (T) -> R
    ): MetaFeature<R>  = NewFeatureAssertionsBuilder.meta.create(container.toExpect(), description, provider)

    // TODO probably not the best place
    private fun <T, R> createSubjectBasedMetaFeature(
        container: AssertionContainer<T>,
        provider: (T) -> MetaFeature<R>
    ): MetaFeature<R> = container.maybeSubject.fold(this::createFeatureSubjectNotDefined) { provider(it) }

    private fun <R> createFeatureSubjectNotDefined(): MetaFeature<R> =
        MetaFeature(
            ErrorMessages.DEDSCRIPTION_BASED_ON_SUBJECT,
            ErrorMessages.REPRESENTATION_BASED_ON_SUBJECT_NOT_DEFINED,
            None
        )

    override fun <T, R> genericFeature(
        container: AssertionContainer<T>,
        metaFeature: MetaFeature<R>
    ): ExtractedFeaturePostStep<T, R> {
        val representation: Any = metaFeature.representation ?: Text.NULL
        return ExpectImpl.feature.extractor(container.toExpect())
            .withDescription(metaFeature.description)
            .withRepresentationForFailure(representation)
            .withFeatureExtraction { metaFeature.maybeSubject }
            .withOptions { withRepresentation { representation } }
            .build()
    }
}