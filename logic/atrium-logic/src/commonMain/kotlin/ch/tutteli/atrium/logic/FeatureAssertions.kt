//TODO 2.0.0 remove file
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.logic

import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.creating.transformers.FeatureExtractorBuilder
import kotlin.reflect.*

/**
 * Collection of functions which help to create feature assertions by returning [FeatureExtractorBuilder.ExecutionStep].
 */
@Deprecated("Switch to FeatureExtractors, will be removed with 2.0.0 at the latest")
interface FeatureAssertions {
    //@formatter:off
    @Deprecated(
        "Migrate from AssertionContainer to ProofContainer, will be removed with 2.0.0 at the latest",
        ReplaceWith("this.property(property)", "ch.tutteli.atrium.creating.proofs.property")
    )
    fun <T, TProperty> property(container: AssertionContainer<T>, property: KProperty1<in T, TProperty>): FeatureExtractorBuilder.ExecutionStep<T, TProperty>

     @Deprecated(
        "Migrate from AssertionContainer to ProofContainer, will be removed with 2.0.0 at the latest",
        ReplaceWith("this.f0(f)", "ch.tutteli.atrium.creating.proofs.f0")
    )
    fun <T, R> f0(container: AssertionContainer<T>, f: KFunction1<T, R>): FeatureExtractorBuilder.ExecutionStep<T, R>

     @Deprecated(
        "Migrate from AssertionContainer to ProofContainer, will be removed with 2.0.0 at the latest",
        ReplaceWith("this.f1(f, a1)", "ch.tutteli.atrium.creating.proofs.f1")
    )
    fun <T, A1, R> f1(container: AssertionContainer<T>, f: KFunction2<T, A1, R>, a1: A1): FeatureExtractorBuilder.ExecutionStep<T, R>

     @Deprecated(
        "Migrate from AssertionContainer to ProofContainer, will be removed with 2.0.0 at the latest",
        ReplaceWith("this.f2(f, a1, a2)", "ch.tutteli.atrium.creating.proofs.f2")
    )
    fun <T, A1, A2, R> f2(container: AssertionContainer<T>, f: KFunction3<T, A1, A2, R>, a1: A1, a2: A2): FeatureExtractorBuilder.ExecutionStep<T, R>

     @Deprecated(
        "Migrate from AssertionContainer to ProofContainer, will be removed with 2.0.0 at the latest",
        ReplaceWith("this.f3(f, a1, a2, a3)", "ch.tutteli.atrium.creating.proofs.f3")
    )
    fun <T, A1, A2, A3, R> f3(container: AssertionContainer<T>, f: KFunction4<T, A1, A2, A3, R>, a1: A1, a2: A2, a3: A3): FeatureExtractorBuilder.ExecutionStep<T, R>

     @Deprecated(
        "Migrate from AssertionContainer to ProofContainer, will be removed with 2.0.0 at the latest",
        ReplaceWith("this.f4(f, a1, a2, a3, a4)", "ch.tutteli.atrium.creating.proofs.f4")
    )
    fun <T, A1, A2, A3, A4, R> f4(container: AssertionContainer<T>, f: KFunction5<T, A1, A2, A3, A4, R>, a1: A1, a2: A2, a3: A3, a4: A4): FeatureExtractorBuilder.ExecutionStep<T, R>

     @Deprecated(
        "Migrate from AssertionContainer to ProofContainer, will be removed with 2.0.0 at the latest",
        ReplaceWith("this.f5(f, a1, a2, a3, a4, a5)", "ch.tutteli.atrium.creating.proofs.f5")
    )
    fun <T, A1, A2, A3, A4, A5, R> f5(container: AssertionContainer<T>, f: KFunction6<T, A1, A2, A3, A4, A5, R>, a1: A1, a2: A2, a3: A3, a4: A4, a5: A5): FeatureExtractorBuilder.ExecutionStep<T, R>
    //@formatter:on

    @Deprecated(
        "Migrate from AssertionContainer to ProofContainer, will be removed with 2.0.0 at the latest",
        ReplaceWith(
            "this.manualFeature(Text(description.getDefault()), provider)",
            "ch.tutteli.atrium.creating.proofs.manualFeature",
            "ch.tutteli.atrium.reporting.Text"
        )
    )
    @Suppress("DEPRECATION")
    fun <T, R> manualFeature(
        container: AssertionContainer<T>,
        description: ch.tutteli.atrium.reporting.translating.Translatable,
        provider: T.() -> R
    ): FeatureExtractorBuilder.ExecutionStep<T, R>

    @Deprecated(
        "Migrate from AssertionContainer to ProofContainer, will be removed with 2.0.0 at the latest",
        ReplaceWith(
            "this.extractSubject(failureDescription?.let { Text(it) }, usageHintsAlternativeWithoutExpectationCreator=listOf(/* .. add a custom usage hint in case you have an overload which does not expect an expectationCreator or use the defaultHintsAtLeastOneExpectationDefined */), assertionCreator)",
            "ch.tutteli.atrium.creating.proofs.manualFeature",
            "ch.tutteli.atrium.reporting.Text"
        )
    )
    fun <T> extractSubject(
        container: AssertionContainer<T>,
        failureDescription: String?,
        assertionCreator: Expect<T>.(T) -> Unit
    ): Expect<T>
}
