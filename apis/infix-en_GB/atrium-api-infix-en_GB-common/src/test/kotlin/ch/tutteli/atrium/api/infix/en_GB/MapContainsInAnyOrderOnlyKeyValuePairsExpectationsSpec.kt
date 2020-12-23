package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.utils.mapArguments
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.specs.integration.mfun2
import org.spekframework.spek2.Spek
import kotlin.jvm.JvmName
import ch.tutteli.atrium.api.infix.en_GB.MapContainsInAnyOrderOnlyKeyValuePairsAssertionsSpec.Companion as C

class MapContainsInAnyOrderOnlyKeyValuePairsAssertionsSpec : Spek({
    include(BuilderSpec)
    include(ShortcutSpec)
}) {

    object BuilderSpec : ch.tutteli.atrium.specs.integration.MapContainsInAnyOrderOnlyKeyValuePairsAssertionsSpec(
        containsKeyValuePair_s to C::containsKeyValuePairs,
        (containsKeyValuePair_s to C::containsKeyValuePairsNullable).withNullableSuffix(),
        "* ", "(/) ", "(x) ", "(!) ", "- ", "» ", ">> ", "=> ",
        "[Atrium][Builder] "
    )

    object ShortcutSpec : ch.tutteli.atrium.specs.integration.MapContainsInAnyOrderOnlyKeyValuePairsAssertionsSpec(
        mfun2<String, Int, Int>(C::containsInAnyOrderOnly),
        mfun2<String?, Int?, Int?>(C::containsInAnyOrderOnly).withNullableSuffix(),
        "* ", "(/) ", "(x) ", "(!) ", "- ", "» ", ">> ", "=> ",
        "[Atrium][Shortcut] "
    )

    companion object : MapContainsSpecBase() {
        val containsKeyValuePair_s = "$contains $filler $inAnyOrder $butOnly $keyValuePair/$keyValuePairs"

        private fun containsKeyValuePairs(
            expect: Expect<Map<out String, Int>>,
            a: Pair<String, Int>,
            aX: Array<out Pair<String, Int>>
        ): Expect<Map<out String, Int>> =
            if (aX.isEmpty()) expect contains o inAny order but only entry (a.first to a.second)
            else expect contains o inAny order but only the pairs(a, *aX)

        private fun containsKeyValuePairsNullable(
            expect: Expect<Map<out String?, Int?>>,
            a: Pair<String?, Int?>,
            aX: Array<out Pair<String?, Int?>>
        ): Expect<Map<out String?, Int?>> =
            if (aX.isEmpty()) expect contains o inAny order but only entry (a.first to a.second)
            else expect contains o inAny order but only the pairs(a, *aX)

        private fun containsInAnyOrderOnly(
            expect: Expect<Map<out String, Int>>,
            a: Pair<String, Int>,
            aX: Array<out Pair<String, Int>>
        ): Expect<Map<out String, Int>> =
            if (aX.isEmpty()) expect containsOnly a
            else expect containsOnly pairs(a, *aX)

        @JvmName("containsInAnyOrderOnlyNullable")
        private fun containsInAnyOrderOnly(
            expect: Expect<Map<out String?, Int?>>,
            a: Pair<String?, Int?>,
            aX: Array<out Pair<String?, Int?>>
        ): Expect<Map<out String?, Int?>> =
            if (aX.isEmpty()) expect containsOnly a
            else expect containsOnly pairs(a, *aX)
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

        map = map contains o inAny order but only entry (1 to "a")
        subMap = subMap contains o inAny order but only entry (1 to "a")
        nKeyMap = nKeyMap contains o inAny order but only entry (1 to "a")
        nValueMap = nValueMap contains o inAny order but only entry (1 to "a")
        nKeyValueMap = nKeyValueMap contains o inAny order but only entry (1 to "a")
        ronKeyValueMap = ronKeyValueMap contains o inAny order but only entry (1 to "a")
        starMap = starMap contains o inAny order but only entry (1 to "a")

        map = map contains o inAny order but only the pairs(1 to "a")
        subMap = subMap contains o inAny order but only the pairs(1 to "a")
        nKeyMap = nKeyMap contains o inAny order but only the pairs(1 to "a")
        nValueMap = nValueMap contains o inAny order but only the pairs(1 to "a")
        nKeyValueMap = nKeyValueMap contains o inAny order but only the pairs(1 to "a")
        ronKeyValueMap = ronKeyValueMap contains o inAny order but only the pairs(1 to "a")
        starMap = starMap contains o inAny order but only the pairs(1 to "a")

        map = map contains o inAny order but only the pairs(
            1 as Number to "a",
            1.2 to "b"
        )
        subMap = subMap contains o inAny order but only the pairs(
            1 as Number to "a",
            1.2 to "b"
        )
        nKeyMap = nKeyMap contains o inAny order but only the pairs(
            1 as Number to "a",
            1.2 to "b"
        )
        nValueMap = nValueMap contains o inAny order but only the pairs(
            1 as Number to "a",
            1.2 to "b"
        )
        nKeyValueMap = nKeyValueMap contains o inAny order but only the pairs(
            1 as Number to "a",
            1.2 to "b"
        )
        ronKeyValueMap = ronKeyValueMap contains o inAny order but only the pairs(
            1 as Number to "a",
            1.2 to "b"
        )
        starMap = starMap contains o inAny order but only the pairs(
            1 as Number to "a",
            1.2 to "b"
        )

        nKeyMap = nKeyMap contains o inAny order but only entry (null to "a")
        nValueMap = nValueMap contains o inAny order but only entry (1.2 to null)
        nKeyValueMap = nKeyValueMap contains o inAny order but only entry (null to null)
        ronKeyValueMap = ronKeyValueMap contains o inAny order but only entry (null to null)
        starMap = starMap contains o inAny order but only entry (null to null)

        nKeyMap = nKeyMap contains o inAny order but only the pairs(null to "a", 1 to "b")
        nValueMap = nValueMap contains o inAny order but only the pairs(1 to null)
        nKeyValueMap = nKeyValueMap contains o inAny order but only the pairs((null to "a"), null to null, 1 to null)
        ronKeyValueMap = ronKeyValueMap contains o inAny order but only the pairs((null to "a"), null to null, 1 to null)
        starMap = starMap contains o inAny order but only the pairs((null to "a"), null to null, 1 to null)


        /// ------------- shortcuts -----------------------------------------------------------------

        map = map containsOnly (1 to "a")
        subMap = subMap containsOnly (1 to "a")
        nKeyMap = nKeyMap containsOnly (1 to "a")
        nValueMap = nValueMap containsOnly (1 to "a")
        nKeyValueMap = nKeyValueMap containsOnly (1 to "a")
        ronKeyValueMap = ronKeyValueMap containsOnly (1 to "a")
        starMap = starMap containsOnly (1 to "a")

        map = map containsOnly (1 to "a")
        subMap = subMap containsOnly (1 to "a")
        nKeyMap = nKeyMap containsOnly (1 to "a")
        nValueMap = nValueMap containsOnly (1 to "a")
        nKeyValueMap = nKeyValueMap containsOnly (1 to "a")
        ronKeyValueMap = ronKeyValueMap containsOnly (1 to "a")
        starMap = starMap containsOnly (1 to "a")

        map = map containsOnly pairs(1 as Number to "a", 1.2 to "b")
        subMap = subMap containsOnly pairs(1 as Number to "a", 1.2 to "b")
        nKeyMap = nKeyMap containsOnly pairs(1 as Number to "a", 1.2 to "b")
        nValueMap = nValueMap containsOnly pairs(1 as Number to "a", 1.2 to "b")
        nKeyValueMap = nKeyValueMap containsOnly pairs(1 as Number to "a", 1.2 to "b")
        ronKeyValueMap = ronKeyValueMap containsOnly pairs(1 as Number to "a", 1.2 to "b")
        starMap = starMap containsOnly pairs(1 as Number to "a", 1.2 to "b")

        nKeyMap = nKeyMap containsOnly (null to "a")
        nValueMap = nValueMap containsOnly (1.2 to null)
        nKeyValueMap = nKeyValueMap containsOnly (null to null)
        ronKeyValueMap = ronKeyValueMap containsOnly (null to "a")
        starMap = starMap containsOnly (null to "a")

        nKeyMap = nKeyMap containsOnly pairs(null to "a")
        nValueMap = nValueMap containsOnly pairs(1.2 to null, 1 to null)
        nKeyValueMap = nKeyValueMap containsOnly pairs(null to "a", null to null, 1 to null)
        ronKeyValueMap = ronKeyValueMap containsOnly pairs(null to "a", null to null, 1 to null)
        starMap = starMap containsOnly pairs(null to "a", null to null, 1 to null)
    }
}
