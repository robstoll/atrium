//---------------------------------------------------
//  Generated content, modify:
//  logic/generateLogic.gradle
//  if necessary - enjoy the day 🙂
//---------------------------------------------------

package ch.tutteli.atrium.scala2.logic

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.domain.creating.changers.ExtractedFeaturePostStep
import ch.tutteli.atrium.logic._

class MapEntryLogic[T](container: AssertionContainer[T]) {

    def isKeyValue(key: K, value: V): Assertion =
        MapEntryKt.isKeyValue(container, key, value)
    def key(): ExtractedFeaturePostStep[T, K] = MapEntryKt.key(container)
    def value(): ExtractedFeaturePostStep[T, V] = MapEntryKt.value(container)
}
