package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.fun1
import ch.tutteli.atrium.specs.notImplemented
import ch.tutteli.atrium.specs.property

class MapEntryFeatureAssertionsSpec : ch.tutteli.atrium.specs.integration.MapEntryFeatureAssertionsSpec(
    property<Map.Entry<String, Int>, String>(Expect<Map.Entry<String, Int>>::key),
    fun1<Map.Entry<String, Int>, Expect<String>.() -> Unit>(Expect<Map.Entry<String, Int>>::key),
    property<Map.Entry<String, Int>, Int>(Expect<Map.Entry<String, Int>>::value),
    fun1<Map.Entry<String, Int>, Expect<Int>.() -> Unit>(Expect<Map.Entry<String, Int>>::value),
    property<Map.Entry<String?, Int?>, String?>(Expect<Map.Entry<String?, Int?>>::key),
    fun1<Map.Entry<String?, Int?>, Expect<String?>.() -> Unit>(Expect<Map.Entry<String?, Int?>>::key),
    property<Map.Entry<String?, Int?>, Int?>(Expect<Map.Entry<String?, Int?>>::value),
    fun1<Map.Entry<String?, Int?>, Expect<Int?>.() -> Unit>(Expect<Map.Entry<String?, Int?>>::value)
) {

    @Suppress("unused", "UNUSED_VALUE")
    private fun ambiguityTest() {
        var a1: Expect<Map.Entry<CharSequence, Number>> = notImplemented()
        var a2: Expect<Map.Entry<CharSequence?, Number>> = notImplemented()
        var a3: Expect<Map.Entry<CharSequence, Number?>> = notImplemented()
        var a4: Expect<Map.Entry<CharSequence?, Number?>> = notImplemented()

        var star: Expect<Map.Entry<*, *>> = notImplemented()

        a1.key
        a2.key
        a3.key
        a4.key
        star.key
        a1 = a1.key { }
        a2 = a2.key { }
        a3 = a3.key { }
        a4 = a4.key { }
        star = star.key { }

        a1.value
        a2.value
        a3.value
        a4.value
        star.value
        a1 = a1.value { }
        a2 = a2.value { }
        a3 = a3.value { }
        a4 = a4.value { }
        star = star.value { }
    }
}
