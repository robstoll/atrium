//---------------------------------------------------
//  Generated content, modify:
//  logic/generateLogic.gradle
//  if necessary - enjoy the day 🙂
//---------------------------------------------------

package ch.tutteli.atrium.scala2.logic

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.creating.changers.ChangedSubjectPostStep
import kotlin.reflect.KClass
import ch.tutteli.atrium.logic.AnyKt
import ch.tutteli.atrium.scala2.logic._

class AnyLogic[T, T2, T3](container: AssertionContainer[T]) {

    def toBe(expected: T): Assertion = AnyKt.toBe(container, expected)
    def notToBe(expected: T): Assertion = AnyKt.notToBe(container, expected)
    def isSameAs(expected: T): Assertion = AnyKt.isSameAs(container, expected)
    def isNotSameAs(expected: T): Assertion = AnyKt.isNotSameAs(container, expected)

    def toBeNull(): Assertion = AnyKt.toBeNull(container)

    def toBeNullIfNullGivenElse(kClass: KClass[T], assertionCreatorOrNull: Expect[T] => Unit): Assertion =
        AnyKt.toBeNullIfNullGivenElse(container, kClass, assertionCreatorOrNull)

    def notToBeNull(subType: KClass[T]): ChangedSubjectPostStep[T, T] = AnyKt.notToBeNull(container, subType)

    //TODO restrict TSub with T once type parameter for upper bounds are supported:
    // https://youtrack.jetbrains.com/issue/KT-33262 is implemented
    def isA[TSub](subType: KClass[TSub]): ChangedSubjectPostStep[T, TSub] = AnyKt.isA(container, subType)
}
