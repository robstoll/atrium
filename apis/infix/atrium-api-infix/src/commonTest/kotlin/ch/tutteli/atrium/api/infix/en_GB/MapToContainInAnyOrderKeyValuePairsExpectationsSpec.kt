package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.specs.integration.mfun2
import org.spekframework.spek2.Spek
import kotlin.jvm.JvmName
import ch.tutteli.atrium.api.infix.en_GB.MapToContainInAnyOrderKeyValuePairsExpectationsSpec.Companion as C

class MapToContainInAnyOrderKeyValuePairsExpectationsSpec : Spek({
    include(BuilderSpec)
    include(ShortcutSpec)
}) {
    object BuilderSpec : ch.tutteli.atrium.specs.integration.MapToContainInAnyOrderKeyValuePairsExpectationsSpec(
        toContainKeyValuePair_s to C::toContainKeyValuePairs,
        (toContainKeyValuePair_s to C::toContainKeyValuePairsNullable).withNullableSuffix(),
        "[Atrium][Shortcut] "
    )

    object ShortcutSpec : ch.tutteli.atrium.specs.integration.MapToContainInAnyOrderKeyValuePairsExpectationsSpec(
        mfun2<String, Int, Int>(C::toContain),
        mfun2<String?, Int?, Int?>(C::toContain).withNullableSuffix(),
        "[Atrium][Shortcut] "
    )

    companion object : MapToContainSpecBase() {
        val toContainKeyValuePair_s = "$toContain $filler $inAnyOrder $keyValue/$theEntries"

        private fun toContainKeyValuePairs(
            expect: Expect<Map<out String, Int>>,
            a: Pair<String, Int>,
            aX: Array<out Pair<String, Int>>
        ): Expect<Map<out String, Int>> =
            if (aX.isEmpty()) expect toContain o inAny order entry (a.first to a.second)
            else expect toContain o inAny order the pairs(a, *aX)

        private fun toContainKeyValuePairsNullable(
            expect: Expect<Map<out String?, Int?>>,
            a: Pair<String?, Int?>,
            aX: Array<out Pair<String?, Int?>>
        ): Expect<Map<out String?, Int?>> =
            if (aX.isEmpty()) expect toContain o inAny order entry (a.first to a.second)
            else expect toContain o inAny order the pairs(a, *aX)

        private fun toContain(
            expect: Expect<Map<out String, Int>>,
            a: Pair<String, Int>,
            aX: Array<out Pair<String, Int>>
        ): Expect<Map<out String, Int>> =
            if (aX.isEmpty()) expect toContain (a.first to a.second)
            else expect toContain pairs(a, *aX)

        @JvmName("toContainNullable")
        private fun toContain(
            expect: Expect<Map<out String?, Int?>>,
            a: Pair<String?, Int?>,
            aX: Array<out Pair<String?, Int?>>
        ): Expect<Map<out String?, Int?>> =
            if (aX.isEmpty()) expect toContain (a.first to a.second)
            else expect toContain pairs(a, *aX)
    }

    @Suppress("unused")
    private fun ambiguityTest() {
        var map: Expect<Map<Number, CharSequence>> = notImplemented()
        var subMap: Expect<LinkedHashMap<out Number, String>> = notImplemented()
        var nKeyMap: Expect<Map<Number?, CharSequence>> = notImplemented()
        var nValueMap: Expect<Map<Number, CharSequence?>> = notImplemented()
        var nKeyValueMap: Expect<Map<Number?, CharSequence?>> = notImplemented()
        var ronKeyValueMap: Expect<Map<out Number?, CharSequence?>> = notImplemented()
        var starMap: Expect<Map<*, *>> = notImplemented()

        map = map toContain o inAny order entry (1 to "a")
        subMap = subMap toContain o inAny order entry (1 to "a")
        nKeyMap = nKeyMap toContain o inAny order entry (1 to "a")
        nValueMap = nValueMap toContain o inAny order entry (1 to "a")
        nKeyValueMap = nKeyValueMap toContain o inAny order entry (1 to "a")
        ronKeyValueMap = ronKeyValueMap toContain o inAny order entry (1 to "a")
        starMap = starMap toContain o inAny order entry (1 to "a")

        map = map toContain o inAny order the pairs(1 to "a")
        subMap = subMap toContain o inAny order the pairs(1 to "a")
        nKeyMap = nKeyMap toContain o inAny order the pairs(1 to "a")
        nValueMap = nValueMap toContain o inAny order the pairs(1 to "a")
        nKeyValueMap = nKeyValueMap toContain o inAny order the pairs(1 to "a")
        ronKeyValueMap = ronKeyValueMap toContain o inAny order the pairs(1 to "a")
        starMap = starMap toContain o inAny order the pairs(1 to "a")

        map = map toContain o inAny order the pairs(
            1 as Number to "a",
            1.2 to "b"
        )
        subMap = subMap toContain o inAny order the pairs(
            1 as Number to "a",
            1.2 to "b"
        )
        nKeyMap = nKeyMap toContain o inAny order the pairs(
            1 as Number to "a",
            1.2 to "b"
        )
        nValueMap = nValueMap toContain o inAny order the pairs(
            1 as Number to "a",
            1.2 to "b"
        )
        nKeyValueMap = nKeyValueMap toContain o inAny order the pairs(
            1 as Number to "a",
            1.2 to "b"
        )
        ronKeyValueMap = ronKeyValueMap toContain o inAny order the pairs(
            1 as Number to "a",
            1.2 to "b"
        )
        starMap = starMap toContain o inAny order the pairs(
            1 as Number to "a",
            1.2 to "b"
        )

        nKeyMap = nKeyMap toContain o inAny order entry (null to "a")
        nValueMap = nValueMap toContain o inAny order entry (1.2 to null)
        nKeyValueMap = nKeyValueMap toContain o inAny order entry (null to null)
        ronKeyValueMap = ronKeyValueMap toContain o inAny order entry (null to null)
        starMap = starMap toContain o inAny order entry (null to null)

        nKeyMap = nKeyMap toContain o inAny order the pairs(null to "a", 1 to "b")
        nValueMap = nValueMap toContain o inAny order the pairs(1 to null)
        nKeyValueMap = nKeyValueMap toContain o inAny order the pairs((null to "a"), null to null, 1 to null)
        ronKeyValueMap = ronKeyValueMap toContain o inAny order the pairs((null to "a"), null to null, 1 to null)
        starMap = starMap toContain o inAny order the pairs((null to "a"), null to null, 1 to null)


        /// ------------- shortcuts -----------------------------------------------------------------

        map = map toContain (1 to "a")
        subMap = subMap toContain (1 to "a")
        nKeyMap = nKeyMap toContain (1 to "a")
        nValueMap = nValueMap toContain (1 to "a")
        nKeyValueMap = nKeyValueMap toContain (1 to "a")
        ronKeyValueMap = ronKeyValueMap toContain (1 to "a")
        starMap = starMap toContain (1 to "a")

        map = map toContain (1 to "a")
        subMap = subMap toContain (1 to "a")
        nKeyMap = nKeyMap toContain (1 to "a")
        nValueMap = nValueMap toContain (1 to "a")
        nKeyValueMap = nKeyValueMap toContain (1 to "a")
        ronKeyValueMap = ronKeyValueMap toContain (1 to "a")
        starMap = starMap toContain (1 to "a")

        map = map toContain pairs(1 as Number to "a", 1.2 to "b")
        subMap = subMap toContain pairs(1 as Number to "a", 1.2 to "b")
        nKeyMap = nKeyMap toContain pairs(1 as Number to "a", 1.2 to "b")
        nValueMap = nValueMap toContain pairs(1 as Number to "a", 1.2 to "b")
        nKeyValueMap = nKeyValueMap toContain pairs(1 as Number to "a", 1.2 to "b")
        ronKeyValueMap = ronKeyValueMap toContain pairs(1 as Number to "a", 1.2 to "b")
        starMap = starMap toContain pairs(1 as Number to "a", 1.2 to "b")

        nKeyMap = nKeyMap toContain (null to "a")
        nValueMap = nValueMap toContain (1.2 to null)
        nKeyValueMap = nKeyValueMap toContain (null to null)
        ronKeyValueMap = ronKeyValueMap toContain (null to "a")
        starMap = starMap toContain (null to "a")

        nKeyMap = nKeyMap toContain pairs(null to "a")
        nValueMap = nValueMap toContain pairs(1.2 to null, 1 to null)
        nKeyValueMap = nKeyValueMap toContain pairs(null to "a", null to null, 1 to null)
        ronKeyValueMap = ronKeyValueMap toContain pairs(null to "a", null to null, 1 to null)
        starMap = starMap toContain pairs(null to "a", null to null, 1 to null)
    }
}
