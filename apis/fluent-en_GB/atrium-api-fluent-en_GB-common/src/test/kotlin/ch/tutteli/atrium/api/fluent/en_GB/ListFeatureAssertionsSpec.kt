package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.*

object ListFeatureAssertionsSpec : ch.tutteli.atrium.specs.integration.ListFeatureAssertionsSpec(
    feature1<List<Int>, Int, Int>(Expect<List<Int>>::get),
    fun2<List<Int>, Int, Expect<Int>.() -> Unit>(Expect<List<Int>>::get),
    feature1<List<Int?>, Int, Int?>(Expect<List<Int?>>::get).withNullableSuffix(),
    fun2<List<Int?>, Int, Expect<Int?>.() -> Unit>(Expect<List<Int?>>::get).withNullableSuffix()
) {
    @Suppress("unused", "UNUSED_VALUE")
    private fun ambiguityTest() {
        var a1: Expect<List<Int>> = notImplemented()
        var a1b: Expect<List<Int?>> = notImplemented()

        var star: Expect<List<*>> = notImplemented()

        a1.get(1)
        a1 = a1.get(1) { }

        a1b.get(1)
        a1b = a1b.get(1) { }

        star.get(1)
        star = star.get(1) { }
    }
}
