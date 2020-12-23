package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.specs.integration.mfun2
import org.spekframework.spek2.Spek
import ch.tutteli.atrium.api.fluent.en_GB.MapContainsInAnyOrderKeyValuePairsAssertionsSpec.Companion as C

class MapContainsInAnyOrderKeyValuePairsAssertionsSpec : Spek({
    include(BuilderSpec)
    include(ShortcutSpec)
}) {

    object BuilderSpec : ch.tutteli.atrium.specs.integration.MapContainsInAnyOrderKeyValuePairsAssertionsSpec(
        functionDescription to C::containsKeyValuePairs,
        (functionDescription to C::containsKeyValuePairsNullable).withNullableSuffix(),
        "[Atrium][Builder] "
    )

    object ShortcutSpec : ch.tutteli.atrium.specs.integration.MapContainsInAnyOrderKeyValuePairsAssertionsSpec(
        mfun2<String, Int, Int>(Expect<Map<out String, Int>>::contains),
        mfun2<String?, Int?, Int?>(Expect<Map<out String?, Int?>>::contains).withNullableSuffix(),
        "[Atrium][Shortcut] "
    )

    companion object : MapContainsSpecBase() {
        val functionDescription = "$contains.$inAnyOrder.$keyValuePair/$keyValuePairs"

        private fun containsKeyValuePairs(
            expect: Expect<Map<out String, Int>>,
            a: Pair<String, Int>,
            aX: Array<out Pair<String, Int>>
        ): Expect<Map<out String, Int>> =
            if (aX.isEmpty()) expect.contains.inAnyOrder.entry(a)
            else expect.contains.inAnyOrder.entries(a, *aX)

        private fun containsKeyValuePairsNullable(
            expect: Expect<Map<out String?, Int?>>,
            a: Pair<String?, Int?>,
            aX: Array<out Pair<String?, Int?>>
        ): Expect<Map<out String?, Int?>> =
            if (aX.isEmpty()) expect.contains.inAnyOrder.entry(a)
            else expect.contains.inAnyOrder.entries(a, *aX)
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

        map = map.contains.inAnyOrder.entry(1 to "a")
        subMap = subMap.contains.inAnyOrder.entry(1 to "a")
        nKeyMap = nKeyMap.contains.inAnyOrder.entry(1 to "a")
        nValueMap = nValueMap.contains.inAnyOrder.entry(1 to "a")
        nKeyValueMap = nKeyValueMap.contains.inAnyOrder.entry(1 to "a")
        ronKeyValueMap = ronKeyValueMap.contains.inAnyOrder.entry(1 to "a")
        starMap = starMap.contains.inAnyOrder.entry(1 to "a")

        map = map.contains.inAnyOrder.entries(1 to "a")
        subMap = subMap.contains.inAnyOrder.entries(1 to "a")
        nKeyMap = nKeyMap.contains.inAnyOrder.entries(1 to "a")
        nValueMap = nValueMap.contains.inAnyOrder.entries(1 to "a")
        nKeyValueMap = nKeyValueMap.contains.inAnyOrder.entries(1 to "a")
        ronKeyValueMap = ronKeyValueMap.contains.inAnyOrder.entries(1 to "a")
        starMap = starMap.contains.inAnyOrder.entries(1 to "a")

        map = map.contains.inAnyOrder.entries(
            1 as Number to "a",
            1.2 to "b"
        )
        subMap = subMap.contains.inAnyOrder.entries(
            1 as Number to "a",
            1.2 to "b"
        )
        nKeyMap = nKeyMap.contains.inAnyOrder.entries(
            1 as Number to "a",
            1.2 to "b"
        )
        nValueMap = nValueMap.contains.inAnyOrder.entries(
            1 as Number to "a",
            1.2 to "b"
        )
        nKeyValueMap = nKeyValueMap.contains.inAnyOrder.entries(
            1 as Number to "a",
            1.2 to "b"
        )
        ronKeyValueMap = ronKeyValueMap.contains.inAnyOrder.entries(
            1 as Number to "a",
            1.2 to "b"
        )
        starMap = starMap.contains.inAnyOrder.entries(
            1 as Number to "a",
            1.2 to "b"
        )

        nKeyMap = nKeyMap.contains.inAnyOrder.entry(null to "a")
        nValueMap = nValueMap.contains.inAnyOrder.entry(1.2 to null)
        nKeyValueMap = nKeyValueMap.contains.inAnyOrder.entry(null to null)
        ronKeyValueMap = ronKeyValueMap.contains.inAnyOrder.entry(null to null)
        starMap = starMap.contains.inAnyOrder.entry(null to null)

        nKeyMap = nKeyMap.contains.inAnyOrder.entries(null to "a", 1 to "b")
        nValueMap = nValueMap.contains.inAnyOrder.entries(1 to null)
        nKeyValueMap = nKeyValueMap.contains.inAnyOrder.entries((null to "a"), null to null, 1 to null)
        ronKeyValueMap = ronKeyValueMap.contains.inAnyOrder.entries((null to "a"), null to null, 1 to null)
        starMap = starMap.contains.inAnyOrder.entries((null to "a"), null to null, 1 to null)


        /// ------------- shortcuts -----------------------------------------------------------------

        map = map.contains(1 to "a")
        subMap = subMap.contains(1 to "a")
        nKeyMap = nKeyMap.contains(1 to "a")
        nValueMap = nValueMap.contains(1 to "a")
        nKeyValueMap = nKeyValueMap.contains(1 to "a")
        ronKeyValueMap = ronKeyValueMap.contains(1 to "a")
        starMap = starMap.contains(1 to "a")

        map = map.contains(1 to "a")
        subMap = subMap.contains(1 to "a")
        nKeyMap = nKeyMap.contains(1 to "a")
        nValueMap = nValueMap.contains(1 to "a")
        nKeyValueMap = nKeyValueMap.contains(1 to "a")
        ronKeyValueMap = ronKeyValueMap.contains(1 to "a")
        starMap = starMap.contains(1 to "a")

        map = map.contains(1 as Number to "a", 1.2 to "b")
        subMap = subMap.contains(1 as Number to "a", 1.2 to "b")
        nKeyMap = nKeyMap.contains(1 as Number to "a", 1.2 to "b")
        nValueMap = nValueMap.contains(1 as Number to "a", 1.2 to "b")
        nKeyValueMap = nKeyValueMap.contains(1 as Number to "a", 1.2 to "b")
        ronKeyValueMap = ronKeyValueMap.contains(1 as Number to "a", 1.2 to "b")
        starMap = starMap.contains(1 as Number to "a", 1.2 to "b")

        nKeyMap = nKeyMap.contains(null to "a")
        nValueMap = nValueMap.contains(1.2 to null)
        nKeyValueMap = nKeyValueMap.contains(null to null)
        ronKeyValueMap = ronKeyValueMap.contains(null to "a")
        starMap = starMap.contains(null to "a")

        nKeyMap = nKeyMap.contains(null to "a")
        nValueMap = nValueMap.contains(1.2 to null, 1 to null)
        nKeyValueMap = nKeyValueMap.contains(null to "a", null to null, 1 to null)
        ronKeyValueMap = ronKeyValueMap.contains(null to "a", null to null, 1 to null)
        starMap = starMap.contains(null to "a", null to null, 1 to null)
    }
}
