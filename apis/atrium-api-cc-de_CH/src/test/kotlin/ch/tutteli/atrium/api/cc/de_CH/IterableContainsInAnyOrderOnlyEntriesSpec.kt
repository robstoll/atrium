package ch.tutteli.atrium.api.cc.de_CH

import ch.tutteli.atrium.AssertionVerbFactory
import ch.tutteli.atrium.creating.AssertionPlant

class IterableContainsInAnyOrderOnlyEntriesSpec : ch.tutteli.atrium.spec.assertions.IterableContainsInAnyOrderOnlyEntriesSpec(
    AssertionVerbFactory,
    getContainsPair(),
    "* ", "(/) ", "(x) ", "(!) ", "- "
) {
    //TODO this test fails now due to toBeWithErrorTolerance for float/double
    companion object : IterableContainsSpecBase() {
        fun getContainsPair() =
            "$contains.$inAnyOrder.$only.$inAnyOrderOnlyEntries" to Companion::containsInAnyOrderOnly

        private fun containsInAnyOrderOnly(plant: AssertionPlant<Iterable<Double>>, a: AssertionPlant<Double>.() -> Unit, aX: Array<out AssertionPlant<Double>.() -> Unit>): AssertionPlant<Iterable<Double>> {
            return if (aX.isEmpty()) {
                plant.enthaelt.inBeliebigerReihenfolge.nur.eintrag(a)
            } else {
                plant.enthaelt.inBeliebigerReihenfolge.nur.eintraege(a, *aX)
            }
        }
    }
}
