package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.api.verbs.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.integration.AbstractMapToContainInAnyOrderOnlyKeyValuePairsExpectationsTest
import ch.tutteli.atrium.specs.withNullableSuffix
import kotlin.test.Test

class MapToContainInAnyOrderOnlyKeyValuePairsExpectationsBuilderTest :
    AbstractMapToContainInAnyOrderOnlyKeyValuePairsExpectationsTest(
        functionDescription to Companion::toContainKeyValuePairs,
        (functionDescription to Companion::toContainKeyValuePairsNullable).withNullableSuffix()
    ) {
    companion object : MapToContainSpecBase() {
        val functionDescription = "$toContain.$inAnyOrder.$only.$keyValuePair/$keyValuePairs"

        private fun toContainKeyValuePairs(
            expect: Expect<Map<out String, Int>>,
            a: Pair<String, Int>,
            aX: Array<out Pair<String, Int>>
        ): Expect<Map<out String, Int>> =
            if (aX.isEmpty()) expect.toContain.inAnyOrder.only.entry(a)
            else expect.toContain.inAnyOrder.only.entries(a, *aX)

        private fun toContainKeyValuePairsNullable(
            expect: Expect<Map<out String?, Int?>>,
            a: Pair<String?, Int?>,
            aX: Array<out Pair<String?, Int?>>
        ): Expect<Map<out String?, Int?>> =
            if (aX.isEmpty()) expect.toContain.inAnyOrder.only.entry(a)
            else expect.toContain.inAnyOrder.only.entries(a, *aX)
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

        map = map.toContain.inAnyOrder.only.entry(1 to "a")
        subMap = subMap.toContain.inAnyOrder.only.entry(1 to "a")
        nKeyMap = nKeyMap.toContain.inAnyOrder.only.entry(1 to "a")
        nValueMap = nValueMap.toContain.inAnyOrder.only.entry(1 to "a")
        nKeyValueMap = nKeyValueMap.toContain.inAnyOrder.only.entry(1 to "a")
        ronKeyValueMap = ronKeyValueMap.toContain.inAnyOrder.only.entry(1 to "a")
        starMap = starMap.toContain.inAnyOrder.only.entry(1 to "a")

        map = map.toContain.inAnyOrder.only.entries(1 to "a")
        subMap = subMap.toContain.inAnyOrder.only.entries(1 to "a")
        nKeyMap = nKeyMap.toContain.inAnyOrder.only.entries(1 to "a")
        nValueMap = nValueMap.toContain.inAnyOrder.only.entries(1 to "a")
        nKeyValueMap = nKeyValueMap.toContain.inAnyOrder.only.entries(1 to "a")
        ronKeyValueMap = ronKeyValueMap.toContain.inAnyOrder.only.entries(1 to "a")
        starMap = starMap.toContain.inAnyOrder.only.entries(1 to "a")

        map = expect(mapOf<Number, CharSequence>(1 to "a", 1.2 to "b"))
        subMap = expect(linkedMapOf<Number, String>(1 to "a", 1.2 to "b"))
        nKeyMap = expect(mapOf<Number?, CharSequence>(1 to "a", 1.2 to "b"))
        nValueMap = expect(mapOf<Number, CharSequence?>(1 to "a", 1.2 to "b"))
        nKeyValueMap = expect(mapOf<Number?, CharSequence?>(1 to "a", 1.2 to "b"))
        ronKeyValueMap = expect(mapOf<Number?, CharSequence?>(1 to "a", 1.2 to "b"))
        starMap = expect(mapOf<Number?, CharSequence?>(1 to "a", 1.2 to "b"))

        map = map.toContain.inAnyOrder.only.entries(
            1 as Number to "a",
            1.2 to "b"
        )
        subMap = subMap.toContain.inAnyOrder.only.entries(
            1 as Number to "a",
            1.2 to "b"
        )
        nKeyMap = nKeyMap.toContain.inAnyOrder.only.entries(
            1 as Number to "a",
            1.2 to "b"
        )
        nValueMap = nValueMap.toContain.inAnyOrder.only.entries(
            1 as Number to "a",
            1.2 to "b"
        )
        nKeyValueMap = nKeyValueMap.toContain.inAnyOrder.only.entries(
            1 as Number to "a",
            1.2 to "b"
        )
        ronKeyValueMap = ronKeyValueMap.toContain.inAnyOrder.only.entries(
            1 as Number to "a",
            1.2 to "b"
        )
        starMap = starMap.toContain.inAnyOrder.only.entries(
            1 as Number to "a",
            1.2 to "b"
        )

        nKeyMap = expect(mapOf<Number?, CharSequence>(null to "a"))
        nValueMap = expect(mapOf<Number, CharSequence?>(1.2 to null))
        nKeyValueMap = expect(mapOf<Number?, CharSequence?>(null to null))
        ronKeyValueMap = expect(mapOf<Number?, CharSequence?>(null to null))
        starMap = expect(mapOf<Number?, CharSequence?>(null to null))

        nKeyMap = nKeyMap.toContain.inAnyOrder.only.entry(null to "a")
        nValueMap = nValueMap.toContain.inAnyOrder.only.entry(1.2 to null)
        nKeyValueMap = nKeyValueMap.toContain.inAnyOrder.only.entry(null to null)
        ronKeyValueMap = ronKeyValueMap.toContain.inAnyOrder.only.entry(null to null)
        starMap = starMap.toContain.inAnyOrder.only.entry(null to null)

        nKeyMap = expect(mapOf<Number?, CharSequence>(null to "a", 1 to "b"))
        nValueMap = expect(mapOf<Number, CharSequence?>(1 to null))
        nKeyValueMap = expect(mapOf<Number?, CharSequence?>(null to "a", 2 to null, 1 to null))
        ronKeyValueMap = expect(mapOf<Number?, CharSequence?>(null to "a", 2 to null, 1 to null))
        starMap = expect(mapOf<Number?, CharSequence?>(null to "a", 2 to null, 1 to null))

        nKeyMap = nKeyMap.toContain.inAnyOrder.only.entries(null to "a", 1 to "b")
        nValueMap = nValueMap.toContain.inAnyOrder.only.entries(1 to null)
        nKeyValueMap = nKeyValueMap.toContain.inAnyOrder.only.entries(null to "a", 2 to null, 1 to null)
        ronKeyValueMap = ronKeyValueMap.toContain.inAnyOrder.only.entries(null to "a", 2 to null, 1 to null)
        starMap = starMap.toContain.inAnyOrder.only.entries(null to "a", 2 to null, 1 to null)
    }
}
