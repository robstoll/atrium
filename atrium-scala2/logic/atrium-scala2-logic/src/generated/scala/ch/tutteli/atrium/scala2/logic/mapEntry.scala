//---------------------------------------------------
//  Generated content, modify:
//  logic/generateLogic.gradle
//  if necessary - enjoy the day 🙂
//---------------------------------------------------

package ch.tutteli.atrium.scala2.logic

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.domain.creating.changers.ExtractedFeaturePostStep
import ch.tutteli.atrium.logic.MapEntryKt
import ch.tutteli.atrium.scala2.logic._

class MapEntryLogic[K, V, T <: Map.Entry[K, V]](container: AssertionContainer[K]) {

    def isKeyValue(key: K, value: V): Assertion =
        MapEntryKt.isKeyValue(container, key, value)
    def key[T : Map.Entry[K, *>](): ExtractedFeaturePostStep[T, K] = MapEntryKt.key(container)
    def value[T : Map.Entry[*, V]](): ExtractedFeaturePostStep[T, V] = MapEntryKt.value(container)
}
