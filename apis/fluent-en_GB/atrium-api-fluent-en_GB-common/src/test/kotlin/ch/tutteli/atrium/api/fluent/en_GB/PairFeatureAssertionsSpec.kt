package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.api.verbs.internal.AssertionVerbFactory
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.fun1
import ch.tutteli.atrium.specs.notImplemented
import ch.tutteli.atrium.specs.property

class PairFeatureAssertionsSpec : ch.tutteli.atrium.specs.integration.PairFeatureAssertionsSpec(
    AssertionVerbFactory,
    property<Pair<String, Int>, String>(Expect<Pair<String, Int>>::first),
    fun1<Pair<String, Int>, Expect<String>.() -> Unit>(Expect<Pair<String, Int>>::first),
    property<Pair<String, Int>, Int>(Expect<Pair<String, Int>>::second),
    fun1<Pair<String, Int>, Expect<Int>.() -> Unit>(Expect<Pair<String, Int>>::second),
    property<Pair<String?, Int?>, String?>(Expect<Pair<String?, Int?>>::first),
    fun1<Pair<String?, Int?>, Expect<String?>.() -> Unit>(Expect<Pair<String?, Int?>>::first),
    property<Pair<String?, Int?>, Int?>(Expect<Pair<String?, Int?>>::second),
    fun1<Pair<String?, Int?>, Expect<Int?>.() -> Unit>(Expect<Pair<String?, Int?>>::second)
) {

    @Suppress("unused", "UNUSED_VALUE")
    private fun ambiguityTest() {
        var a1: Expect<out Pair<String, Int>> = notImplemented()
        var a2: Expect<Pair<String?, Int>> = notImplemented()
        var a3: Expect<Pair<String?, Int?>> = notImplemented()

        a1.first
        a2.first
        a3.first
        a1 = a1.first { }
        a2 = a2.first { }
        a3 = a3.first { }

        a1.second
        a2.second
        a3.second
        a1 = a1.second { }
        a2 = a2.second { }
        a3 = a3.second { }
    }
}
