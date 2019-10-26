package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.fun2
import ch.tutteli.atrium.specs.notImplemented

object MapEntryAssertionsSpec : ch.tutteli.atrium.specs.integration.MapEntryAssertionsSpec(
    fun2(Expect<Map.Entry<String, Int>>::isKeyValue),
    fun2(Expect<Map.Entry<String?, Int?>>::isKeyValue)
) {
    @Suppress("unused", "UNUSED_VALUE")
    private fun ambiguityTest() {
        val a1: Expect<Map.Entry<String, Int>> = notImplemented()
        val a2: Expect<Map.Entry<String?, Int>> = notImplemented()
        val a3: Expect<Map.Entry<String, Int?>> = notImplemented()
        val a4: Expect<Map.Entry<String?, Int?>> = notImplemented()
        val a5: Expect<Map.Entry<*, *>> = notImplemented()

        a1.isKeyValue("a", 1)
        a2.isKeyValue("a", 1)
        a3.isKeyValue("a", 1)
        a4.isKeyValue("a", 1)
        a5.isKeyValue("a", 1)

        a2.isKeyValue(null, 1)
        a3.isKeyValue("a", null)
        a4.isKeyValue(null, null)
        a5.isKeyValue(null, null)
    }
}
