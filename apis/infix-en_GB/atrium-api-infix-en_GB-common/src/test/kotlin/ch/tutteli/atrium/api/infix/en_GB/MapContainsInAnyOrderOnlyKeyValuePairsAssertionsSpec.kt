package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.*
import org.spekframework.spek2.Spek
import ch.tutteli.atrium.api.infix.en_GB.MapContainsInAnyOrderOnlyKeyValuePairsAssertionsSpec.Companion as C

class MapContainsInAnyOrderOnlyKeyValuePairsAssertionsSpec : Spek({
    include(BuilderSpec)
    //TODO #68 in 0.15.0
//    include(ShortcutSpec)
}) {

    object BuilderSpec : ch.tutteli.atrium.specs.integration.MapContainsInAnyOrderOnlyKeyValuePairsAssertionsSpec(
        containsKeyValuePair_s to C::containsKeyValuePairs,
        (containsKeyValuePair_s to C::containsKeyValuePairsNullable).withNullableSuffix(),
        "◆ ", "✔ ", "✘ ", "❗❗ ", "⚬ ", "» ", "▶ ", "◾ ",
        "[Atrium][Builder] "
    )

    //TODO #68 in 0.15.0
//    object ShortcutSpec : ch.tutteli.atrium.specs.integration.MapContainsInAnyOrderOnlyAssertionsSpec(
//        mfun2<String, Int, Int>(Expect<Map<out String, Int>>::contains),
//        mfun2<String?, Int?, Int?>(Expect<Map<out String?, Int?>>::contains).withNullableSuffix(),
//        mfun2<String, Int, Expect<Int>.() -> Unit>(Companion::contains).adjustName { "$it ${KeyValue::class.simpleName}" },
//        mfun2<String?, Int?, (Expect<Int>.() -> Unit)?>(Companion::contains).adjustName { "$it ${KeyValue::class.simpleName}" }
//            .withNullableSuffix(),
//        "[Atrium][Shortcut] "
//    )

    companion object : MapContainsSpecBase() {
        val containsKeyValuePair_s = "${contains}.${inAnyOrder}.${andOnly}.$keyValuePair/$keyValuePairs"

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
            if (aX.isEmpty())  expect contains o inAny order but only entry (a.first to a.second)
            else expect contains o inAny order but only the pairs(a, *aX)

        //TODO #68 in 0.15.0
//        private fun contains(
//            expect: Expect<Map<out String, Int>>,
//            keyValue: Pair<String, Expect<Int>.() -> Unit>,
//            otherKeyValues: Array<out Pair<String, Expect<Int>.() -> Unit>>
//        ) = mapArguments(keyValue, otherKeyValues).to { KeyValue(it.first, it.second) }.let { (first, others) ->
//            expect.containsInAnyOrderOnly(first, *others)
//        }
//
//        @JvmName("containsNullable")
//        private fun contains(
//            expect: Expect<Map<out String?, Int?>>,
//            keyValue: Pair<String?, (Expect<Int>.() -> Unit)?>,
//            otherKeyValues: Array<out Pair<String?, (Expect<Int>.() -> Unit)?>>
//        ) = mapArguments(keyValue, otherKeyValues).to { KeyValue(it.first, it.second) }.let { (first, others) ->
//            expect.containsInAnyOrderOnly(first, *others)
//        }
    }
}
