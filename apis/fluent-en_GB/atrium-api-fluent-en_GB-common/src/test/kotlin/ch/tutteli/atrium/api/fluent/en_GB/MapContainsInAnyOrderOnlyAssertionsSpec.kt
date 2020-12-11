package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.utils.mapArguments
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.specs.integration.mfun2
import org.spekframework.spek2.Spek
import kotlin.jvm.JvmName
import ch.tutteli.atrium.api.fluent.en_GB.MapContainsInAnyOrderOnlyAssertionsSpec.Companion as C

class MapContainsInAnyOrderOnlyAssertionsSpec : Spek({
    include(BuilderSpec)
    //TODO #68 in 0.15.0
//    include(ShortcutSpec)
}) {

    object BuilderSpec : ch.tutteli.atrium.specs.integration.MapContainsInAnyOrderOnlyAssertionsSpec(
        containsKeyValuePair_s to C::containsKeyValuePairs,
        (containsKeyValuePair_s to C::containsKeyValuePairsNullable).withNullableSuffix(),
        containsKeyValue_s to C::containsKeyValues,
        (containsKeyValue_s to C::containsKeyValuesNullable).withNullableSuffix(),
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

        private fun containsKeyValues(
            expect: Expect<Map<out String, Int>>,
            a: Pair<String, Expect<Int>.() -> Unit>,
            aX: Array<out Pair<String, Expect<Int>.() -> Unit>>
        ) = mapArguments(a, aX).to { KeyValue(it.first, it.second) }.let { (first, others) ->
            if (others.isEmpty()) expect.contains.inAnyOrder.only.entry(first)
            else expect.contains.inAnyOrder.only.entries(first, *others)
        }

        private fun containsKeyValuesNullable(
            expect: Expect<Map<out String?, Int?>>,
            a: Pair<String?, (Expect<Int>.() -> Unit)?>,
            aX: Array<out Pair<String?, (Expect<Int>.() -> Unit)?>>
        ) = mapArguments(a, aX).to { KeyValue(it.first, it.second) }.let { (first, others) ->
            if (others.isEmpty()) expect.contains.inAnyOrder.only.entry(first)
            else expect.contains.inAnyOrder.only.entries(first, *others)
        }

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
