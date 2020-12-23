package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.notImplemented
import ch.tutteli.atrium.specs.withNullableSuffix
import org.spekframework.spek2.Spek
import ch.tutteli.atrium.api.infix.en_GB.MapContainsInOrderOnlyKeyValuePairsExpectationsSpec.Companion as C

class MapContainsInOrderOnlyKeyValuePairsExpectationsSpec : Spek({
    include(BuilderSpec)
}) {

    object BuilderSpec : ch.tutteli.atrium.specs.integration.MapContainsInOrderOnlyKeyValuePairsExpectationsSpec(
        containsKeyValuePair_s to C::containsKeyValuePairs,
        (containsKeyValuePair_s to C::containsKeyValuePairsNullable).withNullableSuffix(),
        "* ", "(/) ", "(x) ", "(!) ", "- ", "» ", ">> ", "=> ",
        "[Atrium][Builder] "
    )

    companion object : MapContainsSpecBase() {
        val containsKeyValuePair_s = "$contains $filler $inOrder $andOnly $keyValuePair/$keyValuePairs"

        private fun containsKeyValuePairs(
            expect: Expect<Map<out String, Int>>,
            a: Pair<String, Int>,
            aX: Array<out Pair<String, Int>>
        ): Expect<Map<out String, Int>> =
            if (aX.isEmpty()) expect contains o inGiven order and only entry (a.first to a.second)
            else expect contains o inGiven order and only the pairs(a, *aX)

        private fun containsKeyValuePairsNullable(
            expect: Expect<Map<out String?, Int?>>,
            a: Pair<String?, Int?>,
            aX: Array<out Pair<String?, Int?>>
        ): Expect<Map<out String?, Int?>> =
            if (aX.isEmpty()) expect contains o inGiven order and only entry (a.first to a.second)
            else expect contains o inGiven order and only the pairs(a, *aX)
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

        map = map contains o inGiven order and only entry (1 to "a")
        subMap = subMap contains o inGiven order and only entry (1 to "a")
        nKeyMap = nKeyMap contains o inGiven order and only entry (1 to "a")
        nValueMap = nValueMap contains o inGiven order and only entry (1 to "a")
        nKeyValueMap = nKeyValueMap contains o inGiven order and only entry (1 to "a")
        ronKeyValueMap = ronKeyValueMap contains o inGiven order and only entry (1 to "a")
        starMap = starMap contains o inGiven order and only entry (1 to "a")

        map = map contains o inGiven order and only the pairs(1 to "a")
        subMap = subMap contains o inGiven order and only the pairs(1 to "a")
        nKeyMap = nKeyMap contains o inGiven order and only the pairs(1 to "a")
        nValueMap = nValueMap contains o inGiven order and only the pairs(1 to "a")
        nKeyValueMap = nKeyValueMap contains o inGiven order and only the pairs(1 to "a")
        ronKeyValueMap = ronKeyValueMap contains o inGiven order and only the pairs(1 to "a")
        starMap = starMap contains o inGiven order and only the pairs(1 to "a")

        map = map contains o inGiven order and only the pairs(
            1 as Number to "a",
            1.2 to "b"
        )
        subMap = subMap contains o inGiven order and only the pairs(
            1 as Number to "a",
            1.2 to "b"
        )
        nKeyMap = nKeyMap contains o inGiven order and only the pairs(
            1 as Number to "a",
            1.2 to "b"
        )
        nValueMap = nValueMap contains o inGiven order and only the pairs(
            1 as Number to "a",
            1.2 to "b"
        )
        nKeyValueMap = nKeyValueMap contains o inGiven order and only the pairs(
            1 as Number to "a",
            1.2 to "b"
        )
        ronKeyValueMap = ronKeyValueMap contains o inGiven order and only the pairs(
            1 as Number to "a",
            1.2 to "b"
        )
        starMap = starMap contains o inGiven order and only the pairs(
            1 as Number to "a",
            1.2 to "b"
        )

        nKeyMap = nKeyMap contains o inGiven order and only entry (null to "a")
        nValueMap = nValueMap contains o inGiven order and only entry (1.2 to null)
        nKeyValueMap = nKeyValueMap contains o inGiven order and only entry (null to null)
        ronKeyValueMap = ronKeyValueMap contains o inGiven order and only entry (null to null)
        starMap = starMap contains o inGiven order and only entry (null to null)

        nKeyMap = nKeyMap contains o inGiven order and only the pairs(null to "a", 1 to "b")
        nValueMap = nValueMap contains o inGiven order and only the pairs(1 to null)
        nKeyValueMap = nKeyValueMap contains o inGiven order and only the pairs((null to "a"), null to null, 1 to null)
        ronKeyValueMap = ronKeyValueMap contains o inGiven order and only the pairs((null to "a"), null to null, 1 to null)
        starMap = starMap contains o inGiven order and only the pairs((null to "a"), null to null, 1 to null)

    }
}
