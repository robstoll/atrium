package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.creating.Expect
import kotlin.reflect.KFunction3

class CharSequenceContainsContainsNotAssertionsSpec :
    ch.tutteli.atrium.specs.integration.CharSequenceContainsContainsNotAssertionsSpec(
        getContainsPair(),
        getContainsNotPair(),
        "◆ ", "⚬ ", "▶ "
    ) {
    companion object : CharSequenceContainsSpecBase() {
        private val containsFun: KFunction3<Expect<CharSequence>, Any, Array<out Any>, Expect<CharSequence>> =
            Expect<CharSequence>::contains

        fun getContainsPair() = containsFun.name to Companion::containsShortcut

        private fun containsShortcut(plant: Expect<CharSequence>, a: Any, aX: Array<out Any>) = plant.contains(a, *aX)

        private val containsNotFun: KFunction3<Expect<CharSequence>, Any, Array<out Any>, Expect<CharSequence>> =
            Expect<CharSequence>::containsNot

        private fun getContainsNotPair() = containsNotFun.name to Companion::containsNotShortcut

        private fun containsNotShortcut(plant: Expect<CharSequence>, a: Any, aX: Array<out Any>) =
            plant.containsNot(a, *aX)
    }
}
