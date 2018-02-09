package ch.tutteli.atrium.api.cc.de_CH

import ch.tutteli.atrium.AssertionVerbFactory
import ch.tutteli.atrium.creating.Assert

class IterableContainsInAnyOrderOnlyObjectsSpec : ch.tutteli.atrium.spec.integration.IterableContainsInAnyOrderOnlyObjectsSpec(
    AssertionVerbFactory,
    getContainsPair(),
    "(/) ", "(x) ", "(!) ", "- "
) {
    companion object : IterableContainsSpecBase() {
        fun getContainsPair() =
            "$contains.$inAnyOrder.$only.$inAnyOrderOnlyValues" to Companion::containsInAnyOrderOnly

        private fun containsInAnyOrderOnly(plant: Assert<Iterable<Double>>, a: Double, aX: Array<out Double>): Assert<Iterable<Double>> {
            return if (aX.isEmpty()) {
                plant.enthaelt.inBeliebigerReihenfolge.nur.wert(a)
            } else {
                plant.enthaelt.inBeliebigerReihenfolge.nur.werte(a, *aX)
            }
        }
    }
}
