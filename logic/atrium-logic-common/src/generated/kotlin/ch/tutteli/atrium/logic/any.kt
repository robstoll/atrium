//---------------------------------------------------
//  Generated content, modify:
//  logic/generateLogic.gradle
//  if necessary - enjoy the day 🙂
//---------------------------------------------------

package ch.tutteli.atrium.logic

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.creating.changers.ChangedSubjectPostStep
import kotlin.reflect.KClass

import ch.tutteli.atrium.core.ExperimentalNewExpectTypes
import ch.tutteli.atrium.logic.impl.DefaultAnyAssertions

@Suppress("DEPRECATION" /* OptIn is only available since 1.3.70 which we cannot use if we want to support 1.2 */)
@UseExperimental(ExperimentalNewExpectTypes::class)
private inline val <T> AssertionContainer<T>.impl: AnyAssertions
    get() = getImpl(AnyAssertions::class) { DefaultAnyAssertions() }

fun <T> AssertionContainer<T>.toBe(expected: T): Assertion = impl.toBe(this, expected)
fun <T> AssertionContainer<T>.notToBe(expected: T): Assertion = impl.notToBe(this, expected)
fun <T> AssertionContainer<T>.isSameAs(expected: T): Assertion = impl.isSameAs(this, expected)
fun <T> AssertionContainer<T>.isNotSameAs(expected: T): Assertion = impl.isNotSameAs(this, expected)

fun <T : Any?> AssertionContainer<T>.toBeNull(): Assertion = impl.toBeNull(this)

fun <T : Any> AssertionContainer<T?>.toBeNullIfNullGivenElse(kClass: KClass<T>, assertionCreatorOrNull: (Expect<T>.() -> Unit)?): Assertion =
    impl.toBeNullIfNullGivenElse(this, kClass, assertionCreatorOrNull)

fun <T : Any> AssertionContainer<T?>.notToBeNull(subType: KClass<T>): ChangedSubjectPostStep<T?, T> = impl.notToBeNull(this, subType)

    //TODO restrict TSub with T once type parameter for upper bounds are supported:
    // https://youtrack.jetbrains.com/issue/KT-33262 is implemented
fun <T, TSub : Any> AssertionContainer<T>.isA(subType: KClass<TSub>): ChangedSubjectPostStep<T, TSub> = impl.isA(this, subType)
