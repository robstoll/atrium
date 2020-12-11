package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.utils.mapArguments
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.specs.integration.mfun2
import org.spekframework.spek2.Spek
import kotlin.jvm.JvmName
import ch.tutteli.atrium.api.fluent.en_GB.MapContainsInAnyOrderKeyValueAssertionsSpec.Companion as C

class MapContainsInAnyOrderKeyValueAssertionsSpec : Spek({
    include(BuilderSpec)
    include(ShortcutSpec)
}) {

    object BuilderSpec : ch.tutteli.atrium.specs.integration.MapContainsInAnyOrderKeyValueAssertionsSpec(
        containsKeyValue_s to C::containsKeyValues,
        (containsKeyValue_s to C::containsKeyValuesNullable).withNullableSuffix(),
        "[Atrium][Builder] "
    )

    object ShortcutSpec : ch.tutteli.atrium.specs.integration.MapContainsInAnyOrderKeyValueAssertionsSpec(
        mfun2<String, Int, Expect<Int>.() -> Unit>(C::contains).adjustName { "$it ${KeyValue::class.simpleName}" },
        mfun2<String?, Int?, (Expect<Int>.() -> Unit)?>(C::contains).adjustName { "$it ${KeyValue::class.simpleName}" }
            .withNullableSuffix(),
        "[Atrium][Shortcut] "
    )

    companion object : MapContainsSpecBase() {
        val containsKeyValue_s = "${contains}.${inAnyOrder}.$keyValue/$keyValues"

        private fun containsKeyValues(
            expect: Expect<Map<out String, Int>>,
            a: Pair<String, Expect<Int>.() -> Unit>,
            aX: Array<out Pair<String, Expect<Int>.() -> Unit>>
        ): Expect<Map<out String, Int>> =
            mapArguments(a, aX).to { KeyValue(it.first, it.second) }.let { (first, others) ->
                if (others.isEmpty()) expect.contains.inAnyOrder.entry(first)
                else expect.contains.inAnyOrder.entries(first, *others)
            }

        private fun containsKeyValuesNullable(
            expect: Expect<Map<out String?, Int?>>,
            a: Pair<String?, (Expect<Int>.() -> Unit)?>,
            aX: Array<out Pair<String?, (Expect<Int>.() -> Unit)?>>
        ) = mapArguments(a, aX).to { KeyValue(it.first, it.second) }.let { (first, others) ->
            if (others.isEmpty()) expect.contains.inAnyOrder.entry(first)
            else expect.contains.inAnyOrder.entries(first, *others)
        }

        private fun contains(
            expect: Expect<Map<out String, Int>>,
            keyValue: Pair<String, Expect<Int>.() -> Unit>,
            otherKeyValues: Array<out Pair<String, Expect<Int>.() -> Unit>>
        ) = mapArguments(keyValue, otherKeyValues).to { KeyValue(it.first, it.second) }.let { (first, others) ->
            expect.contains(first, *others)
        }

        @JvmName("containsNullable")
        private fun contains(
            expect: Expect<Map<out String?, Int?>>,
            keyValue: Pair<String?, (Expect<Int>.() -> Unit)?>,
            otherKeyValues: Array<out Pair<String?, (Expect<Int>.() -> Unit)?>>
        ) = mapArguments(keyValue, otherKeyValues).to { KeyValue(it.first, it.second) }.let { (first, others) ->
            expect.contains(first, *others)
        }

    }
}
