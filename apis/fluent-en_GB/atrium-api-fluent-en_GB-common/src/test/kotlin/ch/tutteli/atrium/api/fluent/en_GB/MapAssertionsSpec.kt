package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.api.verbs.internal.AssertionVerbFactory
import ch.tutteli.atrium.api.verbs.internal.assert
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.builders.utils.mapArguments
import kotlin.reflect.KFunction3

class MapAssertionsSpec : ch.tutteli.atrium.specs.integration.MapAssertionsSpec(
    AssertionVerbFactory,
    containsFun.name to Companion::contains,
    containsNullableFun.name to Expect<Map<out String?, Int?>>::contains,
    "${containsKeyWithValueAssertionsFun.name} ${KeyValue::class.simpleName}" to Companion::containsKeyValue,
    "${containsKeyWithNullableValueAssertionsFun.name} ${KeyValue::class.simpleName}" to Companion::containsNullable,
    Expect<Map<out String, *>>::containsKey.name to Expect<Map<out String, *>>::containsKey,
    Expect<Map<out String?, *>>::containsKey.name to Expect<Map<out String?, *>>::containsKey,
    Expect<Map<out String, *>>::containsKey.name to Expect<Map<out String, *>>::containsNotKey,
    Expect<Map<out String?, *>>::containsKey.name to Expect<Map<out String?, *>>::containsNotKey,
    Expect<Map<*, *>>::isEmpty.name to Expect<Map<*, *>>::isEmpty,
    Expect<Map<*, *>>::isNotEmpty.name to Expect<Map<*, *>>::isNotEmpty
) {
    companion object {
        //@formatter:off
        private val containsFun : KFunction3<Expect<Map<out String, Int>>, Pair<String, Int>, Array<out Pair<String, Int>>, Expect<Map<out String, Int>>> = Expect<Map<out String, Int>>::contains
        private val containsNullableFun : KFunction3<Expect<Map<out String?, Int?>>, Pair<String?, Int?>, Array<out Pair<String?, Int?>>, Expect<Map<out String?, Int?>>> = Expect<Map<out String?, Int?>>::contains
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

    private fun <T> magic(): T = throw NotImplementedError()

    @Suppress("unused")
    private fun ambiguityTest() {
        val map: Map<Number, CharSequence> = magic()
        val subMap: LinkedHashMap<out Number, String> = magic()
        val nullableKeyMap: Map<Number?, CharSequence> = magic()
        val nullableValueMap: Map<Number, CharSequence?> = magic()
        val nullableKeyValueMap: Map<Number?, CharSequence?> = magic()
        val readOnlyNullableKeyValueMap: Map<out Number?, CharSequence?> = magic()

        assert(map).contains(1 to "a")
        assert(map).contains(1 to "a", 2 to "b")
        assert(map).contains(KeyValue(1) {})
        assert(map).contains(KeyValue(1) {}, KeyValue(2) {})
        assert(map).contains(1.0 to StringBuilder("a"))
        assert(map).contains(12f to "a", 2L to StringBuilder("b"))
        assert(map).contains(KeyValue(1) {})
        assert(map).contains(KeyValue(1) {}, KeyValue(2) {})

        assert(subMap).contains(1 to "a")
        assert(subMap).contains(1 to "a", 2 to "b")
        assert(subMap).contains(KeyValue(1) {})
        assert(subMap).contains(KeyValue(1) {}, KeyValue(2) {})
        assert(subMap).contains(1.0 to StringBuilder("a"))
        assert(subMap).contains(12f to "a", 2L to StringBuilder("b"))
        assert(subMap).contains(KeyValue(1) {})
        assert(subMap).contains(KeyValue(1) {}, KeyValue(2) {})

        assert(nullableKeyMap).contains(1 to "a")
        assert(nullableKeyMap).contains(1 to "a", 2 to "b")
        assert(nullableKeyMap).contains(KeyValue(1) {})
        assert(nullableKeyMap).contains(KeyValue(1) {}, KeyValue(2) {})
        assert(nullableKeyMap).contains(null to "a")
        assert(nullableKeyMap).contains(null to "a", null to "b")
        assert(nullableKeyMap).contains(null to "a", 2 to "b")
        assert(nullableKeyMap).contains(KeyValue(null) {})
        assert(nullableKeyMap).contains(KeyValue(null) {}, KeyValue(null) {})
        assert(nullableKeyMap).contains(KeyValue(null) {}, KeyValue(2) {})

        assert(nullableValueMap).contains(1 to "a")
        assert(nullableValueMap).contains(1 to "a", 2 to "b")
        assert(nullableValueMap).contains(KeyValue(1) {})
        assert(nullableValueMap).contains(KeyValue(1) {}, KeyValue(2) {})
        assert(nullableValueMap).contains(1 to null)
        assert(nullableValueMap).contains(1 to null, 2 to null)
        assert(nullableValueMap).contains(1 to null, 2 to "a")
        assert(nullableValueMap).contains(KeyValue(1, null))
        assert(nullableValueMap).contains(KeyValue(1, null), KeyValue(2, null))
        assert(nullableValueMap).contains(KeyValue(1, null), KeyValue(2) {})

        assert(nullableKeyValueMap).contains(1 to "a")
        assert(nullableKeyValueMap).contains(1 to "a", 2 to "b")
        assert(nullableKeyValueMap).contains(KeyValue(1) {})
        assert(nullableKeyValueMap).contains(KeyValue(1) {}, KeyValue(2) {})

        assert(nullableKeyValueMap).contains(null to "a")
        assert(nullableKeyValueMap).contains(null to "a", null to "b")
        assert(nullableKeyValueMap).contains(null to "a", 2 to "b")
        assert(nullableKeyValueMap).contains(KeyValue(null) {})
        assert(nullableKeyValueMap).contains(KeyValue(null) {}, KeyValue(null) {})
        assert(nullableKeyValueMap).contains(KeyValue(null) {}, KeyValue(2) {})

        assert(nullableKeyValueMap).contains(1 to null)
        assert(nullableKeyValueMap).contains(1 to null, 2 to null)
        assert(nullableKeyValueMap).contains(1 to null, 2 to "a")
        assert(nullableKeyValueMap).contains(KeyValue(1, null))
        assert(nullableKeyValueMap).contains(KeyValue(1, null), KeyValue(2, null))
        assert(nullableKeyValueMap).contains(KeyValue(1, null), KeyValue(2) {})

        assert(nullableKeyValueMap).contains(null to null)
        assert(nullableKeyValueMap).contains(null to null, null to null)
        assert(nullableKeyValueMap).contains(1 to null, null to "a")
        assert(nullableKeyValueMap).contains(KeyValue(null, null))
        assert(nullableKeyValueMap).contains(KeyValue(null, null), KeyValue(null, null))
        assert(nullableKeyValueMap).contains(KeyValue(1, null), KeyValue(null) {})

        assert(readOnlyNullableKeyValueMap).contains(1 to "a")
        assert(readOnlyNullableKeyValueMap).contains(1 to "a", 2 to "b")
        assert(readOnlyNullableKeyValueMap).contains(KeyValue(1) {})
        assert(readOnlyNullableKeyValueMap).contains(KeyValue(1) {}, KeyValue(2) {})

        assert(readOnlyNullableKeyValueMap).contains(null to "a")
        assert(readOnlyNullableKeyValueMap).contains(null to "a", null to "b")
        assert(readOnlyNullableKeyValueMap).contains(null to "a", 2 to "b")
        assert(readOnlyNullableKeyValueMap).contains(KeyValue(null) {})
        assert(readOnlyNullableKeyValueMap).contains(KeyValue(null) {}, KeyValue(null) {})
        assert(readOnlyNullableKeyValueMap).contains(KeyValue(null) {}, KeyValue(2) {})

        assert(readOnlyNullableKeyValueMap).contains(1 to null)
        assert(readOnlyNullableKeyValueMap).contains(1 to null, 2 to null)
        assert(readOnlyNullableKeyValueMap).contains(1 to null, 2 to "a")
        assert(readOnlyNullableKeyValueMap).contains(KeyValue(1, null))
        assert(readOnlyNullableKeyValueMap).contains(KeyValue(1, null), KeyValue(2, null))
        assert(readOnlyNullableKeyValueMap).contains(KeyValue(1, null), KeyValue(2) {})

        assert(readOnlyNullableKeyValueMap).contains(null to null)
        assert(readOnlyNullableKeyValueMap).contains(null to null, null to null)
        assert(readOnlyNullableKeyValueMap).contains(1 to null, null to "a")
        assert(readOnlyNullableKeyValueMap).contains(KeyValue(null, null))
        assert(readOnlyNullableKeyValueMap).contains(KeyValue(null, null), KeyValue(null, null))
        assert(readOnlyNullableKeyValueMap).contains(KeyValue(1, null), KeyValue(null) {})

        assert(map).containsKey(1)
        assert(map).containsKey(1f)
        assert(subMap).containsKey(1)
        assert(subMap).containsKey(1f)
        assert(nullableKeyMap).containsKey(1)
        assert(nullableKeyMap).containsKey(1f)
        assert(readOnlyNullableKeyValueMap).containsKey(1)
        assert(readOnlyNullableKeyValueMap).containsKey(1f)

        assert(map).containsNotKey(1)
        assert(map).containsNotKey(1f)
        assert(subMap).containsNotKey(1)
        assert(subMap).containsNotKey(1f)
        assert(nullableKeyMap).containsNotKey(1)
        assert(nullableKeyMap).containsNotKey(1f)
        assert(readOnlyNullableKeyValueMap).containsNotKey(1)
        assert(readOnlyNullableKeyValueMap).containsNotKey(1f)

        assert(map).isEmpty()
        assert(map).isNotEmpty()
        assert(subMap).isEmpty()
        assert(subMap).isNotEmpty()
        assert(nullableKeyMap).isEmpty()
        assert(nullableKeyMap).isNotEmpty()
        assert(readOnlyNullableKeyValueMap).isEmpty()
        assert(readOnlyNullableKeyValueMap).isNotEmpty()

        assert(map).asEntries()
        assert(subMap).asEntries()
        assert(nullableKeyMap).asEntries()
        assert(readOnlyNullableKeyValueMap).asEntries()
    }
}
