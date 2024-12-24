package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.*
import kotlin.jvm.JvmName

class MapExpectationsSpec : ch.tutteli.atrium.specs.integration.MapExpectationsSpec(
    fun1<Map<out String, *>, String>(Companion::toContainKey),
    fun1<Map<out String?, *>, String?>(Companion::toContainKey).withNullableSuffix(),
    fun1<Map<out String, *>, String>(Companion::notToContainKey),
    fun1<Map<out String?, *>, String?>(Companion::notToContainKey).withNullableSuffix(),
    feature1<Map<out String, Int>, String, Int>(Expect<Map<out String, Int>>::getExisting),
    fun2<Map<out String, Int>, String, Expect<Int>.() -> Unit>(Companion::getExisting),
    feature1<Map<out String?, Int?>, String?, Int?>(Expect<Map<out String?, Int?>>::getExisting).withNullableSuffix(),
    fun2<Map<out String?, Int?>, String?, Expect<Int?>.() -> Unit>(Companion::getExisting).withNullableSuffix(),
    "toBe ${empty::class.simpleName}" to Companion::toBeEmpty,
    "notToBe ${empty::class.simpleName}" to Companion::notToBeEmpty,
    fun1<Map<out String, Int>, Expect<Set<String>>.() -> Unit>(Expect<Map<out String, Int>>::keys),
    property<Map<out String, Int>, Set<String>>(Expect<Map<out String, Int>>::keys),
    property<Map<out String, Int>, Collection<Int>>(Expect<Map<out String, Int>>::values),
    fun1<Map<out String, Int>, Expect<Collection<Int>>.() -> Unit>(Expect<Map<out String, Int>>::values)
) {
    companion object {
        private fun toContainKey(expect: Expect<Map<out String, *>>, key: String) =
            expect toContainKey key

        @JvmName("toContainKeyNullable")
        private fun toContainKey(expect: Expect<Map<out String?, *>>, key: String?) =
            expect toContainKey key

        private fun notToContainKey(expect: Expect<Map<out String, *>>, key: String) =
            expect notToContainKey key

        @JvmName("notToContainKeyNullable")
        private fun notToContainKey(expect: Expect<Map<out String?, *>>, key: String?) =
            expect notToContainKey key

        private fun toBeEmpty(expect: Expect<Map<*, *>>) = expect toBe empty

        private fun notToBeEmpty(expect: Expect<Map<*, *>>) = expect notToBe empty

        private fun getExisting(
            expect: Expect<Map<out String, Int>>,
            key: String,
            assertionCreator: Expect<Int>.() -> Unit
        ): Expect<Map<out String, Int>> = expect getExisting key(key) { assertionCreator() }

        @JvmName("getExistingNullable")
        private fun getExisting(
            expect: Expect<Map<out String?, Int?>>,
            key: String?,
            assertionCreator: Expect<Int?>.() -> Unit
        ): Expect<Map<out String?, Int?>> = expect getExisting key(key) { assertionCreator() }

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

        map toContainKey 1
        map toContainKey 1f
        subMap toContainKey 1
        subMap toContainKey 1f
        nullableKeyMap toContainKey 1
        nullableKeyMap toContainKey 1f
        readOnlyNullableKeyValueMap toContainKey 1
        readOnlyNullableKeyValueMap toContainKey 1f

        map notToContainKey 1
        map notToContainKey 1f
        subMap notToContainKey 1
        subMap notToContainKey 1f
        nullableKeyMap notToContainKey 1
        nullableKeyMap notToContainKey 1f
        readOnlyNullableKeyValueMap notToContainKey 1
        readOnlyNullableKeyValueMap notToContainKey 1f

        map = map toBe empty
        subMap = subMap toBe empty
        nullableKeyMap = nullableKeyMap toBe empty
        nullableValueMap = nullableValueMap toBe empty
        nullableKeyValueMap = nullableKeyValueMap toBe empty
        readOnlyNullableKeyValueMap = readOnlyNullableKeyValueMap toBe empty
        starMap = starMap toBe empty

        map = map notToBe empty
        subMap = subMap notToBe empty
        nullableKeyMap = nullableKeyMap notToBe empty
        nullableValueMap = nullableValueMap notToBe empty
        nullableKeyValueMap = nullableKeyValueMap notToBe empty
        readOnlyNullableKeyValueMap = readOnlyNullableKeyValueMap notToBe empty
        starMap = starMap notToBe empty

        map.keys
        subMap.keys
        nullableKeyMap.keys
        nullableValueMap.keys
        nullableKeyValueMap.keys
        readOnlyNullableKeyValueMap.keys
        starMap.keys

        map = map keys { }
        subMap = subMap keys { }
        nullableKeyMap = nullableKeyMap keys { }
        nullableValueMap = nullableValueMap keys { }
        nullableKeyValueMap = nullableKeyValueMap keys { }
        readOnlyNullableKeyValueMap = readOnlyNullableKeyValueMap keys { }
        starMap = starMap keys { }

        map.values
        subMap.values
        nullableKeyMap.values
        nullableValueMap.values
        nullableKeyValueMap.values
        readOnlyNullableKeyValueMap.values
        starMap.values

        map = map values { }
        subMap = subMap values { }
        nullableKeyMap = nullableKeyMap values { }
        nullableValueMap = nullableValueMap values { }
        nullableKeyValueMap = nullableKeyValueMap values { }
        readOnlyNullableKeyValueMap = readOnlyNullableKeyValueMap values { }
        starMap = starMap values { }

        //TODO ideally this would not work as the map has not defined the key to be out
        map getExisting 1f
        subMap getExisting 1f
        nullableKeyMap getExisting 1
        nullableValueMap getExisting 1f
        nullableKeyValueMap getExisting 1
        readOnlyNullableKeyValueMap getExisting 1f
        starMap getExisting 1

        map = map getExisting key(1f) { }
        subMap = subMap getExisting key(1f) { }
        nullableKeyMap = nullableKeyMap getExisting key(1) { }
        nullableValueMap = nullableValueMap getExisting key(1f) { }
        nullableKeyValueMap = nullableKeyValueMap getExisting key(1) { }
        readOnlyNullableKeyValueMap = readOnlyNullableKeyValueMap getExisting key(1f) { }
        starMap = starMap getExisting key(1) { }
    }
}
