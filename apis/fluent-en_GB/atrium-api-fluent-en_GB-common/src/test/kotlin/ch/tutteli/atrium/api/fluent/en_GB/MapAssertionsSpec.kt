package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.api.verbs.internal.expect
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
        val map: Map<Number, CharSequence> = notImplemented()
        val subMap: LinkedHashMap<out Number, String> = notImplemented()
        val nullableKeyMap: Map<Number?, CharSequence> = notImplemented()
        val nullableValueMap: Map<Number, CharSequence?> = notImplemented()
        val nullableKeyValueMap: Map<Number?, CharSequence?> = notImplemented()
        val readOnlyNullableKeyValueMap: Map<out Number?, CharSequence?> = notImplemented()

        expect(map).contains(1 to "a")
        expect(map).contains(1 to "a", 2 to "b")
        expect(map).contains(KeyValue(1) {})
        expect(map).contains(KeyValue(1) {}, KeyValue(2) {})
        expect(map).contains(1.0 to StringBuilder("a"))
        expect(map).contains(12f to "a", 2L to StringBuilder("b"))
        expect(map).contains(KeyValue(1) {})
        expect(map).contains(KeyValue(1) {}, KeyValue(2) {})

        expect(subMap).contains(1 to "a")
        expect(subMap).contains(1 to "a", 2 to "b")
        expect(subMap).contains(KeyValue(1) {})
        expect(subMap).contains(KeyValue(1) {}, KeyValue(2) {})
        expect(subMap).contains(1.0 to StringBuilder("a"))
        expect(subMap).contains(12f to "a", 2L to StringBuilder("b"))
        expect(subMap).contains(KeyValue(1) {})
        expect(subMap).contains(KeyValue(1) {}, KeyValue(2) {})

        expect(nullableKeyMap).contains(1 to "a")
        expect(nullableKeyMap).contains(1 to "a", 2 to "b")
        expect(nullableKeyMap).contains(KeyValue(1) {})
        expect(nullableKeyMap).contains(KeyValue(1) {}, KeyValue(2) {})
        expect(nullableKeyMap).contains(null to "a")
        expect(nullableKeyMap).contains(null to "a", null to "b")
        expect(nullableKeyMap).contains(null to "a", 2 to "b")
        expect(nullableKeyMap).contains(KeyValue(null) {})
        expect(nullableKeyMap).contains(KeyValue(null) {}, KeyValue(null) {})
        expect(nullableKeyMap).contains(KeyValue(null) {}, KeyValue(2) {})

        expect(nullableValueMap).contains(1 to "a")
        expect(nullableValueMap).contains(1 to "a", 2 to "b")
        expect(nullableValueMap).contains(KeyValue(1) {})
        expect(nullableValueMap).contains(KeyValue(1) {}, KeyValue(2) {})
        expect(nullableValueMap).contains(1 to null)
        expect(nullableValueMap).contains(1 to null, 2 to null)
        expect(nullableValueMap).contains(1 to null, 2 to "a")
        expect(nullableValueMap).contains(KeyValue(1, null))
        expect(nullableValueMap).contains(KeyValue(1, null), KeyValue(2, null))
        expect(nullableValueMap).contains(KeyValue(1, null), KeyValue(2) {})

        expect(nullableKeyValueMap).contains(1 to "a")
        expect(nullableKeyValueMap).contains(1 to "a", 2 to "b")
        expect(nullableKeyValueMap).contains(KeyValue(1) {})
        expect(nullableKeyValueMap).contains(KeyValue(1) {}, KeyValue(2) {})

        expect(nullableKeyValueMap).contains(null to "a")
        expect(nullableKeyValueMap).contains(null to "a", null to "b")
        expect(nullableKeyValueMap).contains(null to "a", 2 to "b")
        expect(nullableKeyValueMap).contains(KeyValue(null) {})
        expect(nullableKeyValueMap).contains(KeyValue(null) {}, KeyValue(null) {})
        expect(nullableKeyValueMap).contains(KeyValue(null) {}, KeyValue(2) {})

        expect(nullableKeyValueMap).contains(1 to null)
        expect(nullableKeyValueMap).contains(1 to null, 2 to null)
        expect(nullableKeyValueMap).contains(1 to null, 2 to "a")
        expect(nullableKeyValueMap).contains(KeyValue(1, null))
        expect(nullableKeyValueMap).contains(KeyValue(1, null), KeyValue(2, null))
        expect(nullableKeyValueMap).contains(KeyValue(1, null), KeyValue(2) {})

        expect(nullableKeyValueMap).contains(null to null)
        expect(nullableKeyValueMap).contains(null to null, null to null)
        expect(nullableKeyValueMap).contains(1 to null, null to "a")
        expect(nullableKeyValueMap).contains(KeyValue(null, null))
        expect(nullableKeyValueMap).contains(KeyValue(null, null), KeyValue(null, null))
        expect(nullableKeyValueMap).contains(KeyValue(1, null), KeyValue(null) {})

        expect(readOnlyNullableKeyValueMap).contains(1 to "a")
        expect(readOnlyNullableKeyValueMap).contains(1 to "a", 2 to "b")
        expect(readOnlyNullableKeyValueMap).contains(KeyValue(1) {})
        expect(readOnlyNullableKeyValueMap).contains(KeyValue(1) {}, KeyValue(2) {})

        expect(readOnlyNullableKeyValueMap).contains(null to "a")
        expect(readOnlyNullableKeyValueMap).contains(null to "a", null to "b")
        expect(readOnlyNullableKeyValueMap).contains(null to "a", 2 to "b")
        expect(readOnlyNullableKeyValueMap).contains(KeyValue(null) {})
        expect(readOnlyNullableKeyValueMap).contains(KeyValue(null) {}, KeyValue(null) {})
        expect(readOnlyNullableKeyValueMap).contains(KeyValue(null) {}, KeyValue(2) {})

        expect(readOnlyNullableKeyValueMap).contains(1 to null)
        expect(readOnlyNullableKeyValueMap).contains(1 to null, 2 to null)
        expect(readOnlyNullableKeyValueMap).contains(1 to null, 2 to "a")
        expect(readOnlyNullableKeyValueMap).contains(KeyValue(1, null))
        expect(readOnlyNullableKeyValueMap).contains(KeyValue(1, null), KeyValue(2, null))
        expect(readOnlyNullableKeyValueMap).contains(KeyValue(1, null), KeyValue(2) {})

        expect(readOnlyNullableKeyValueMap).contains(null to null)
        expect(readOnlyNullableKeyValueMap).contains(null to null, null to null)
        expect(readOnlyNullableKeyValueMap).contains(1 to null, null to "a")
        expect(readOnlyNullableKeyValueMap).contains(KeyValue(null, null))
        expect(readOnlyNullableKeyValueMap).contains(KeyValue(null, null), KeyValue(null, null))
        expect(readOnlyNullableKeyValueMap).contains(KeyValue(1, null), KeyValue(null) {})

        expect(map).containsKey(1)
        expect(map).containsKey(1f)
        expect(subMap).containsKey(1)
        expect(subMap).containsKey(1f)
        expect(nullableKeyMap).containsKey(1)
        expect(nullableKeyMap).containsKey(1f)
        expect(readOnlyNullableKeyValueMap).containsKey(1)
        expect(readOnlyNullableKeyValueMap).containsKey(1f)

        expect(map).containsNotKey(1)
        expect(map).containsNotKey(1f)
        expect(subMap).containsNotKey(1)
        expect(subMap).containsNotKey(1f)
        expect(nullableKeyMap).containsNotKey(1)
        expect(nullableKeyMap).containsNotKey(1f)
        expect(readOnlyNullableKeyValueMap).containsNotKey(1)
        expect(readOnlyNullableKeyValueMap).containsNotKey(1f)

        expect(map).isEmpty()
        expect(map).isNotEmpty()
        expect(subMap).isEmpty()
        expect(subMap).isNotEmpty()
        expect(nullableKeyMap).isEmpty()
        expect(nullableKeyMap).isNotEmpty()
        expect(readOnlyNullableKeyValueMap).isEmpty()
        expect(readOnlyNullableKeyValueMap).isNotEmpty()

        expect(map).asEntries()
        expect(subMap).asEntries()
        expect(nullableKeyMap).asEntries()
        expect(readOnlyNullableKeyValueMap).asEntries()
    }
}
