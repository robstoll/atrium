package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.fun2
import ch.tutteli.atrium.specs.notImplemented

class MapEntryAssertionsSpec : ch.tutteli.atrium.specs.integration.MapEntryAssertionsSpec(
    fun2(Expect<Map.Entry<String, Int>>::isKeyValue),
    fun2(Expect<Map.Entry<String?, Int?>>::isKeyValue)
){
    companion object {


        fun isKeyValue(key: String, value: Int)
            = fun2 (key to value)

        //fun key () =

        //fun value () =
    }
}
