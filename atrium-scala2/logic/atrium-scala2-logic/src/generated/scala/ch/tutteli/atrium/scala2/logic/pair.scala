//---------------------------------------------------
//  Generated content, modify:
//  logic/generateLogic.gradle
//  if necessary - enjoy the day 🙂
//---------------------------------------------------

package ch.tutteli.atrium.scala2.logic

import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.domain.creating.changers.ExtractedFeaturePostStep
import ch.tutteli.atrium.logic._

class PairLogic[T](container: AssertionContainer[T]) {

    def first(): ExtractedFeaturePostStep[T, K] = PairKt.first(container)
    def second(): ExtractedFeaturePostStep[T, V] = PairKt.second(container)
}
