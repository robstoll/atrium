package ch.tutteli.atrium.scala2.logic

import ch.tutteli.atrium.creating.AssertionContainer

trait LogicImpls {

  implicit def containerToAnyLogic[T](container: AssertionContainer[T]): AnyLogic[T] = new AnyLogic(container)
}
