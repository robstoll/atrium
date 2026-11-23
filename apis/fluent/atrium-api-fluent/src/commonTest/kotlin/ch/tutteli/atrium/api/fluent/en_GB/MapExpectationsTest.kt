package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.*
import kotlin.test.Test

class MapExpectationsTest : ch.tutteli.atrium.specs.integration.AbstractMapExpectationsTest(
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

    @Suppress("UNUSED_VALUE", "UNUSED_VARIABLE", "unused", "AssignedValueIsNeverRead")
    @Test
    fun ambiguityTest() {
        var map: Expect<Map<Number, CharSequence>> = expect(mapOf<Number, CharSequence>(1 to "a", 2f to "b"))
        var subMap: Expect<LinkedHashMap<out Number, String>> = expect(linkedMapOf<Number, String>(1 to "a", 2f to "b"))
        var nullableKeyMap: Expect<Map<Number?, CharSequence>> = expect(mapOf<Number?, CharSequence>(1 to "a", 2f to "b"))
        var nullableValueMap: Expect<Map<Number, CharSequence?>> = expect(mapOf<Number, CharSequence?>(1 to "a", 2f to "b"))
        var nullableKeyValueMap: Expect<Map<Number?, CharSequence?>> = expect(mapOf<Number?, CharSequence?>(1 to "a", 2f to "b"))
        var readOnlyNullableKeyValueMap: Expect<Map<out Number?, CharSequence?>> =
            expect(mapOf<Number?, CharSequence?>(1 to "a", 2f to "b"))
        var starMap: Expect<Map<*, *>> = expect(mapOf<Any?, Any?>(1 to "a", 2f to "b", "a" to "c"))

        map.toContainKey(1)
        map.toContainKey(2f)
        subMap.toContainKey(1)
        subMap.toContainKey(2f)
        nullableKeyMap.toContainKey(1)
        nullableKeyMap.toContainKey(2f)
        readOnlyNullableKeyValueMap.toContainKey(1)
        readOnlyNullableKeyValueMap.toContainKey(2f)

        map.notToContainKey(3)
        map.notToContainKey(3f)
        subMap.notToContainKey(3)
        subMap.notToContainKey(3f)
        nullableKeyMap.notToContainKey(3)
        nullableKeyMap.notToContainKey(3f)
        readOnlyNullableKeyValueMap.notToContainKey(3)
        readOnlyNullableKeyValueMap.notToContainKey(3f)

        map = expect(mapOf<Number, CharSequence>())
        subMap = expect(linkedMapOf<Number, String>())
        nullableKeyMap = expect(mapOf<Number?, CharSequence>())
        nullableValueMap = expect(mapOf<Number, CharSequence?>())
        nullableKeyValueMap = expect(mapOf<Number?, CharSequence?>())
        readOnlyNullableKeyValueMap = expect(mapOf<Number?, CharSequence?>())
        starMap = expect(mapOf<Any?, Any?>())

        map = map.toBeEmpty()
        subMap = subMap.toBeEmpty()
        nullableKeyMap = nullableKeyMap.toBeEmpty()
        nullableValueMap = nullableValueMap.toBeEmpty()
        nullableKeyValueMap = nullableKeyValueMap.toBeEmpty()
        readOnlyNullableKeyValueMap = readOnlyNullableKeyValueMap.toBeEmpty()
        starMap = starMap.toBeEmpty()

        map = expect(mapOf<Number, CharSequence>(1 to "a", 2f to "b"))
        subMap = expect(linkedMapOf<Number, String>(1 to "a", 2f to "b"))
        nullableKeyMap = expect(mapOf<Number?, CharSequence>(1 to "a", 2f to "b"))
        nullableValueMap = expect(mapOf<Number, CharSequence?>(1 to "a", 2f to "b"))
        nullableKeyValueMap = expect(mapOf<Number?, CharSequence?>(1 to "a", 2f to "b"))
        readOnlyNullableKeyValueMap = expect(mapOf<Number?, CharSequence?>(1 to "a", 2f to "b"))
        starMap = expect(mapOf<Any?, Any?>(1 to "a", 2f to "b", "a" to "c"))

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

        map = map.keys { toHaveSize(2) }
        subMap = subMap.keys { toHaveSize(2) }
        nullableKeyMap = nullableKeyMap.keys { toHaveSize(2) }
        nullableValueMap = nullableValueMap.keys { toHaveSize(2) }
        nullableKeyValueMap = nullableKeyValueMap.keys { toHaveSize(2) }
        readOnlyNullableKeyValueMap = readOnlyNullableKeyValueMap.keys { toHaveSize(2) }
        starMap = starMap.keys { toHaveSize(3) }

        map.values
        subMap.values
        nullableKeyMap.values
        nullableValueMap.values
        nullableKeyValueMap.values
        readOnlyNullableKeyValueMap.values
        starMap.values

        map = map.values { toHaveSize(2) }
        subMap = subMap.values { toHaveSize(2) }
        nullableKeyMap = nullableKeyMap.values { toHaveSize(2) }
        nullableValueMap = nullableValueMap.values { toHaveSize(2) }
        nullableKeyValueMap = nullableKeyValueMap.values { toHaveSize(2) }
        readOnlyNullableKeyValueMap = readOnlyNullableKeyValueMap.values { toHaveSize(2) }
        starMap = starMap.values { toHaveSize(3) }

        //TODO ideally this would not work as the map has not defined the key to be out
        map.getExisting(2f)
        subMap.getExisting(2f)
        nullableKeyMap.getExisting(1)
        nullableValueMap.getExisting(2f)
        nullableKeyValueMap.getExisting(1)
        readOnlyNullableKeyValueMap.getExisting(2f)
        starMap.getExisting(1)

        map = map.getExisting(2f) { toEqual("b") }
        subMap = subMap.getExisting(2f) { toEqual("b") }
        nullableKeyMap = nullableKeyMap.getExisting(1) { toEqual("a") }
        nullableValueMap = nullableValueMap.getExisting(2f) { toEqual("b") }
        nullableKeyValueMap = nullableKeyValueMap.getExisting(1) { toEqual("a") }
        readOnlyNullableKeyValueMap = readOnlyNullableKeyValueMap.getExisting(2f) { toEqual("b") }
        starMap = starMap.getExisting(1) { toEqual("a") }
        starMap = starMap.getExisting("a") { toEqual("c") }
    }
}
