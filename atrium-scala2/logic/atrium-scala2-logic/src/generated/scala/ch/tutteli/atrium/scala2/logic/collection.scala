//---------------------------------------------------
//  Generated content, modify:
//  logic/generateLogic.gradle
//  if necessary - enjoy the day 🙂
//---------------------------------------------------

package ch.tutteli.atrium.scala2.logic

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.domain.creating.changers.ExtractedFeaturePostStep
import ch.tutteli.atrium.logic.CollectionKt
import ch.tutteli.atrium.scala2.logic._

class CollectionLogic[(container: AssertionContainer[[]) {

    def isEmpty(): Assertion = CollectionKt.isEmpty(container)
    def isNotEmpty(): Assertion = CollectionKt.isNotEmpty(container)
    def size(): ExtractedFeaturePostStep[T, Int] = CollectionKt.size(container)
}
