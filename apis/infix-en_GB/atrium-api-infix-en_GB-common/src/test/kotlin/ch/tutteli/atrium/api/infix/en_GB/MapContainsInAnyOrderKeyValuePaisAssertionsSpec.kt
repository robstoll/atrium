package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.specs.integration.mfun2
import org.spekframework.spek2.Spek
import kotlin.jvm.JvmName
import ch.tutteli.atrium.api.infix.en_GB.MapContainsInAnyOrderKeyValuePaisAssertionsSpec.Companion as C

class MapContainsInAnyOrderKeyValuePaisAssertionsSpec : Spek({
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
        val containsKeyValuePair_s = "$contains $filler $inAnyOrder keyValue"

        private fun containsKeyValuePairs(
            expect: Expect<Map<out String, Int>>,
            pair: Pair<String, Int>,
            otherPairs: Array<out Pair<String, Int>>
        ): Expect<Map<out String, Int>> =
            if (otherPairs.isEmpty()) expect contains o inAny order entry (pair.first to pair.second)
            else expect contains o inAny order the pairs(pair, *otherPairs)

        private fun containsKeyValuePairsNullable(
            expect: Expect<Map<out String?, Int?>>,
            pair: Pair<String?, Int?>,
            otherPairs: Array<out Pair<String?, Int?>>
        ): Expect<Map<out String?, Int?>> =
            if (otherPairs.isEmpty()) expect contains o inAny order entry (pair.first to pair.second)
            else expect contains o inAny order the pairs(pair, *otherPairs)

        private fun contains(
            expect: Expect<Map<out String, Int>>,
            pair: Pair<String, Int>,
            otherPairs: Array<out Pair<String, Int>>
        ): Expect<Map<out String, Int>> =
            if (otherPairs.isEmpty()) expect contains (pair.first to pair.second)
            else expect contains pairs(pair, *otherPairs)

        @JvmName("containsNullable")
        private fun contains(
            expect: Expect<Map<out String?, Int?>>,
            pair: Pair<String?, Int?>,
            otherPairs: Array<out Pair<String?, Int?>>
        ): Expect<Map<out String?, Int?>> =
            if (otherPairs.isEmpty()) expect contains (pair.first to pair.second)
            else expect contains pairs(pair, *otherPairs)
    }
}
