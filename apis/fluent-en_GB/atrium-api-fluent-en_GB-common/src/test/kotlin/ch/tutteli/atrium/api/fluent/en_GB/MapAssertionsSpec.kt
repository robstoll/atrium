package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.utils.mapArguments
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.specs.integration.mfun2
import kotlin.jvm.JvmName

class MapAssertionsSpec : ch.tutteli.atrium.specs.integration.MapAssertionsSpec(
    fun1(Expect<Map<out String, *>>::containsKey),
    fun1(Expect<Map<out String?, *>>::containsKey).withNullableSuffix(),
    fun1(Expect<Map<out String, *>>::containsNotKey),
    fun1(Expect<Map<out String?, *>>::containsNotKey).withNullableSuffix(),
    feature1<Map<out String, Int>, String, Int>(Expect<Map<out String, Int>>::getExisting),
    fun2<Map<out String, Int>, String, Expect<Int>.() -> Unit>(Expect<Map<out String, Int>>::getExisting),
    feature1<Map<out String?, Int?>, String?, Int?>(Expect<Map<out String?, Int?>>::getExisting).withNullableSuffix(),
    fun2<Map<out String?, Int?>, String?, Expect<Int?>.() -> Unit>(Expect<Map<out String?, Int?>>::getExisting).withNullableSuffix(),
    fun0(Expect<Map<*, *>>::isEmpty),
    fun0(Expect<Map<*, *>>::isNotEmpty),
    fun1<Map<out String, Int>, Expect<Set<String>>.() -> Unit>(Expect<Map<out String, Int>>::keys),
    property<Map<out String, Int>, Set<String>>(Expect<Map<out String, Int>>::keys),
    property<Map<out String, Int>, Collection<Int>>(Expect<Map<out String, Int>>::values),
    fun1<Map<out String, Int>, Expect<Collection<Int>>.() -> Unit>(Expect<Map<out String, Int>>::values)
) {

    companion object {
        private fun contains(
            expect: Expect<Map<out String, Int>>,
            keyValue: Pair<String, Expect<Int>.() -> Unit>,
            otherKeyValues: Array<out Pair<String, Expect<Int>.() -> Unit>>
        ) = mapArguments<Pair<String, Expect<Int>.() -> Unit>>(keyValue, otherKeyValues).to { KeyValue(it.first, it.second) }.let { (first, others) ->
            expect.contains(first, *others)
        }

        @JvmName("containsNullable")
        private fun contains(
            expect: Expect<Map<out String?, Int?>>,
            keyValue: Pair<String?, (Expect<Int>.() -> Unit)?>,
            otherKeyValues: Array<out Pair<String?, (Expect<Int>.() -> Unit)?>>
        ) = mapArguments(keyValue, otherKeyValues).to { KeyValue(it.first, it.second) }.let { (first, others) ->
            expect.contains(first, *others)
        }

    }

    @Suppress("unused", "UNUSED_VALUE")
    private fun ambiguityTest() {
        var map: Expect<Map<Number, CharSequence>> = notImplemented()
        var subMap: Expect<LinkedHashMap<out Number, String>> = notImplemented()
        var nullableKeyMap: Expect<Map<Number?, CharSequence>> = notImplemented()
        var nullableValueMap: Expect<Map<Number, CharSequence?>> = notImplemented()
        var nullableKeyValueMap: Expect<Map<Number?, CharSequence?>> = notImplemented()
        var readOnlyNullableKeyValueMap: Expect<Map<out Number?, CharSequence?>> = notImplemented()
        var starMap: Expect<Map<*, *>> = notImplemented()

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

        map = map.isEmpty()
        subMap = subMap.isEmpty()
        nullableKeyMap = nullableKeyMap.isEmpty()
        nullableValueMap = nullableValueMap.isEmpty()
        nullableKeyValueMap = nullableKeyValueMap.isEmpty()
        readOnlyNullableKeyValueMap = readOnlyNullableKeyValueMap.isEmpty()
        starMap = starMap.isEmpty()

        map = map.isNotEmpty()
        subMap = subMap.isNotEmpty()
        nullableKeyMap = nullableKeyMap.isNotEmpty()
        nullableValueMap = nullableValueMap.isNotEmpty()
        nullableKeyValueMap = nullableKeyValueMap.isNotEmpty()
        readOnlyNullableKeyValueMap = readOnlyNullableKeyValueMap.isNotEmpty()
        starMap = starMap.isNotEmpty()

        map.keys
        subMap.keys
        nullableKeyMap.keys
        nullableValueMap.keys
        nullableKeyValueMap.keys
        readOnlyNullableKeyValueMap.keys
        starMap.keys

        map = map.keys { }
        subMap = subMap.keys { }
        nullableKeyMap = nullableKeyMap.keys { }
        nullableValueMap = nullableValueMap.keys { }
        nullableKeyValueMap = nullableKeyValueMap.keys { }
        readOnlyNullableKeyValueMap = readOnlyNullableKeyValueMap.keys { }
        starMap = starMap.keys { }

        map.values
        subMap.values
        nullableKeyMap.values
        nullableValueMap.values
        nullableKeyValueMap.values
        readOnlyNullableKeyValueMap.values
        starMap.values

        map = map.values { }
        subMap = subMap.values { }
        nullableKeyMap = nullableKeyMap.values { }
        nullableValueMap = nullableValueMap.values { }
        nullableKeyValueMap = nullableKeyValueMap.values { }
        readOnlyNullableKeyValueMap = readOnlyNullableKeyValueMap.values { }
        starMap = starMap.values { }

        //TODO ideally this would not work as the map has not defined the key to be out
        map.getExisting(1f)
        subMap.getExisting(1f)
        nullableKeyMap.getExisting(1)
        nullableValueMap.getExisting(1f)
        nullableKeyValueMap.getExisting(1)
        readOnlyNullableKeyValueMap.getExisting(1f)
        starMap.getExisting(1)

        map = map.getExisting(1f) { }
        subMap = subMap.getExisting(1f) { }
        nullableKeyMap = nullableKeyMap.getExisting(1) { }
        nullableValueMap = nullableValueMap.getExisting(1f) { }
        nullableKeyValueMap = nullableKeyValueMap.getExisting(1) { }
        readOnlyNullableKeyValueMap = readOnlyNullableKeyValueMap.getExisting(1f) { }
        starMap = starMap.getExisting(1) { }
        starMap = starMap.getExisting("a") { }
    }
}
