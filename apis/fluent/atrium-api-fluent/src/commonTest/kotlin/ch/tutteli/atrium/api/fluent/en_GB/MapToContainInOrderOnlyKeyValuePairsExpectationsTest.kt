package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.api.verbs.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.creating.iterablelike.contains.reporting.InOrderOnlyReportingOptions
import ch.tutteli.atrium.specs.integration.AbstractMapToContainInOrderOnlyKeyValuePairsExpectationsTest
import ch.tutteli.atrium.specs.integration.MapLikeToContainFormatSpecBase.Companion.emptyInOrderOnlyReportOptions
import ch.tutteli.atrium.specs.withNullableSuffix
import kotlin.test.Test

class MapToContainInOrderOnlyKeyValuePairsExpectationsTest :
    AbstractMapToContainInOrderOnlyKeyValuePairsExpectationsTest(
        functionDescription to Companion::toContainKeyValuePairs,
        (functionDescription to Companion::toContainKeyValuePairsNullable).withNullableSuffix()
    ) {
    companion object : MapToContainSpecBase() {
        val functionDescription = "$toContain.$inOrder.$only.$keyValuePair/$keyValuePairs"

        private fun toContainKeyValuePairs(
            expect: Expect<Map<out String, Int>>,
            a: Pair<String, Int>,
            aX: Array<out Pair<String, Int>>,
            report: InOrderOnlyReportingOptions.() -> Unit
        ) =
            if (report === emptyInOrderOnlyReportOptions) {
                if (aX.isEmpty()) expect.toContain.inOrder.only.entry(a)
                else expect.toContain.inOrder.only.entries(a, *aX)
            } else expect.toContain.inOrder.only.entries(a, *aX, report = report)

        private fun toContainKeyValuePairsNullable(
            expect: Expect<Map<out String?, Int?>>,
            a: Pair<String?, Int?>,
            aX: Array<out Pair<String?, Int?>>,
            report: InOrderOnlyReportingOptions.() -> Unit
        ): Expect<Map<out String?, Int?>> =
            if (report === emptyInOrderOnlyReportOptions) {
                if (aX.isEmpty()) expect.toContain.inOrder.only.entry(a)
                else expect.toContain.inOrder.only.entries(a, *aX)
            } else expect.toContain.inOrder.only.entries(a, *aX, report = report)
    }

    @Suppress("AssignedValueIsNeverRead", "UNUSED_VARIABLE", "UNUSED_VALUE")
    @Test
    fun ambiguityTest() {
        var map: Expect<Map<Number, CharSequence>> = expect(mapOf<Number, CharSequence>(1 to "a"))
        var subMap: Expect<LinkedHashMap<out Number, String>> = expect(linkedMapOf<Number, String>(1 to "a"))
        var nKeyMap: Expect<Map<Number?, CharSequence>> = expect(mapOf<Number?, CharSequence>(1 to "a"))
        var nValueMap: Expect<Map<Number, CharSequence?>> = expect(mapOf<Number, CharSequence?>(1 to "a"))
        var nKeyValueMap: Expect<Map<Number?, CharSequence?>> = expect(mapOf<Number?, CharSequence?>(1 to "a"))
        var ronKeyValueMap: Expect<Map<out Number?, CharSequence?>> = expect(mapOf<Number?, CharSequence?>(1 to "a"))
        var starMap: Expect<Map<*, *>> = expect(mapOf<Number, CharSequence?>(1 to "a"))

        map = map.toContain.inOrder.only.entry(1 to "a")
        subMap = subMap.toContain.inOrder.only.entry(1 to "a")
        nKeyMap = nKeyMap.toContain.inOrder.only.entry(1 to "a")
        nValueMap = nValueMap.toContain.inOrder.only.entry(1 to "a")
        nKeyValueMap = nKeyValueMap.toContain.inOrder.only.entry(1 to "a")
        ronKeyValueMap = ronKeyValueMap.toContain.inOrder.only.entry(1 to "a")
        starMap = starMap.toContain.inOrder.only.entry(1 to "a")

        map = map.toContain.inOrder.only.entries(1 to "a")
        subMap = subMap.toContain.inOrder.only.entries(1 to "a")
        nKeyMap = nKeyMap.toContain.inOrder.only.entries(1 to "a")
        nValueMap = nValueMap.toContain.inOrder.only.entries(1 to "a")
        nKeyValueMap = nKeyValueMap.toContain.inOrder.only.entries(1 to "a")
        ronKeyValueMap = ronKeyValueMap.toContain.inOrder.only.entries(1 to "a")
        starMap = starMap.toContain.inOrder.only.entries(1 to "a")

        map = map.toContain.inOrder.only.entries(1 to "a", report = {})
        subMap = subMap.toContain.inOrder.only.entries(1 to "a", report = {})
        nKeyMap = nKeyMap.toContain.inOrder.only.entries(1 to "a", report = {})
        nValueMap = nValueMap.toContain.inOrder.only.entries(1 to "a", report = {})
        nKeyValueMap = nKeyValueMap.toContain.inOrder.only.entries(1 to "a", report = {})
        ronKeyValueMap = ronKeyValueMap.toContain.inOrder.only.entries(1 to "a", report = {})
        starMap = starMap.toContain.inOrder.only.entries(1 to "a", report = {})

        map = expect(mapOf<Number, CharSequence>(1 to "a", 1.2 to "b"))
        subMap = expect(linkedMapOf<Number, String>(1 to "a", 1.2 to "b"))
        nKeyMap = expect(mapOf<Number?, CharSequence>(1 to "a", 1.2 to "b"))
        nValueMap = expect(mapOf<Number, CharSequence?>(1 to "a", 1.2 to "b"))
        nKeyValueMap = expect(mapOf<Number?, CharSequence?>(1 to "a", 1.2 to "b"))
        ronKeyValueMap = expect(mapOf<Number?, CharSequence?>(1 to "a", 1.2 to "b"))
        starMap = expect(mapOf<Number?, CharSequence?>(1 to "a", 1.2 to "b"))

        map = map.toContain.inOrder.only.entries(
            1 as Number to "a",
            1.2 to "b"
        )
        subMap = subMap.toContain.inOrder.only.entries(
            1 as Number to "a",
            1.2 to "b"
        )
        nKeyMap = nKeyMap.toContain.inOrder.only.entries(
            1 as Number to "a",
            1.2 to "b"
        )
        nValueMap = nValueMap.toContain.inOrder.only.entries(
            1 as Number to "a",
            1.2 to "b"
        )
        nKeyValueMap = nKeyValueMap.toContain.inOrder.only.entries(
            1 as Number to "a",
            1.2 to "b"
        )
        ronKeyValueMap = ronKeyValueMap.toContain.inOrder.only.entries(
            1 as Number to "a",
            1.2 to "b"
        )
        starMap = starMap.toContain.inOrder.only.entries(
            1 as Number to "a",
            1.2 to "b"
        )

        nKeyMap = expect(mapOf<Number?, CharSequence>(null to "a"))
        nValueMap = expect(mapOf<Number, CharSequence?>(1.2 to null))
        nKeyValueMap = expect(mapOf<Number?, CharSequence?>(null to null))
        ronKeyValueMap = expect(mapOf<Number?, CharSequence?>(null to null))
        starMap = expect(mapOf<Number?, CharSequence?>(null to null))

        nKeyMap = nKeyMap.toContain.inOrder.only.entry(null to "a")
        nValueMap = nValueMap.toContain.inOrder.only.entry(1.2 to null)
        nKeyValueMap = nKeyValueMap.toContain.inOrder.only.entry(null to null)
        ronKeyValueMap = ronKeyValueMap.toContain.inOrder.only.entry(null to null)
        starMap = starMap.toContain.inOrder.only.entry(null to null)

        nKeyMap = expect(mapOf<Number?, CharSequence>(null to "a", 1 to "b"))
        nValueMap = expect(mapOf<Number, CharSequence?>(1 to null))
        nKeyValueMap = expect(mapOf<Number?, CharSequence?>(null to "a", 2 to null, 1 to null))
        ronKeyValueMap = expect(mapOf<Number?, CharSequence?>(null to "a", 2 to null, 1 to null))
        starMap = expect(mapOf<Number?, CharSequence?>(null to "a", 2 to null, 1 to null))

        nKeyMap = nKeyMap.toContain.inOrder.only.entries(null to "a", 1 to "b")
        nValueMap = nValueMap.toContain.inOrder.only.entries(1 to null)
        nKeyValueMap = nKeyValueMap.toContain.inOrder.only.entries(null to "a", 2 to null, 1 to null)
        ronKeyValueMap = ronKeyValueMap.toContain.inOrder.only.entries(null to "a", 2 to null, 1 to null)
        starMap = starMap.toContain.inOrder.only.entries(null to "a", 2 to null, 1 to null)

        nKeyMap = expect(mapOf<Number?, CharSequence>(null to "a", 1 to "b"))
        nValueMap = expect(mapOf<Number, CharSequence?>(1 to null))
        nKeyValueMap = expect(mapOf<Number?, CharSequence?>(null to "a", 2 to null, 1 to null))
        ronKeyValueMap = expect(mapOf<Number?, CharSequence?>(null to "a", 2 to null, 1 to null))
        starMap = expect(mapOf<Number?, CharSequence?>(null to "a", 2 to null, 1 to null))

        nKeyMap = nKeyMap.toContain.inOrder.only.entries(null to "a", 1 to "b", report = {})
        nValueMap = nValueMap.toContain.inOrder.only.entries(1 to null, report = {})
        nKeyValueMap = nKeyValueMap.toContain.inOrder.only.entries(null to "a", 2 to null, 1 to null, report = {})
        ronKeyValueMap =
            ronKeyValueMap.toContain.inOrder.only.entries(null to "a", 2 to null, 1 to null, report = {})
        starMap = starMap.toContain.inOrder.only.entries(null to "a", 2 to null, 1 to null, report = {})
    }
}
