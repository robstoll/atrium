package ch.tutteli.atrium.api.fluent.logic.based.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.specs.integration.mfun2
import org.spekframework.spek2.Spek
import ch.tutteli.atrium.api.fluent.logic.based.en_GB.MapToContainInAnyOrderKeyValuePairsExpectationsSpec.Companion as C

class MapToContainInAnyOrderKeyValuePairsExpectationsSpec : Spek({
    include(BuilderSpec)
    include(ShortcutSpec)
}) {

    object BuilderSpec : ch.tutteli.atrium.specs.integration.MapToContainInAnyOrderKeyValuePairsExpectationsSpec(
        functionDescription to C::toContainKeyValuePairs,
        (functionDescription to C::toContainKeyValuePairsNullable).withNullableSuffix(),
        "[Atrium][Builder] "
    )

    object ShortcutSpec : ch.tutteli.atrium.specs.integration.MapToContainInAnyOrderKeyValuePairsExpectationsSpec(
        mfun2<String, Int, Int>(Expect<Map<out String, Int>>::toContain),
        mfun2<String?, Int?, Int?>(Expect<Map<out String?, Int?>>::toContain).withNullableSuffix(),
        "[Atrium][Shortcut] "
    )

    companion object : MapToContainSpecBase() {
        val functionDescription = "$toContain.$inAnyOrder.$keyValuePair/$keyValuePairs"

        private fun toContainKeyValuePairs(
            expect: Expect<Map<out String, Int>>,
            a: Pair<String, Int>,
            aX: Array<out Pair<String, Int>>
        ): Expect<Map<out String, Int>> =
            if (aX.isEmpty()) expect.toContain.inAnyOrder.entry(a)
            else expect.toContain.inAnyOrder.entries(a, *aX)

        private fun toContainKeyValuePairsNullable(
            expect: Expect<Map<out String?, Int?>>,
            a: Pair<String?, Int?>,
            aX: Array<out Pair<String?, Int?>>
        ): Expect<Map<out String?, Int?>> =
            if (aX.isEmpty()) expect.toContain.inAnyOrder.entry(a)
            else expect.toContain.inAnyOrder.entries(a, *aX)
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

        map = map.toContain.inAnyOrder.entry(1 to "a")
        subMap = subMap.toContain.inAnyOrder.entry(1 to "a")
        nKeyMap = nKeyMap.toContain.inAnyOrder.entry(1 to "a")
        nValueMap = nValueMap.toContain.inAnyOrder.entry(1 to "a")
        nKeyValueMap = nKeyValueMap.toContain.inAnyOrder.entry(1 to "a")
        ronKeyValueMap = ronKeyValueMap.toContain.inAnyOrder.entry(1 to "a")
        starMap = starMap.toContain.inAnyOrder.entry(1 to "a")

        map = map.toContain.inAnyOrder.entries(1 to "a")
        subMap = subMap.toContain.inAnyOrder.entries(1 to "a")
        nKeyMap = nKeyMap.toContain.inAnyOrder.entries(1 to "a")
        nValueMap = nValueMap.toContain.inAnyOrder.entries(1 to "a")
        nKeyValueMap = nKeyValueMap.toContain.inAnyOrder.entries(1 to "a")
        ronKeyValueMap = ronKeyValueMap.toContain.inAnyOrder.entries(1 to "a")
        starMap = starMap.toContain.inAnyOrder.entries(1 to "a")

        map = map.toContain.inAnyOrder.entries(
            1 as Number to "a",
            1.2 to "b"
        )
        subMap = subMap.toContain.inAnyOrder.entries(
            1 as Number to "a",
            1.2 to "b"
        )
        nKeyMap = nKeyMap.toContain.inAnyOrder.entries(
            1 as Number to "a",
            1.2 to "b"
        )
        nValueMap = nValueMap.toContain.inAnyOrder.entries(
            1 as Number to "a",
            1.2 to "b"
        )
        nKeyValueMap = nKeyValueMap.toContain.inAnyOrder.entries(
            1 as Number to "a",
            1.2 to "b"
        )
        ronKeyValueMap = ronKeyValueMap.toContain.inAnyOrder.entries(
            1 as Number to "a",
            1.2 to "b"
        )
        starMap = starMap.toContain.inAnyOrder.entries(
            1 as Number to "a",
            1.2 to "b"
        )

        nKeyMap = nKeyMap.toContain.inAnyOrder.entry(null to "a")
        nValueMap = nValueMap.toContain.inAnyOrder.entry(1.2 to null)
        nKeyValueMap = nKeyValueMap.toContain.inAnyOrder.entry(null to null)
        ronKeyValueMap = ronKeyValueMap.toContain.inAnyOrder.entry(null to null)
        starMap = starMap.toContain.inAnyOrder.entry(null to null)

        nKeyMap = nKeyMap.toContain.inAnyOrder.entries(null to "a", 1 to "b")
        nValueMap = nValueMap.toContain.inAnyOrder.entries(1 to null)
        nKeyValueMap = nKeyValueMap.toContain.inAnyOrder.entries((null to "a"), null to null, 1 to null)
        ronKeyValueMap = ronKeyValueMap.toContain.inAnyOrder.entries((null to "a"), null to null, 1 to null)
        starMap = starMap.toContain.inAnyOrder.entries((null to "a"), null to null, 1 to null)


        /// ------------- shortcuts -----------------------------------------------------------------

        map = map.toContain(1 to "a")
        subMap = subMap.toContain(1 to "a")
        nKeyMap = nKeyMap.toContain(1 to "a")
        nValueMap = nValueMap.toContain(1 to "a")
        nKeyValueMap = nKeyValueMap.toContain(1 to "a")
        ronKeyValueMap = ronKeyValueMap.toContain(1 to "a")
        starMap = starMap.toContain(1 to "a")

        map = map.toContain(1 to "a")
        subMap = subMap.toContain(1 to "a")
        nKeyMap = nKeyMap.toContain(1 to "a")
        nValueMap = nValueMap.toContain(1 to "a")
        nKeyValueMap = nKeyValueMap.toContain(1 to "a")
        ronKeyValueMap = ronKeyValueMap.toContain(1 to "a")
        starMap = starMap.toContain(1 to "a")

        map = map.toContain(1 as Number to "a", 1.2 to "b")
        subMap = subMap.toContain(1 as Number to "a", 1.2 to "b")
        nKeyMap = nKeyMap.toContain(1 as Number to "a", 1.2 to "b")
        nValueMap = nValueMap.toContain(1 as Number to "a", 1.2 to "b")
        nKeyValueMap = nKeyValueMap.toContain(1 as Number to "a", 1.2 to "b")
        ronKeyValueMap = ronKeyValueMap.toContain(1 as Number to "a", 1.2 to "b")
        starMap = starMap.toContain(1 as Number to "a", 1.2 to "b")

        nKeyMap = nKeyMap.toContain(null to "a")
        nValueMap = nValueMap.toContain(1.2 to null)
        nKeyValueMap = nKeyValueMap.toContain(null to null)
        ronKeyValueMap = ronKeyValueMap.toContain(null to "a")
        starMap = starMap.toContain(null to "a")

        nKeyMap = nKeyMap.toContain(null to "a")
        nValueMap = nValueMap.toContain(1.2 to null, 1 to null)
        nKeyValueMap = nKeyValueMap.toContain(null to "a", null to null, 1 to null)
        ronKeyValueMap = ronKeyValueMap.toContain(null to "a", null to null, 1 to null)
        starMap = starMap.toContain(null to "a", null to null, 1 to null)
    }
}
