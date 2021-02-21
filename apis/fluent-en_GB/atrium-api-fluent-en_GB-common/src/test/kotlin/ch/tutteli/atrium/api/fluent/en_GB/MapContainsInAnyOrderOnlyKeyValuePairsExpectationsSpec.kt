package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.specs.integration.mfun2
import org.spekframework.spek2.Spek
import ch.tutteli.atrium.api.fluent.en_GB.MapContainsInAnyOrderOnlyKeyValuePairsExpectationsSpec.Companion as C

class MapContainsInAnyOrderOnlyKeyValuePairsExpectationsSpec : Spek({
    include(BuilderSpec)
    include(ShortcutSpec)
}) {

    object BuilderSpec : ch.tutteli.atrium.specs.integration.MapContainsInAnyOrderOnlyKeyValuePairsExpectationsSpec(
        functionDescription to C::containsKeyValuePairs,
        (functionDescription to C::containsKeyValuePairsNullable).withNullableSuffix(),
        "[Atrium][Builder] "
    )

    object ShortcutSpec : ch.tutteli.atrium.specs.integration.MapContainsInAnyOrderOnlyKeyValuePairsExpectationsSpec(
        mfun2<String, Int, Int>(Expect<Map<out String, Int>>::containsOnly),
        mfun2<String?, Int?, Int?>(Expect<Map<out String?, Int?>>::containsOnly).withNullableSuffix(),
        "[Atrium][Shortcut] "
    )

    companion object : MapContainsSpecBase() {
        val functionDescription = "$contains.$inAnyOrder.$only.$keyValuePair/$keyValuePairs"

        private fun containsKeyValuePairs(
            expect: Expect<Map<out String, Int>>,
            a: Pair<String, Int>,
            aX: Array<out Pair<String, Int>>
        ): Expect<Map<out String, Int>> =
            if (aX.isEmpty()) expect.contains.inAnyOrder.only.entry(a)
            else expect.contains.inAnyOrder.only.entries(a, *aX)

        private fun containsKeyValuePairsNullable(
            expect: Expect<Map<out String?, Int?>>,
            a: Pair<String?, Int?>,
            aX: Array<out Pair<String?, Int?>>
        ): Expect<Map<out String?, Int?>> =
            if (aX.isEmpty()) expect.contains.inAnyOrder.only.entry(a)
            else expect.contains.inAnyOrder.only.entries(a, *aX)
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

        map = map.contains.inAnyOrder.only.entry(1 to "a")
        subMap = subMap.contains.inAnyOrder.only.entry(1 to "a")
        nKeyMap = nKeyMap.contains.inAnyOrder.only.entry(1 to "a")
        nValueMap = nValueMap.contains.inAnyOrder.only.entry(1 to "a")
        nKeyValueMap = nKeyValueMap.contains.inAnyOrder.only.entry(1 to "a")
        ronKeyValueMap = ronKeyValueMap.contains.inAnyOrder.only.entry(1 to "a")
        starMap = starMap.contains.inAnyOrder.only.entry(1 to "a")

        map = map.contains.inAnyOrder.only.entries(1 to "a")
        subMap = subMap.contains.inAnyOrder.only.entries(1 to "a")
        nKeyMap = nKeyMap.contains.inAnyOrder.only.entries(1 to "a")
        nValueMap = nValueMap.contains.inAnyOrder.only.entries(1 to "a")
        nKeyValueMap = nKeyValueMap.contains.inAnyOrder.only.entries(1 to "a")
        ronKeyValueMap = ronKeyValueMap.contains.inAnyOrder.only.entries(1 to "a")
        starMap = starMap.contains.inAnyOrder.only.entries(1 to "a")

        map = map.contains.inAnyOrder.only.entries(
            1 as Number to "a",
            1.2 to "b"
        )
        subMap = subMap.contains.inAnyOrder.only.entries(
            1 as Number to "a",
            1.2 to "b"
        )
        nKeyMap = nKeyMap.contains.inAnyOrder.only.entries(
            1 as Number to "a",
            1.2 to "b"
        )
        nValueMap = nValueMap.contains.inAnyOrder.only.entries(
            1 as Number to "a",
            1.2 to "b"
        )
        nKeyValueMap = nKeyValueMap.contains.inAnyOrder.only.entries(
            1 as Number to "a",
            1.2 to "b"
        )
        ronKeyValueMap = ronKeyValueMap.contains.inAnyOrder.only.entries(
            1 as Number to "a",
            1.2 to "b"
        )
        starMap = starMap.contains.inAnyOrder.only.entries(
            1 as Number to "a",
            1.2 to "b"
        )

        nKeyMap = nKeyMap.contains.inAnyOrder.only.entry(null to "a")
        nValueMap = nValueMap.contains.inAnyOrder.only.entry(1.2 to null)
        nKeyValueMap = nKeyValueMap.contains.inAnyOrder.only.entry(null to null)
        ronKeyValueMap = ronKeyValueMap.contains.inAnyOrder.only.entry(null to null)
        starMap = starMap.contains.inAnyOrder.only.entry(null to null)

        nKeyMap = nKeyMap.contains.inAnyOrder.only.entries(null to "a", 1 to "b")
        nValueMap = nValueMap.contains.inAnyOrder.only.entries(1 to null)
        nKeyValueMap = nKeyValueMap.contains.inAnyOrder.only.entries((null to "a"), null to null, 1 to null)
        ronKeyValueMap = ronKeyValueMap.contains.inAnyOrder.only.entries((null to "a"), null to null, 1 to null)
        starMap = starMap.contains.inAnyOrder.only.entries((null to "a"), null to null, 1 to null)


        /// ------------- shortcuts -----------------------------------------------------------------

        map = map.containsOnly(1 to "a")
        subMap = subMap.containsOnly(1 to "a")
        nKeyMap = nKeyMap.containsOnly(1 to "a")
        nValueMap = nValueMap.containsOnly(1 to "a")
        nKeyValueMap = nKeyValueMap.containsOnly(1 to "a")
        ronKeyValueMap = ronKeyValueMap.containsOnly(1 to "a")
        starMap = starMap.containsOnly(1 to "a")

        map = map.containsOnly(1 to "a")
        subMap = subMap.containsOnly(1 to "a")
        nKeyMap = nKeyMap.containsOnly(1 to "a")
        nValueMap = nValueMap.containsOnly(1 to "a")
        nKeyValueMap = nKeyValueMap.containsOnly(1 to "a")
        ronKeyValueMap = ronKeyValueMap.containsOnly(1 to "a")
        starMap = starMap.containsOnly(1 to "a")

        map = map.containsOnly(1 as Number to "a", 1.2 to "b")
        subMap = subMap.containsOnly(1 as Number to "a", 1.2 to "b")
        nKeyMap = nKeyMap.containsOnly(1 as Number to "a", 1.2 to "b")
        nValueMap = nValueMap.containsOnly(1 as Number to "a", 1.2 to "b")
        nKeyValueMap = nKeyValueMap.containsOnly(1 as Number to "a", 1.2 to "b")
        ronKeyValueMap = ronKeyValueMap.containsOnly(1 as Number to "a", 1.2 to "b")
        starMap = starMap.containsOnly(1 as Number to "a", 1.2 to "b")

        nKeyMap = nKeyMap.containsOnly(null to "a")
        nValueMap = nValueMap.containsOnly(1.2 to null)
        nKeyValueMap = nKeyValueMap.containsOnly(null to null)
        ronKeyValueMap = ronKeyValueMap.containsOnly(null to "a")
        starMap = starMap.containsOnly(null to "a")

        nKeyMap = nKeyMap.containsOnly(null to "a")
        nValueMap = nValueMap.containsOnly(1.2 to null, 1 to null)
        nKeyValueMap = nKeyValueMap.containsOnly(null to "a", null to null, 1 to null)
        ronKeyValueMap = ronKeyValueMap.containsOnly(null to "a", null to null, 1 to null)
        starMap = starMap.containsOnly(null to "a", null to null, 1 to null)
    }
}
