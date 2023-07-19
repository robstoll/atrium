// @formatter:off
//---------------------------------------------------
//  Generated content, modify:
//  buildSrc/generation.kt
//  if necessary - enjoy the day 🙂
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

    //TODO 1.2.0 consider to suppress the warning of using type parameters as upper bound of other type parameters and
    // resolve the following TODO this way (until it has proper support at least)
    //TODO restrict TSub with T once type parameter for upper bounds are supported:
    // https://youtrack.jetbrains.com/issue/KT-33262 is implemented
fun <T, TSub : Any> AssertionContainer<T>.isA(subType: KClass<TSub>): SubjectChangerBuilder.ExecutionStep<T, TSub> = impl.isA(this, subType)

fun <T> AssertionContainer<T>.isNotIn(expected: Iterable<T>): Assertion = impl.isNotIn(this, expected)

fun <T> AssertionContainer<T>.because(reason: String, assertionCreator: (Expect<T>.() -> Unit)): Assertion =
    impl.because(this, reason, assertionCreator)

@OptIn(ExperimentalNewExpectTypes::class)
private inline val <T> AssertionContainer<T>.impl: AnyAssertions
    get() = getImpl(AnyAssertions::class) { DefaultAnyAssertions() }
