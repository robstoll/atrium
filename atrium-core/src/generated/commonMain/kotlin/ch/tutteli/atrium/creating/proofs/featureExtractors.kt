// @formatter:off
//---------------------------------------------------
//  Generated content, modify:
//  gradle/build-logic/dev/src/main/kotlin/generation.kt
//  if necessary - enjoy the day 🙂
//---------------------------------------------------
package ch.tutteli.atrium.creating.proofs

import ch.tutteli.atrium.creating.ProofContainer
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.creating.proofs.feature.MetaFeature
import ch.tutteli.atrium.creating.transformers.FeatureExtractorBuilder
import ch.tutteli.atrium.reporting.reportables.Diagnostic
import ch.tutteli.atrium.reporting.reportables.InlineElement
import ch.tutteli.atrium.reporting.reportables.Reportable
import kotlin.reflect.*
import ch.tutteli.atrium.core.ExperimentalNewExpectTypes
import ch.tutteli.atrium.creating.proofs.impl.DefaultFeatureExtractors

    //@formatter:off
    /** @since 1.3.0 */
fun <SubjectT, TProperty> ProofContainer<SubjectT>.property(property: KProperty1<in SubjectT, TProperty>): FeatureExtractorBuilder.ExecutionStep<SubjectT, TProperty> =
    impl.property(this, property)

    /** @since 1.3.0 */
fun <SubjectT, R> ProofContainer<SubjectT>.f0(f: KFunction1<SubjectT, R>): FeatureExtractorBuilder.ExecutionStep<SubjectT, R> =
    impl.f0(this, f)

    /** @since 1.3.0 */
fun <SubjectT, A1, R> ProofContainer<SubjectT>.f1(f: KFunction2<SubjectT, A1, R>, a1: A1): FeatureExtractorBuilder.ExecutionStep<SubjectT, R> =
    impl.f1(this, f, a1)

    /** @since 1.3.0 */
fun <SubjectT, A1, A2, R> ProofContainer<SubjectT>.f2(f: KFunction3<SubjectT, A1, A2, R>, a1: A1, a2: A2): FeatureExtractorBuilder.ExecutionStep<SubjectT, R> =
    impl.f2(this, f, a1, a2)

    /** @since 1.3.0 */
fun <SubjectT, A1, A2, A3, R> ProofContainer<SubjectT>.f3(f: KFunction4<SubjectT, A1, A2, A3, R>, a1: A1, a2: A2, a3: A3): FeatureExtractorBuilder.ExecutionStep<SubjectT, R> =
    impl.f3(this, f, a1, a2, a3)

    /** @since 1.3.0 */
fun <SubjectT, A1, A2, A3, A4, R> ProofContainer<SubjectT>.f4(f: KFunction5<SubjectT, A1, A2, A3, A4, R>, a1: A1, a2: A2, a3: A3, a4: A4): FeatureExtractorBuilder.ExecutionStep<SubjectT, R> =
    impl.f4(this, f, a1, a2, a3, a4)

    /** @since 1.3.0 */
fun <SubjectT, A1, A2, A3, A4, A5, R> ProofContainer<SubjectT>.f5(f: KFunction6<SubjectT, A1, A2, A3, A4, A5, R>, a1: A1, a2: A2, a3: A3, a4: A4, a5: A5): FeatureExtractorBuilder.ExecutionStep<SubjectT, R> =
    impl.f5(this, f, a1, a2, a3, a4, a5)
    //@formatter:on

    /** @since 1.3.0 */
fun <SubjectT, R> ProofContainer<SubjectT>.featureBasedOnMetaFeature(provider: (SubjectT) -> MetaFeature<R>): FeatureExtractorBuilder.ExecutionStep<SubjectT, R> =
    impl.featureBasedOnMetaFeature(this, provider)


    //TODO 1.4.0 provide another overload which accepts Diagnostic for description?
    /** @since 1.3.0 */
fun <SubjectT, R> ProofContainer<SubjectT>.manualFeature(description: InlineElement, provider: SubjectT.() -> R): FeatureExtractorBuilder.ExecutionStep<SubjectT, R> =
    impl.manualFeature(this, description, provider)

    /** @since 1.3.0 */
fun <T> ProofContainer<T>.extractSubject(failureDescription: InlineElement?, usageHintsAlternativeWithoutExpectationCreator: List<InlineElement>, expectationCreator: Expect<T>.(T) -> Unit,): Expect<T> =
    impl.extractSubject(this, failureDescription, usageHintsAlternativeWithoutExpectationCreator, expectationCreator)

@OptIn(ExperimentalNewExpectTypes::class)
private inline val <T> ProofContainer<T>.impl: FeatureExtractors
    get() = getImpl(FeatureExtractors::class) { DefaultFeatureExtractors() }
