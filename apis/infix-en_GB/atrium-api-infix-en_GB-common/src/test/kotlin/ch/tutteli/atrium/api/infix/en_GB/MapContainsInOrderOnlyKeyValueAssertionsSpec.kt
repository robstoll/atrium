package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.utils.mapArguments
import ch.tutteli.atrium.specs.*
import org.spekframework.spek2.Spek
import ch.tutteli.atrium.api.infix.en_GB.MapContainsInOrderOnlyKeyValueAssertionsSpec.Companion as C

class MapContainsInOrderOnlyKeyValueAssertionsSpec : Spek({
    include(BuilderSpec)
}) {

    object BuilderSpec : ch.tutteli.atrium.specs.integration.MapContainsInOrderOnlyKeyValueAssertionsSpec(
        containsKeyValue_s to C::containsKeyValues,
        (containsKeyValue_s to C::containsKeyValuesNullable).withNullableSuffix(),
        "* ", "(/) ", "(x) ", "(!) ", "- ", "» ", ">> ", "=> ",
        "[Atrium][Builder] "
    )

    companion object : MapContainsSpecBase() {
        val containsKeyValue_s = "$contains $filler $inOrder $andOnly $keyValue/$keyValues"

        private fun containsKeyValues(
            expect: Expect<Map<out String, Int>>,
            a: Pair<String, Expect<Int>.() -> Unit>,
            aX: Array<out Pair<String, Expect<Int>.() -> Unit>>
        ) = mapArguments(a, aX).to { keyValue(it.first, it.second) }.let { (first, others) ->
            if (others.isEmpty()) expect contains o inGiven order and only entry first
            else expect contains o inGiven order and only the keyValues(first, *others)
        }

        private fun containsKeyValuesNullable(
            expect: Expect<Map<out String?, Int?>>,
            a: Pair<String?, (Expect<Int>.() -> Unit)?>,
            aX: Array<out Pair<String?, (Expect<Int>.() -> Unit)?>>
        ) = mapArguments(a, aX).to { keyValue(it.first, it.second) }.let { (first, others) ->
            if (others.isEmpty()) expect contains o inGiven order and only entry first
            else expect contains o inGiven order and only the keyValues(first, *others)
        }
    }
}
