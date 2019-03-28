package ch.tutteli.atrium.api.cc.en_GB

import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.verbs.internal.AssertionVerbFactory

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
                plant.containsNot.entry(a)
            } else {
                plant.containsNot.entries(a, *aX)
            }
        }

        private fun getContainsNotNullablePair() = "$containsNot nullable" to Companion::containsNotNullableFun

        private fun containsNotNullableFun(plant: Assert<Iterable<Double?>>, a: (Assert<Double>.() -> Unit)?, aX: Array<out (Assert<Double>.() -> Unit)?>): Assert<Iterable<Double?>> {
            return if (aX.isEmpty()) {
                plant.containsNot.entry(a)
            } else {
                plant.containsNot.entries(a, *aX)
            }
        }
    }
}
