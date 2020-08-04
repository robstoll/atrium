//---------------------------------------------------
//  Generated content, modify:
//  logic/generateLogic.gradle
//  if necessary - enjoy the day 🙂
//---------------------------------------------------


package ch.tutteli.atrium.scala2.logic

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.creating.changers.ExtractedFeaturePostStep
import kotlin.reflect.KClass
import ch.tutteli.atrium.logic.MapKt
import ch.tutteli.atrium.scala2.logic._

class MapLogic[K, V, T <: Map[out K, V]](container: AssertionContainer[K]) {

    def contains(keyValuePairs: List[Pair[K, V]]): Assertion = MapKt.contains(container, keyValuePairs)

    def containsKeyWithValueAssertions[V, T : Map[out K, V]](valueType: KClass[V], keyValues: List[Pair[K, Expect[V] => Unit]]): Assertion =
        MapKt.containsKeyWithValueAssertions(container, valueType, keyValues)

    def containsKey[T : Map[out K, *>](key: K): Assertion = MapKt.containsKey(container, key)
    def containsNotKey[T : Map[out K, *>](key: K): Assertion = MapKt.containsNotKey(container, key)

    def isEmpty(): Assertion = MapKt.isEmpty(container)
    def isNotEmpty(): Assertion = MapKt.isNotEmpty(container)

    def getExisting(key: K): ExtractedFeaturePostStep[T, V] = MapKt.getExisting(container, key)

    def size(): ExtractedFeaturePostStep[T, Int] = MapKt.size(container)
}
