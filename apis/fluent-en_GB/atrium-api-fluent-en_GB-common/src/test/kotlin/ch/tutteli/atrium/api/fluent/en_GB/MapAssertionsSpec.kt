package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.builders.utils.mapArguments
import ch.tutteli.atrium.specs.fun0
import ch.tutteli.atrium.specs.fun1
import ch.tutteli.atrium.specs.fun2
import ch.tutteli.atrium.specs.notImplemented
import kotlin.reflect.KFunction3

class MapAssertionsSpec : ch.tutteli.atrium.specs.integration.MapAssertionsSpec(
    containsFun.name to Companion::contains,
    fun2<Map<out String?, Int?>, Pair<String?, Int?>, Array<out Pair<String?, Int?>>>(Expect<Map<out String?, Int?>>::contains),
    "${containsKeyWithValueAssertionsFun.name} ${KeyValue::class.simpleName}" to Companion::containsKeyValue,
    "${containsKeyWithNullableValueAssertionsFun.name} ${KeyValue::class.simpleName}" to Companion::containsNullable,
    fun1(Expect<Map<out String, *>>::containsKey),
    fun1(Expect<Map<out String?, *>>::containsKey),
    fun1(Expect<Map<out String, *>>::containsNotKey),
    fun1(Expect<Map<out String?, *>>::containsNotKey),
    fun0(Expect<Map<*, *>>::isEmpty),
    fun0(Expect<Map<*, *>>::isNotEmpty)
) {
    companion object {
        //@formatter:off
        private val containsFun : KFunction3<Expect<Map<out String, Int>>, Pair<String, Int>, Array<out Pair<String, Int>>, Expect<Map<out String, Int>>> = Expect<Map<out String, Int>>::contains
        private val containsKeyWithValueAssertionsFun : KFunction3<Expect<Map<out String, Int>>, KeyValue<String, Int>, Array<out KeyValue<String, Int>>, Expect<Map<out String, Int>>> = Expect<Map<out String, Int>>::contains
        private val containsKeyWithNullableValueAssertionsFun : KFunction3<Expect<Map<out String?, Int?>>, KeyValue<String?, Int>, Array<out KeyValue<String?, Int>>, Expect<Map<out String?, Int?>>> = Expect<Map<out String?, Int?>>::contains
        //@formatter:on

        fun contains(
            plant: Expect<Map<out String, Int>>,
            keyValuePair: Pair<String, Int>,
            otherKeyValuePairs: Array<out Pair<String, Int>>
        ) = plant.contains(keyValuePair, *otherKeyValuePairs)

        fun containsKeyValue(
            plant: Expect<Map<out String, Int>>,
            keyValue: Pair<String, Expect<Int>.() -> Unit>,
            otherKeyValues: Array<out Pair<String, Expect<Int>.() -> Unit>>
        ) = mapArguments(keyValue, otherKeyValues).to { KeyValue(it.first, it.second) }.let { (first, others) ->
            plant.contains(first, *others)
        }

        fun containsNullable(
            plant: Expect<Map<out String?, Int?>>,
            keyValue: Pair<String?, (Expect<Int>.() -> Unit)?>,
            otherKeyValues: Array<out Pair<String?, (Expect<Int>.() -> Unit)?>>
        ) = mapArguments(keyValue, otherKeyValues).to { KeyValue(it.first, it.second) }.let { (first, others) ->
            plant.contains(first, *others)
        }
    }

    @Suppress("unused")
    private fun ambiguityTest() {
        val map: Expect<Map<Number, CharSequence>> = notImplemented()
        val subMap: Expect<LinkedHashMap<out Number, String>> = notImplemented()
        val nullableKeyMap: Expect<Map<Number?, CharSequence>> = notImplemented()
        val nullableValueMap: Expect<Map<Number, CharSequence?>> = notImplemented()
        val nullableKeyValueMap: Expect<Map<Number?, CharSequence?>> = notImplemented()
        val readOnlyNullableKeyValueMap: Expect<Map<out Number?, CharSequence?>> = notImplemented()
        val starMap: Expect<Map<*, *>> = notImplemented()

        map.contains(1 to "a")
        map.contains(1 to "a", 2 to "b")
        map.contains(KeyValue(1) {})
        map.contains(KeyValue(1) {}, KeyValue(2) {})
        map.contains(1.0 to StringBuilder("a"))
        map.contains(12f to "a", 2L to StringBuilder("b"))
        map.contains(KeyValue(1) {})
        map.contains(KeyValue(1) {}, KeyValue(2) {})

        subMap.contains(1 to "a")
        subMap.contains(1 to "a", 2 to "b")
        subMap.contains(KeyValue(1) {})
        subMap.contains(KeyValue(1) {}, KeyValue(2) {})
        subMap.contains(1.0 to StringBuilder("a"))
        subMap.contains(12f to "a", 2L to StringBuilder("b"))
        subMap.contains(KeyValue(1) {})
        subMap.contains(KeyValue(1) {}, KeyValue(2) {})

        nullableKeyMap.contains(1 to "a")
        nullableKeyMap.contains(1 to "a", 2 to "b")
        nullableKeyMap.contains(KeyValue(1) {})
        nullableKeyMap.contains(KeyValue(1) {}, KeyValue(2) {})
        nullableKeyMap.contains(null to "a")
        nullableKeyMap.contains(null to "a", null to "b")
        nullableKeyMap.contains(null to "a", 2 to "b")
        nullableKeyMap.contains(KeyValue(null) {})
        nullableKeyMap.contains(KeyValue(null) {}, KeyValue(null) {})
        nullableKeyMap.contains(KeyValue(null) {}, KeyValue(2) {})

        nullableValueMap.contains(1 to "a")
        nullableValueMap.contains(1 to "a", 2 to "b")
        nullableValueMap.contains(KeyValue(1) {})
        nullableValueMap.contains(KeyValue(1) {}, KeyValue(2) {})
        nullableValueMap.contains(1 to null)
        nullableValueMap.contains(1 to null, 2 to null)
        nullableValueMap.contains(1 to null, 2 to "a")
        nullableValueMap.contains(KeyValue(1, null))
        nullableValueMap.contains(KeyValue(1, null), KeyValue(2, null))
        nullableValueMap.contains(KeyValue(1, null), KeyValue(2) {})

        nullableKeyValueMap.contains(1 to "a")
        nullableKeyValueMap.contains(1 to "a", 2 to "b")
        nullableKeyValueMap.contains(KeyValue(1) {})
        nullableKeyValueMap.contains(KeyValue(1) {}, KeyValue(2) {})

        nullableKeyValueMap.contains(null to "a")
        nullableKeyValueMap.contains(null to "a", null to "b")
        nullableKeyValueMap.contains(null to "a", 2 to "b")
        nullableKeyValueMap.contains(KeyValue(null) {})
        nullableKeyValueMap.contains(KeyValue(null) {}, KeyValue(null) {})
        nullableKeyValueMap.contains(KeyValue(null) {}, KeyValue(2) {})

        nullableKeyValueMap.contains(1 to null)
        nullableKeyValueMap.contains(1 to null, 2 to null)
        nullableKeyValueMap.contains(1 to null, 2 to "a")
        nullableKeyValueMap.contains(KeyValue(1, null))
        nullableKeyValueMap.contains(KeyValue(1, null), KeyValue(2, null))
        nullableKeyValueMap.contains(KeyValue(1, null), KeyValue(2) {})

        nullableKeyValueMap.contains(null to null)
        nullableKeyValueMap.contains(null to null, null to null)
        nullableKeyValueMap.contains(1 to null, null to "a")
        nullableKeyValueMap.contains(KeyValue(null, null))
        nullableKeyValueMap.contains(KeyValue(null, null), KeyValue(null, null))
        nullableKeyValueMap.contains(KeyValue(1, null), KeyValue(null) {})

        readOnlyNullableKeyValueMap.contains(1 to "a")
        readOnlyNullableKeyValueMap.contains(1 to "a", 2 to "b")
        readOnlyNullableKeyValueMap.contains(KeyValue(1) {})
        readOnlyNullableKeyValueMap.contains(KeyValue(1) {}, KeyValue(2) {})

        readOnlyNullableKeyValueMap.contains(null to "a")
        readOnlyNullableKeyValueMap.contains(null to "a", null to "b")
        readOnlyNullableKeyValueMap.contains(null to "a", 2 to "b")
        readOnlyNullableKeyValueMap.contains(KeyValue(null) {})
        readOnlyNullableKeyValueMap.contains(KeyValue(null) {}, KeyValue(null) {})
        readOnlyNullableKeyValueMap.contains(KeyValue(null) {}, KeyValue(2) {})

        readOnlyNullableKeyValueMap.contains(1 to null)
        readOnlyNullableKeyValueMap.contains(1 to null, 2 to null)
        readOnlyNullableKeyValueMap.contains(1 to null, 2 to "a")
        readOnlyNullableKeyValueMap.contains(KeyValue(1, null))
        readOnlyNullableKeyValueMap.contains(KeyValue(1, null), KeyValue(2, null))
        readOnlyNullableKeyValueMap.contains(KeyValue(1, null), KeyValue(2) {})

        readOnlyNullableKeyValueMap.contains(null to null)
        readOnlyNullableKeyValueMap.contains(null to null, null to null)
        readOnlyNullableKeyValueMap.contains(1 to null, null to "a")
        readOnlyNullableKeyValueMap.contains(KeyValue(null, null))
        readOnlyNullableKeyValueMap.contains(KeyValue(null, null), KeyValue(null, null))
        readOnlyNullableKeyValueMap.contains(KeyValue(1, null), KeyValue(null) {})

        readOnlyNullableKeyValueMap.contains(1 to "a")
        readOnlyNullableKeyValueMap.contains(1 to "a", 2 to "b")
        readOnlyNullableKeyValueMap.contains(KeyValue(1) {})
        readOnlyNullableKeyValueMap.contains(KeyValue(1) {}, KeyValue(2) {})

        starMap.contains(null to "a")
        starMap.contains(null to "a", null to "b")
        starMap.contains(null to "a", 2 to "b")
        starMap.contains(KeyValue(null) {})
        starMap.contains(KeyValue(null) {}, KeyValue(null) {})
        starMap.contains(KeyValue(null) {}, KeyValue(2) {})

        starMap.contains(1 to null)
        starMap.contains(1 to null, 2 to null)
        starMap.contains(1 to null, 2 to "a")
        starMap.contains(KeyValue(1, null))
        starMap.contains(KeyValue(1, null), KeyValue(2, null))
        starMap.contains(KeyValue(1, null), KeyValue(2) {})

        starMap.contains(null to null)
        starMap.contains(null to null, null to null)
        starMap.contains(1 to null, null to "a")
        starMap.contains(KeyValue(null, null))
        starMap.contains(KeyValue(null, null), KeyValue(null, null))
        starMap.contains(KeyValue(1, null), KeyValue(null) {})

        map.containsKey(1)
        map.containsKey(1f)
        subMap.containsKey(1)
        subMap.containsKey(1f)
        nullableKeyMap.containsKey(1)
        nullableKeyMap.containsKey(1f)
        readOnlyNullableKeyValueMap.containsKey(1)
        readOnlyNullableKeyValueMap.containsKey(1f)

        map.containsNotKey(1)
        map.containsNotKey(1f)
        subMap.containsNotKey(1)
        subMap.containsNotKey(1f)
        nullableKeyMap.containsNotKey(1)
        nullableKeyMap.containsNotKey(1f)
        readOnlyNullableKeyValueMap.containsNotKey(1)
        readOnlyNullableKeyValueMap.containsNotKey(1f)

        map.isEmpty()
        map.isNotEmpty()
        subMap.isEmpty()
        subMap.isNotEmpty()
        nullableKeyMap.isEmpty()
        nullableKeyMap.isNotEmpty()
        readOnlyNullableKeyValueMap.isEmpty()
        readOnlyNullableKeyValueMap.isNotEmpty()
        starMap.isEmpty()
        starMap.isNotEmpty()
    }
}
