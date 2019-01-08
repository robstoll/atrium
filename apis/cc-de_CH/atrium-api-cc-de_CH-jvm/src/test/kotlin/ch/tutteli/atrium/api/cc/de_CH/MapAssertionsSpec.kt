package ch.tutteli.atrium.api.cc.de_CH

import ch.tutteli.atrium.AssertionVerbFactory
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.domain.creating.map.KeyNullableValue
import ch.tutteli.atrium.domain.creating.map.KeyValue
import kotlin.reflect.KFunction3

class MapAssertionsSpec : ch.tutteli.atrium.spec.integration.MapAssertionsSpec(
    AssertionVerbFactory,
    containsFun.name to Assert<Map<String, Int>>::enthaelt,
    containsNullableFun.name to Assert<Map<String?, Int?>>::enthaeltNullable,
    "${containsKeyWithValueAssertionsFun.name} ${KeyValue::class.simpleName}" to Assert<Map<String, Int>>::enthaelt,
    "${containsKeyWithNullableValueAssertionsFun.name} ${KeyNullableValue::class.simpleName}" to Assert<Map<String?, Int?>>::enthaeltNullable,
    Assert<Map<String, *>>::enthaeltKey.name to Assert<Map<String, *>>::enthaeltKey,
    "${Assert<Map<String?, *>>::enthaeltKey.name} for nullable" to Assert<Map<String?, *>>::enthaeltKey,
    Assert<Map<*, *>>::hatDieGroesse.name to Assert<Map<*, *>>::hatDieGroesse,
    Assert<Map<*, *>>::istLeer.name to Assert<Map<*, *>>::istLeer,
    Assert<Map<*, *>>::istNichtLeer.name to Assert<Map<*, *>>::istNichtLeer
){
    companion object {
        private val containsFun : KFunction3<Assert<Map<String, Int>>, Pair<String, Int>, Array<out Pair<String, Int>>, Assert<Map<String, Int>>> = Assert<Map<String, Int>>::enthaelt
        private val containsNullableFun : KFunction3<Assert<Map<String?, Int?>>, Pair<String?, Int?>, Array<out Pair<String?, Int?>>, Assert<Map<String?, Int?>>> = Assert<Map<String?, Int?>>::enthaeltNullable
        private val containsKeyWithValueAssertionsFun : KFunction3<Assert<Map<String, Int>>, KeyValue<String, Int>, Array<out KeyValue<String, Int>>, Assert<Map<String, Int>>> = Assert<Map<String, Int>>::enthaelt
        private val containsKeyWithNullableValueAssertionsFun : KFunction3<Assert<Map<String?, Int?>>, KeyNullableValue<String?, Int>, Array<out KeyNullableValue<String?, Int>>, Assert<Map<String?, Int?>>> = Assert<Map<String?, Int?>>::enthaeltNullable
    }
}

