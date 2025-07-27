// @formatter:off
//---------------------------------------------------
//  Generated content, don't change here but in:
//  gradle/build-logic/dev/src/main/kotlin/generation.kt
//  Enjoy the day 🙂
//---------------------------------------------------
package ch.tutteli.atrium.logic

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.creating.transformers.SubjectChangerBuilder
import kotlin.reflect.KClass
import ch.tutteli.atrium.core.ExperimentalNewExpectTypes
import ch.tutteli.atrium.logic.impl.DefaultAnyAssertions

fun <T> AssertionContainer<T>.toBe(expected: T): Assertion = impl.toBe(this, expected)
fun <T> AssertionContainer<T>.notToBe(expected: T): Assertion = impl.notToBe(this, expected)
fun <T> AssertionContainer<T>.isSameAs(expected: T): Assertion = impl.isSameAs(this, expected)
fun <T> AssertionContainer<T>.isNotSameAs(expected: T): Assertion = impl.isNotSameAs(this, expected)

fun <T : Any> AssertionContainer<T?>.toBeNullIfNullGivenElse(assertionCreatorOrNull: (Expect<T>.() -> Unit)?): Assertion = impl.toBeNullIfNullGivenElse(this, assertionCreatorOrNull)

fun <T : Any> AssertionContainer<T?>.notToBeNullButOfType(subType: KClass<T>): SubjectChangerBuilder.ExecutionStep<T?, T> = impl.notToBeNullButOfType(this, subType)

    @Suppress("BOUNDS_NOT_ALLOWED_IF_BOUNDED_BY_TYPE_PARAMETER")
fun <T, SubTypeOfT> AssertionContainer<T>.isA(subType: KClass<SubTypeOfT>): SubjectChangerBuilder.ExecutionStep<T, SubTypeOfT> where SubTypeOfT : Any, SubTypeOfT : T = impl.isA(this, subType)

fun <T> AssertionContainer<T>.isNotIn(expected: Iterable<T>): Assertion = impl.isNotIn(this, expected)

fun <T> AssertionContainer<T>.because(reason: String, assertionCreator: (Expect<T>.() -> Unit)): Assertion =
    impl.because(this, reason, assertionCreator)

fun <T> AssertionContainer<T>.notToBeAnInstanceOf(notExpectedTypes: List<KClass<*>>): Assertion = impl.notToBeAnInstanceOf(this, notExpectedTypes)

@OptIn(ExperimentalNewExpectTypes::class)
private inline val <T> AssertionContainer<T>.impl: AnyAssertions
    get() = getImpl(AnyAssertions::class) { DefaultAnyAssertions() }
