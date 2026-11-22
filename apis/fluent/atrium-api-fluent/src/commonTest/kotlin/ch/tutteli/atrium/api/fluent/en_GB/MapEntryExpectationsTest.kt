package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.specs.integration.mapEntry
import kotlin.test.Test

class MapEntryExpectationsTest : ch.tutteli.atrium.specs.integration.AbstractMapEntryExpectationsTest(
    fun2(Expect<Map.Entry<String, Int>>::toEqualKeyValue),
    fun2(Expect<Map.Entry<String?, Int?>>::toEqualKeyValue).withNullableSuffix(),
    property<Map.Entry<String, Int>, String>(Expect<Map.Entry<String, Int>>::key),
    fun1<Map.Entry<String, Int>, Expect<String>.() -> Unit>(Expect<Map.Entry<String, Int>>::key),
    property<Map.Entry<String, Int>, Int>(Expect<Map.Entry<String, Int>>::value),
    fun1<Map.Entry<String, Int>, Expect<Int>.() -> Unit>(Expect<Map.Entry<String, Int>>::value),
    property<Map.Entry<String?, Int?>, String?>(Expect<Map.Entry<String?, Int?>>::key).withNullableSuffix(),
    fun1<Map.Entry<String?, Int?>, Expect<String?>.() -> Unit>(Expect<Map.Entry<String?, Int?>>::key).withNullableSuffix(),
    property<Map.Entry<String?, Int?>, Int?>(Expect<Map.Entry<String?, Int?>>::value).withNullableSuffix(),
    fun1<Map.Entry<String?, Int?>, Expect<Int?>.() -> Unit>(Expect<Map.Entry<String?, Int?>>::value).withNullableSuffix()
) {

    @Suppress("UNUSED_VALUE", "UNUSED_VARIABLE", "unused", "AssignedValueIsNeverRead")
    @Test
    fun ambiguityTest() {
        var a1: Expect<Map.Entry<String, Int>> = expect(mapEntry("a", 1))
        var a2: Expect<Map.Entry<String?, Int>> = expect(mapEntry<String?, Int>("a", 1))
        var a3: Expect<Map.Entry<String, Int?>> = expect(mapEntry<String, Int?>("a", 1))
        var a4: Expect<Map.Entry<String?, Int?>> = expect(mapEntry<String?, Int?>("a", 1))
        var a2WithNullKey: Expect<Map.Entry<String?, Int>> = expect(mapEntry<String?, Int>(null, 1))
        var a3WithNullValue: Expect<Map.Entry<String, Int?>> = expect(mapEntry<String, Int?>("a", null))
        var a4WithNullKey: Expect<Map.Entry<String?, Int?>> = expect(mapEntry<String?, Int?>(null, 1))
        var a4WithNullValue: Expect<Map.Entry<String?, Int?>> = expect(mapEntry<String?, Int?>("a", null))
        var a4WithNullKeyAndValue: Expect<Map.Entry<String?, Int?>> = expect(mapEntry<String?, Int?>(null, null))

        var star: Expect<Map.Entry<*, *>> = expect(mapEntry<String, Int>("a", 1))
        var starWithNullKeyAndValue: Expect<Map.Entry<*, *>> = expect(mapEntry<String?, Int?>(null, null))

        a1.toEqualKeyValue("a", 1)
        a2.toEqualKeyValue("a", 1)
        a3.toEqualKeyValue("a", 1)
        a4.toEqualKeyValue("a", 1)
        star.toEqualKeyValue("a", 1)

        a2WithNullKey.toEqualKeyValue(null, 1)
        a3WithNullValue.toEqualKeyValue("a", null)
        a4WithNullKey.toEqualKeyValue(null, 1)
        a4WithNullValue.toEqualKeyValue("a", null)
        a4WithNullKeyAndValue.toEqualKeyValue(null, null)
        starWithNullKeyAndValue.toEqualKeyValue(null, null)

        a1.key
        a2.key
        a3.key
        a4.key
        star.key
        a1 = a1.key { toEqual("a") }
        a2 = a2.key { toEqual("a") }
        a3 = a3.key { toEqual("a") }
        a4 = a4.key { toEqual("a") }
        star = star.key { toEqual("a") }

        a1.value
        a2.value
        a3.value
        a4.value
        star.value
        a1 = a1.value { toEqual(1) }
        a2 = a2.value { toEqual(1) }
        a3 = a3.value { toEqual(1) }
        a4 = a4.value { toEqual(1) }
        star = star.value { toEqual(1) }
    }
}
