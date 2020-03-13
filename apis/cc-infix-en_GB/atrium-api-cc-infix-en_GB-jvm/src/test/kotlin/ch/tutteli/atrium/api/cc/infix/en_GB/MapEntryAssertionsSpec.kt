@file:Suppress("DEPRECATION" /* will be removed with 1.0.0 */)
package ch.tutteli.atrium.api.cc.infix.en_GB

import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.verbs.internal.AssertionVerbFactory

//TODO remove with 1.0.0, no need to migrate to Spek 2
class MapEntryAssertionsSpec : ch.tutteli.atrium.spec.integration.MapEntryAssertionsSpec(
    AssertionVerbFactory,
    isKeyValueFun.name to Companion::isKeyValue
){
    companion object {
        private val isKeyValueFun =  Assert<Map.Entry<String, Int>>::isKeyValue

        fun isKeyValue(plant: Assert<Map.Entry<String, Int>>, key: String, value: Int)
            = plant isKeyValue (key to value)
    }
}
