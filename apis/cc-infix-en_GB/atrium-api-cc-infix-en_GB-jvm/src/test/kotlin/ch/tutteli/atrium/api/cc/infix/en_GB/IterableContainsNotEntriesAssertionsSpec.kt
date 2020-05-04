// TODO remove file with 1.0.0
@file:Suppress("DEPRECATION", "TYPEALIAS_EXPANSION_DEPRECATION")

package ch.tutteli.atrium.api.cc.infix.en_GB

import ch.tutteli.atrium.verbs.internal.AssertionVerbFactory
import ch.tutteli.atrium.api.cc.infix.en_GB.keywords.contain
import ch.tutteli.atrium.api.infix.en_GB.containsNot
import ch.tutteli.atrium.api.infix.en_GB.entry
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.api.infix.en_GB.o
import ch.tutteli.atrium.domain.builders.migration.asAssert
import ch.tutteli.atrium.domain.builders.migration.asExpect
import ch.tutteli.atrium.domain.builders.migration.asSubExpect

//TODO remove with 1.0.0, no need to migrate to Spek 2
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
                plant.asExpect().containsNot(o).entry(asSubExpect(a)).asAssert()
            } else {
                plant.asExpect().containsNot(o) the Entries(a, *aX)
            }
        }

        private fun getContainsNotNullablePair() = containsNot to Companion::containsNotNullableFun

        private fun containsNotNullableFun(plant: Assert<Iterable<Double?>>, a: (Assert<Double>.() -> Unit)?, aX: Array<out (Assert<Double>.() -> Unit)?>): Assert<Iterable<Double?>> {
            return if (aX.isEmpty()) {
                plant.asExpect().containsNot(o).entry(asSubExpect(a)).asAssert()
            } else {
                plant.asExpect().containsNot(o) the Entries(a, *aX)
            }
        }
    }
}
