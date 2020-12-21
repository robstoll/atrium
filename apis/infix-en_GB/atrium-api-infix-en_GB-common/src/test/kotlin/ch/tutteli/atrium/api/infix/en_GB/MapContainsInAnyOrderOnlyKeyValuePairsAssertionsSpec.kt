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
            // TODO 0.15.0 replace with shortcut
            if (aX.isEmpty()) expect contains o inAny order but only entry (a.first to a.second)
            else expect contains o inAny order but only the pairs(a, *aX)
//            if (aX.isEmpty()) expect containsInAnyOrderOnly (a.first to a.second)
//            else expect containsInAnyOrderOnly pairs(a, *aX)

        @JvmName("containsInAnyOrderOnlyNullable")
        private fun containsInAnyOrderOnly(
            expect: Expect<Map<out String?, Int?>>,
            a: Pair<String?, Int?>,
            aX: Array<out Pair<String?, Int?>>
        ): Expect<Map<out String?, Int?>> =
            // TODO 0.15.0 replace with shortcut
            if (aX.isEmpty()) expect contains o inAny order but only entry (a.first to a.second)
            else expect contains o inAny order but only the pairs(a, *aX)
//            if (aX.isEmpty()) expect containsInAnyOrderOnly (a.first to a.second)
//            else expect containsInAnyOrderOnly pairs(a, *aX)
    }
}
