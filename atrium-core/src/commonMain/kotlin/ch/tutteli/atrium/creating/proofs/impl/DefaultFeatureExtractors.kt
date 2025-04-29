package ch.tutteli.atrium.creating.proofs.impl

import ch.tutteli.atrium.core.ExperimentalNewExpectTypes
import ch.tutteli.atrium.core.None
import ch.tutteli.atrium.core.Some
import ch.tutteli.atrium.creating.*
import ch.tutteli.atrium.creating.proofs.FeatureExtractors
import ch.tutteli.atrium.creating.proofs.builders.buildProof
import ch.tutteli.atrium.creating.proofs.feature.MetaFeature
import ch.tutteli.atrium.creating.transformers.FeatureExtractorBuilder
import ch.tutteli.atrium.reporting.MethodCallFormatter
import ch.tutteli.atrium.reporting.Text
import ch.tutteli.atrium.reporting.reportables.ErrorMessages
import ch.tutteli.atrium.reporting.reportables.InlineElement
import ch.tutteli.atrium.reporting.reportables.Reportable
import kotlin.reflect.*

class DefaultFeatureExtractors : FeatureExtractors {

    //@formatter:off
    override fun <T, TProperty> property(container: ProofContainer<T>, property: KProperty1<in T, TProperty>): FeatureExtractorBuilder.ExecutionStep<T, TProperty> =
        extractFeature(container, property.name, property::get)

    override fun <T, R> f0(container: ProofContainer<T>, f: KFunction1<T, R>): FeatureExtractorBuilder.ExecutionStep<T, R> =
        extractFeature(container, buildMethodCallFormatter(container).formatCall(f.name, arrayOf()), f::invoke)

    override fun <T, A1, R> f1(container: ProofContainer<T>, f: KFunction2<T, A1, R>, a1: A1): FeatureExtractorBuilder.ExecutionStep<T, R> =
        extractFeature(container, buildMethodCallFormatter(container).formatCall(f.name, arrayOf<Any?>(a1))) { f(it, a1) }

    override fun <T, A1, A2, R> f2(container: ProofContainer<T>, f: KFunction3<T, A1, A2, R>, a1: A1, a2: A2): FeatureExtractorBuilder.ExecutionStep<T, R> =
        extractFeature(container ,buildMethodCallFormatter(container).formatCall(f.name, arrayOf(a1, a2))) { f(it, a1, a2) }

    override fun <T, A1, A2, A3, R> f3(container: ProofContainer<T>, f: KFunction4<T, A1, A2, A3, R>, a1: A1, a2: A2, a3: A3): FeatureExtractorBuilder.ExecutionStep<T, R> =
        extractFeature(container, buildMethodCallFormatter(container).formatCall(f.name, arrayOf(a1, a2, a3))) { f(it, a1, a2, a3) }

    override fun <T, A1, A2, A3, A4, R> f4(container: ProofContainer<T>, f: KFunction5<T, A1, A2, A3, A4, R>, a1: A1, a2: A2, a3: A3, a4: A4): FeatureExtractorBuilder.ExecutionStep<T, R> =
        extractFeature(container, buildMethodCallFormatter(container).formatCall(f.name, arrayOf(a1, a2, a3, a4))) { f(it, a1, a2, a3, a4) }

    override fun <T, A1, A2, A3, A4, A5, R> f5(container: ProofContainer<T>, f: KFunction6<T, A1, A2, A3, A4, A5, R>, a1: A1, a2: A2, a3: A3, a4: A4, a5: A5): FeatureExtractorBuilder.ExecutionStep<T, R> =
        extractFeature(container, buildMethodCallFormatter(container).formatCall(f.name, arrayOf(a1, a2, a3, a4, a5))) { f(it, a1, a2, a3, a4, a5) }
    //@formatter:on

    //TODO 1.4.0 format method calls better, put arguments on separate lines
    @OptIn(ExperimentalComponentFactoryContainer::class)
    private fun <T> buildMethodCallFormatter(container: ProofContainer<T>) =
        container.components.build<MethodCallFormatter>()

    override fun <SubjectT, R> featureBasedOnMetaFeature(
        container: ProofContainer<SubjectT>,
        provider: (SubjectT) -> MetaFeature<R>
    ): FeatureExtractorBuilder.ExecutionStep<SubjectT, R> {
        val metaFeature = container.maybeSubject.fold(::createFeatureSubjectNotDefined) { provider(it) }
        val representation: Any = metaFeature.representation ?: Text.NULL
        @OptIn(ExperimentalNewExpectTypes::class)
        return container.extractFeature
            .withDescription(metaFeature.description)
            .withRepresentationForFailure(representation)
            .withFeatureExtraction { metaFeature.maybeSubject }
            .withOptions { withRepresentationIfSubjectDefined { representation } }
            .build()
    }

    private fun <R> createFeatureSubjectNotDefined(): MetaFeature<R> =
        MetaFeature(
            ErrorMessages.DESCRIPTION_BASED_ON_SUBJECT,
            ErrorMessages.REPRESENTATION_BASED_ON_SUBJECT_NOT_DEFINED,
            None
        )


    override fun <T, R> manualFeature(
        container: ProofContainer<T>,
        description: InlineElement,
        provider: T.() -> R
    ): FeatureExtractorBuilder.ExecutionStep<T, R> = extractFeature(container, description, provider)

    private fun <T, R> extractFeature(
        container: ProofContainer<T>,
        description: String,
        provider: (T) -> R
    ): FeatureExtractorBuilder.ExecutionStep<T, R> = extractFeature(container, Text(description), provider)

    private fun <T, R> extractFeature(
        container: ProofContainer<T>,
        description: InlineElement,
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
        container: ProofContainer<T>,
        failureDescription: InlineElement?,
        usageHintsAlternativeWithoutExpectationCreator: List<InlineElement>,
        expectationCreator: Expect<T>.(T) -> Unit
    ): Expect<T> = container.append(
        container.maybeSubject.fold({
            container.buildProof {
                representationOnlyProof(failureDescription ?: ErrorMessages.FEATURE_EXTRACTION_NOT_POSSIBLE) { false }
            }
        }) { subject ->
            container.collect(ExpectationCreatorWithUsageHints(usageHintsAlternativeWithoutExpectationCreator) {
                this.expectationCreator(
                    subject
                )
            })
        }
    )
}
