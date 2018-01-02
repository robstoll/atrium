package ch.tutteli.atrium.api.cc.en_UK

import ch.tutteli.atrium.AssertionVerbFactory
import ch.tutteli.atrium.api.cc.en_UK.assertions.iterable.contains.builders.IterableContainsAtLeastCheckerBuilder
import ch.tutteli.atrium.assertions.iterable.contains.builders.IterableContainsBuilder
import ch.tutteli.atrium.assertions.iterable.contains.searchbehaviours.IterableContainsInAnyOrderSearchBehaviour
import ch.tutteli.atrium.creating.AssertionPlant
import kotlin.reflect.KFunction3

class IterableContainsNullSpec : ch.tutteli.atrium.spec.assertions.IterableContainsNullSpec(
    AssertionVerbFactory,
    getContainsPair(),
    getContainsNotPair(),
    getContainsInAnyOrderNullableEntriesPair()
) {
    companion object : IterableContainsSpecBase() {
        private val containsFun: KFunction3<AssertionPlant<Iterable<Double?>>, Double?, Array<out Double?>, AssertionPlant<Iterable<Double?>>> = AssertionPlant<Iterable<Double?>>::contains
        fun getContainsPair() = containsFun.name to Companion::containsShortcut

        private fun containsShortcut(plant: AssertionPlant<Iterable<Double?>>, a: Double?, aX: Array<out Double?>)
            = plant.contains(a, *aX)

        private val containsNotFun: KFunction3<AssertionPlant<Iterable<Double?>>, Double?, Array<out Double?>, AssertionPlant<Iterable<Double?>>> = AssertionPlant<Iterable<Double?>>::containsNot
        private fun getContainsNotPair() = containsNotFun.name to Companion::containsNotShortcut

        private fun containsNotShortcut(plant: AssertionPlant<Iterable<Double?>>, a: Double?, aX: Array<out Double?>)
            = plant.containsNot(a, *aX)

        fun getContainsInAnyOrderNullableEntriesPair()
            = "$contains.$inAnyOrder.$inAnyOrderEntries" to Companion::containsNullableEntries

        private fun containsNullableEntries(plant: AssertionPlant<Iterable<Double?>>, a: (AssertionPlant<Double>.() -> Unit)?, aX: Array<out (AssertionPlant<Double>.() -> Unit)?>): AssertionPlant<Iterable<Double?>> {
            return if (aX.isEmpty()) {
                plant.contains.inAnyOrder.atLeast(1).entry(a)
            } else {
                plant.contains.inAnyOrder.atLeast(1).entries(a, *aX)
            }
        }
    }
}
