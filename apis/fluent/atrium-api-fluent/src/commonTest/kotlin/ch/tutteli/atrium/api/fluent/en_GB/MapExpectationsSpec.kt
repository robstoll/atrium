package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.utils.mapArguments
import ch.tutteli.atrium.specs.*
import kotlin.jvm.JvmName

class MapExpectationsSpec : ch.tutteli.atrium.specs.integration.MapExpectationsSpec(
    fun1(Expect<Map<out String, *>>::toContainKey),
    fun1(Expect<Map<out String?, *>>::toContainKey).withNullableSuffix(),
    fun1(Expect<Map<out String, *>>::notToContainKey),
    fun1(Expect<Map<out String?, *>>::notToContainKey).withNullableSuffix(),
    feature1<Map<out String, Int>, String, Int>(Expect<Map<out String, Int>>::getExisting),
    fun2<Map<out String, Int>, String, Expect<Int>.() -> Unit>(Expect<Map<out String, Int>>::getExisting),
    feature1<Map<out String?, Int?>, String?, Int?>(Expect<Map<out String?, Int?>>::getExisting).withNullableSuffix(),
    fun2<Map<out String?, Int?>, String?, Expect<Int?>.() -> Unit>(Expect<Map<out String?, Int?>>::getExisting).withNullableSuffix(),
    fun0(Expect<Map<*, *>>::toBeEmpty),
    fun0(Expect<Map<*, *>>::notToBeEmpty),
    fun1<Map<out String, Int>, Expect<Set<String>>.() -> Unit>(Expect<Map<out String, Int>>::keys),
    property<Map<out String, Int>, Set<String>>(Expect<Map<out String, Int>>::keys),
    property<Map<out String, Int>, Collection<Int>>(Expect<Map<out String, Int>>::values),
    fun1<Map<out String, Int>, Expect<Collection<Int>>.() -> Unit>(Expect<Map<out String, Int>>::values)
) {

    companion object {
        private fun toContain(
            expect: Expect<Map<out String, Int>>,
            keyValue: Pair<String, Expect<Int>.() -> Unit>,
            otherKeyValues: Array<out Pair<String, Expect<Int>.() -> Unit>>
        ) = mapArguments<Pair<String, Expect<Int>.() -> Unit>>(keyValue, otherKeyValues).to { KeyValue(it.first, it.second) }.let { (first, others) ->
            expect.toContain(first, *others)
        }

        @JvmName("toContainNullable")
        private fun toContain(
            expect: Expect<Map<out String?, Int?>>,
            keyValue: Pair<String?, (Expect<Int>.() -> Unit)?>,
            otherKeyValues: Array<out Pair<String?, (Expect<Int>.() -> Unit)?>>
        ) = mapArguments(keyValue, otherKeyValues).to { KeyValue(it.first, it.second) }.let { (first, others) ->
            expect.toContain(first, *others)
        }

    }

    @Suppress("unused")
    private fun ambiguityTest() {
        var map: Expect<Map<Number, CharSequence>> = notImplemented()
        var subMap: Expect<LinkedHashMap<out Number, String>> = notImplemented()
        var nullableKeyMap: Expect<Map<Number?, CharSequence>> = notImplemented()
        var nullableValueMap: Expect<Map<Number, CharSequence?>> = notImplemented()
        var nullableKeyValueMap: Expect<Map<Number?, CharSequence?>> = notImplemented()
        var readOnlyNullableKeyValueMap: Expect<Map<out Number?, CharSequence?>> = notImplemented()
        var starMap: Expect<Map<*, *>> = notImplemented()

        map.toContainKey(1)
        map.toContainKey(1f)
        subMap.toContainKey(1)
        subMap.toContainKey(1f)
        nullableKeyMap.toContainKey(1)
        nullableKeyMap.toContainKey(1f)
        readOnlyNullableKeyValueMap.toContainKey(1)
        readOnlyNullableKeyValueMap.toContainKey(1f)

        map.notToContainKey(1)
        map.notToContainKey(1f)
        subMap.notToContainKey(1)
        subMap.notToContainKey(1f)
        nullableKeyMap.notToContainKey(1)
        nullableKeyMap.notToContainKey(1f)
        readOnlyNullableKeyValueMap.notToContainKey(1)
        readOnlyNullableKeyValueMap.notToContainKey(1f)

        map = map.toBeEmpty()
        subMap = subMap.toBeEmpty()
        nullableKeyMap = nullableKeyMap.toBeEmpty()
        nullableValueMap = nullableValueMap.toBeEmpty()
        nullableKeyValueMap = nullableKeyValueMap.toBeEmpty()
        readOnlyNullableKeyValueMap = readOnlyNullableKeyValueMap.toBeEmpty()
        starMap = starMap.toBeEmpty()

        map = map.notToBeEmpty()
        subMap = subMap.notToBeEmpty()
        nullableKeyMap = nullableKeyMap.notToBeEmpty()
        nullableValueMap = nullableValueMap.notToBeEmpty()
        nullableKeyValueMap = nullableKeyValueMap.notToBeEmpty()
        readOnlyNullableKeyValueMap = readOnlyNullableKeyValueMap.notToBeEmpty()
        starMap = starMap.notToBeEmpty()

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
