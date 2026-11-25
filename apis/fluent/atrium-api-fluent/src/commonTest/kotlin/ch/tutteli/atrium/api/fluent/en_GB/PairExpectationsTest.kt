package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.fun1
import ch.tutteli.atrium.specs.property
import kotlin.test.Test

class PairExpectationsTest : ch.tutteli.atrium.specs.integration.AbstractPairExpectationsTest(
    property<Pair<String, Int>, String>(Expect<Pair<String, Int>>::first),
    fun1<Pair<String, Int>, Expect<String>.() -> Unit>(Expect<Pair<String, Int>>::first),
    property<Pair<String, Int>, Int>(Expect<Pair<String, Int>>::second),
    fun1<Pair<String, Int>, Expect<Int>.() -> Unit>(Expect<Pair<String, Int>>::second),
    property<Pair<String?, Int?>, String?>(Expect<Pair<String?, Int?>>::first),
    fun1<Pair<String?, Int?>, Expect<String?>.() -> Unit>(Expect<Pair<String?, Int?>>::first),
    property<Pair<String?, Int?>, Int?>(Expect<Pair<String?, Int?>>::second),
    fun1<Pair<String?, Int?>, Expect<Int?>.() -> Unit>(Expect<Pair<String?, Int?>>::second)
) {

    @Suppress("unused", "UNUSED_VALUE", "AssignedValueIsNeverRead")
    @Test
    fun ambiguityTest() {
        var a1: Expect<Pair<String, Int>> = expect(Pair("a", 1))
        var a2: Expect<Pair<String?, Int>> = expect(Pair<String?, Int>("a", 1))
        var a3: Expect<Pair<String, Int?>> = expect(Pair<String, Int?>("a", 1))
        var a4: Expect<Pair<String?, Int?>> = expect(Pair<String?, Int?>("a", 1))
        var star: Expect<Pair<*, *>> = expect(Pair("a", 1))

        a1.first.toEqual("a")
        a2.first.toEqual("a")
        a3.first.toEqual("a")
        a4.first.toEqual("a")
        star.first.toEqual("a")
        a1 = a1.first { toEqual("a") }
        a2 = a2.first { toEqual("a") }
        a3 = a3.first { toEqual("a") }
        a4 = a4.first { toEqual("a") }
        star = star.first { toEqual("a") }

        a1.second.toEqual(1)
        a2.second.toEqual(1)
        a3.second.toEqual(1)
        a4.second.toEqual(1)
        star.second.toEqual(1)
        a1 = a1.second { toEqual(1) }
        a2 = a2.second { toEqual(1) }
        a3 = a3.second { toEqual(1) }
        a4 = a4.second { toEqual(1) }
        star = star.second { toEqual(1) }
    }
}
