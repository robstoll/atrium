package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.specs.integration.mfun2
import org.spekframework.spek2.Spek
import kotlin.jvm.JvmName
import ch.tutteli.atrium.api.fluent.en_GB.MapContainsInAnyOrderOnlyKeyValuePairsAssertionsSpec.Companion as C

class MapContainsInAnyOrderOnlyKeyValuePairsAssertionsSpec : Spek({
    include(BuilderSpec)
    include(ShortcutSpec)
}) {

    object BuilderSpec : ch.tutteli.atrium.specs.integration.MapContainsInAnyOrderOnlyKeyValuePairsAssertionsSpec(
        containsKeyValuePair_s to C::containsKeyValuePairs,
        (containsKeyValuePair_s to C::containsKeyValuePairsNullable).withNullableSuffix(),
        "◆ ", "✔ ", "✘ ", "❗❗ ", "⚬ ", "» ", "▶ ", "◾ ",
        "[Atrium][Builder] "
    )

    object ShortcutSpec : ch.tutteli.atrium.specs.integration.MapContainsInAnyOrderOnlyKeyValuePairsAssertionsSpec(
        mfun2<String, Int, Int>(C::containsInAnyOrderOnly),
        mfun2<String?, Int?, Int?>(C::containsInAnyOrderOnly).withNullableSuffix(),
        "◆ ", "✔ ", "✘ ", "❗❗ ", "⚬ ", "» ", "▶ ", "◾ ",
        "[Atrium][Shortcut] "
    )

    companion object : MapContainsSpecBase() {
        val containsKeyValuePair_s = "${contains}.${inAnyOrder}.${only}.$keyValuePair/$keyValuePairs"
        val containsKeyValue_s = "${contains}.${inAnyOrder}.${only}.$keyValue/$keyValues"

        private fun containsKeyValuePairs(
            expect: Expect<Map<out String, Int>>,
            a: Pair<String, Int>,
            aX: Array<out Pair<String, Int>>
        ): Expect<Map<out String, Int>> =
            if (aX.isEmpty()) expect.contains.inAnyOrder.only.entry(a)
            else expect.contains.inAnyOrder.only.entries(a, *aX)

        private fun containsKeyValuePairsNullable(
            expect: Expect<Map<out String?, Int?>>,
            a: Pair<String?, Int?>,
            aX: Array<out Pair<String?, Int?>>
        ): Expect<Map<out String?, Int?>> =
            if (aX.isEmpty()) expect.contains.inAnyOrder.only.entry(a)
            else expect.contains.inAnyOrder.only.entries(a, *aX)

        private fun containsInAnyOrderOnly(
            expect: Expect<Map<out String, Int>>,
            a: Pair<String, Int>,
            aX: Array<out Pair<String, Int>>
        ): Expect<Map<out String, Int>> =
            // TODO 0.15.0 replace with shortcut
            expect.contains.inAnyOrder.only.entries(a, *aX)
//            expect.containsInAnyOrderOnlyEntries(a, *aX)

        @JvmName("containsInAnyOrderOnlyNullable")
        private fun containsInAnyOrderOnly(
            expect: Expect<Map<out String?, Int?>>,
            a: Pair<String?, Int?>,
            aX: Array<out Pair<String?, Int?>>
        ): Expect<Map<out String?, Int?>> =
            // TODO 0.15.0 replace with shortcut
            expect.contains.inAnyOrder.only.entries(a, *aX)
//            expect.containsInAnyOrderOnlyEntries(a, *aX)
    }
}
