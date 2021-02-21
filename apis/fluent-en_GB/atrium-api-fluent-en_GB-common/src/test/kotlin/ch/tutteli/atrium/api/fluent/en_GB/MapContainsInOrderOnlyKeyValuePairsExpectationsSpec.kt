package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.notImplemented
import ch.tutteli.atrium.specs.withNullableSuffix
import org.spekframework.spek2.Spek
import ch.tutteli.atrium.api.fluent.en_GB.MapContainsInOrderOnlyKeyValuePairsExpectationsSpec.Companion as C

class MapContainsInOrderOnlyKeyValuePairsExpectationsSpec : Spek({
    include(BuilderSpec)
}) {

    object BuilderSpec : ch.tutteli.atrium.specs.integration.MapContainsInOrderOnlyKeyValuePairsExpectationsSpec(
        functionDescription to C::containsKeyValuePairs,
        (functionDescription to C::containsKeyValuePairsNullable).withNullableSuffix(),
        "[Atrium][Builder] "
    )

    companion object : MapContainsSpecBase() {
        val functionDescription = "$contains.$inOrder.$only.$keyValuePair/$keyValuePairs"

        private fun containsKeyValuePairs(
            expect: Expect<Map<out String, Int>>,
            a: Pair<String, Int>,
            aX: Array<out Pair<String, Int>>
        ): Expect<Map<out String, Int>> =
            if (aX.isEmpty()) expect.contains.inOrder.only.entry(a)
            else expect.contains.inOrder.only.entries(a, *aX)

        private fun containsKeyValuePairsNullable(
            expect: Expect<Map<out String?, Int?>>,
            a: Pair<String?, Int?>,
            aX: Array<out Pair<String?, Int?>>
        ): Expect<Map<out String?, Int?>> =
            if (aX.isEmpty()) expect.contains.inOrder.only.entry(a)
            else expect.contains.inOrder.only.entries(a, *aX)
    }


    @Suppress("unused", "UNUSED_VALUE")
    private fun ambiguityTest() {
        var map: Expect<Map<Number, CharSequence>> = notImplemented()
        var subMap: Expect<LinkedHashMap<out Number, String>> = notImplemented()
        var nKeyMap: Expect<Map<Number?, CharSequence>> = notImplemented()
        var nValueMap: Expect<Map<Number, CharSequence?>> = notImplemented()
        var nKeyValueMap: Expect<Map<Number?, CharSequence?>> = notImplemented()
        var ronKeyValueMap: Expect<Map<out Number?, CharSequence?>> = notImplemented()
        var starMap: Expect<Map<*, *>> = notImplemented()

        map = map.contains.inOrder.only.entry(1 to "a")
        subMap = subMap.contains.inOrder.only.entry(1 to "a")
        nKeyMap = nKeyMap.contains.inOrder.only.entry(1 to "a")
        nValueMap = nValueMap.contains.inOrder.only.entry(1 to "a")
        nKeyValueMap = nKeyValueMap.contains.inOrder.only.entry(1 to "a")
        ronKeyValueMap = ronKeyValueMap.contains.inOrder.only.entry(1 to "a")
        starMap = starMap.contains.inOrder.only.entry(1 to "a")

        map = map.contains.inOrder.only.entries(1 to "a")
        subMap = subMap.contains.inOrder.only.entries(1 to "a")
        nKeyMap = nKeyMap.contains.inOrder.only.entries(1 to "a")
        nValueMap = nValueMap.contains.inOrder.only.entries(1 to "a")
        nKeyValueMap = nKeyValueMap.contains.inOrder.only.entries(1 to "a")
        ronKeyValueMap = ronKeyValueMap.contains.inOrder.only.entries(1 to "a")
        starMap = starMap.contains.inOrder.only.entries(1 to "a")

        map = map.contains.inOrder.only.entries(
            1 as Number to "a",
            1.2 to "b"
        )
        subMap = subMap.contains.inOrder.only.entries(
            1 as Number to "a",
            1.2 to "b"
        )
        nKeyMap = nKeyMap.contains.inOrder.only.entries(
            1 as Number to "a",
            1.2 to "b"
        )
        nValueMap = nValueMap.contains.inOrder.only.entries(
            1 as Number to "a",
            1.2 to "b"
        )
        nKeyValueMap = nKeyValueMap.contains.inOrder.only.entries(
            1 as Number to "a",
            1.2 to "b"
        )
        ronKeyValueMap = ronKeyValueMap.contains.inOrder.only.entries(
            1 as Number to "a",
            1.2 to "b"
        )
        starMap = starMap.contains.inOrder.only.entries(
            1 as Number to "a",
            1.2 to "b"
        )

        nKeyMap = nKeyMap.contains.inOrder.only.entry(null to "a")
        nValueMap = nValueMap.contains.inOrder.only.entry(1.2 to null)
        nKeyValueMap = nKeyValueMap.contains.inOrder.only.entry(null to null)
        ronKeyValueMap = ronKeyValueMap.contains.inOrder.only.entry(null to null)
        starMap = starMap.contains.inOrder.only.entry(null to null)

        nKeyMap = nKeyMap.contains.inOrder.only.entries(null to "a", 1 to "b")
        nValueMap = nValueMap.contains.inOrder.only.entries(1 to null)
        nKeyValueMap = nKeyValueMap.contains.inOrder.only.entries((null to "a"), null to null, 1 to null)
        ronKeyValueMap = ronKeyValueMap.contains.inOrder.only.entries((null to "a"), null to null, 1 to null)
        starMap = starMap.contains.inOrder.only.entries((null to "a"), null to null, 1 to null)
    }
}
