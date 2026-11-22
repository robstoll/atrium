package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.api.verbs.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.utils.mapArguments
import ch.tutteli.atrium.specs.integration.AbstractMapToContainInAnyOrderKeyValueExpectationsTest
import ch.tutteli.atrium.specs.integration.mfun2
import ch.tutteli.atrium.specs.withNullableSuffix
import kotlin.jvm.JvmName
import kotlin.test.Test

class MapToContainInAnyOrderKeyValueExpectationsShortcutTest :
    AbstractMapToContainInAnyOrderKeyValueExpectationsTest(
        mfun2<String, Int, Expect<Int>.() -> Unit>(Companion::toContain),
        mfun2<String?, Int?, (Expect<Int>.() -> Unit)?>(Companion::toContain).withNullableSuffix()
    ) {
    companion object {
        private fun toContain(
            expect: Expect<Map<out String, Int>>,
            KeyValue: Pair<String, Expect<Int>.() -> Unit>,
            otherKeyValues: Array<out Pair<String, Expect<Int>.() -> Unit>>
        ): Expect<Map<out String, Int>> =
            mapArguments(KeyValue, otherKeyValues).to { KeyValue(it.first, it.second) }.let { (first, others) ->
                expect.toContain(first, *others)
            }

        @JvmName("toContainNullable")
        private fun toContain(
            expect: Expect<Map<out String?, Int?>>,
            KeyValue: Pair<String?, (Expect<Int>.() -> Unit)?>,
            otherKeyValues: Array<out Pair<String?, (Expect<Int>.() -> Unit)?>>
        ): Expect<Map<out String?, Int?>> =
            mapArguments(KeyValue, otherKeyValues).to { KeyValue(it.first, it.second) }.let { (first, others) ->
                expect.toContain(first, *others)
            }
    }

    @Suppress("AssignedValueIsNeverRead", "UNUSED_VARIABLE", "UNUSED_VALUE", "unused")
    @Test
    fun ambiguityTest() {
        var map: Expect<Map<Number, CharSequence>> = expect(mapOf(1 to "a"))
        var subMap: Expect<LinkedHashMap<out Number, String>> = expect(linkedMapOf(1 to "a"))
        var nKeyMap: Expect<Map<Number?, CharSequence>> = expect(mapOf(1 to "a"))
        var nValueMap: Expect<Map<Number, CharSequence?>> = expect(mapOf(1 to "a"))
        var nKeyValueMap: Expect<Map<Number?, CharSequence?>> = expect(mapOf(1 to "a"))
        var ronKeyValueMap: Expect<Map<out Number?, CharSequence?>> = expect(mapOf(1 to "a"))
        var starMap: Expect<Map<*, *>> = expect(mapOf(1 to "a"))

        map = map.toContain(KeyValue(1) { toEqual("a") })
        subMap = subMap.toContain(KeyValue(1) { toEqual("a") })
        nKeyMap = nKeyMap.toContain(KeyValue(1) { toEqual("a") })
        nValueMap = nValueMap.toContain(KeyValue(1) { toEqual("a") })
        nKeyValueMap = nKeyValueMap.toContain(KeyValue(1) { toEqual("a") })
        ronKeyValueMap = ronKeyValueMap.toContain(KeyValue(1) { toEqual("a") })
        starMap = starMap.toContain(KeyValue(1) { toEqual("a") })

        var mapTwoEntries: Expect<Map<Number, CharSequence>> = expect(mapOf(1 to "a", 1.2 to "b"))
        var subMapTwoEntries: Expect<LinkedHashMap<out Number, String>> =
            expect(linkedMapOf<Number, String>(1 to "a", 1.2 to "b"))
        var nKeyMapTwoEntries: Expect<Map<Number?, CharSequence>> = expect(mapOf(1 to "a", 1.2 to "b"))
        var nValueMapTwoEntries: Expect<Map<Number, CharSequence?>> = expect(mapOf(1 to "a", 1.2 to "b"))
        var nKeyValueMapTwoEntries: Expect<Map<Number?, CharSequence?>> =
            expect(mapOf<Number?, CharSequence?>(1 to "a", 1.2 to "b"))
        var ronKeyValueMapTwoEntries: Expect<Map<out Number?, CharSequence?>> =
            expect(mapOf<Number?, CharSequence?>(1 to "a", 1.2 to "b"))
        var starMapTwoEntries: Expect<Map<*, *>> = expect(mapOf(1 to "a", 1.2 to "b"))

        mapTwoEntries = mapTwoEntries.toContain(
            KeyValue(1 as Number) { toEqual("a") },
            KeyValue(1.2) { toEqual("b") }
        )
        subMapTwoEntries = subMapTwoEntries.toContain(
            KeyValue(1 as Number) { toEqual("a") },
            KeyValue(1.2) { toEqual("b") }
        )
        nKeyMapTwoEntries = nKeyMapTwoEntries.toContain(
            KeyValue(1 as Number) { toEqual("a") },
            KeyValue(1.2) { toEqual("b") }
        )
        nValueMapTwoEntries = nValueMapTwoEntries.toContain(
            KeyValue(1 as Number) { toEqual("a") },
            KeyValue(1.2) { toEqual("b") }
        )
        nKeyValueMapTwoEntries = nKeyValueMapTwoEntries.toContain(
            KeyValue(1 as Number) { toEqual("a") },
            KeyValue(1.2) { toEqual("b") }
        )
        ronKeyValueMapTwoEntries = ronKeyValueMapTwoEntries.toContain(
            KeyValue(1 as Number) { toEqual("a") },
            KeyValue(1.2) { toEqual("b") }
        )
        starMapTwoEntries = starMapTwoEntries.toContain(
            KeyValue(1 as Number) { toEqual("a") },
            KeyValue(1.2) { toEqual("b") }
        )

        var nKeyMapNullKey: Expect<Map<Number?, CharSequence>> = expect(mapOf(null to "a"))
        var nKeyValueMapNullKey: Expect<Map<Number?, CharSequence?>> = expect(mapOf(null to "a"))
        var ronKeyValueMapNullKey: Expect<Map<out Number?, CharSequence?>> = expect(mapOf(null to "a"))
        var starMapNullKey: Expect<Map<*, *>> = expect(mapOf(null to "a"))

        nKeyMapNullKey = nKeyMapNullKey.toContain(KeyValue(null) { toEqual("a") })
        nKeyValueMapNullKey = nKeyValueMapNullKey.toContain(KeyValue(null) { toEqual("a") })
        ronKeyValueMapNullKey = ronKeyValueMapNullKey.toContain(KeyValue(null) { toEqual("a") })
        starMapNullKey = starMapNullKey.toContain(KeyValue(null) { toEqual("a") })

        var nKeyValueMapNullEntry: Expect<Map<Number?, CharSequence?>> = expect(mapOf(null to null))
        var ronKeyValueMapNullEntry: Expect<Map<out Number?, CharSequence?>> = expect(mapOf(null to null))
        var starMapNullEntry: Expect<Map<*, *>> = expect(mapOf(null to null))

        nKeyValueMapNullEntry = nKeyValueMapNullEntry.toContain(KeyValue(null, null))
        ronKeyValueMapNullEntry = ronKeyValueMapNullEntry.toContain(KeyValue(null, null))
        starMapNullEntry = starMapNullEntry.toContain(KeyValue(null, null))

        var nValueMapNullValue: Expect<Map<Number, CharSequence?>> = expect(mapOf(1.2 to null))
        nValueMapNullValue = nValueMapNullValue.toContain(KeyValue(1.2, null))

        var nValueMapNullableEntries: Expect<Map<Number, CharSequence?>> =
            expect(mapOf<Number, CharSequence?>(1 to null, 1.2 to "a"))
        nValueMapNullableEntries = nValueMapNullableEntries.toContain(
            KeyValue(1, null),
            KeyValue(1.2) { toEqual("a") }
        )

        var nKeyValueMapNullableEntries: Expect<Map<Number?, CharSequence?>> =
            expect(mapOf<Number?, CharSequence?>(null to "a", 1 to null))
        var ronKeyValueMapNullableEntries: Expect<Map<out Number?, CharSequence?>> =
            expect(mapOf<Number?, CharSequence?>(null to "a", 1 to null))
        var starMapNullableEntries: Expect<Map<*, *>> = expect(mapOf(null to "a", 1 to null))

        nKeyValueMapNullableEntries = nKeyValueMapNullableEntries.toContain(
            KeyValue(null) { toEqual("a") },
            KeyValue(1, null)
        )
        ronKeyValueMapNullableEntries = ronKeyValueMapNullableEntries.toContain(
            KeyValue(null) { toEqual("a") },
            KeyValue(1, null)
        )
        starMapNullableEntries = starMapNullableEntries.toContain(
            KeyValue(null) { toEqual("a") },
            KeyValue(1, null)
        )

        var nKeyValueMapThreeNullableEntries: Expect<Map<Number?, CharSequence?>> =
            expect(mapOf<Number?, CharSequence?>(null to "a", 1 to null, 1.2 to "b"))
        var ronKeyValueMapThreeNullableEntries: Expect<Map<out Number?, CharSequence?>> =
            expect(mapOf<Number?, CharSequence?>(null to "a", 1 to null, 1.2 to "b"))
        var starMapThreeNullableEntries: Expect<Map<*, *>> =
            expect(mapOf(null to "a", 1 to null, 1.2 to "b"))

        nKeyValueMapThreeNullableEntries = nKeyValueMapThreeNullableEntries.toContain(
            KeyValue(null) { toEqual("a") },
            KeyValue(1, null),
            KeyValue(1.2) { toEqual("b") }
        )
        ronKeyValueMapThreeNullableEntries = ronKeyValueMapThreeNullableEntries.toContain(
            KeyValue(null) { toEqual("a") },
            KeyValue(1, null),
            KeyValue(1.2) { toEqual("b") }
        )
        starMapThreeNullableEntries = starMapThreeNullableEntries.toContain(
            KeyValue(null) { toEqual("a") },
            KeyValue(1, null),
            KeyValue(1.2) { toEqual("b") }
        )
    }
}
