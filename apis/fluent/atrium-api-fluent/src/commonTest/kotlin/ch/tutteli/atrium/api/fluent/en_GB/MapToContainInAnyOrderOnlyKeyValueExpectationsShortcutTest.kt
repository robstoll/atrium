package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.api.verbs.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.utils.mapArguments
import ch.tutteli.atrium.specs.integration.AbstractMapToContainInAnyOrderOnlyKeyValueExpectationsTest
import ch.tutteli.atrium.specs.integration.mfun2
import ch.tutteli.atrium.specs.withNullableSuffix
import kotlin.test.Test

class MapToContainInAnyOrderOnlyKeyValueExpectationsShortcutTest :
    AbstractMapToContainInAnyOrderOnlyKeyValueExpectationsTest(
        mfun2(Companion::toContainOnly),
        mfun2(Companion::toContainOnlyNullable).withNullableSuffix()
    ) {
    companion object {
        private fun toContainOnly(
            expect: Expect<Map<out String, Int>>,
            a: Pair<String, Expect<Int>.() -> Unit>,
            aX: Array<out Pair<String, Expect<Int>.() -> Unit>>
        ): Expect<Map<out String, Int>> =
            mapArguments(a, aX).to { KeyValue(it.first, it.second) }.let { (first, others) ->
                expect.toContainOnly(first, *others)
            }

        private fun toContainOnlyNullable(
            expect: Expect<Map<out String?, Int?>>,
            a: Pair<String?, (Expect<Int>.() -> Unit)?>,
            aX: Array<out Pair<String?, (Expect<Int>.() -> Unit)?>>
        ): Expect<Map<out String?, Int?>> =
            mapArguments(a, aX).to { KeyValue(it.first, it.second) }.let { (first, others) ->
                expect.toContainOnly(first, *others)
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

        map = map.toContainOnly(KeyValue(1) { toEqual("a") })
        subMap = subMap.toContainOnly(KeyValue(1) { toEqual("a") })
        nKeyMap = nKeyMap.toContainOnly(KeyValue(1) { toEqual("a") })
        nValueMap = nValueMap.toContainOnly(KeyValue(1) { toEqual("a") })
        nKeyValueMap = nKeyValueMap.toContainOnly(KeyValue(1) { toEqual("a") })
        ronKeyValueMap = ronKeyValueMap.toContainOnly(KeyValue(1) { toEqual("a") })
        starMap = starMap.toContainOnly(KeyValue(1) { toEqual("a") })

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

        mapTwoEntries = mapTwoEntries.toContainOnly(
            KeyValue(1 as Number) { toEqual("a") },
            KeyValue(1.2) { toEqual("b") }
        )
        subMapTwoEntries = subMapTwoEntries.toContainOnly(
            KeyValue(1 as Number) { toEqual("a") },
            KeyValue(1.2) { toEqual("b") }
        )
        nKeyMapTwoEntries = nKeyMapTwoEntries.toContainOnly(
            KeyValue(1 as Number) { toEqual("a") },
            KeyValue(1.2) { toEqual("b") }
        )
        nValueMapTwoEntries = nValueMapTwoEntries.toContainOnly(
            KeyValue(1 as Number) { toEqual("a") },
            KeyValue(1.2) { toEqual("b") }
        )
        nKeyValueMapTwoEntries = nKeyValueMapTwoEntries.toContainOnly(
            KeyValue(1 as Number) { toEqual("a") },
            KeyValue(1.2) { toEqual("b") }
        )
        ronKeyValueMapTwoEntries = ronKeyValueMapTwoEntries.toContainOnly(
            KeyValue(1 as Number) { toEqual("a") },
            KeyValue(1.2) { toEqual("b") }
        )
        starMapTwoEntries = starMapTwoEntries.toContainOnly(
            KeyValue(1 as Number) { toEqual("a") },
            KeyValue(1.2) { toEqual("b") }
        )

        var nKeyMapNullKey: Expect<Map<Number?, CharSequence>> = expect(mapOf(null to "a"))
        var nKeyValueMapNullKey: Expect<Map<Number?, CharSequence?>> = expect(mapOf(null to "a"))
        var ronKeyValueMapNullKey: Expect<Map<out Number?, CharSequence?>> = expect(mapOf(null to "a"))
        var starMapNullKey: Expect<Map<*, *>> = expect(mapOf(null to "a"))

        nKeyMapNullKey = nKeyMapNullKey.toContainOnly(KeyValue(null) { toEqual("a") })
        nKeyValueMapNullKey = nKeyValueMapNullKey.toContainOnly(KeyValue(null) { toEqual("a") })
        ronKeyValueMapNullKey = ronKeyValueMapNullKey.toContainOnly(KeyValue(null) { toEqual("a") })
        starMapNullKey = starMapNullKey.toContainOnly(KeyValue(null) { toEqual("a") })

        var nKeyValueMapNullEntry: Expect<Map<Number?, CharSequence?>> = expect(mapOf(null to null))
        var ronKeyValueMapNullEntry: Expect<Map<out Number?, CharSequence?>> = expect(mapOf(null to null))
        var starMapNullEntry: Expect<Map<*, *>> = expect(mapOf(null to null))

        nKeyValueMapNullEntry = nKeyValueMapNullEntry.toContainOnly(KeyValue(null, null))
        ronKeyValueMapNullEntry = ronKeyValueMapNullEntry.toContainOnly(KeyValue(null, null))
        starMapNullEntry = starMapNullEntry.toContainOnly(KeyValue(null, null))

        var nValueMapNullValue: Expect<Map<Number, CharSequence?>> = expect(mapOf(1.2 to null))
        nValueMapNullValue = nValueMapNullValue.toContainOnly(KeyValue(1.2, null))

        var nValueMapNullableEntries: Expect<Map<Number, CharSequence?>> =
            expect(mapOf<Number, CharSequence?>(1 to null, 1.2 to "a"))
        nValueMapNullableEntries = nValueMapNullableEntries.toContainOnly(
            KeyValue(1, null),
            KeyValue(1.2) { toEqual("a") }
        )

        var nKeyValueMapNullableEntries: Expect<Map<Number?, CharSequence?>> =
            expect(mapOf<Number?, CharSequence?>(null to "a", 1 to null))
        var ronKeyValueMapNullableEntries: Expect<Map<out Number?, CharSequence?>> =
            expect(mapOf<Number?, CharSequence?>(null to "a", 1 to null))
        var starMapNullableEntries: Expect<Map<*, *>> = expect(mapOf(null to "a", 1 to null))

        nKeyValueMapNullableEntries = nKeyValueMapNullableEntries.toContainOnly(
            KeyValue(null) { toEqual("a") },
            KeyValue(1, null)
        )
        ronKeyValueMapNullableEntries = ronKeyValueMapNullableEntries.toContainOnly(
            KeyValue(null) { toEqual("a") },
            KeyValue(1, null)
        )
        starMapNullableEntries = starMapNullableEntries.toContainOnly(
            KeyValue(null) { toEqual("a") },
            KeyValue(1, null)
        )

        var nKeyValueMapThreeNullableEntries: Expect<Map<Number?, CharSequence?>> =
            expect(mapOf<Number?, CharSequence?>(null to "a", 1 to null, 1.2 to "b"))
        var ronKeyValueMapThreeNullableEntries: Expect<Map<out Number?, CharSequence?>> =
            expect(mapOf<Number?, CharSequence?>(null to "a", 1 to null, 1.2 to "b"))
        var starMapThreeNullableEntries: Expect<Map<*, *>> =
            expect(mapOf(null to "a", 1 to null, 1.2 to "b"))

        nKeyValueMapThreeNullableEntries = nKeyValueMapThreeNullableEntries.toContainOnly(
            KeyValue(null) { toEqual("a") },
            KeyValue(1, null),
            KeyValue(1.2) { toEqual("b") }
        )
        ronKeyValueMapThreeNullableEntries = ronKeyValueMapThreeNullableEntries.toContainOnly(
            KeyValue(null) { toEqual("a") },
            KeyValue(1, null),
            KeyValue(1.2) { toEqual("b") }
        )
        starMapThreeNullableEntries = starMapThreeNullableEntries.toContainOnly(
            KeyValue(null) { toEqual("a") },
            KeyValue(1, null),
            KeyValue(1.2) { toEqual("b") }
        )
    }
}
