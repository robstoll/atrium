package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.utils.mapArguments
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.specs.integration.mfun2
import org.spekframework.spek2.Spek
import kotlin.jvm.JvmName
import ch.tutteli.atrium.api.fluent.en_GB.MapContainsInAnyOrderOnlyKeyValueAssertionsSpec.Companion as C

class MapContainsInAnyOrderOnlyKeyValueAssertionsSpec : Spek({
    include(BuilderSpec)
    include(ShortcutSpec)
}) {

    object BuilderSpec : ch.tutteli.atrium.specs.integration.MapContainsInAnyOrderOnlyKeyValueAssertionsSpec(
        containsKeyValue_s to C::containsKeyValues,
        (containsKeyValue_s to C::containsKeyValuesNullable).withNullableSuffix(),
        "◆ ", "✔ ", "✘ ", "❗❗ ", "⚬ ", "» ", "▶ ", "◾ ",
        "[Atrium][Builder] "
    )


    object ShortcutSpec : ch.tutteli.atrium.specs.integration.MapContainsInAnyOrderOnlyKeyValueAssertionsSpec(
        mfun2<String, Int, Expect<Int>.() -> Unit>(C::containsInAnyOrderOnly),
        mfun2<String?, Int?, (Expect<Int>.() -> Unit)?>(C::containsInAnyOrderOnly).withNullableSuffix(),
        "◆ ", "✔ ", "✘ ", "❗❗ ", "⚬ ", "» ", "▶ ", "◾ ",
        "[Atrium][Shortcut] "
    )

    companion object : MapContainsSpecBase() {
        val containsKeyValue_s = "${contains}.${inAnyOrder}.${only}.$keyValue/$keyValues"

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

        private fun containsInAnyOrderOnly(
            expect: Expect<Map<out String, Int>>,
            a: Pair<String, Expect<Int>.() -> Unit>,
            aX: Array<out Pair<String, Expect<Int>.() -> Unit>>
        ): Expect<Map<out String, Int>> = mapArguments(a, aX).to { KeyValue(it.first, it.second) }.let { (first, others) ->
            // TODO 0.15.0 replace with shortcut
            expect.contains.inAnyOrder.only.entries(first, *others)
//          expect.containsInAnyOrderOnly(first, *others)
        }

        @JvmName("containsInAnyOrderOnlyNullable")
        private fun containsInAnyOrderOnly(
            expect: Expect<Map<out String?, Int?>>,
            a: Pair<String?, (Expect<Int>.() -> Unit)?>,
            aX: Array<out Pair<String?, (Expect<Int>.() -> Unit)?>>
        ): Expect<Map<out String?, Int?>> = mapArguments(a, aX).to { KeyValue(it.first, it.second) }.let { (first, others) ->
            // TODO 0.15.0 replace with shortcut
            expect.contains.inAnyOrder.only.entries(first, *others)
//          expect.containsInAnyOrderOnly(first, *others)
        }
    }
}
