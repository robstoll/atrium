//---------------------------------------------------
//  Generated content, modify:
//  logic/generateLogic.gradle
//  if necessary - enjoy the day 🙂
//---------------------------------------------------

package ch.tutteli.atrium.scala2.logic

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.logic.IteratorKt
import ch.tutteli.atrium.scala2.logic._

class IteratorLogic[(container: AssertionContainer[[]) {

    def hasNext[T : Iterator[E]](): Assertion = IteratorKt.hasNext(container)
    def hasNotNext[T : Iterator[E]](): Assertion = IteratorKt.hasNotNext(container)
}
