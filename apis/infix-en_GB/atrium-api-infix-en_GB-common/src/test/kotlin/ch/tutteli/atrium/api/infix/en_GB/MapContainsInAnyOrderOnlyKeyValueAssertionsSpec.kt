package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.utils.mapArguments
import ch.tutteli.atrium.specs.*
import org.spekframework.spek2.Spek
import ch.tutteli.atrium.api.infix.en_GB.MapContainsInAnyOrderOnlyKeyValueAssertionsSpec.Companion as C

class MapContainsInAnyOrderOnlyKeyValueAssertionsSpec : Spek({
    include(BuilderSpec)
    //TODO #68 in 0.15.0
//    include(ShortcutSpec)
}) {

    object BuilderSpec : ch.tutteli.atrium.specs.integration.MapContainsInAnyOrderOnlyKeyValueAssertionsSpec(
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
        val containsKeyValue_s = "$contains $filler $inAnyOrder $butOnly $keyValue/$keyValue"

        private fun containsKeyValues(
            expect: Expect<Map<out String, Int>>,
            a: Pair<String, Expect<Int>.() -> Unit>,
            aX: Array<out Pair<String, Expect<Int>.() -> Unit>>
        ) = mapArguments(a, aX).to { keyValue(it.first, it.second) }.let { (first, others) ->
            if (others.isEmpty()) expect contains o inAny order but only entry first
            else expect contains o inAny order but only the keyValues(first, *others)
        }

        private fun containsKeyValuesNullable(
            expect: Expect<Map<out String?, Int?>>,
            a: Pair<String?, (Expect<Int>.() -> Unit)?>,
            aX: Array<out Pair<String?, (Expect<Int>.() -> Unit)?>>
        ) = mapArguments(a, aX).to { keyValue(it.first, it.second) }.let { (first, others) ->
            if (others.isEmpty()) expect contains o inAny order but only entry first
            else expect contains o inAny order but only the keyValues(first, *others)
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
