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
        var m1: Expect<Map.Entry<CharSequence, Number>> = notImplemented()
        var m2: Expect<Map.Entry<CharSequence?, Number>> = notImplemented()
        var m3: Expect<Map.Entry<CharSequence, Number?>> = notImplemented()
        var m4: Expect<Map.Entry<CharSequence?, Number?>> = notImplemented()
        var m5: Expect<out Map.Entry<CharSequence?, Number?>> = notImplemented()

        m1 = m1.isKeyValue("a", 1)
        m1 = m1.isKeyValue("a" as CharSequence, 1 as Number)

        m2 = m2.isKeyValue("a", 1)
        m2 = m2.isKeyValue("a" as CharSequence, 1 as Number)

        m3 = m3.isKeyValue("a", 1)
        m3 = m3.isKeyValue("a" as CharSequence, 1 as Number)

        m4 = m4.isKeyValue("a", 1)
        m4 = m4.isKeyValue("a" as CharSequence, 1 as Number)

        m5 = m5.isKeyValue("a", 1)
        m5 = m5.isKeyValue("a" as CharSequence, 1 as Number)

        m1.key
        m2.key
        m3.key
        m4.key
        m5.key
        m1 = m1.key { }
        m2 = m2.key { }
        m3 = m3.key { }
        m4 = m4.key { }
        m5 = m5.key { }

        m1.value
        m2.value
        m3.value
        m4.value
        m5.value
        m1 = m1.value { }
        m2 = m2.value { }
        m3 = m3.value { }
        m4 = m4.value { }
        m5 = m5.value { }
    }
}
