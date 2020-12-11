package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.api.infix.en_GB.creating.map.KeyWithValueCreator
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.utils.mapArguments
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.specs.integration.mfun2
import org.spekframework.spek2.Spek
import kotlin.jvm.JvmName
import ch.tutteli.atrium.api.infix.en_GB.MapContainsInAnyOrderKeyValueAssertionsSpec.Companion as C

class MapContainsInAnyOrderKeyValueAssertionsSpec : Spek({
    include(BuilderSpec)
    include(ShortcutSpec)
}) {
    object BuilderSpec : ch.tutteli.atrium.specs.integration.MapContainsInAnyOrderKeyValueAssertionsSpec(
        containsKeyValue_s to C::containsKeyValues,
        (containsKeyValue_s to C::containsKeyValuesNullable).withNullableSuffix(),
        "[Atrium][Shortcut] "
    )

    object ShortcutSpec : ch.tutteli.atrium.specs.integration.MapContainsInAnyOrderKeyValueAssertionsSpec(
        mfun2<String, Int, Expect<Int>.() -> Unit>(C::contains),
        mfun2<String?, Int?, (Expect<Int>.() -> Unit)?>(C::contains).withNullableSuffix(),
        "[Atrium][Shortcut] "
    )

    companion object : MapContainsSpecBase() {
        val containsKeyValue_s = "$contains $filler $inAnyOrder "

        private fun containsKeyValues(
            expect: Expect<Map<out String, Int>>,
            a: Pair<String, Expect<Int>.() -> Unit>,
            aX: Array<out Pair<String, Expect<Int>.() -> Unit>>
        ): Expect<Map<out String, Int>> =
            mapArguments(a, aX).to { KeyWithValueCreator(it.first, it.second) }.let { (first, others) ->
                if (others.isEmpty()) expect contains o inAny order entry first
                else expect contains o inAny order the keyValues(first, *others)
            }

        private fun containsKeyValuesNullable(
            expect: Expect<Map<out String?, Int?>>,
            a: Pair<String?, (Expect<Int>.() -> Unit)?>,
            aX: Array<out Pair<String?, (Expect<Int>.() -> Unit)?>>
        ): Expect<Map<out String?, Int?>> =
            mapArguments(a, aX).to { KeyWithValueCreator(it.first, it.second) }.let { (first, others) ->
                if (others.isEmpty()) expect contains o inAny order entry first
                else expect contains o inAny order the keyValues(first, *others)
            }

        @JvmName("containsKeyWithValueAssertions")
        private fun contains(
            expect: Expect<Map<out String, Int>>,
            keyValue: Pair<String, Expect<Int>.() -> Unit>,
            otherKeyValues: Array<out Pair<String, Expect<Int>.() -> Unit>>
        ): Expect<Map<out String, Int>> =
            if (otherKeyValues.isEmpty()) expect contains keyValue(keyValue.first, keyValue.second)
            else mapArguments(keyValue, otherKeyValues)
                .to { keyValue(it.first, it.second) }
                .let { (first, others) -> expect contains all(first, *others) }

        @JvmName("containsKeyWithNullableValueAssertions")
        private fun contains(
            expect: Expect<Map<out String?, Int?>>,
            keyValue: Pair<String?, (Expect<Int>.() -> Unit)?>,
            otherKeyValues: Array<out Pair<String?, (Expect<Int>.() -> Unit)?>>
        ): Expect<Map<out String?, Int?>> =
            if (otherKeyValues.isEmpty()) expect contains keyValue(keyValue.first, keyValue.second)
            else mapArguments(keyValue, otherKeyValues)
                .to { keyValue(it.first, it.second) }
                .let { (first, others) -> expect contains all(first, *others) }
    }
}
