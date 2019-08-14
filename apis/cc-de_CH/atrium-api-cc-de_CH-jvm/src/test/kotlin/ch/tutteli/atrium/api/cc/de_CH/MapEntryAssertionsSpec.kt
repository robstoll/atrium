@file:Suppress("DEPRECATION" /* will be removed with 1.0.0 */)
package ch.tutteli.atrium.api.cc.de_CH

import ch.tutteli.atrium.AssertionVerbFactory
import ch.tutteli.atrium.creating.Assert

class MapEntryAssertionsSpec : ch.tutteli.atrium.spec.integration.MapEntryAssertionsSpec(
    AssertionVerbFactory,
    isKeyValueFun.name to isKeyValueFun
){
    companion object {
        private val isKeyValueFun =  Assert<Map.Entry<String, Int>>::istKeyValue
    }
}
