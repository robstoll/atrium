package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.builders.utils.mapArguments
import ch.tutteli.atrium.specs.fun1
import ch.tutteli.atrium.specs.integration.mfun2
import ch.tutteli.atrium.specs.notImplemented
import ch.tutteli.atrium.specs.testutils.WithAsciiReporter
import ch.tutteli.atrium.specs.withNullableSuffix
import kotlin.jvm.JvmName

class MapAssertionsSpec : ch.tutteli.atrium.specs.integration.MapAssertionsSpec(
    mfun2<String, Int, Int>(Companion::contains),
    mfun2<String?, Int?, Int?>(Companion::contains).withNullableSuffix(),
    mfun2<String, Int, Expect<Int>.() -> Unit>(Companion::contains),
    mfun2<String?, Int?, (Expect<Int>.() -> Unit)?>(Companion::contains).withNullableSuffix(),
    fun1<Map<out String, *>, String>(Companion::containsKey),
    fun1<Map<out String?, *>, String?>(Companion::containsKey).withNullableSuffix(),
    fun1<Map<out String, *>, String>(Companion::containsNotKey),
    fun1<Map<out String?, *>, String?>(Companion::containsNotKey).withNullableSuffix(),
    "toBe ${empty::class.simpleName}" to Companion::isEmpty,
    "notToBe ${empty::class.simpleName}" to Companion::isNotEmpty
) {
    companion object : WithAsciiReporter() {

        private fun contains(
            expect: Expect<Map<out String, Int>>,
            pair: Pair<String, Int>,
            otherPairs: Array<out Pair<String, Int>>
        ): Expect<Map<out String, Int>> =
            if (otherPairs.isEmpty()) expect contains (pair.first to pair.second)
            else expect contains pairs(pair, *otherPairs)

        @JvmName("containsNullable")
        private fun contains(
            expect: Expect<Map<out String?, Int?>>,
            pair: Pair<String?, Int?>,
            otherPairs: Array<out Pair<String?, Int?>>
        ): Expect<Map<out String?, Int?>> =
            if (otherPairs.isEmpty()) expect contains (pair.first to pair.second)
            else expect contains pairs(pair, *otherPairs)

        @JvmName("containsKeyWithValueAssertions")
        private fun contains(
            expect: Expect<Map<out String, Int>>,
            keyValue: Pair<String, Expect<Int>.() -> Unit>,
            otherKeyValues: Array<out Pair<String, Expect<Int>.() -> Unit>>
        ): Expect<Map<out String, Int>> =
            if (otherKeyValues.isEmpty()) expect contains keyValue(keyValue.first, keyValue.second)
            else mapArguments(keyValue, otherKeyValues)
                .to { keyValue(it.first, it.second) }
                .let { (first, others) -> expect contains all(first, *others) }

        @JvmName("containsKeyWithNullableValueAssertions")
        private fun contains(
            expect: Expect<Map<out String?, Int?>>,
            keyValue: Pair<String?, (Expect<Int>.() -> Unit)?>,
            otherKeyValues: Array<out Pair<String?, (Expect<Int>.() -> Unit)?>>
        ): Expect<Map<out String?, Int?>> =
            if (otherKeyValues.isEmpty()) expect contains keyValue(keyValue.first, keyValue.second)
            else mapArguments(keyValue, otherKeyValues)
                .to { keyValue(it.first, it.second) }
                .let { (first, others) -> expect contains all(first, *others) }

        private fun containsKey(expect: Expect<Map<out String, *>>, key: String) =
            expect containsKey key

        @JvmName("containsKeyNullable")
        private fun containsKey(expect: Expect<Map<out String?, *>>, key: String?) =
            expect containsKey key

        private fun containsNotKey(expect: Expect<Map<out String, *>>, key: String) =
            expect containsNotKey key

        @JvmName("containsNotKeyNullable")
        private fun containsNotKey(expect: Expect<Map<out String?, *>>, key: String?) =
            expect containsNotKey key

        private fun isEmpty(expect: Expect<Map<*, *>>) = expect toBe empty

        private fun isNotEmpty(expect: Expect<Map<*, *>>) = expect notToBe empty

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

        map contains (1 to "a")
        map contains pairs(1 to "a", 2 to "b")
        map contains keyValue(1) {}
        map contains all(keyValue(1) {}, keyValue(2) {})
        map contains pairs(1.0 to StringBuilder("a"))
        map contains pairs(12f to "a", 2L to StringBuilder("b"))
        map contains keyValue(1) {}
        map contains all(keyValue(1) {}, keyValue(2) {})
        subMap contains (1 to "a")
        subMap contains pairs(1 to "a", 2 to "b")
        subMap contains keyValue(1) {}
        subMap contains all(keyValue(1) {}, keyValue(2) {})
        subMap contains (1.0 to StringBuilder("a"))
        subMap contains pairs(12f to "a", 2L to StringBuilder("b"))
        subMap contains keyValue(1) {}
        subMap contains all(keyValue(1) {}, keyValue(2) {})

        nullableKeyMap contains (1 to "a")
        nullableKeyMap contains pairs(1 to "a", 2 to "b")
        nullableKeyMap contains keyValue(1) {}
        nullableKeyMap contains all(keyValue(1) {}, keyValue(2) {})
        nullableKeyMap contains (null to "a")
        nullableKeyMap contains pairs(null to "a", null to "b")
        nullableKeyMap contains pairs(null to "a", 2 to "b")
        nullableKeyMap contains (keyValue(null) {})
        nullableKeyMap contains all(keyValue(null) {}, keyValue(null) {})
        nullableKeyMap contains all(keyValue(null) {}, keyValue(2) {})

        nullableValueMap contains (1 to "a")
        nullableValueMap contains pairs(1 to "a", 2 to "b")
        nullableValueMap contains keyValue(1) {}
        nullableValueMap contains all(keyValue(1) {}, keyValue(2) {})
        nullableValueMap contains (1 to null)
        nullableValueMap contains pairs(1 to null, 2 to null)
        nullableValueMap contains pairs(1 to null, 2 to "a")
        nullableValueMap contains (keyValue(1, null))
        nullableValueMap contains all(keyValue(1, null), keyValue(2, null))
        nullableValueMap contains all(keyValue(1, null), keyValue(2) {})
        nullableKeyValueMap contains (1 to "a")
        nullableKeyValueMap contains pairs(1 to "a", 2 to "b")
        nullableKeyValueMap contains keyValue(1) {}
        nullableKeyValueMap contains all(keyValue(1) {}, keyValue(2) {})

        nullableKeyValueMap contains (null to "a")
        nullableKeyValueMap contains pairs(null to "a", null to "b")
        nullableKeyValueMap contains pairs(null to "a", 2 to "b")
        nullableKeyValueMap contains (keyValue(null) {})
        nullableKeyValueMap contains all(keyValue(null) {}, keyValue(null) {})
        nullableKeyValueMap contains all(keyValue(null) {}, keyValue(2) {})

        nullableKeyValueMap contains (1 to null)
        nullableKeyValueMap contains pairs(1 to null, 2 to null)
        nullableKeyValueMap contains pairs(1 to null, 2 to "a")
        nullableKeyValueMap contains (keyValue(1, null))
        nullableKeyValueMap contains all(keyValue(1, null), keyValue(2, null))
        nullableKeyValueMap contains all(keyValue(1, null), keyValue(2) {})

        nullableKeyValueMap contains (null to null)
        nullableKeyValueMap contains pairs(null to null, null to null)
        nullableKeyValueMap contains pairs(1 to null, null to "a")
        nullableKeyValueMap contains (keyValue(null, null))
        nullableKeyValueMap contains all(keyValue(null, null), keyValue(null, null))
        nullableKeyValueMap contains all(keyValue(1, null), keyValue(null) {})

        readOnlyNullableKeyValueMap contains (1 to "a")
        readOnlyNullableKeyValueMap contains pairs(1 to "a", 2 to "b")
        readOnlyNullableKeyValueMap contains keyValue(1) {}
        readOnlyNullableKeyValueMap contains all(keyValue(1) {}, keyValue(2) {})

        readOnlyNullableKeyValueMap contains (null to "a")
        readOnlyNullableKeyValueMap contains pairs(null to "a", null to "b")
        readOnlyNullableKeyValueMap contains pairs(null to "a", 2 to "b")
        readOnlyNullableKeyValueMap contains (keyValue(null) {})
        readOnlyNullableKeyValueMap contains all(keyValue(null) {}, keyValue(null) {})
        readOnlyNullableKeyValueMap contains all(keyValue(null) {}, keyValue(2) {})

        readOnlyNullableKeyValueMap contains (1 to null)
        readOnlyNullableKeyValueMap contains pairs(1 to null, 2 to null)
        readOnlyNullableKeyValueMap contains pairs(1 to null, 2 to "a")
        readOnlyNullableKeyValueMap contains (keyValue(1, null))
        readOnlyNullableKeyValueMap contains all(keyValue(1, null), keyValue(2, null))
        readOnlyNullableKeyValueMap contains all(keyValue(1, null), keyValue(2) {})

        readOnlyNullableKeyValueMap contains (null to null)
        readOnlyNullableKeyValueMap contains pairs(null to null, null to null)
        readOnlyNullableKeyValueMap contains pairs(1 to null, null to "a")
        readOnlyNullableKeyValueMap contains (keyValue(null, null))
        readOnlyNullableKeyValueMap contains all(keyValue(null, null), keyValue(null, null))
        readOnlyNullableKeyValueMap contains all(keyValue(1, null), keyValue(null) {})

        readOnlyNullableKeyValueMap contains (1 to "a")
        readOnlyNullableKeyValueMap contains pairs(1 to "a", 2 to "b")
        readOnlyNullableKeyValueMap contains keyValue(1) {}
        readOnlyNullableKeyValueMap contains all(keyValue(1) {}, keyValue(2) {})

        starMap contains (null to "a")
        starMap contains pairs(null to "a", null to "b")
        starMap contains pairs(null to "a", 2 to "b")
        starMap contains (keyValue(null) {})
        starMap contains all(keyValue(null) {}, keyValue(null) {})
        starMap contains all(keyValue(null) {}, keyValue(2) {})

        starMap contains (1 to null)
        starMap contains pairs(1 to null, 2 to null)
        starMap contains pairs(1 to null, 2 to "a")
        starMap contains (keyValue(1, null))
        starMap contains all(keyValue(1, null), keyValue(2, null))
        starMap contains all(keyValue(1, null), keyValue(2) {})

        starMap contains (null to null)
        starMap contains pairs(null to null, null to null)
        starMap contains pairs(1 to null, null to "a")
        starMap contains (keyValue(null, null))
        starMap contains all(keyValue(null, null), keyValue(null, null))
        starMap contains all(keyValue(1, null), keyValue(null) {})

        map containsKey 1
        map containsKey 1f
        subMap containsKey 1
        subMap containsKey 1f
        nullableKeyMap containsKey 1
        nullableKeyMap containsKey 1f
        readOnlyNullableKeyValueMap containsKey 1
        readOnlyNullableKeyValueMap containsKey 1f

        map containsNotKey 1
        map containsNotKey 1f
        subMap containsNotKey 1
        subMap containsNotKey 1f
        nullableKeyMap containsNotKey 1
        nullableKeyMap containsNotKey 1f
        readOnlyNullableKeyValueMap containsNotKey 1
        readOnlyNullableKeyValueMap containsNotKey 1f


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

    }
}
