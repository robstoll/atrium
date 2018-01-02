package ch.tutteli.atrium.api.cc.infix.en_UK

import ch.tutteli.atrium.AssertionVerbFactory
import ch.tutteli.atrium.creating.AssertionPlant
import kotlin.reflect.KFunction2

class IterableContainsNullSpec : ch.tutteli.atrium.spec.assertions.IterableContainsNullSpec(
    AssertionVerbFactory,
    getContainsPair(),
    getContainsNotPair(),
    getContainsInAnyOrderNullableEntriesPair()
) {
    companion object : IterableContainsSpecBase() {
        private val containsFun: KFunction2<AssertionPlant<Iterable<Double?>>, Values<Double?>, AssertionPlant<Iterable<Double?>>> = AssertionPlant<Iterable<Double?>>::contains
        fun getContainsPair() = containsFun.name to Companion::containsShortcut

        private fun containsShortcut(plant: AssertionPlant<Iterable<Double?>>, a: Double?, aX: Array<out Double?>): AssertionPlant<Iterable<Double?>> {
            return if (aX.isEmpty()) {
                plant contains a
            } else {
                plant contains Values(a, *aX)
            }
        }

        private val containsNotFun: KFunction2<AssertionPlant<Iterable<Double?>>, Values<Double?>, AssertionPlant<Iterable<Double?>>> = AssertionPlant<Iterable<Double?>>::containsNot
        fun getContainsNotPair() = containsNotFun.name to Companion::containsNotShortcut

        private fun containsNotShortcut(plant: AssertionPlant<Iterable<Double?>>, a: Double?, aX: Array<out Double?>): AssertionPlant<Iterable<Double?>> {
            return if (aX.isEmpty()) {
                plant containsNot a
            } else {
                plant containsNot Values(a, *aX)
            }
        }

        fun getContainsInAnyOrderNullableEntriesPair()
            = "$toContain $inAnyOrder $inAnyOrderEntries" to Companion::containsNullableEntries

        private fun containsNullableEntries(plant: AssertionPlant<Iterable<Double?>>, a: (AssertionPlant<Double>.() -> Unit)?, aX: Array<out (AssertionPlant<Double>.() -> Unit)?>): AssertionPlant<Iterable<Double?>> {
            return if (aX.isEmpty()) {
                plant to contain inAny order atLeast 1 entry a
            } else {
                plant to contain inAny order atLeast 1 the Entries(a, *aX)
            }
        }
    }
}
