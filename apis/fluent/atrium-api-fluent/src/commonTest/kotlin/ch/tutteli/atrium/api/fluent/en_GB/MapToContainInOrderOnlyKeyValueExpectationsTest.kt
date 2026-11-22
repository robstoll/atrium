package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.api.verbs.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.creating.iterablelike.contains.reporting.InOrderOnlyReportingOptions
import ch.tutteli.atrium.logic.utils.mapArguments
import ch.tutteli.atrium.specs.integration.AbstractMapToContainInOrderOnlyKeyValueExpectationsTest
import ch.tutteli.atrium.specs.integration.MapLikeToContainFormatSpecBase.Companion.emptyInOrderOnlyReportOptions
import ch.tutteli.atrium.specs.withNullableSuffix
import kotlin.test.Test

class MapToContainInOrderOnlyKeyValueExpectationsTest :
    AbstractMapToContainInOrderOnlyKeyValueExpectationsTest(
        functionDescription to Companion::toContainKeyValues,
        (functionDescription to Companion::toContainKeyValuesNullable).withNullableSuffix()
    ) {
    companion object : MapToContainSpecBase() {
        val functionDescription = "$toContain.$inOrder.$only.$keyValue/$keyValues"

        private fun toContainKeyValues(
            expect: Expect<Map<out String, Int>>,
            a: Pair<String, Expect<Int>.() -> Unit>,
            aX: Array<out Pair<String, Expect<Int>.() -> Unit>>,
            report: InOrderOnlyReportingOptions.() -> Unit
        ) = mapArguments(a, aX).to { KeyValue(it.first, it.second) }.let { (first, others) ->
            if (report === emptyInOrderOnlyReportOptions) {
                if (others.isEmpty()) expect.toContain.inOrder.only.entry(first)
                else expect.toContain.inOrder.only.entries(first, *others)
            } else expect.toContain.inOrder.only.entries(first, *others, report = report)
        }

        private fun toContainKeyValuesNullable(
            expect: Expect<Map<out String?, Int?>>,
            a: Pair<String?, (Expect<Int>.() -> Unit)?>,
            aX: Array<out Pair<String?, (Expect<Int>.() -> Unit)?>>,
            report: InOrderOnlyReportingOptions.() -> Unit
        ) = mapArguments(a, aX).to { KeyValue(it.first, it.second) }.let { (first, others) ->
            if (report === emptyInOrderOnlyReportOptions) {
                if (others.isEmpty()) expect.toContain.inOrder.only.entry(first)
                else expect.toContain.inOrder.only.entries(first, *others)
            } else expect.toContain.inOrder.only.entries(first, *others, report = report)
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

        map = map.toContain.inOrder.only.entry(KeyValue(1) { toEqual("a") })
        subMap = subMap.toContain.inOrder.only.entry(KeyValue(1) { toEqual("a") })
        nKeyMap = nKeyMap.toContain.inOrder.only.entry(KeyValue(1) { toEqual("a") })
        nValueMap = nValueMap.toContain.inOrder.only.entry(KeyValue(1) { toEqual("a") })
        nKeyValueMap = nKeyValueMap.toContain.inOrder.only.entry(KeyValue(1) { toEqual("a") })
        ronKeyValueMap = ronKeyValueMap.toContain.inOrder.only.entry(KeyValue(1) { toEqual("a") })
        starMap = starMap.toContain.inOrder.only.entry(KeyValue(1) { toEqual("a") })

        map = map.toContain.inOrder.only.entries(KeyValue(1) { toEqual("a") })
        subMap = subMap.toContain.inOrder.only.entries(KeyValue(1) { toEqual("a") })
        nKeyMap = nKeyMap.toContain.inOrder.only.entries(KeyValue(1) { toEqual("a") })
        nValueMap = nValueMap.toContain.inOrder.only.entries(KeyValue(1) { toEqual("a") })
        nKeyValueMap = nKeyValueMap.toContain.inOrder.only.entries(KeyValue(1) { toEqual("a") })
        ronKeyValueMap = ronKeyValueMap.toContain.inOrder.only.entries(KeyValue(1) { toEqual("a") })
        starMap = starMap.toContain.inOrder.only.entries(KeyValue(1) { toEqual("a") })

        map = map.toContain.inOrder.only.entries(KeyValue(1) { toEqual("a") }, report = {})
        subMap = subMap.toContain.inOrder.only.entries(KeyValue(1) { toEqual("a") }, report = {})
        nKeyMap = nKeyMap.toContain.inOrder.only.entries(KeyValue(1) { toEqual("a") }, report = {})
        nValueMap = nValueMap.toContain.inOrder.only.entries(KeyValue(1) { toEqual("a") }, report = {})
        nKeyValueMap = nKeyValueMap.toContain.inOrder.only.entries(KeyValue(1) { toEqual("a") }, report = {})
        ronKeyValueMap = ronKeyValueMap.toContain.inOrder.only.entries(KeyValue(1) { toEqual("a") }, report = {})
        starMap = starMap.toContain.inOrder.only.entries(KeyValue(1) { toEqual("a") }, report = {})

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

        mapTwoEntries = mapTwoEntries.toContain.inOrder.only.entries(
            KeyValue(1 as Number) { toEqual("a") },
            KeyValue(1.2) { toEqual("b") }
        )
        subMapTwoEntries = subMapTwoEntries.toContain.inOrder.only.entries(
            KeyValue(1 as Number) { toEqual("a") },
            KeyValue(1.2) { toEqual("b") }
        )
        nKeyMapTwoEntries = nKeyMapTwoEntries.toContain.inOrder.only.entries(
            KeyValue(1 as Number) { toEqual("a") },
            KeyValue(1.2) { toEqual("b") }
        )
        nValueMapTwoEntries = nValueMapTwoEntries.toContain.inOrder.only.entries(
            KeyValue(1 as Number) { toEqual("a") },
            KeyValue(1.2) { toEqual("b") }
        )
        nKeyValueMapTwoEntries = nKeyValueMapTwoEntries.toContain.inOrder.only.entries(
            KeyValue(1 as Number) { toEqual("a") },
            KeyValue(1.2) { toEqual("b") }
        )
        ronKeyValueMapTwoEntries = ronKeyValueMapTwoEntries.toContain.inOrder.only.entries(
            KeyValue(1 as Number) { toEqual("a") },
            KeyValue(1.2) { toEqual("b") }
        )
        starMapTwoEntries = starMapTwoEntries.toContain.inOrder.only.entries(
            KeyValue(1 as Number) { toEqual("a") },
            KeyValue(1.2) { toEqual("b") }
        )

        var nKeyMapNullKey: Expect<Map<Number?, CharSequence>> = expect(mapOf(null to "a"))
        var nKeyValueMapNullKey: Expect<Map<Number?, CharSequence?>> = expect(mapOf(null to "a"))
        var ronKeyValueMapNullKey: Expect<Map<out Number?, CharSequence?>> = expect(mapOf(null to "a"))
        var starMapNullKey: Expect<Map<*, *>> = expect(mapOf(null to "a"))

        nKeyMapNullKey = nKeyMapNullKey.toContain.inOrder.only.entry(KeyValue(null) { toEqual("a") })
        nKeyValueMapNullKey = nKeyValueMapNullKey.toContain.inOrder.only.entry(KeyValue(null) { toEqual("a") })
        ronKeyValueMapNullKey = ronKeyValueMapNullKey.toContain.inOrder.only.entry(KeyValue(null) { toEqual("a") })
        starMapNullKey = starMapNullKey.toContain.inOrder.only.entry(KeyValue(null) { toEqual("a") })

        nKeyMapNullKey = nKeyMapNullKey.toContain.inOrder.only.entries(KeyValue(null) { toEqual("a") })
        nKeyValueMapNullKey = nKeyValueMapNullKey.toContain.inOrder.only.entries(KeyValue(null) { toEqual("a") })
        ronKeyValueMapNullKey = ronKeyValueMapNullKey.toContain.inOrder.only.entries(KeyValue(null) { toEqual("a") })
        starMapNullKey = starMapNullKey.toContain.inOrder.only.entries(KeyValue(null) { toEqual("a") })

        nKeyMapNullKey = nKeyMapNullKey.toContain.inOrder.only.entries(
            KeyValue(null) { toEqual("a") },
            report = {}
        )
        nKeyValueMapNullKey = nKeyValueMapNullKey.toContain.inOrder.only.entries(
            KeyValue(null) { toEqual("a") },
            report = {}
        )
        ronKeyValueMapNullKey = ronKeyValueMapNullKey.toContain.inOrder.only.entries(
            KeyValue(null) { toEqual("a") },
            report = {}
        )
        starMapNullKey = starMapNullKey.toContain.inOrder.only.entries(
            KeyValue(null) { toEqual("a") },
            report = {}
        )

        var nKeyValueMapNullEntry: Expect<Map<Number?, CharSequence?>> = expect(mapOf(null to null))
        var ronKeyValueMapNullEntry: Expect<Map<out Number?, CharSequence?>> = expect(mapOf(null to null))
        var starMapNullEntry: Expect<Map<*, *>> = expect(mapOf(null to null))

        nKeyValueMapNullEntry = nKeyValueMapNullEntry.toContain.inOrder.only.entry(KeyValue(null, null))
        ronKeyValueMapNullEntry = ronKeyValueMapNullEntry.toContain.inOrder.only.entry(KeyValue(null, null))
        starMapNullEntry = starMapNullEntry.toContain.inOrder.only.entry(KeyValue(null, null))

        var nValueMapNullValue: Expect<Map<Number, CharSequence?>> = expect(mapOf(1.2 to null))
        nValueMapNullValue = nValueMapNullValue.toContain.inOrder.only.entry(KeyValue(1.2, null))

        var nValueMapNullableEntries: Expect<Map<Number, CharSequence?>> =
            expect(mapOf<Number, CharSequence?>(1 to null, 1.2 to "a"))
        nValueMapNullableEntries = nValueMapNullableEntries.toContain.inOrder.only.entries(
            KeyValue(1, null),
            KeyValue(1.2) { toEqual("a") }
        )

        var nKeyValueMapNullableEntries: Expect<Map<Number?, CharSequence?>> =
            expect(mapOf<Number?, CharSequence?>(null to "a", 1 to null))
        var ronKeyValueMapNullableEntries: Expect<Map<out Number?, CharSequence?>> =
            expect(mapOf<Number?, CharSequence?>(null to "a", 1 to null))
        var starMapNullableEntries: Expect<Map<*, *>> = expect(mapOf(null to "a", 1 to null))

        nKeyValueMapNullableEntries = nKeyValueMapNullableEntries.toContain.inOrder.only.entries(
            KeyValue(null) { toEqual("a") },
            KeyValue(1, null)
        )
        ronKeyValueMapNullableEntries = ronKeyValueMapNullableEntries.toContain.inOrder.only.entries(
            KeyValue(null) { toEqual("a") },
            KeyValue(1, null)
        )
        starMapNullableEntries = starMapNullableEntries.toContain.inOrder.only.entries(
            KeyValue(null) { toEqual("a") },
            KeyValue(1, null)
        )

        var nKeyValueMapThreeNullableEntries: Expect<Map<Number?, CharSequence?>> =
            expect(mapOf<Number?, CharSequence?>(null to "a", 1 to null, 1.2 to "b"))
        var ronKeyValueMapThreeNullableEntries: Expect<Map<out Number?, CharSequence?>> =
            expect(mapOf<Number?, CharSequence?>(null to "a", 1 to null, 1.2 to "b"))
        var starMapThreeNullableEntries: Expect<Map<*, *>> =
            expect(mapOf(null to "a", 1 to null, 1.2 to "b"))

        nKeyValueMapThreeNullableEntries = nKeyValueMapThreeNullableEntries.toContain.inOrder.only.entries(
            KeyValue(null) { toEqual("a") },
            KeyValue(1, null),
            KeyValue(1.2) { toEqual("b") }
        )
        ronKeyValueMapThreeNullableEntries = ronKeyValueMapThreeNullableEntries.toContain.inOrder.only.entries(
            KeyValue(null) { toEqual("a") },
            KeyValue(1, null),
            KeyValue(1.2) { toEqual("b") }
        )
        starMapThreeNullableEntries = starMapThreeNullableEntries.toContain.inOrder.only.entries(
            KeyValue(null) { toEqual("a") },
            KeyValue(1, null),
            KeyValue(1.2) { toEqual("b") }
        )
    }
}
