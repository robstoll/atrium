package ch.tutteli.atrium.api.fluent.logic.based.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.specs.integration.mfun2
import org.spekframework.spek2.Spek
import ch.tutteli.atrium.api.fluent.logic.based.en_GB.MapToContainInAnyOrderOnlyKeyValuePairsExpectationsSpec.Companion as C

class MapToContainInAnyOrderOnlyKeyValuePairsExpectationsSpec : Spek({
    include(BuilderSpec)
    include(ShortcutSpec)
}) {

    object BuilderSpec : ch.tutteli.atrium.specs.integration.MapToContainInAnyOrderOnlyKeyValuePairsExpectationsSpec(
        functionDescription to C::toContainKeyValuePairs,
        (functionDescription to C::toContainKeyValuePairsNullable).withNullableSuffix(),
        "[Atrium][Builder] "
    )

    object ShortcutSpec : ch.tutteli.atrium.specs.integration.MapToContainInAnyOrderOnlyKeyValuePairsExpectationsSpec(
        mfun2<String, Int, Int>(Expect<Map<out String, Int>>::toContainOnly),
        mfun2<String?, Int?, Int?>(Expect<Map<out String?, Int?>>::toContainOnly).withNullableSuffix(),
        "[Atrium][Shortcut] "
    )

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


    @Suppress("unused", "UNUSED_VALUE")
    private fun ambiguityTest() {
        var map: Expect<Map<Number, CharSequence>> = notImplemented()
        var subMap: Expect<LinkedHashMap<out Number, String>> = notImplemented()
        var nKeyMap: Expect<Map<Number?, CharSequence>> = notImplemented()
        var nValueMap: Expect<Map<Number, CharSequence?>> = notImplemented()
        var nKeyValueMap: Expect<Map<Number?, CharSequence?>> = notImplemented()
        var ronKeyValueMap: Expect<Map<out Number?, CharSequence?>> = notImplemented()
        var starMap: Expect<Map<*, *>> = notImplemented()

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

        nKeyMap = nKeyMap.toContain.inAnyOrder.only.entry(null to "a")
        nValueMap = nValueMap.toContain.inAnyOrder.only.entry(1.2 to null)
        nKeyValueMap = nKeyValueMap.toContain.inAnyOrder.only.entry(null to null)
        ronKeyValueMap = ronKeyValueMap.toContain.inAnyOrder.only.entry(null to null)
        starMap = starMap.toContain.inAnyOrder.only.entry(null to null)

        nKeyMap = nKeyMap.toContain.inAnyOrder.only.entries(null to "a", 1 to "b")
        nValueMap = nValueMap.toContain.inAnyOrder.only.entries(1 to null)
        nKeyValueMap = nKeyValueMap.toContain.inAnyOrder.only.entries((null to "a"), null to null, 1 to null)
        ronKeyValueMap = ronKeyValueMap.toContain.inAnyOrder.only.entries((null to "a"), null to null, 1 to null)
        starMap = starMap.toContain.inAnyOrder.only.entries((null to "a"), null to null, 1 to null)


        /// ------------- shortcuts -----------------------------------------------------------------

        map = map.toContainOnly(1 to "a")
        subMap = subMap.toContainOnly(1 to "a")
        nKeyMap = nKeyMap.toContainOnly(1 to "a")
        nValueMap = nValueMap.toContainOnly(1 to "a")
        nKeyValueMap = nKeyValueMap.toContainOnly(1 to "a")
        ronKeyValueMap = ronKeyValueMap.toContainOnly(1 to "a")
        starMap = starMap.toContainOnly(1 to "a")

        map = map.toContainOnly(1 to "a")
        subMap = subMap.toContainOnly(1 to "a")
        nKeyMap = nKeyMap.toContainOnly(1 to "a")
        nValueMap = nValueMap.toContainOnly(1 to "a")
        nKeyValueMap = nKeyValueMap.toContainOnly(1 to "a")
        ronKeyValueMap = ronKeyValueMap.toContainOnly(1 to "a")
        starMap = starMap.toContainOnly(1 to "a")

        map = map.toContainOnly(1 as Number to "a", 1.2 to "b")
        subMap = subMap.toContainOnly(1 as Number to "a", 1.2 to "b")
        nKeyMap = nKeyMap.toContainOnly(1 as Number to "a", 1.2 to "b")
        nValueMap = nValueMap.toContainOnly(1 as Number to "a", 1.2 to "b")
        nKeyValueMap = nKeyValueMap.toContainOnly(1 as Number to "a", 1.2 to "b")
        ronKeyValueMap = ronKeyValueMap.toContainOnly(1 as Number to "a", 1.2 to "b")
        starMap = starMap.toContainOnly(1 as Number to "a", 1.2 to "b")

        nKeyMap = nKeyMap.toContainOnly(null to "a")
        nValueMap = nValueMap.toContainOnly(1.2 to null)
        nKeyValueMap = nKeyValueMap.toContainOnly(null to null)
        ronKeyValueMap = ronKeyValueMap.toContainOnly(null to "a")
        starMap = starMap.toContainOnly(null to "a")

        nKeyMap = nKeyMap.toContainOnly(null to "a")
        nValueMap = nValueMap.toContainOnly(1.2 to null, 1 to null)
        nKeyValueMap = nKeyValueMap.toContainOnly(null to "a", null to null, 1 to null)
        ronKeyValueMap = ronKeyValueMap.toContainOnly(null to "a", null to null, 1 to null)
        starMap = starMap.toContainOnly(null to "a", null to null, 1 to null)
    }
}
