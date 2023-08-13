package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.notImplemented
import ch.tutteli.atrium.specs.withNullableSuffix
import org.spekframework.spek2.Spek
import ch.tutteli.atrium.api.infix.en_GB.MapToContainInOrderOnlyKeyValuePairsExpectationsSpec.Companion as C

class MapToContainInOrderOnlyKeyValuePairsExpectationsSpec : Spek({
    include(BuilderSpec)
}) {

    object BuilderSpec : ch.tutteli.atrium.specs.integration.MapToContainInOrderOnlyKeyValuePairsExpectationsSpec(
        toContainKeyValuePair_s to C::toContainKeyValuePairs,
        (toContainKeyValuePair_s to C::toContainKeyValuePairsNullable).withNullableSuffix(),
        "[Atrium][Builder] "
    )

    companion object : MapToContainSpecBase() {
        val toContainKeyValuePair_s = "$toContain $filler $inOrder $andOnly $keyValuePair/$theKeyValuePairs"

        private fun toContainKeyValuePairs(
            expect: Expect<Map<out String, Int>>,
            a: Pair<String, Int>,
            aX: Array<out Pair<String, Int>>
        ): Expect<Map<out String, Int>> =
            if (aX.isEmpty()) expect toContain o inGiven order and only entry (a.first to a.second)
            else expect toContain o inGiven order and only the pairs(a, *aX)

        private fun toContainKeyValuePairsNullable(
            expect: Expect<Map<out String?, Int?>>,
            a: Pair<String?, Int?>,
            aX: Array<out Pair<String?, Int?>>
        ): Expect<Map<out String?, Int?>> =
            if (aX.isEmpty()) expect toContain o inGiven order and only entry (a.first to a.second)
            else expect toContain o inGiven order and only the pairs(a, *aX)
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

        map = map toContain o inGiven order and only entry (1 to "a")
        subMap = subMap toContain o inGiven order and only entry (1 to "a")
        nKeyMap = nKeyMap toContain o inGiven order and only entry (1 to "a")
        nValueMap = nValueMap toContain o inGiven order and only entry (1 to "a")
        nKeyValueMap = nKeyValueMap toContain o inGiven order and only entry (1 to "a")
        ronKeyValueMap = ronKeyValueMap toContain o inGiven order and only entry (1 to "a")
        starMap = starMap toContain o inGiven order and only entry (1 to "a")

        map = map toContain o inGiven order and only the pairs(1 to "a")
        subMap = subMap toContain o inGiven order and only the pairs(1 to "a")
        nKeyMap = nKeyMap toContain o inGiven order and only the pairs(1 to "a")
        nValueMap = nValueMap toContain o inGiven order and only the pairs(1 to "a")
        nKeyValueMap = nKeyValueMap toContain o inGiven order and only the pairs(1 to "a")
        ronKeyValueMap = ronKeyValueMap toContain o inGiven order and only the pairs(1 to "a")
        starMap = starMap toContain o inGiven order and only the pairs(1 to "a")

        map = map toContain o inGiven order and only the pairs(
            1 as Number to "a",
            1.2 to "b"
        )
        subMap = subMap toContain o inGiven order and only the pairs(
            1 as Number to "a",
            1.2 to "b"
        )
        nKeyMap = nKeyMap toContain o inGiven order and only the pairs(
            1 as Number to "a",
            1.2 to "b"
        )
        nValueMap = nValueMap toContain o inGiven order and only the pairs(
            1 as Number to "a",
            1.2 to "b"
        )
        nKeyValueMap = nKeyValueMap toContain o inGiven order and only the pairs(
            1 as Number to "a",
            1.2 to "b"
        )
        ronKeyValueMap = ronKeyValueMap toContain o inGiven order and only the pairs(
            1 as Number to "a",
            1.2 to "b"
        )
        starMap = starMap toContain o inGiven order and only the pairs(
            1 as Number to "a",
            1.2 to "b"
        )

        nKeyMap = nKeyMap toContain o inGiven order and only entry (null to "a")
        nValueMap = nValueMap toContain o inGiven order and only entry (1.2 to null)
        nKeyValueMap = nKeyValueMap toContain o inGiven order and only entry (null to null)
        ronKeyValueMap = ronKeyValueMap toContain o inGiven order and only entry (null to null)
        starMap = starMap toContain o inGiven order and only entry (null to null)

        nKeyMap = nKeyMap toContain o inGiven order and only the pairs(null to "a", 1 to "b")
        nValueMap = nValueMap toContain o inGiven order and only the pairs(1 to null)
        nKeyValueMap = nKeyValueMap toContain o inGiven order and only the pairs((null to "a"), null to null, 1 to null)
        ronKeyValueMap = ronKeyValueMap toContain o inGiven order and only the pairs((null to "a"), null to null, 1 to null)
        starMap = starMap toContain o inGiven order and only the pairs((null to "a"), null to null, 1 to null)

    }
}
