package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.specs.integration.mfun2
import org.spekframework.spek2.Spek
import kotlin.jvm.JvmName
import ch.tutteli.atrium.api.infix.en_GB.MapToContainInAnyOrderOnlyKeyValuePairsExpectationsSpec.Companion as C

class MapToContainInAnyOrderOnlyKeyValuePairsExpectationsSpec : Spek({
    include(BuilderSpec)
    include(ShortcutSpec)
}) {

    object BuilderSpec : ch.tutteli.atrium.specs.integration.MapToContainInAnyOrderOnlyKeyValuePairsExpectationsSpec(
        toContainKeyValuePair_s to C::toContainKeyValuePairs,
        (toContainKeyValuePair_s to C::toContainKeyValuePairsNullable).withNullableSuffix(),
        "[Atrium][Builder] "
    )

    object ShortcutSpec : ch.tutteli.atrium.specs.integration.MapToContainInAnyOrderOnlyKeyValuePairsExpectationsSpec(
        mfun2<String, Int, Int>(C::toContainInAnyOrderOnly),
        mfun2<String?, Int?, Int?>(C::toContainInAnyOrderOnly).withNullableSuffix(),
        "[Atrium][Shortcut] "
    )

    companion object : MapToContainSpecBase() {
        val toContainKeyValuePair_s = "$toContain $filler $inAnyOrder $butOnly $keyValuePair/$theKeyValuePairs"

        private fun toContainKeyValuePairs(
            expect: Expect<Map<out String, Int>>,
            a: Pair<String, Int>,
            aX: Array<out Pair<String, Int>>
        ): Expect<Map<out String, Int>> =
            if (aX.isEmpty()) expect toContain o inAny order but only entry (a.first to a.second)
            else expect toContain o inAny order but only the pairs(a, *aX)

        private fun toContainKeyValuePairsNullable(
            expect: Expect<Map<out String?, Int?>>,
            a: Pair<String?, Int?>,
            aX: Array<out Pair<String?, Int?>>
        ): Expect<Map<out String?, Int?>> =
            if (aX.isEmpty()) expect toContain o inAny order but only entry (a.first to a.second)
            else expect toContain o inAny order but only the pairs(a, *aX)

        private fun toContainInAnyOrderOnly(
            expect: Expect<Map<out String, Int>>,
            a: Pair<String, Int>,
            aX: Array<out Pair<String, Int>>
        ): Expect<Map<out String, Int>> =
            if (aX.isEmpty()) expect toContainOnly a
            else expect toContainOnly pairs(a, *aX)

        @JvmName("toContainInAnyOrderOnlyNullable")
        private fun toContainInAnyOrderOnly(
            expect: Expect<Map<out String?, Int?>>,
            a: Pair<String?, Int?>,
            aX: Array<out Pair<String?, Int?>>
        ): Expect<Map<out String?, Int?>> =
            if (aX.isEmpty()) expect toContainOnly a
            else expect toContainOnly pairs(a, *aX)
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

        map = map toContain o inAny order but only entry (1 to "a")
        subMap = subMap toContain o inAny order but only entry (1 to "a")
        nKeyMap = nKeyMap toContain o inAny order but only entry (1 to "a")
        nValueMap = nValueMap toContain o inAny order but only entry (1 to "a")
        nKeyValueMap = nKeyValueMap toContain o inAny order but only entry (1 to "a")
        ronKeyValueMap = ronKeyValueMap toContain o inAny order but only entry (1 to "a")
        starMap = starMap toContain o inAny order but only entry (1 to "a")

        map = map toContain o inAny order but only the pairs(1 to "a")
        subMap = subMap toContain o inAny order but only the pairs(1 to "a")
        nKeyMap = nKeyMap toContain o inAny order but only the pairs(1 to "a")
        nValueMap = nValueMap toContain o inAny order but only the pairs(1 to "a")
        nKeyValueMap = nKeyValueMap toContain o inAny order but only the pairs(1 to "a")
        ronKeyValueMap = ronKeyValueMap toContain o inAny order but only the pairs(1 to "a")
        starMap = starMap toContain o inAny order but only the pairs(1 to "a")

        map = map toContain o inAny order but only the pairs(
            1 as Number to "a",
            1.2 to "b"
        )
        subMap = subMap toContain o inAny order but only the pairs(
            1 as Number to "a",
            1.2 to "b"
        )
        nKeyMap = nKeyMap toContain o inAny order but only the pairs(
            1 as Number to "a",
            1.2 to "b"
        )
        nValueMap = nValueMap toContain o inAny order but only the pairs(
            1 as Number to "a",
            1.2 to "b"
        )
        nKeyValueMap = nKeyValueMap toContain o inAny order but only the pairs(
            1 as Number to "a",
            1.2 to "b"
        )
        ronKeyValueMap = ronKeyValueMap toContain o inAny order but only the pairs(
            1 as Number to "a",
            1.2 to "b"
        )
        starMap = starMap toContain o inAny order but only the pairs(
            1 as Number to "a",
            1.2 to "b"
        )

        nKeyMap = nKeyMap toContain o inAny order but only entry (null to "a")
        nValueMap = nValueMap toContain o inAny order but only entry (1.2 to null)
        nKeyValueMap = nKeyValueMap toContain o inAny order but only entry (null to null)
        ronKeyValueMap = ronKeyValueMap toContain o inAny order but only entry (null to null)
        starMap = starMap toContain o inAny order but only entry (null to null)

        nKeyMap = nKeyMap toContain o inAny order but only the pairs(null to "a", 1 to "b")
        nValueMap = nValueMap toContain o inAny order but only the pairs(1 to null)
        nKeyValueMap = nKeyValueMap toContain o inAny order but only the pairs((null to "a"), null to null, 1 to null)
        ronKeyValueMap = ronKeyValueMap toContain o inAny order but only the pairs((null to "a"), null to null, 1 to null)
        starMap = starMap toContain o inAny order but only the pairs((null to "a"), null to null, 1 to null)


        /// ------------- shortcuts -----------------------------------------------------------------

        map = map toContainOnly (1 to "a")
        subMap = subMap toContainOnly (1 to "a")
        nKeyMap = nKeyMap toContainOnly (1 to "a")
        nValueMap = nValueMap toContainOnly (1 to "a")
        nKeyValueMap = nKeyValueMap toContainOnly (1 to "a")
        ronKeyValueMap = ronKeyValueMap toContainOnly (1 to "a")
        starMap = starMap toContainOnly (1 to "a")

        map = map toContainOnly (1 to "a")
        subMap = subMap toContainOnly (1 to "a")
        nKeyMap = nKeyMap toContainOnly (1 to "a")
        nValueMap = nValueMap toContainOnly (1 to "a")
        nKeyValueMap = nKeyValueMap toContainOnly (1 to "a")
        ronKeyValueMap = ronKeyValueMap toContainOnly (1 to "a")
        starMap = starMap toContainOnly (1 to "a")

        map = map toContainOnly pairs(1 as Number to "a", 1.2 to "b")
        subMap = subMap toContainOnly pairs(1 as Number to "a", 1.2 to "b")
        nKeyMap = nKeyMap toContainOnly pairs(1 as Number to "a", 1.2 to "b")
        nValueMap = nValueMap toContainOnly pairs(1 as Number to "a", 1.2 to "b")
        nKeyValueMap = nKeyValueMap toContainOnly pairs(1 as Number to "a", 1.2 to "b")
        ronKeyValueMap = ronKeyValueMap toContainOnly pairs(1 as Number to "a", 1.2 to "b")
        starMap = starMap toContainOnly pairs(1 as Number to "a", 1.2 to "b")

        nKeyMap = nKeyMap toContainOnly (null to "a")
        nValueMap = nValueMap toContainOnly (1.2 to null)
        nKeyValueMap = nKeyValueMap toContainOnly (null to null)
        ronKeyValueMap = ronKeyValueMap toContainOnly (null to "a")
        starMap = starMap toContainOnly (null to "a")

        nKeyMap = nKeyMap toContainOnly pairs(null to "a")
        nValueMap = nValueMap toContainOnly pairs(1.2 to null, 1 to null)
        nKeyValueMap = nKeyValueMap toContainOnly pairs(null to "a", null to null, 1 to null)
        ronKeyValueMap = ronKeyValueMap toContainOnly pairs(null to "a", null to null, 1 to null)
        starMap = starMap toContainOnly pairs(null to "a", null to null, 1 to null)
    }
}
