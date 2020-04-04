package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.feature1
import ch.tutteli.atrium.specs.fun1
import ch.tutteli.atrium.specs.name
import ch.tutteli.atrium.specs.notImplemented
import ch.tutteli.atrium.specs.testutils.WithAsciiReporter

class IterableFeatureAssertionsSpec : ch.tutteli.atrium.specs.integration.IterableFeatureAssertionsSpec(
    minFeaturePair(),
    fun1<Iterable<Int>, Expect<Int>.() -> Unit>(Expect<Iterable<Int>>::min),
    maxFeaturePair(),
    fun1<Iterable<Int>, Expect<Int>.() -> Unit>(Expect<Iterable<Int>>::max)
) {
    companion object : WithAsciiReporter() {
        private fun minFeaturePair() = feature1<Iterable<Int>, o, Int>(Expect<Iterable<Int>>::min).name to ::minFeature
        private fun minFeature(expect: Expect<Iterable<Int>>) = expect min o

        private fun maxFeaturePair() = feature1<Iterable<Int>, o, Int>(Expect<Iterable<Int>>::min).name to ::maxFeature
        private fun maxFeature(expect: Expect<Iterable<Int>>) = expect max o
    }

    @Suppress("unused", "UNUSED_VALUE")
    private fun ambiguityTest() {
        var a1: Expect<List<Int>> = notImplemented()
        //nullable not supported by min/max or rather T : Comparable<T> does not exist for T? (one cannot implement an interface for the nullable type)
        //same for Iterable<*>

        a1 min o toBe 2
        a1 max o toBe 3

        a1 = a1 min { }
        a1 = a1 max { }
    }
}
