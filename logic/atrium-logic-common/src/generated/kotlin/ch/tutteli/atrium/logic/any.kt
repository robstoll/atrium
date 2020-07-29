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

fun <T> AssertionContainer<T>.toBe(expected: T): Assertion = _anyImpl.toBe(this, expected)
fun <T> AssertionContainer<T>.notToBe(expected: T): Assertion = _anyImpl.notToBe(this, expected)
fun <T> AssertionContainer<T>.isSameAs(expected: T): Assertion = _anyImpl.isSameAs(this, expected)
fun <T> AssertionContainer<T>.isNotSameAs(expected: T): Assertion = _anyImpl.isNotSameAs(this, expected)

fun <T : Any?> AssertionContainer<T>.toBeNull(): Assertion = _anyImpl.toBeNull(this)

fun <T : Any> AssertionContainer<T?>.toBeNullIfNullGivenElse(type: KClass<T>, assertionCreatorOrNull: (Expect<T>.() -> Unit)?): Assertion =
    _anyImpl.toBeNullIfNullGivenElse(this, type, assertionCreatorOrNull)

fun <T : Any> AssertionContainer<T?>.notToBeNull(subType: KClass<T>): ChangedSubjectPostStep<T?, T> = _anyImpl.notToBeNull(this, subType)

    //TODO restrict TSub with T once type parameter for upper bounds are supported:
    // https://youtrack.jetbrains.com/issue/KT-33262 is implemented
fun <T, TSub : Any> AssertionContainer<T>.isA(subType: KClass<TSub>): ChangedSubjectPostStep<T, TSub> = _anyImpl.isA(this, subType)

fun <T> AssertionContainer<T>.isNotIn(expected: List<T>): Assertion = _anyImpl.isNotIn(this, expected)
