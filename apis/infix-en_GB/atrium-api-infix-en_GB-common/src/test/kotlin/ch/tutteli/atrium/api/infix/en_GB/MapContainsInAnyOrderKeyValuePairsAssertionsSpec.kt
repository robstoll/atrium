package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.specs.integration.mfun2
import org.spekframework.spek2.Spek
import kotlin.jvm.JvmName
import ch.tutteli.atrium.api.infix.en_GB.MapContainsInAnyOrderKeyValuePairsAssertionsSpec.Companion as C

class MapContainsInAnyOrderKeyValuePairsAssertionsSpec : Spek({
    include(BuilderSpec)
    include(ShortcutSpec)
}) {
    object BuilderSpec : ch.tutteli.atrium.specs.integration.MapContainsInAnyOrderKeyValuePairsAssertionsSpec(
        containsKeyValuePair_s to C::containsKeyValuePairs,
        (containsKeyValuePair_s to C::containsKeyValuePairsNullable).withNullableSuffix(),
        "[Atrium][Shortcut] "
    )

    object ShortcutSpec : ch.tutteli.atrium.specs.integration.MapContainsInAnyOrderKeyValuePairsAssertionsSpec(
        mfun2<String, Int, Int>(C::contains),
        mfun2<String?, Int?, Int?>(C::contains).withNullableSuffix(),
        "[Atrium][Shortcut] "
    )

    companion object : MapContainsSpecBase() {
        val containsKeyValuePair_s = "$contains $filler $inAnyOrder $keyValue/$keyValues"

        private fun containsKeyValuePairs(
            expect: Expect<Map<out String, Int>>,
            a: Pair<String, Int>,
            aX: Array<out Pair<String, Int>>
        ): Expect<Map<out String, Int>> =
            if (aX.isEmpty()) expect contains o inAny order entry (a.first to a.second)
            else expect contains o inAny order the pairs(a, *aX)

        private fun containsKeyValuePairsNullable(
            expect: Expect<Map<out String?, Int?>>,
            a: Pair<String?, Int?>,
            aX: Array<out Pair<String?, Int?>>
        ): Expect<Map<out String?, Int?>> =
            if (aX.isEmpty()) expect contains o inAny order entry (a.first to a.second)
            else expect contains o inAny order the pairs(a, *aX)

        private fun contains(
            expect: Expect<Map<out String, Int>>,
            a: Pair<String, Int>,
            aX: Array<out Pair<String, Int>>
        ): Expect<Map<out String, Int>> =
            if (aX.isEmpty()) expect contains (a.first to a.second)
            else expect contains pairs(a, *aX)

        @JvmName("containsNullable")
        private fun contains(
            expect: Expect<Map<out String?, Int?>>,
            a: Pair<String?, Int?>,
            aX: Array<out Pair<String?, Int?>>
        ): Expect<Map<out String?, Int?>> =
            if (aX.isEmpty()) expect contains (a.first to a.second)
            else expect contains pairs(a, *aX)
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

        map = map contains o inAny order entry (1 to "a")
        subMap = subMap contains o inAny order entry (1 to "a")
        nKeyMap = nKeyMap contains o inAny order entry (1 to "a")
        nValueMap = nValueMap contains o inAny order entry (1 to "a")
        nKeyValueMap = nKeyValueMap contains o inAny order entry (1 to "a")
        ronKeyValueMap = ronKeyValueMap contains o inAny order entry (1 to "a")
        starMap = starMap contains o inAny order entry (1 to "a")

        map = map contains o inAny order the pairs(1 to "a")
        subMap = subMap contains o inAny order the pairs(1 to "a")
        nKeyMap = nKeyMap contains o inAny order the pairs(1 to "a")
        nValueMap = nValueMap contains o inAny order the pairs(1 to "a")
        nKeyValueMap = nKeyValueMap contains o inAny order the pairs(1 to "a")
        ronKeyValueMap = ronKeyValueMap contains o inAny order the pairs(1 to "a")
        starMap = starMap contains o inAny order the pairs(1 to "a")

        map = map contains o inAny order the pairs(
            1 as Number to "a",
            1.2 to "b"
        )
        subMap = subMap contains o inAny order the pairs(
            1 as Number to "a",
            1.2 to "b"
        )
        nKeyMap = nKeyMap contains o inAny order the pairs(
            1 as Number to "a",
            1.2 to "b"
        )
        nValueMap = nValueMap contains o inAny order the pairs(
            1 as Number to "a",
            1.2 to "b"
        )
        nKeyValueMap = nKeyValueMap contains o inAny order the pairs(
            1 as Number to "a",
            1.2 to "b"
        )
        ronKeyValueMap = ronKeyValueMap contains o inAny order the pairs(
            1 as Number to "a",
            1.2 to "b"
        )
        starMap = starMap contains o inAny order the pairs(
            1 as Number to "a",
            1.2 to "b"
        )

        nKeyMap = nKeyMap contains o inAny order entry (null to "a")
        nValueMap = nValueMap contains o inAny order entry (1.2 to null)
        nKeyValueMap = nKeyValueMap contains o inAny order entry (null to null)
        ronKeyValueMap = ronKeyValueMap contains o inAny order entry (null to null)
        starMap = starMap contains o inAny order entry (null to null)

        nKeyMap = nKeyMap contains o inAny order the pairs(null to "a", 1 to "b")
        nValueMap = nValueMap contains o inAny order the pairs(1 to null)
        nKeyValueMap = nKeyValueMap contains o inAny order the pairs((null to "a"), null to null, 1 to null)
        ronKeyValueMap = ronKeyValueMap contains o inAny order the pairs((null to "a"), null to null, 1 to null)
        starMap = starMap contains o inAny order the pairs((null to "a"), null to null, 1 to null)


        /// ------------- shortcuts -----------------------------------------------------------------

        map = map contains (1 to "a")
        subMap = subMap contains (1 to "a")
        nKeyMap = nKeyMap contains (1 to "a")
        nValueMap = nValueMap contains (1 to "a")
        nKeyValueMap = nKeyValueMap contains (1 to "a")
        ronKeyValueMap = ronKeyValueMap contains (1 to "a")
        starMap = starMap contains (1 to "a")

        map = map contains (1 to "a")
        subMap = subMap contains (1 to "a")
        nKeyMap = nKeyMap contains (1 to "a")
        nValueMap = nValueMap contains (1 to "a")
        nKeyValueMap = nKeyValueMap contains (1 to "a")
        ronKeyValueMap = ronKeyValueMap contains (1 to "a")
        starMap = starMap contains (1 to "a")

        map = map contains pairs(1 as Number to "a", 1.2 to "b")
        subMap = subMap contains pairs(1 as Number to "a", 1.2 to "b")
        nKeyMap = nKeyMap contains pairs(1 as Number to "a", 1.2 to "b")
        nValueMap = nValueMap contains pairs(1 as Number to "a", 1.2 to "b")
        nKeyValueMap = nKeyValueMap contains pairs(1 as Number to "a", 1.2 to "b")
        ronKeyValueMap = ronKeyValueMap contains pairs(1 as Number to "a", 1.2 to "b")
        starMap = starMap contains pairs(1 as Number to "a", 1.2 to "b")

        nKeyMap = nKeyMap contains (null to "a")
        nValueMap = nValueMap contains (1.2 to null)
        nKeyValueMap = nKeyValueMap contains (null to null)
        ronKeyValueMap = ronKeyValueMap contains (null to "a")
        starMap = starMap contains (null to "a")

        nKeyMap = nKeyMap contains pairs(null to "a")
        nValueMap = nValueMap contains pairs(1.2 to null, 1 to null)
        nKeyValueMap = nKeyValueMap contains pairs(null to "a", null to null, 1 to null)
        ronKeyValueMap = ronKeyValueMap contains pairs(null to "a", null to null, 1 to null)
        starMap = starMap contains pairs(null to "a", null to null, 1 to null)
    }
}
