//---------------------------------------------------
//  Generated content, modify:
//  logic/generateLogic.gradle
//  if necessary - enjoy the day 🙂
//---------------------------------------------------

package ch.tutteli.atrium.scala2.logic

import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.domain.creating.changers.ChangedSubjectPostStep
import kotlin.reflect.KClass
import ch.tutteli.atrium.logic.Fun0Kt
import ch.tutteli.atrium.scala2.logic._

class Fun0Logic[TExpected <: Throwable](container: AssertionContainer[TExpected]) {


    def toThrow(expectedType: KClass[TExpected]): ChangedSubjectPostStep[*, TExpected] = Fun0Kt.toThrow(container, expectedType)

    def notToThrow[T : () => R](): ChangedSubjectPostStep[*, R] = Fun0Kt.notToThrow(container)
}
