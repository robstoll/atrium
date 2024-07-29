// @formatter:off
//---------------------------------------------------
//  Generated content, modify:
//  buildSrc/generation.kt
//  if necessary - enjoy the day 🙂
//---------------------------------------------------
//TODO 2.0.0 remove file
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.logic

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.creating.transformers.SubjectChangerBuilder
import kotlin.reflect.KClass
import ch.tutteli.atrium.core.ExperimentalNewExpectTypes
import ch.tutteli.atrium.logic.impl.DefaultAnyAssertions

    @Deprecated(
        "Migrate from AssertionContainer to ProofContainer and use toEqual, will be removed with 2.0.0 at the latest",
        ReplaceWith("this.toEqual(expected)", "ch.tutteli.atrium.creating.proofs.toEqual")
    )
fun <T> AssertionContainer<T>.toBe(expected: T): Assertion = impl.toBe(this, expected)

    @Deprecated(
        "Migrate from AssertionContainer to ProofContainer and use notToEqual, will be removed with 2.0.0 at the latest",
        ReplaceWith("this.notToEqual(expected)", "ch.tutteli.atrium.creating.proofs.notToEqual")
    )
fun <T> AssertionContainer<T>.notToBe(expected: T): Assertion = impl.notToBe(this, expected)

    @Deprecated(
        "Migrate from AssertionContainer to ProofContainer and use toBeTheInstance, will be removed with 2.0.0 at the latest",
        ReplaceWith("this.toBeTheInstance(expected)", "ch.tutteli.atrium.creating.proofs.toBeTheInstance")
    )
fun <T> AssertionContainer<T>.isSameAs(expected: T): Assertion = impl.isSameAs(this, expected)

    @Deprecated(
        "Migrate from AssertionContainer to ProofContainer and use notToBeTheInstance, will be removed with 2.0.0 at the latest",
        ReplaceWith("this.notToBeTheInstance(expected)", "ch.tutteli.atrium.creating.proofs.notToBeTheInstance")
    )
fun <T> AssertionContainer<T>.isNotSameAs(expected: T): Assertion = impl.isNotSameAs(this, expected)

    @Deprecated(
        "Migrate from AssertionContainer to ProofContainer and use toBeAnInstanceOf, will be removed with 2.0.0 at the latest",
        ReplaceWith("this.toBeAnInstanceOf(subType)", "ch.tutteli.atrium.creating.proofs.toBeAnInstanceOf")
    )
    @Suppress("BOUNDS_NOT_ALLOWED_IF_BOUNDED_BY_TYPE_PARAMETER")
fun <T, SubTypeOfT> AssertionContainer<T>.isA(subType: KClass<SubTypeOfT>): SubjectChangerBuilder.ExecutionStep<T, SubTypeOfT> where SubTypeOfT : Any, SubTypeOfT : T = impl.isA(this, subType)


fun <T> AssertionContainer<T>.notToBeAnInstanceOf(notExpectedTypes: List<KClass<*>>): Assertion = impl.notToBeAnInstanceOf(this, notExpectedTypes)

fun <T : Any> AssertionContainer<T?>.toBeNullIfNullGivenElse(assertionCreatorOrNull: (Expect<T>.() -> Unit)?): Assertion = impl.toBeNullIfNullGivenElse(this, assertionCreatorOrNull)

fun <T : Any> AssertionContainer<T?>.notToBeNullButOfType(subType: KClass<T>): SubjectChangerBuilder.ExecutionStep<T?, T> = impl.notToBeNullButOfType(this, subType)


fun <T> AssertionContainer<T>.isNotIn(expected: Iterable<T>): Assertion = impl.isNotIn(this, expected)

fun <T> AssertionContainer<T>.because(reason: String, assertionCreator: (Expect<T>.() -> Unit)): Assertion =
    impl.because(this, reason, assertionCreator)

@OptIn(ExperimentalNewExpectTypes::class)
private inline val <T> AssertionContainer<T>.impl: AnyAssertions
    get() = getImpl(AnyAssertions::class) { DefaultAnyAssertions() }
