package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.feature1
import ch.tutteli.atrium.specs.fun2
import ch.tutteli.atrium.specs.notImplemented

class ListFeatureAssertionsSpec : ch.tutteli.atrium.specs.integration.ListFeatureAssertionsSpec(
    feature1<List<Int>, Int, Int>(Expect<List<Int>>::get),
    fun2<List<Int>, Int, Expect<Int>.() -> Unit>(Expect<List<Int>>::get),
    feature1<List<Int?>, Int, Int?>(Expect<List<Int?>>::get),
    fun2<List<Int?>, Int, Expect<Int?>.() -> Unit>(Expect<List<Int?>>::get)
) {
    companion object {
        @Suppress("unused", "UNUSED_VALUE")
        private fun ambiguityTest() {
            var a1: Expect<List<Int>> = notImplemented()
            var a2: Expect<out List<Int>> = notImplemented()

            a1.get(1)
            a1 = a1.get(1) { }
            a2.get(1)
            a2 = a2.get(1) { }
        }
    }
}
