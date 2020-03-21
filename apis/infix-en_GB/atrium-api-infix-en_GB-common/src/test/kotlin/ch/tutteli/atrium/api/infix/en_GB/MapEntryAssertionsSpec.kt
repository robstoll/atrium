package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.fun1
import ch.tutteli.atrium.specs.name
import ch.tutteli.atrium.specs.notImplemented
import ch.tutteli.atrium.specs.testutils.WithAsciiReporter
import ch.tutteli.atrium.specs.withNullableSuffix

class MapEntryAssertionsSpec : ch.tutteli.atrium.specs.integration.MapEntryAssertionsSpec(
    fun1(Expect<Map.Entry<String, Int>>::isKeyValue).name to Companion::isKeyValue,
    (fun1(Expect<Map.Entry<String?, Int?>>::isKeyValue).name to Companion::isKeyValueNullable).withNullableSuffix()
) {
    companion object : WithAsciiReporter() {

        private fun isKeyValue(expect: Expect<Map.Entry<String, Int>>, key: String, value: Int) =
            expect isKeyValue (key to value)

        private fun isKeyValueNullable(expect: Expect<Map.Entry<String?, Int?>>, key: String?, value: Int?) =
            expect isKeyValue (key to value)
    }

    @Suppress("unused", "UNUSED_VALUE")
    private fun ambiguityTest() {
        val a1: Expect<Map.Entry<String, Int>> = notImplemented()
        val a2: Expect<Map.Entry<String?, Int>> = notImplemented()
        val a3: Expect<Map.Entry<String, Int?>> = notImplemented()
        val a4: Expect<Map.Entry<String?, Int?>> = notImplemented()

        val star: Expect<Map.Entry<*, *>> = notImplemented()

        a1 isKeyValue ("a" to 1)
        a2 isKeyValue ("a" to 1)
        a3 isKeyValue ("a" to 1)
        a4 isKeyValue ("a" to 1)
        star isKeyValue ("a" to 1)

        a2 isKeyValue (null to 1)
        a3 isKeyValue ("a" to null)
        a4 isKeyValue (null to null)
        star isKeyValue (null to null)
    }
}
