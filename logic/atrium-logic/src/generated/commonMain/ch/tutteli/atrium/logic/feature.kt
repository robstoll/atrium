// @formatter:off
//---------------------------------------------------
//  Generated content, modify:
//  buildSrc/generation.kt
//  if necessary - enjoy the day 🙂
//---------------------------------------------------
//TODO 2.0.0 remove file
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.logic

import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.creating.transformers.FeatureExtractorBuilder
import kotlin.reflect.*
import ch.tutteli.atrium.core.ExperimentalNewExpectTypes
import ch.tutteli.atrium.logic.impl.DefaultFeatureAssertions

    //@formatter:off
    @Deprecated(
        "Migrate from AssertionContainer to ProofContainer, will be removed with 2.0.0 at the latest",
        ReplaceWith("this.property(property)", "ch.tutteli.atrium.creating.proofs.property")
    )
fun <T, TProperty> AssertionContainer<T>.property(property: KProperty1<in T, TProperty>): FeatureExtractorBuilder.ExecutionStep<T, TProperty> = impl.property(this, property)

     @Deprecated(
        "Migrate from AssertionContainer to ProofContainer, will be removed with 2.0.0 at the latest",
        ReplaceWith("this.f0(f)", "ch.tutteli.atrium.creating.proofs.f0")
    )
fun <T, R> AssertionContainer<T>.f0(f: KFunction1<T, R>): FeatureExtractorBuilder.ExecutionStep<T, R> = impl.f0(this, f)

     @Deprecated(
        "Migrate from AssertionContainer to ProofContainer, will be removed with 2.0.0 at the latest",
        ReplaceWith("this.f1(f, a1)", "ch.tutteli.atrium.creating.proofs.f1")
    )
fun <T, A1, R> AssertionContainer<T>.f1(f: KFunction2<T, A1, R>, a1: A1): FeatureExtractorBuilder.ExecutionStep<T, R> =
    impl.f1(this, f, a1)

     @Deprecated(
        "Migrate from AssertionContainer to ProofContainer, will be removed with 2.0.0 at the latest",
        ReplaceWith("this.f2(f, a1, a2)", "ch.tutteli.atrium.creating.proofs.f2")
    )
fun <T, A1, A2, R> AssertionContainer<T>.f2(f: KFunction3<T, A1, A2, R>, a1: A1, a2: A2): FeatureExtractorBuilder.ExecutionStep<T, R> =
    impl.f2(this, f, a1, a2)

     @Deprecated(
        "Migrate from AssertionContainer to ProofContainer, will be removed with 2.0.0 at the latest",
        ReplaceWith("this.f3(f, a1, a2, a3)", "ch.tutteli.atrium.creating.proofs.f3")
    )
fun <T, A1, A2, A3, R> AssertionContainer<T>.f3(f: KFunction4<T, A1, A2, A3, R>, a1: A1, a2: A2, a3: A3): FeatureExtractorBuilder.ExecutionStep<T, R> =
    impl.f3(this, f, a1, a2, a3)

     @Deprecated(
        "Migrate from AssertionContainer to ProofContainer, will be removed with 2.0.0 at the latest",
        ReplaceWith("this.f4(f, a1, a2, a3, a4)", "ch.tutteli.atrium.creating.proofs.f4")
    )
fun <T, A1, A2, A3, A4, R> AssertionContainer<T>.f4(f: KFunction5<T, A1, A2, A3, A4, R>, a1: A1, a2: A2, a3: A3, a4: A4): FeatureExtractorBuilder.ExecutionStep<T, R> =
    impl.f4(this, f, a1, a2, a3, a4)

     @Deprecated(
        "Migrate from AssertionContainer to ProofContainer, will be removed with 2.0.0 at the latest",
        ReplaceWith("this.f5(f, a1, a2, a3, a4, a5)", "ch.tutteli.atrium.creating.proofs.f5")
    )
fun <T, A1, A2, A3, A4, A5, R> AssertionContainer<T>.f5(f: KFunction6<T, A1, A2, A3, A4, A5, R>, a1: A1, a2: A2, a3: A3, a4: A4, a5: A5): FeatureExtractorBuilder.ExecutionStep<T, R> =
    impl.f5(this, f, a1, a2, a3, a4, a5)
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
fun <T, R> AssertionContainer<T>.manualFeature(description: ch.tutteli.atrium.reporting.translating.Translatable, provider: T.() -> R): FeatureExtractorBuilder.ExecutionStep<T, R> =
    impl.manualFeature(this, description, provider)

    @Deprecated(
        "Migrate from AssertionContainer to ProofContainer, will be removed with 2.0.0 at the latest",
        ReplaceWith(
            "this.extractSubject(failureDescription?.let { Text(it) }, usageHintsAlternativeWithoutExpectationCreator=listOf(/* .. add a custom usage hint in case you have an overload which does not expect an expectationCreator or use the generic */), assertionCreator)",
            "ch.tutteli.atrium.creating.proofs.manualFeature",
            "ch.tutteli.atrium.reporting.Text"
        )
    )
fun <T> AssertionContainer<T>.extractSubject(failureDescription: String?, assertionCreator: Expect<T>.(T) -> Unit): Expect<T> =
    impl.extractSubject(this, failureDescription, assertionCreator)

@OptIn(ExperimentalNewExpectTypes::class)
private inline val <T> AssertionContainer<T>.impl: FeatureAssertions
    get() = getImpl(FeatureAssertions::class) { DefaultFeatureAssertions() }
