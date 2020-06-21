//---------------------------------------------------
//  Generated content, modify: 
//  logic/atrium-logic-common/build.gradle 
//  if necessary - enjoy the day 🙂 
//---------------------------------------------------

package ch.tutteli.atrium.logic

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.creating.changers.ChangedSubjectPostStep
import kotlin.reflect.KClass

fun <T> AssertionContainer<T>.toBe(expected: T): Assertion = _impl.toBe(this, expected)
fun <T> AssertionContainer<T>.notToBe(expected: T): Assertion = _impl.notToBe(this, expected)
fun <T> AssertionContainer<T>.isSameAs(expected: T): Assertion = _impl.isSameAs(this, expected)
fun <T> AssertionContainer<T>.isNotSameAs(expected: T): Assertion = _impl.isNotSameAs(this, expected)

fun <T : Any?> AssertionContainer<T>.toBeNull(): Assertion = _impl.toBeNull(this)

fun <T : Any> AssertionContainer<T?>.toBeNullIfNullGivenElse(type: KClass<T>, assertionCreatorOrNull: (Expect<T>.() -> Unit)?): Assertion =
    _impl.toBeNullIfNullGivenElse(this, type, assertionCreatorOrNull)

fun <T : Any> AssertionContainer<T?>.notToBeNull(subType: KClass<T>): ChangedSubjectPostStep<T?, T> = _impl.notToBeNull(this, subType)

    //TODO restrict TSub with T once type parameter for upper bounds are supported:
    // https://youtrack.jetbrains.com/issue/KT-33262 is implemented
fun <T, TSub : Any> AssertionContainer<T>.isA(subType: KClass<TSub>): ChangedSubjectPostStep<T, TSub> = _impl.isA(this, subType)
