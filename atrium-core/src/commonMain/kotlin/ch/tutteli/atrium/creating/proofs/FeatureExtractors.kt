package ch.tutteli.atrium.creating.proofs

import ch.tutteli.atrium.creating.ProofContainer
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.creating.proofs.feature.MetaFeature
import ch.tutteli.atrium.creating.transformers.FeatureExtractorBuilder
import ch.tutteli.atrium.reporting.reportables.Diagnostic
import ch.tutteli.atrium.reporting.reportables.InlineElement
import ch.tutteli.atrium.reporting.reportables.Reportable
import kotlin.reflect.*

/**
 * Collection of functions which help to extract features out of a subject of a [ProofContainer]
 * returning [FeatureExtractorBuilder.ExecutionStep].
 *
 * @since 1.3.0
 */
interface FeatureExtractors {
    //@formatter:off
    /** @since 1.3.0 */
    fun <SubjectT, TProperty> property(container: ProofContainer<SubjectT>, property: KProperty1<in SubjectT, TProperty>): FeatureExtractorBuilder.ExecutionStep<SubjectT, TProperty>

    /** @since 1.3.0 */
    fun <SubjectT, R> f0(container: ProofContainer<SubjectT>, f: KFunction1<SubjectT, R>): FeatureExtractorBuilder.ExecutionStep<SubjectT, R>

    /** @since 1.3.0 */
    fun <SubjectT, A1, R> f1(container: ProofContainer<SubjectT>, f: KFunction2<SubjectT, A1, R>, a1: A1): FeatureExtractorBuilder.ExecutionStep<SubjectT, R>

    /** @since 1.3.0 */
    fun <SubjectT, A1, A2, R> f2(container: ProofContainer<SubjectT>, f: KFunction3<SubjectT, A1, A2, R>, a1: A1, a2: A2): FeatureExtractorBuilder.ExecutionStep<SubjectT, R>

    /** @since 1.3.0 */
    fun <SubjectT, A1, A2, A3, R> f3(container: ProofContainer<SubjectT>, f: KFunction4<SubjectT, A1, A2, A3, R>, a1: A1, a2: A2, a3: A3): FeatureExtractorBuilder.ExecutionStep<SubjectT, R>

    /** @since 1.3.0 */
    fun <SubjectT, A1, A2, A3, A4, R> f4(container: ProofContainer<SubjectT>, f: KFunction5<SubjectT, A1, A2, A3, A4, R>, a1: A1, a2: A2, a3: A3, a4: A4): FeatureExtractorBuilder.ExecutionStep<SubjectT, R>

    /** @since 1.3.0 */
    fun <SubjectT, A1, A2, A3, A4, A5, R> f5(container: ProofContainer<SubjectT>, f: KFunction6<SubjectT, A1, A2, A3, A4, A5, R>, a1: A1, a2: A2, a3: A3, a4: A4, a5: A5): FeatureExtractorBuilder.ExecutionStep<SubjectT, R>
    //@formatter:on

    /** @since 1.3.0 */
    fun <SubjectT, R> featureBasedOnMetaFeature(
        container: ProofContainer<SubjectT>,
        provider: (SubjectT) -> MetaFeature<R>
    ): FeatureExtractorBuilder.ExecutionStep<SubjectT, R>


    //TODO 1.4.0 provide another overload which accepts Diagnostic for description?
    /** @since 1.3.0 */
    fun <SubjectT, R> manualFeature(
        container: ProofContainer<SubjectT>,
        description: InlineElement,
        provider: SubjectT.() -> R
    ): FeatureExtractorBuilder.ExecutionStep<SubjectT, R>

    /** @since 1.3.0 */
    fun <T> extractSubject(
        container: ProofContainer<T>,
        failureDescription: InlineElement?,
        usageHintsAlternativeWithoutExpectationCreator: List<InlineElement>,
        expectationCreator: Expect<T>.(T) -> Unit,
    ): Expect<T>
}
