package ch.tutteli.atrium.api.cc.infix.en_GB

import ch.tutteli.atrium.verbs.internal.AssertionVerbFactory
import ch.tutteli.atrium.api.cc.infix.en_GB.keywords.contain
import ch.tutteli.atrium.creating.Assert

class IterableContainsNotEntriesAssertionsSpec : ch.tutteli.atrium.spec.integration.IterableContainsNotEntriesAssertionsSpec(
    AssertionVerbFactory,
    getContainsNotPair(),
    getContainsNotNullablePair(),
    "◆ ", "✔ ", "✘ ", "⚬ ", "» ", "▶ ", "◾ ",
    "[Atrium][Builder] "
){
    companion object : IterableContainsSpecBase() {

        private fun getContainsNotPair() = containsNot to Companion::containsNotFun

        private fun containsNotFun(plant: Assert<Iterable<Double>>, a: Assert<Double>.() -> Unit, aX: Array<out Assert<Double>.() -> Unit>): Assert<Iterable<Double>> {
            return if (aX.isEmpty()) {
                plant notTo contain entry a
            } else {
                plant notTo contain the Entries(a, *aX)
            }
        }

        private fun getContainsNotNullablePair() = containsNot to Companion::containsNotNullableFun

        private fun containsNotNullableFun(plant: Assert<Iterable<Double?>>, a: (Assert<Double>.() -> Unit)?, aX: Array<out (Assert<Double>.() -> Unit)?>): Assert<Iterable<Double?>> {
            return if (aX.isEmpty()) {
                plant notTo contain entry  a
            } else {
                plant notTo contain the NullableEntries(a, *aX)
            }
        }
    }
}
