package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.specs.testutils.WithAsciiReporter

class MapEntryAssertionsSpec : ch.tutteli.atrium.specs.integration.MapEntryAssertionsSpec(
    fun1(Expect<Map.Entry<String, Int>>::isKeyValue).name to Companion::isKeyValue,
    (fun1(Expect<Map.Entry<String?, Int?>>::isKeyValue).name to Companion::isKeyValueNullable).withNullableSuffix(),
    property<Map.Entry<String, Int>, String>(Expect<Map.Entry<String, Int>>::key),
    fun1<Map.Entry<String, Int>, Expect<String>.() -> Unit>(Expect<Map.Entry<String, Int>>::key),
    property<Map.Entry<String, Int>, Int>(Expect<Map.Entry<String, Int>>::value),
    fun1<Map.Entry<String, Int>, Expect<Int>.() -> Unit>(Expect<Map.Entry<String, Int>>::value),
    property<Map.Entry<String?, Int?>, String?>(Expect<Map.Entry<String?, Int?>>::key),
    fun1<Map.Entry<String?, Int?>, Expect<String?>.() -> Unit>(Expect<Map.Entry<String?, Int?>>::key),
    property<Map.Entry<String?, Int?>, Int?>(Expect<Map.Entry<String?, Int?>>::value),
    fun1<Map.Entry<String?, Int?>, Expect<Int?>.() -> Unit>(Expect<Map.Entry<String?, Int?>>::value)
) {
    companion object : WithAsciiReporter() {

        private fun isKeyValue(expect: Expect<Map.Entry<String, Int>>, key: String, value: Int) =
            expect isKeyValue (key to value)

        private fun isKeyValueNullable(expect: Expect<Map.Entry<String?, Int?>>, key: String?, value: Int?) =
            expect isKeyValue (key to value)
    }

    @Suppress("unused", "UNUSED_VALUE")
    private fun ambiguityTest() {
        var a1: Expect<Map.Entry<String, Int>> = notImplemented()
        var a2: Expect<Map.Entry<String?, Int>> = notImplemented()
        var a3: Expect<Map.Entry<String, Int?>> = notImplemented()
        var a4: Expect<Map.Entry<String?, Int?>> = notImplemented()

        var star: Expect<Map.Entry<*, *>> = notImplemented()

        a1 isKeyValue ("a" to 1)
        a2 isKeyValue ("a" to 1)
        a3 isKeyValue ("a" to 1)
        a4 isKeyValue ("a" to 1)
        star isKeyValue ("a" to 1)

        a2 isKeyValue (null to 1)
        a3 isKeyValue ("a" to null)
        a4 isKeyValue (null to null)
        star isKeyValue (null to null)

        a1.key
        a2.key
        a3.key
        a4.key
        star.key
        a1 = a1 key { }
        a2 = a2 key { }
        a3 = a3 key { }
        a4 = a4 key { }
        star = star key { }

        a1.value
        a2.value
        a3.value
        a4.value
        star.value
        a1 = a1 value { }
        a2 = a2 value { }
        a3 = a3 value { }
        a4 = a4 value { }
        star = star value { }
    }
}
